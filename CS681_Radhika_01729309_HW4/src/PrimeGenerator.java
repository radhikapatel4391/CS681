import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.ArrayList;

public class PrimeGenerator {
	protected long from, to;
	protected List<Long> primes;

	public PrimeGenerator(long from, long to){
		if(from >= 1 && to >= from){
			this.primes = new ArrayList<Long>();
			this.from = from;
			this.to = to;
		}else{
			throw new RuntimeException("Wrong input values: from=" + from + " to=" + to);
		}
	}
	
	public List<Long> getPrimes(){ return primes; };
	
	protected boolean isEven(long n){
		if(n%2 == 0){ return true; }
		else{ return false; }
	}
	
	protected boolean isPrime(long n){
		// 1 is not prime. 
		if(n == 1){ return false; }
		// Even numbers that are greater than 2 are not prime. 
		if( n > 2 && isEven(n) ){ return false; }
		long i;
		// Find a number "i" that can divide "n" 
        for (i = (long) Math.sqrt(n); n%i != 0 && i >= 1; i--){}
        // If such a number "i" is found, n is not prime. Otherwise, n is prime. 
        if (i == 1){ return true; }
        else{ return false; }
	}

	/*public void generatePrimes(){
		this.primes = LongStream.rangeClosed(this.from,this.to)
								.filter(n -> isPrime(n))
								.boxed()
								.collect(Collectors.toList());
								
	}*/
	public void generatePrimes(){
		for (long n = from; n <= to; n++) {
			if( isPrime(n) ){ primes.add(n); }
        }
	}
	
	public static void main(String[] args) {
		PrimeGenerator gen = new PrimeGenerator(1, 100);
		gen.generatePrimes();
		gen.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
		System.out.println("\n" + gen.getPrimes().size() + " prime numbers are found.");

	}
}
