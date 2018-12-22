package hw13;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
	static AccessCounter instance = null;
	static ReentrantLock lock = new ReentrantLock();
	ReentrantLock l = new ReentrantLock();

	HashMap<Path, Integer> ac = new HashMap<Path, Integer>();

	private AccessCounter() {

	}

	public static AccessCounter getInstance() {
		lock.lock();
		try {
			if (instance == null) {
				System.out.println("I read null............");
				instance = new AccessCounter();
			}
		} finally {
			lock.unlock();
		}
		return instance;
	}

	public void increment(Path path) {
		l.lock();
		try {
			Integer count = ac.containsKey(path) ? ac.get(path) : 0;
			ac.put(path, count + 1);
		} finally {
			l.unlock();
		}
	}

	int getCount(Path path) {
		l.lock();
		try {
			return ac.get(path);
		} finally {
			l.unlock();
		}

	}

}
