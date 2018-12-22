package hw10;

//import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentSingleton implements Runnable {
	
	private Singleton singletonInstance;
	
	public void run() {
		singletonInstance = Singleton.getInstance();
		System.out.println("I am instance at run: "+singletonInstance);
		
	}
	/*private ConcurrentSingleton(){};
	
	private static ConcurrentSingleton instance = null;
	//private static ReentrantLock lock = new ReentrantLock();

	// Factory method to create or return the singleton instance
	public static ConcurrentSingleton getInstance() {
		//lock.lock();
		System.out.println("locked ");
		//try {
			
			if (instance == null) {
				
				instance = new ConcurrentSingleton();
				System.out.println("I am instance just assgned: "+instance);
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			System.out.println("unlocked..");
			return instance;
		//} finally {
			//System.out.println("unlocked..");
			//lock.unlock();
		//}
	}
	public void run() {
		System.out.println("I am singlton instance: "+ConcurrentSingleton.getInstance());
	}*/

}
