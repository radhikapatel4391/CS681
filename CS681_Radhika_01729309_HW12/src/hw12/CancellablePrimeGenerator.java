package hw12;

public class CancellablePrimeGenerator extends PrimeGenerator{
	private boolean done = false;
	

	public CancellablePrimeGenerator(long from, long to) {
		super(from, to);
	}
		
	public void setDone(){
		done = true;
	}


	public void generatePrimes(){
		for (long n = from; n <= to; n++) {
			// Stop generating prime numbers if done==true
			
			if(done){
				System.out.println("Stopped generating prime numbers.");
				this.primes.clear();
				break;
			}
			
			if( isPrime(n) ){ this.primes.add(n); }
		}
	}
}
