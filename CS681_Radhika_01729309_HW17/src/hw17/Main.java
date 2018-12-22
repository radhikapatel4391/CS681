package hw17;

import java.util.ArrayList;



public class Main {

	public static void main(String[] args) {
		AdmissionControl admissionC = new AdmissionControl();		
		int tcount = 6;
		
		ArrayList<Thread> threads = new ArrayList<Thread>(tcount);
//.................Creation.................................................
		 for (int i = 0; i <3 ; i++) 
		 {
			 Thread t1 = new Thread(admissionC.new EntranceHandler());
			 threads.add(t1);
			 t1.start();
			 
		 }
		 
			 Thread tm = new Thread(admissionC.new MonitorHandler());
			 threads.add(tm);
			 tm.start();
			 try {
						Thread.sleep(10);// we want more increment before we start decrement so we can reached the limit..
					} catch (InterruptedException e) {			
						e.printStackTrace();
					}
			 
		
		 for (int i = 0; i <2 ; i++) 
		 {
			 Thread te = new Thread(admissionC.new ExitHandler());
			 threads.add(te);
			 te.start();
			 
		 }
				 try {
						Thread.sleep(10);
					} catch (InterruptedException e) {			
						e.printStackTrace();
					}	
		 			System.out.println("Main: going to make it intrupt..threads.get(i).interrupt()");
//...............Intrupt..............................................................
		 for (int i = 0; i < tcount; i++) 
		 {
			
			 threads.get(i).interrupt();
		 }
//...............2ns step done........................................................
		 
		 				System.out.println("Main: going to set done..admissionC.setDone()");
		 				admissionC.setDone();
		 				System.out.println("Main: set done finished..");
		 				
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
		
//Testing............................		
		System.out.println("Main: Increment Decremnt Threads are done....Both Gates are Closed.........");
		admissionC.resetDone();
		Thread tt = new Thread(admissionC.new MonitorHandler());		
		 tt.start();
		 try {
			Thread.sleep(1);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}		 
		admissionC.setDone();
		System.out.println("Main: here |#incremented statements- #decremented statements| = |Last visitor Reading| ");
		System.out.println("Main: Means Thread safe code..");
		
			
	}

}
