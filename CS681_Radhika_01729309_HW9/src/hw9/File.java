package hw9;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.locks.ReentrantLock;

public class File {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	ReentrantLock lock = new ReentrantLock();

	private boolean changed = false;

	

	public void change() {
		lock.lock();
		System.out.println("change locked");
		try {
			changed = true;
		}finally {
			System.out.println("change Going to unlock");
			lock.unlock();
		}
		
	}

	public void save() {
		lock.lock();
		System.out.println("save locked");
		try {
			if (!changed)
				return;
			if (changed) {
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				System.out.println("Context saved at: " + timestamp);
			}
		} finally {
			System.out.println("save Going to unlock");
			lock.unlock();
		}

	}
}
