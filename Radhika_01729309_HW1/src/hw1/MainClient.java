package hw1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class MainClient {

	public static void main(String[] args) {

		Observer PieChartObserver = (Observable obs, Object arg) -> {
			if (arg instanceof DJIAEvent) {
				DJIAEvent dJIAEvent = (DJIAEvent) arg;
				System.out.println("PieChartObserver_update: I am instance of DJIAEvent class.\n");
				System.out.println("DJIA :" + dJIAEvent.getDjia() + " \n");
			} else if (arg instanceof StockEvent) {
				StockEvent stockevent = (StockEvent) arg;
				System.out.println("PieChartObserver_update: I am instance of StockEvent class.\n");
				System.out.println(stockevent.getTicker() + "  " + stockevent.getQuote() + " \n");
			}
		};
		Observer TableObserver = (Observable obs, Object arg) -> {
			if (arg instanceof DJIAEvent) {
				DJIAEvent dJIAEvent = (DJIAEvent) arg;
				System.out.println("TableObserver_update: I am instance of DJIAEvent class.\n");
				System.out.println("DJIA :" + dJIAEvent.getDjia() + " \n");
			} else if (arg instanceof StockEvent) {
				StockEvent stockevent = (StockEvent) arg;
				System.out.println("TableObserver_update: I am instance of StockEvent class.\n");
				System.out.println(stockevent.getTicker() + "  " + stockevent.getQuote() + " \n");
			}
		};
		Observer ThreeDObserver = (Observable obs, Object arg) -> {
			if (arg instanceof DJIAEvent) {
				DJIAEvent dJIAEvent = (DJIAEvent) arg;
				System.out.println("ThreeDObserver_update: I am instance of DJIAEvent class.\n");
				System.out.println("DJIA :" + dJIAEvent.getDjia() + " \n");
			} else if (arg instanceof StockEvent) {
				StockEvent stockevent = (StockEvent) arg;
				System.out.println("ThreeDObserver: I am instance of StockEvent class.\n");
				System.out.println(stockevent.getTicker() + "  " + stockevent.getQuote() + " \n");
			}
		};

		// -----------------------------------------------------------
		StockQuoteObservable stockObservable = new StockQuoteObservable();
		HashMap<String, Float> map = new HashMap<>();

		stockObservable.addObservers(PieChartObserver);
		stockObservable.addObservers(TableObserver);
		stockObservable.addObservers(ThreeDObserver);

		stockObservable.changeQuote("Google", 80);
		stockObservable.changeQuote("Apple", 92);
		stockObservable.changeQuote("Google", 90);

		map.put("Google", (float) 80);
		map.put("Apple", (float) 92);
		map.put("Google", (float) 90);

		// .........................................................
		DJIAQuoteObservable dJIAQuoteObservable = new DJIAQuoteObservable();
		Set<Float> data = new HashSet<Float>();

		dJIAQuoteObservable.addObservers(PieChartObserver);
		dJIAQuoteObservable.addObservers(TableObserver);
		dJIAQuoteObservable.addObservers(ThreeDObserver);

		dJIAQuoteObservable.changeQuote(70);
		dJIAQuoteObservable.changeQuote(80);
		dJIAQuoteObservable.changeQuote(90);
		data.add((float) 70);
		data.add((float) 80);
		data.add((float) 90);

	}

}
