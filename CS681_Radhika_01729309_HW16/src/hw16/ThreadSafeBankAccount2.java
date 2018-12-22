package hw16;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount2{
	private double balance = 0;
	private ReentrantLock lock;
	private Condition sufficientFundsCondition, belowUpperLimitFundsCondition;
	private ThreadSafeBankAccount2 account;
	private volatile boolean done = false;
	public void setDone() {
		done = true; //thread safe because done is volatile 
	}
	
	public ThreadSafeBankAccount2(){
		lock = new ReentrantLock();
		sufficientFundsCondition = lock.newCondition();
		belowUpperLimitFundsCondition = lock.newCondition();
		account =  this;
	}
	
	public void deposit(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getId() + 
					" (d): current balance: " + balance);
			while(balance >= 300){
				System.out.println(Thread.currentThread().getId() + 
						" (d): await(): Balance exceeds the upper limit.");
				belowUpperLimitFundsCondition.await();
			}
			balance += amount;
			System.out.println(Thread.currentThread().getId() + 
					" (d): new balance: " + balance);
			sufficientFundsCondition.signalAll();
		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	public void withdraw(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getId() + 
					" (w): current balance: " + balance);
			while(balance <= 0){
				System.out.println(Thread.currentThread().getId() + 
						" (w): await(): Insufficient funds");
				sufficientFundsCondition.await();
			}
			balance -= amount;
			System.out.println(Thread.currentThread().getId() + 
					" (w): new balance: " + balance);
			belowUpperLimitFundsCondition.signalAll();
		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	public static void main(String[] args){
		ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
		for(int i = 0; i < 5; i++){
			new Thread( bankAccount.new DepositRunnable() ).start();
		}
		new Thread( bankAccount.new WithdrawRunnable() ).start();
	}

	public class DepositRunnable implements Runnable{
		public void run(){
			while(!done) { //thread safe because done is volatile 
				account.deposit(100);// two thread might concurently execute this statement but it as expected..
					/*try{						
							Thread.sleep(2);
						}
					catch(InterruptedException exception){
						continue;
					}*/
			}
			System.out.println(Thread.currentThread().getId() + " done = true");
			
		}
	}
	
	public class WithdrawRunnable implements Runnable{
		public void run(){
			while(!done) { //thread safe because done is volatile 
				account.withdraw(100); // two thread might concurently execute this statement but it as expected..
				/*try{						
						Thread.sleep(2);
					}
				catch(InterruptedException exception){
					continue; 
				}*/
			}
			System.out.println(Thread.currentThread().getId() + " done = true");
			
		}
	}
}
