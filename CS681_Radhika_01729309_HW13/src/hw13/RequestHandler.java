package hw13;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
public class RequestHandler implements Runnable{
	AccessCounter ac = AccessCounter.getInstance();;
	Path[] arr = {Paths.get("..\\..\\..\\a.java"),Paths.get("..\\..\\..\\b.java"),Paths.get("..\\..\\..\\c.java"),Paths.get("..\\..\\..\\d.java")};
	boolean done = false;
	ReentrantLock rlock = new ReentrantLock();
	

	public void setDone(){
			System.out.println("set callaed..");
		rlock.lock();
			System.out.println("set locked..");
		try {
		done = true;
		}finally {
				System.out.println("set going to unlock..");
			rlock.unlock();
		}
	}
		
	public void run() {		
		while(true)
		{
			Random ran = new Random();
			Path p = arr[ran.nextInt(arr.length)];
			rlock.lock();
			try {				
				if(done) {
					System.out.println("done = true exit.."+Thread.currentThread().getName());
					break;
				}				
				ac.increment(p);
				System.out.println(p.getFileName() + ":   " +ac.getCount(p) + " ---"+Thread.currentThread().getName());
				
			}finally {
				rlock.unlock();
			}
			try {
				System.out.println("going to sleep for 3 minute..."+Thread.currentThread().getName());
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				System.out.println("wake up.."+Thread.currentThread().getName());
				//e.printStackTrace();
				continue;
			}
			
		}
	}

}
