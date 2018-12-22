package hw7;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class RunnablePrimeGenerator extends PrimeGenerator {
	
	public RunnablePrimeGenerator(long from, long to) {
		super(from, to);
	}	

	public static void main(String[] args) {
		ArrayList<Thread> threads = new ArrayList<Thread>();

	   System.out.println("Calculating Prime numbers between 0 to 10000");
	    final int nThread = Integer.parseInt(args[0]);
	    
	    //added by radhika................
	   
	    System.out.println("No of Threds: "+nThread);
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	    System.out.println( "Start Time : "+ sdf.format(cal.getTime()) );
	    
	    for (int i = 0; i < nThread; i++) 
	    {
	    RunnablePrimeGenerator gen = new RunnablePrimeGenerator(1, 10000);
	      Thread t = new Thread(()->{gen.primes = LongStream.rangeClosed(gen.from,gen.to)
														.filter(n -> gen.isPrime(n))
														.boxed()
														.collect(Collectors.toList());
	      								//System.out.println("total Prime numbers are:   "+ gen.primes.size());
	      							}
	      						);
	      						
	      threads.add(t);
	      t.start();
	    }

	    for (Thread t: threads) {
	      try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	  //added by radhika....................
	    Calendar calout = Calendar.getInstance();    
	    SimpleDateFormat sdfout = new SimpleDateFormat("HH:mm:ss");
	    System.out.println( "End Tim  : "+ sdfout.format(calout.getTime()) );    
	    System.out.println("Total Mili : " + (calout.getTimeInMillis() - cal.getTimeInMillis()));
	    //---------------------------------------------

	}

}
