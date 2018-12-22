package hw18;

import java.util.concurrent.locks.ReentrantLock;

import hw18.ThreadSafeBankAccount2_3.DepositRunnable;
import hw18.ThreadSafeBankAccount2_3.TransferRunnable;
import hw18.ThreadSafeBankAccount2_3.WithdrawRunnable;

import java.util.Random;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount2_4 {
	private double balance = 0;
	public double getBalance() {
		return balance; // no need to make thread safe because we are calling this method in lock area...
		                //when this method call no other method might change value because of lock
	}

	private ReentrantLock lock;
	public ReentrantLock getLock() {
		return lock; //no need to protect because only reading... no writing
	}

	private Condition sufficientFundsCondition, belowUpperLimitFundsCondition;
	private ThreadSafeBankAccount2_4 account;
	
	private volatile boolean done = false;

	public void setDone() {
		done = true; // thread safe because done is volatile
	}

	public ThreadSafeBankAccount2_4() {
		lock = new ReentrantLock();
		sufficientFundsCondition = lock.newCondition();
		belowUpperLimitFundsCondition = lock.newCondition();
		account = this;
	}

	public void deposit(double amount) {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getId()+"Lock obtained");
			System.out.println(Thread.currentThread().getId()+ "   ,   "+System.identityHashCode(account)+ " (d): current balance: " + balance);
			while (balance >= 300) {
				System.out.println(Thread.currentThread().getId()+ "   ,   "+System.identityHashCode(account)+ " (d): await(): Balance exceeds the upper limit.");
				belowUpperLimitFundsCondition.await();
			}
			balance += amount;
			System.out.println(Thread.currentThread().getId()+ "   ,   "+System.identityHashCode(account)+ " (d): new balance: " + balance);
			sufficientFundsCondition.signalAll();
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		} finally {
			lock.unlock();
			System.out.println(Thread.currentThread().getId()+"Lock released");
		}
	}

	public void withdraw(double amount) {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getId()+"   Lock obtained");
			System.out.println(Thread.currentThread().getId()+ "   ,   "+System.identityHashCode(account)+ " (w): current balance: " + balance);
			while (balance <= 0) {
				System.out.println(Thread.currentThread().getId()+ "   ,   "+System.identityHashCode(account)+ " (w): await(): Insufficient funds");
				sufficientFundsCondition.await();
			}
			balance -= amount;
			System.out.println(Thread.currentThread().getId()+ "   ,   "+System.identityHashCode(account)+ " (w): new balance: " + balance);
			belowUpperLimitFundsCondition.signalAll();
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		} finally {
			lock.unlock();
			System.out.println(Thread.currentThread().getId()+"Lock released");
		}
	}

	public void transfer(ThreadSafeBankAccount2_4 source, ThreadSafeBankAccount2_4 destination, double amount) {
		Random random = new Random();
		
		while (true) {
			ReentrantLock slock = source.getLock();
			ReentrantLock dlock = destination.getLock();
			if (slock.tryLock()) {
				System.out.println(Thread.currentThread().getId()+"   SLock obtained");
				try {
					if (dlock.tryLock()) {
						System.out.println(Thread.currentThread().getId()+"   DLock obtained");
						try {
							System.out.println(Thread.currentThread().getId()+" (t): at transfer");
							if (source.getBalance() < amount || destination.getBalance()>=300) {
								System.out.println("ensufficient balance or destination already at higher balance..");
								throw new Exception();

							} else {
								
								source.withdraw(amount);
								destination.deposit(amount);
							}
							return;
							
						} catch (Exception e) {
							System.out.println(e);
							return;
						} finally {
							dlock.unlock();
							System.out.println(Thread.currentThread().getId()+"DLock released");
						}
					}
				} finally {
					slock.unlock();
					System.out.println(Thread.currentThread().getId()+"SLock released");
				}
			}
			try {
				Thread.sleep(random.nextInt(1000));
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		ThreadSafeBankAccount2_4 bankAccount = new ThreadSafeBankAccount2_4();
		ThreadSafeBankAccount2_4 bankAccount2 = new ThreadSafeBankAccount2_4();
		for (int i = 0; i <2; i++) {
			new Thread(bankAccount.new DepositRunnable()).start();
			new Thread(bankAccount2.new DepositRunnable()).start();
		}
		for (int i = 0; i <2; i++) {
			new Thread(bankAccount.new TransferRunnable(bankAccount2)).start();
			new Thread(bankAccount2.new TransferRunnable(bankAccount)).start();
		}
		new Thread(bankAccount.new WithdrawRunnable()).start();
		new Thread(bankAccount2.new WithdrawRunnable()).start();
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		bankAccount.setDone();
		bankAccount2.setDone();
	}

	public class DepositRunnable implements Runnable {
		public void run() {
			while (!done) { // thread safe because done is volatile
				account.deposit(100);// two thread might concurently execute this statement but it as expected..
				
			}
			System.out.println(Thread.currentThread().getId() + " done = true");

		}
	}

	public class WithdrawRunnable implements Runnable {
		public void run() {
			while (!done) { // thread safe because done is volatile				
				account.withdraw(100); // two thread might concurently execute this statement but it as expected..
				
			}
			System.out.println(Thread.currentThread().getId() + " done = true");

		}
	}
	public class TransferRunnable implements Runnable {
		private ThreadSafeBankAccount2_4 destination;
		TransferRunnable(ThreadSafeBankAccount2_4 destination){
			this.destination = destination;
		}
		
		public void run() {
			while (!done) { // thread safe because done is volatile
				System.out.println(Thread.currentThread().getId()+" (t):......................................................");
				account.transfer(account,destination,100.00);// two thread might concurently execute this statement but it as expected..
				
			}
			System.out.println(Thread.currentThread().getId() + " done = true");

		}
	}

}
