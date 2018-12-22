package hw8;

import java.util.concurrent.locks.ReentrantLock;

public class CancellablePrimeGenerator extends PrimeGenerator{
	private boolean done = false;
	ReentrantLock lock = new ReentrantLock();

	public CancellablePrimeGenerator(long from, long to) {
		super(from, to);
	}
		
	public void setDone(){
		lock.lock();
		System.out.println("------------");
		System.out.println("setDone lock");
		try {
			done = true;
			
		}finally
		{
			System.out.println("setdone going to unlock");
			System.out.println("------------");
			lock.unlock();
			
		}
		
	}

	public void generatePrimes(){
		for (long n = from; n <= to; n++) {
			// Stop generating prime numbers if done==true
			lock.lock();
			System.out.println("generatePrime lock");
			try {				
			if(done){
				System.out.println("Stopped generating prime numbers.");
				this.primes.clear();
				break;
			}
			if( isPrime(n) ){ this.primes.add(n); }
		}finally {
			lock.unlock();
			System.out.println("generatePrime unlock");
		}
		}
	}
}
