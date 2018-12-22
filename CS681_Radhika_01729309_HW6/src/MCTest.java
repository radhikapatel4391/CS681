import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MCTest{
  public static void main(String[] args) throws Exception {
	System.out.println("Replaced the anonymous class with a lambda expression");
    ArrayList<Thread> threads = new ArrayList<Thread>();

    final long nTimes  = Long.parseLong(args[0]);
    final int nThread = Integer.parseInt(args[1]);
    //added by radhika................
    System.out.println("No of Times: "+nTimes);
    System.out.println("No of Threds: "+nThread);
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    System.out.println( "Start Time : "+ sdf.format(cal.getTime()) );
  
  
    for (int i = 0; i < nThread; i++) {
    
	  Thread t = new Thread(() -> {	int n = 25;
									for (long j = 0; j < nTimes; j++)
									{
								  		n *= 25;
								  	}
										//System.out.println("n is : "+n);
								  }
						   );
      threads.add(t);
      t.start();
    }
   
    for (Thread t: threads) {
      t.join();
    }
    //added by radhika....................
    Calendar calout = Calendar.getInstance();    
    SimpleDateFormat sdfout = new SimpleDateFormat("HH:mm:ss");
    System.out.println( "End Tim  : "+ sdfout.format(calout.getTime()) );    
    System.out.println("Total Time : " + (calout.getTimeInMillis() - cal.getTimeInMillis()));
    //---------------------------------------------
  }
}
