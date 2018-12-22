import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class StreamBasedRunnablePrimeGenerator extends PrimeGenerator{
	public StreamBasedRunnablePrimeGenerator(long from, long to) {
		super(from, to);
		// TODO Auto-generated constructor stub
	}

	public void generatePrimes(){
		System.out.println("StreamBasedRunnablePrimeGenerator generatePrimes called....");
		this.primes = LongStream.rangeClosed(this.from,this.to)
								.filter(n -> isPrime(n))
								.boxed()
								.collect(Collectors.toList());
								
	}

	public static void main(String[] args) {
		
		StreamBasedRunnablePrimeGenerator gen = new StreamBasedRunnablePrimeGenerator(1, 100);
		gen.generatePrimes();
		gen.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
		System.out.println("\n" + gen.getPrimes().size() + " prime numbers are found.");

	}

}
