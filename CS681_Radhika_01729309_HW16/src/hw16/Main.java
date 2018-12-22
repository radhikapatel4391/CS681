package hw16;

import java.util.ArrayList;

import hw16.ThreadSafeBankAccount2.DepositRunnable;
import hw16.ThreadSafeBankAccount2.WithdrawRunnable;

public class Main {

	public static void main(String[] args) {
		ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();		
		int tcount = 5;
		
		ArrayList<Thread> threads = new ArrayList<Thread>(tcount);
		//.................Creation
		 for (int i = 0; i <tcount-1 ; i++) 
		 {
			 Thread t1 = new Thread(bankAccount.new DepositRunnable());
			 threads.add(t1);
			 t1.start();
			 
		 }
		
			 Thread t1 = new Thread(bankAccount.new WithdrawRunnable());
			 threads.add(t1);
			 t1.start();
			 
		
		 //...............
					 try {
						Thread.sleep(10);
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
