package hw5;
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
	
	public List<Long> getPrimes()
	{ return this.primes; };
	
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
	
	public static void main(String[] args) throws InterruptedException {		
		PrimeGenerator gen = new PrimeGenerator(1, 100);
		Thread thread = new Thread( ()->{gen.primes = LongStream.rangeClosed(gen.from,gen.to)
																	.filter(n -> gen.isPrime(n))
																		.boxed()
																			.collect(Collectors.toList());
		System.out.println("I am the length of primes:  "+ gen.primes.size());});
		thread.start();
		thread.join();
		List<Long> temp = gen.getPrimes();
		System.out.println("Out side size: "+ temp.size());
		gen.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
		System.out.println("\n" + gen.getPrimes().size() + " prime numbers are found.");
	}
}
