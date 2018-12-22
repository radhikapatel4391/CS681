package hw9;

import java.util.concurrent.locks.ReentrantLock;

public class Editor implements Runnable {
	private File aFile;
	private Boolean done = false;
	ReentrantLock lockDone = new ReentrantLock();

	public Editor(File file) {
		this.aFile = file;
	}

	@Override
	public void run() {
		while (true) {
			lockDone.lock();
			System.out.println("Editor done locked..");
			try {
				if (done) 
				{
					System.out.println("Editor Run done..............");
					break;
				}
				aFile.change();
				aFile.save();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} finally {
				System.out.println("Editor done going to unlocked..");
				lockDone.unlock();
			}

			
		}

	}
	public void setDone() {
		done = true;
	}

}
