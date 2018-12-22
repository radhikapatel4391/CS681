package hw12;

public class RunnableCancellablePrimeGenerator	extends CancellablePrimeGenerator
												implements Runnable {
	public RunnableCancellablePrimeGenerator(long from, long to) {
		super(from, to);
	}
	
	@Override
	public void run() {
		generatePrimes();
	}

	/*public static void main(String[] args) {
		RunnableCancellablePrimeGenerator gen = new RunnableCancellablePrimeGenerator(1,100);
		Thread thread = new Thread(gen);
		thread.start();
		gen.setDone();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gen.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
		System.out.println("\n" + gen.getPrimes().size() + " prime numbers are found.");
	}*/
}
