package hw15;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AccessCounter {
	static AccessCounter instance = null;
	static ReentrantLock lock = new ReentrantLock();
	ReentrantReadWriteLock l = new ReentrantReadWriteLock();

	HashMap<Path, Integer> acMap = new HashMap<Path, Integer>();

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
		l.writeLock().lock();
		try {
				System.out.println("before inc: "+path.getFileName() + ":   " +acMap.get(path) + " ---"+Thread.currentThread().getName());
			Integer count = acMap.containsKey(path) ? acMap.get(path) : 0;
			acMap.put(path, count + 1);
		} finally {
				System.out.println("after inc: "+path.getFileName() + ":   " +acMap.get(path) + " ---"+Thread.currentThread().getName());
			l.writeLock().unlock();
		}
	}

	int getCount(Path path) {
		l.readLock().lock();
		try {
			Integer i = acMap.get(path);
				System.out.println("get: "+path.getFileName() + ":   " +i + " ---"+Thread.currentThread().getName());
			return i ;
		} finally {
			l.readLock().unlock();
		}

	}

}
