package hw10;

import java.util.concurrent.locks.ReentrantLock;

public class Singleton {
	static ReentrantLock lock = new ReentrantLock();
	private Singleton() {
	};

	private static Singleton instance = null;

// Factory method to return the singleton instance
	public static Singleton getInstance() {
		lock.lock();
		try {
		System.out.println("locked");
		if (instance == null) {	
			System.out.println("I read null ");
			new Singleton();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			instance = new Singleton();
			System.out.println("instance: "+instance);
		}		
			return instance;
		}finally {
			System.out.println("going to unlocked");
			lock.unlock();
		}
	}
}