package hw9;

import java.util.concurrent.locks.ReentrantLock;

public class AutoSaver implements Runnable {
	private File aFile;
	private Boolean done = false;
	ReentrantLock lockDone = new ReentrantLock();

	public AutoSaver(File file) {
		this.aFile = file;
	}

	@Override
	public void run() {
		while (true) {
			lockDone.lock();
			System.out.println("AutoSaver done locked..");
			try {
				if (done) {
					System.out.println("AutoSaver Run done..............");
					break;
				}
				aFile.save();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} finally {
				System.out.println("Autosaver done going to unlocked..");
				lockDone.unlock();
			}			
			
		}
	}

	public void setDone() {
		done = true;
	}

}
