package hw11;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableInterruptiblePrimeGenerator extends InterruptiblePrimeGenerator implements Runnable {
	public RunnableInterruptiblePrimeGenerator(long from, long to) {
		super(from, to);
		// TODO Auto-generated constructor stub
	}

	private final ReentrantLock lock = new ReentrantLock();

	public ReentrantLock getLock() {
		System.out.println("----------------------return lock---------------------");
		return lock;
	}

	public void generatePrimes() {
		System.out.println("RunnableInterruptiblePrimeGenerator . generatePrimes");
		for (long n = from; n <= to; n++) {
			
			lock.lock();
			try {				
				System.out.println("locked block..");
				if (Thread.interrupted()) {
					System.out.println("Stopped becuase intruppted...");
					this.primes.clear();
					break;
				}
				if (isPrime(n)) {
					this.primes.add(n);
				}
				
				
			} finally
			{
				System.out.println("going to unlock block..");
				lock.unlock();
			}			
		}
	}

	public void run() {
		generatePrimes();
	}
}
