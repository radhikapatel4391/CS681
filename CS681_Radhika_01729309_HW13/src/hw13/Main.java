package hw13;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int tcount = 15;
		RequestHandler r1 = new RequestHandler();
		ArrayList<Thread> threads = new ArrayList<Thread>();
		//.................Creation
		 for (int i = 0; i < tcount; i++) 
		 {
			 Thread t1 = new Thread(r1);
			 threads.add(t1);
			 t1.start();
			 
		 }
		 //...............
					 try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		
		 			System.out.println("Main going to make it intrupt..");
		 //...............Intrupt
		 for (int i = 0; i < tcount; i++) 
		 {
			
			 threads.get(i).interrupt();
		 }
		 //...............2ns step done..........
		 
		 				System.out.println("going to set done..");
		 r1.setDone();
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
		System.out.println("Main end stamement....");
		
	}

}
