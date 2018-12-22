package hw14;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		int tcount = 3;		
		Address add1 = new Address("arizona","arlington","MA",02474);
		Customer r1 = new Customer(add1);
		ArrayList<Thread> threads = new ArrayList<Thread>();
		//.................Creation
		 for (int i = 0; i < tcount; i++) 
		 {
			 Thread t1 = new Thread(()->{
				 Address newAddress = new Address(Thread.currentThread().getName(),"NewCity","NS",12474);				
				 r1.getAddress().toString();
				 r1.setAddress(newAddress);
				 //r1.getAddress().toString();				
				 r1.setAddress(r1.getAddress().change("ChangeSt", "ChaCity",Thread.currentThread().getName(),22474));							 
			 });
			 threads.add(t1);
			 t1.start();		 
		 }		
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
