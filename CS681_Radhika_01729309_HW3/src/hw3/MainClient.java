package hw3;

import java.util.ArrayList;

public class MainClient {

	public static void main(String[] args) {
		
		ArrayList<Car> usedCars = new ArrayList<Car>();
		
		usedCars.add(new Car(40000,2018,10000,"TATA"));
		usedCars.add(new Car(40000,2012,1050000,"TATA"));
		usedCars.add(new Car(50000,2010,50000,"BMW"));
		usedCars.add(new Car(20000,2017,30000,"Toyato"));
		usedCars.add(new Car(10000,2003,75000,"Toyato"));
		usedCars.add(new Car(10000,2018,750,"Honda"));
		usedCars.add(new Car(30000,2018,750,"Honda"));
		usedCars.add(new Car(40000,2018,750,"Honda"));
		
		System.out.println("\n Inserted Car Price: \n");
		//print price
		usedCars.forEach( (Car car)->System.out.println(car.getPrice()));
		System.out.println("\n \n HW3-1 min max count with reduce.......");
		//Min with reduce..........
		
		Integer minPrice = usedCars.stream().map( (Car car)-> car.getPrice() )
												. reduce(0, (result, carPrice)->{
															if(result==0) return carPrice;
															else if(carPrice < result) return carPrice;
															else return result;} );
		System.out.println("\n Minimum car price is: " +minPrice);
		
		// Max with reduce.................
		Integer maxPrice = usedCars.stream().map( (Car car)-> car.getPrice() )
				. reduce(0, (result, carPrice)->{
							if(result==0) return carPrice;
							else if(carPrice < result) return result;
							else return carPrice;} );
		System.out.println("\n Maximum car price is: " +maxPrice);
		
		// Count with reduce.................
				
				Integer Count = usedCars.stream().map( (Car car)-> car.getPrice() )
						. reduce(0, (result, carPrice)->{return ++result;} );
				System.out.println("\n Total car are: " +Count);
				
		//HW3-2 average with reduce...............
				System.out.println("\n \n HW3-2 average car price with the 3rd version of reduce() \n");
				int[] avgArr = usedCars.stream().map((Car car)-> car.getPrice() )
						. reduce(new int[3],
								(arr, price)->{arr[0] = 1+arr[0];
												arr[1]=price+ arr[1];
												if(arr[0]!=0)
													arr[2] = arr[1]/arr[0];
									return arr; },
									(arr, intermediateArr)->{ return arr; });
				System.out.println("\n Total car price is: "+avgArr[1]);
				System.out.println("\n No of Cars are: "+ avgArr[0]);
				System.out.println("\n Avrage of car price is: " +avgArr[2]);
				
	}
			
}
