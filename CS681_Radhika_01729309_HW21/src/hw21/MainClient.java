package hw21;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class MainClient {

	public static void main(String[] args) {
		// lambda function implementation update....
		Observer PieChartObserver = (Observable obs, Object arg) -> {
			if (arg instanceof DJIAEvent) {
				DJIAEvent dJIAEvent = (DJIAEvent) arg;
				System.out.println(Thread.currentThread().getId()+ "   PieChartObserver_update  DJIAEvent.\n");
				System.out.println(Thread.currentThread().getId()+ "   DJIA :" + dJIAEvent.getDjia() + " \n");
			} else if (arg instanceof StockEvent) {
				StockEvent stockevent = (StockEvent) arg;
				System.out.println(Thread.currentThread().getId()+ "  PieChartObserver_update  StockEvent "+stockevent.getTicker() + "  " + stockevent.getQuote());
			}
		};
		Observer TableObserver = (Observable obs, Object arg) -> {
			if (arg instanceof DJIAEvent) {
				DJIAEvent dJIAEvent = (DJIAEvent) arg;
				System.out.println(Thread.currentThread().getId()+ "   TableObserver_update  DJIAEvent.\n");
				System.out.println(Thread.currentThread().getId()+ "   DJIA :" + dJIAEvent.getDjia() + " \n");
			} else if (arg instanceof StockEvent) {
				StockEvent stockevent = (StockEvent) arg;
				
				System.out.println(Thread.currentThread().getId()+ "  TableObserver_update  StockEvent "+stockevent.getTicker() + "  " + stockevent.getQuote());
			}
		};
		Observer ThreeDObserver = (Observable obs, Object arg) -> {
			if (arg instanceof DJIAEvent) {
				DJIAEvent dJIAEvent = (DJIAEvent) arg;
				System.out.println(Thread.currentThread().getId()+ "   ThreeDObserver_update  DJIAEvent.\n");
				System.out.println(Thread.currentThread().getId()+ "   DJIA :" + dJIAEvent.getDjia() + " \n");
			} else if (arg instanceof StockEvent) {
				StockEvent stockevent = (StockEvent) arg;
				System.out.println(+Thread.currentThread().getId()+ "  ThreeDObserver  StockEvent "+stockevent.getTicker() + "  " + stockevent.getQuote());
				
			}
		};

		// -----------------------------------------------------------
		
		StockQuoteObservable stockObservable = new StockQuoteObservable();
		System.out.println(".........................Main added PieChartObserver...........................");
		stockObservable.addObservers(PieChartObserver);
		
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < 15; i++) {
			Thread t = new Thread(() -> {
				stockObservable.changeQuote("Google", 100 + Thread.currentThread().getId());
				//stockObservable.changeQuote("Apple", 200 + Thread.currentThread().getId());
				//stockObservable.changeQuote("WMT", 300 + Thread.currentThread().getId());
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			threads.add(t);
			t.start();
		}
		try {
			Thread.sleep(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(".........................Main going add TableObserver...........................");
		stockObservable.addObservers(TableObserver);
		try {
			System.out.println(Thread.currentThread().getId()+" sleeping....");
			Thread.sleep(1);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(".........................Main going to add ThreeDObserver...........................");
		stockObservable.addObservers(ThreeDObserver);
		try {
			//System.out.println(Thread.currentThread().getId()+"sleeping....");
			Thread.sleep(1);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(".........................Main going to Remove PieChartObserver...........................");
		stockObservable.deleteObserver(PieChartObserver);
		


// .........................................................
/*		DJIAQuoteObservable dJIAQuoteObservable = new DJIAQuoteObservable();
	

		dJIAQuoteObservable.addObservers(PieChartObserver);
		dJIAQuoteObservable.addObservers(TableObserver);
		dJIAQuoteObservable.addObservers(ThreeDObserver);
		
		for (int i = 0; i < 4; i++) {
			Thread t = new Thread(() -> {
				dJIAQuoteObservable.changeQuote(100 + Thread.currentThread().getId());
				//dJIAQuoteObservable.changeQuote(200 + Thread.currentThread().getId());
				//dJIAQuoteObservable.changeQuote(300 + Thread.currentThread().getId());
				try {
					Thread.sleep(1 * 1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			threads.add(t);
			t.start();
		}*/
		for (int i = 0; i < 15; i++) 
		 {
			 try {								
					 threads.get(i).join();
				 }
			catch (Exception e) {
				System.out.println(e);
			}
		}		
	}

}
