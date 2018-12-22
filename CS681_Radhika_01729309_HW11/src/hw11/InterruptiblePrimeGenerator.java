package hw11;

public class InterruptiblePrimeGenerator extends PrimeGenerator{
	
	public InterruptiblePrimeGenerator(long from, long to) {
		super(from, to);
	}
			
	public void generatePrimes(){
		for (long n = from; n <= to; n++) {
			// Stop generating prime numbers if Thread.interrupted()==true
			if(Thread.interrupted()){
				System.out.println("Stopped generating prime numbers due to a thread interruption.");
				this.primes.clear();
				break;
			}
			if( isPrime(n) ){ this.primes.add(n); }
		}
	}	
}
