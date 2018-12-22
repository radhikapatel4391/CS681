package hw18;

import java.util.ArrayList;

import hw18.ThreadSafeBankAccount2_4.DepositRunnable;
import hw18.ThreadSafeBankAccount2_4.WithdrawRunnable;

public class Main {

	public static void main(String[] args) {
		ThreadSafeBankAccount2_4 bankAccount = new ThreadSafeBankAccount2_4();
		ThreadSafeBankAccount2_4 bankAccount2 = new ThreadSafeBankAccount2_4();
		
		int tcount = 10;
		
		ArrayList<Thread> threads = new ArrayList<Thread>(tcount);
		//.................Creation
		 for (int i = 0; i <2 ; i++) 
		 {
			 Thread t1 = new Thread(bankAccount.new DepositRunnable());
			 threads.add(t1);
			 t1.start();
			 
			 Thread t2 = new Thread(bankAccount2.new DepositRunnable());
			 threads.add(t2);
			 t2.start();
			 
		 }
		 for (int i = 0; i <2 ; i++) 
		 {
			 Thread t1 = new Thread(bankAccount.new TransferRunnable(bankAccount2));
			 threads.add(t1);
			 t1.start();
			 
			 Thread t2 = new Thread(bankAccount2.new TransferRunnable(bankAccount));
			 threads.add(t2);
			 t2.start();
			 
		 }
		
			 Thread t1 = new Thread(bankAccount.new WithdrawRunnable());
			 threads.add(t1);
			 t1.start();
			 
			 Thread t2 = new Thread(bankAccount2.new WithdrawRunnable());
			 threads.add(t2);
			 t2.start();
			 
		
		 //...............
					 try {
						Thread.sleep(5);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		
		 			System.out.println("Main going to make it intrupt..threads.get(i).interrupt()");
		 //...............Intrupt
		 for (int i = 0; i < tcount; i++) 
		 {
			
			 threads.get(i).interrupt();
		 }
		 //...............2ns step done..........
		 
		 				System.out.println("going to set done..bankAccount.setDone()");
		 				bankAccount.setDone();
		 				bankAccount2.setDone();
		 				System.out.println("set done finished..");
		 				
		 //...............join.............				
		 for (int i = 0; i < tcount; i++) 
		 {
			 try {								
					 threads.get(i).join();
				 }
			catch (Exception e) {
				System.out.println(e);
			}
		}
		//....................
		System.out.println("Main end");
		
	}

}
