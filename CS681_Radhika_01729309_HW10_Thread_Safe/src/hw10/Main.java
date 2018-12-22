package hw10;

public class Main {

	public static void main(String[] args) {
		System.out.println("Running Main..........");
		Thread t1 = new Thread(new ConcurrentSingleton());
		Thread t2 = new Thread(new ConcurrentSingleton());
		Thread t3 = new Thread(new ConcurrentSingleton());
		Thread t4 = new Thread(new ConcurrentSingleton());
		Thread t5 = new Thread(new ConcurrentSingleton());
		Thread t6 = new Thread(new ConcurrentSingleton());
		Thread t7 = new Thread(new ConcurrentSingleton());
		Thread t8 = new Thread(new ConcurrentSingleton());
		t1.start();		
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
			t6.join();
			t7.join();
			t8.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
