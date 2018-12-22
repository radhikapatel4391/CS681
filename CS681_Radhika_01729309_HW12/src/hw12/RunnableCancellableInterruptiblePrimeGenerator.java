package hw12;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeGenerator extends RunnableCancellablePrimeGenerator {
	private boolean done = false;
	ReentrantLock lock = new ReentrantLock();

	public RunnableCancellableInterruptiblePrimeGenerator(long from, long to) {
		super(from, to);
		// TODO Auto-generated constructor stub
	}
	
	public void setDone(){
			System.out.println("set callaed..");
		lock.lock();
			System.out.println("set locked..");
		try {
		done = true;
		}finally {
				System.out.println("set going to unlock..");
			lock.unlock();
		}
	}
	
	public void generatePrimes() {
		System.out.println("RunnableCancellableInterruptiblePrimeGenerator.generatePrimes exec....");
		for (long n = from; n <= to; n++) {
			// Stop generating prime numbers if done==true
			lock.lock();
				System.out.println("genprime locked..");
			try {
				if (done) {
						System.out.println("Stopped generating prime numbers.Done = true..");
					this.primes.clear();
					break;
				}
				if (isPrime(n)) {
					this.primes.add(n);
				}
				
				
			} finally {
					System.out.println("gePrime going to unlock");
				lock.unlock();
			}
			try {
					System.out.println("RunnableCancellableInterruptiblePrimeGenerator.generatePrimes exec....going to sleep....10000");
				Thread.sleep(10000);
			} catch (Exception InterruptedException) {
				System.out.println("----------------------genPrime sleep catch...Note did not sleep 1 min just wake up because intrupt called... ");
				continue;
			}
			
		}
	}
	
}
