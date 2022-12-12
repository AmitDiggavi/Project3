import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class BackendDeveloperTests
{
	
	
	/*
	 * this test is used to check the getCurrentLocation() method
	 * the user gives a string as an input
	 * which is then returned through the get method
	 */
	@Test
	public void BackendTest1()
	{
		Backend backend = new Backend();
		
		backend.setCurrentLocation("Madison");
		
		assertEquals("Madison", backend.getCurrentLocation());
	}
	
	/*
	 * this test is used to the calculateRoute() method
	 * the method returns List of cities of after setting current and target
	 * the method returns a list of ILocation
	 * the locations are then converted to a string and compared
	 */
	@Test
	public void BackendTest2()
	{
		Backend backend = new Backend();
		
		ArrayList<ILocation> path2 = new ArrayList<>();
    	
    	String Expectedroute = "Madison,Rockford";
		
		backend.setCurrentLocation("Madison");
		
		backend.setTargetLocation("Rockford");  
		
		path2 = backend.calculateRoute();
		
		String Calculatedroute = "";
		
		for(ILocation p: path2)
    	{
			Calculatedroute = Calculatedroute + (Calculatedroute.length() > 0? ",": "") + p.getLocation();
    	}
		
		assertEquals(Expectedroute, Calculatedroute);
		
	}
	
	/*
	 * this test further tests the calculateRoute() method
	 * additional stops are added from the target
	 * it should return the path from current -> target -> additional stops
	 */
	@Test
	public void BackendTest3()
	{
		Backend backend = new Backend();
		
		ArrayList<ILocation> path2 = new ArrayList<>();
		
		String Expectedroute = "Madison,Rockford,Chicago,Milwaukee,Whitewater,Rockford";
		
        backend.setCurrentLocation("Madison");
		
		backend.setTargetLocation("Rockford");
		
        ArrayList<String> stops = new ArrayList<>();
    	
    	stops.add("Chicago");
    	
    	stops.add("Milwaukee");
    	
    	backend.addStops(stops);
    	
		path2 = backend.calculateRoute();
		
        String Calculatedroute = "";
		
		for(ILocation p: path2)
    	{
			Calculatedroute = Calculatedroute + (Calculatedroute.length() > 0? ",": "") + p.getLocation();
		
    	}
		
		assertEquals(Expectedroute, Calculatedroute);

		
	}
	
	/*
	 * this test checks the calculatRouteDistance()
	 * this method returns the distance covered by the shortest route
	 * it also takes additional stops into consideration
	 */
	@Test
	public void BackendTest4()
	{
		Backend backend = new Backend();
		
		backend.setCurrentLocation("Madison");
		
		backend.setTargetLocation("Wilwaukee");
		
		ArrayList<String> stops = new ArrayList<>();
		
         stops.add("Chicago");
    	
    	
    	 backend.addStops(stops);
    	
    	Double distance = backend.calculateRouteDistance();
    	
    	assertEquals(distance, 165.0);
			
		
	}
	
	/*
	 * this test is used to check the getTargetLocation() method
	 * the user gives a string as an input
	 * which is then returned through the get method
	 */
	@Test
	public void BackendTest5()
	{
        Backend backend = new Backend();
	
        backend.setTargetLocation("Chicago");
		
		assertEquals("Chicago", backend.getTargetLocation());
	}
			

}
