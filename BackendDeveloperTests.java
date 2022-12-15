import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class BackendDeveloperTests
{
	
	
	/*
	 * Role Test 1
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
	 * Role Test 2
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
	 * Role test 3
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
	 * Role Test 4
	 * this test checks the calculatRouteDistance()
	 * this method returns the distance covered by the shortest route
	 * it also takes additional stops into consideration
	 */
	@Test
	public void BackendTest4()
	{
		Backend backend = new Backend();
		
		backend.setCurrentLocation("Madison");
		
		backend.setTargetLocation("Milwaukee");
		
		ArrayList<String> stops = new ArrayList<>();
		
         stops.add("Chicago");
    	
    	
    	 backend.addStops(stops);
    	
    	ArrayList<Double> distances = backend.calculateRouteDistance();
		Double totalDistance = 0.0;
		for (Double d : distances) {
			totalDistance += d;
		}
    	
    	assertEquals(totalDistance, 255.0);
			
		
	}
	
	/*
	 * Role Test 5
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
	
	/*
	 * Integration test 1
	 * this test checks the overall program 
	 * it takes input from the frontend 
	 * then uses backend and algorithm engineer to calculate the shortest path
	 */
		@Test
		public void BackendIntegrationTest1()
		{
			 
			 TextUITester tester = new TextUITester("2\nMadison\n3\nChicago\n5\n7\n");
			 
			 Scanner scn = new Scanner(System.in);
			 
			 IBackend backend = new Backend();
			 
			 IFrontend frontend = new Frontend(backend, scn);
			 
			 frontend.runCommandLoop();
			 
			 String expected = "Welcome to MadMapper\n"
			 		+ "—--------------------------------------------------\n"
			 		+ "\n"
			 		+ "You are in the Main Menu:\n"
			 		+ "Current Itinerary: Unset -> Unset\n"
			 		+ "Choose an option:\n"
			 		+ "	 1. List all cities\n"
			 		+ "	 2. Set current city\n"
			 		+ "	 3. Set target city\n"
			 		+ "	 4. Add stop\n"
			 		+ "	 5. Find route\n"
			 		+ "	 6. Reset stops\n"
			 		+ "	 7. Exit\n"
			 		+ "\n"
			 		+ "Enter City:\n"
			 		+ "\n"
			 		+ "You are in the Main Menu:\n"
			 		+ "Current Itinerary: Madison -> Unset\n"
			 		+ "Choose an option:\n"
			 		+ "	 1. List all cities\n"
			 		+ "	 2. Set current city\n"
			 		+ "	 3. Set target city\n"
			 		+ "	 4. Add stop\n"
			 		+ "	 5. Find route\n"
			 		+ "	 6. Reset stops\n"
			 		+ "	 7. Exit\n"
			 		+ "\n"
			 		+ "Enter City:\n"
			 		+ "\n"
			 		+ "You are in the Main Menu:\n"
			 		+ "Current Itinerary: Madison -> Chicago\n"
			 		+ "Choose an option:\n"
			 		+ "	 1. List all cities\n"
			 		+ "	 2. Set current city\n"
			 		+ "	 3. Set target city\n"
			 		+ "	 4. Add stop\n"
			 		+ "	 5. Find route\n"
			 		+ "	 6. Reset stops\n"
			 		+ "	 7. Exit\n"
			 		+ "\n"
			 		+ "The shortest route is:\n"
			 		+ "Route: Madison -> Rockford (75.0 Miles) -> Chicago (90.0 Miles)\n" +
					 "Total Distance: 165.0 Miles\n"
			 		+ "\n"
			 		+ "Safe travels!\n"
			 		+ "\n"
			 		+ "You are in the Main Menu:\n"
			 		+ "Current Itinerary: Madison -> Chicago\n"
			 		+ "Choose an option:\n"
			 		+ "	 1. List all cities\n"
			 		+ "	 2. Set current city\n"
			 		+ "	 3. Set target city\n"
			 		+ "	 4. Add stop\n"
			 		+ "	 5. Find route\n"
			 		+ "	 6. Reset stops\n"
			 		+ "	 7. Exit\n"
			 		+ "\n";
			 
			 assertEquals(expected, tester.checkOutput());
			 
		}
		
		/*
		 * Integration Test 2
		 * this test checks the overall program
		 * it takes input from the frontend 
		 * then uses backend and algorithm engineer to calculate the shortest path with additional stops
		 * current -> additional stop -> target
		 */
		@Test
		public void BackendIntegrationTest2()
		{
			 
			 TextUITester tester = new TextUITester("2\nMadison\n3\nChicago\n4\nRockford\n5\n7\n");
			 
			 Scanner scn = new Scanner(System.in);
			 
			 IBackend backend = new Backend();
			 
			 IFrontend frontend = new Frontend(backend, scn);
			 
			 frontend.runCommandLoop();
			 
			 String expected = "Welcome to MadMapper\n"
			 		+ "—--------------------------------------------------\n"
			 		+ "\n"
			 		+ "You are in the Main Menu:\n"
			 		+ "Current Itinerary: Unset -> Unset\n"
			 		+ "Choose an option:\n"
			 		+ "	 1. List all cities\n"
			 		+ "	 2. Set current city\n"
			 		+ "	 3. Set target city\n"
			 		+ "	 4. Add stop\n"
			 		+ "	 5. Find route\n"
			 		+ "	 6. Reset stops\n"
			 		+ "	 7. Exit\n"
			 		+ "\n"
			 		+ "Enter City:\n"
			 		+ "\n"
			 		+ "You are in the Main Menu:\n"
			 		+ "Current Itinerary: Madison -> Unset\n"
			 		+ "Choose an option:\n"
			 		+ "	 1. List all cities\n"
			 		+ "	 2. Set current city\n"
			 		+ "	 3. Set target city\n"
			 		+ "	 4. Add stop\n"
			 		+ "	 5. Find route\n"
			 		+ "	 6. Reset stops\n"
			 		+ "	 7. Exit\n"
			 		+ "\n"
			 		+ "Enter City:\n"
			 		+ "\n"
			 		+ "You are in the Main Menu:\n"
			 		+ "Current Itinerary: Madison -> Chicago\n"
			 		+ "Choose an option:\n"
			 		+ "	 1. List all cities\n"
			 		+ "	 2. Set current city\n"
			 		+ "	 3. Set target city\n"
			 		+ "	 4. Add stop\n"
			 		+ "	 5. Find route\n"
			 		+ "	 6. Reset stops\n"
			 		+ "	 7. Exit\n"
			 		+ "\n"
			 		+ "Enter City:\n"
			 		+ "\n"
			 		+ "You are in the Main Menu:\n"
			 		+ "Current Itinerary: Madison -> Rockford -> Chicago\n"
			 		+ "Choose an option:\n"
			 		+ "	 1. List all cities\n"
			 		+ "	 2. Set current city\n"
			 		+ "	 3. Set target city\n"
			 		+ "	 4. Add stop\n"
			 		+ "	 5. Find route\n"
			 		+ "	 6. Reset stops\n"
			 		+ "	 7. Exit\n"
			 		+ "\n"
			 		+ "The shortest route is:\n"
			 		+ "Route: Madison -> Rockford (75.0 Miles) -> Chicago (90.0 Miles)\n"
					+ "Total Distance: 165.0 Miles\n"
			 		+ "\n"
			 		+ "Safe travels!\n"
			 		+ "\n"
			 		+ "You are in the Main Menu:\n"
			 		+ "Current Itinerary: Madison -> Rockford -> Chicago\n"
			 		+ "Choose an option:\n"
			 		+ "	 1. List all cities\n"
			 		+ "	 2. Set current city\n"
			 		+ "	 3. Set target city\n"
			 		+ "	 4. Add stop\n"
			 		+ "	 5. Find route\n"
			 		+ "	 6. Reset stops\n"
			 		+ "	 7. Exit\n"
			 		+ "\n";
			 
			 assertEquals(expected, tester.checkOutput());
			 
		}
		
		
		/**
	     * this test is to checks if the frontend is displaying the current city in the itinerary
	     * then it exits the program
	     */
	    @Test
	    public void BackendDeveloperCodeReviewForFrontendDeveloper1()
	    {
	    	TextUITester tester = new TextUITester("1\n7\n");
			 
			 Scanner scn = new Scanner(System.in);
			 
			 IBackend backend = new Backend();
			 
			 IFrontend frontend = new Frontend(backend, scn);
			 
			 frontend.runCommandLoop();
			 
	     
	     String expected = "Welcome to MadMapper\n"
	    			+ "—--------------------------------------------------\n"
	    			+ "\n"
	    			+ "You are in the Main Menu:\n"
	    			+ "Current Itinerary: Unset -> Unset\n"
	    			+ "Choose an option:\n"
	    			+ "	 1. List all cities\n"
	    			+ "	 2. Set current city\n"
	    			+ "	 3. Set target city\n"
	    			+ "	 4. Add stop\n"
	    			+ "	 5. Find route\n"
	    			+ "	 6. Reset stops\n"
	    			+ "	 7. Exit\n"
	    			+ "\n"
	    			+ "List of cities:\n"
	    			+ "	1. Appleton\n"
	    			+ "	2. Chicago\n"
	    			+ "	3. Eau Claire\n"
	    			+ "	4. Green Bay\n"
	    			+ "	5. Janesville\n"
	    			+ "	6. Kenosha\n"
	    			+ "	7. La Crosse\n"
	    			+ "	8. Madison\n"
	    			+ "	9. Middleton\n"
	    			+ "	10. Milwaukee\n"
	    			+ "	11. Minneapolis\n"
	    			+ "	12. Naperville\n"
	    			+ "	13. Oshkosh\n"
	    			+ "	14. Rochester\n"
	    			+ "	15. Rockford\n"
	    			+ "	16. Saint Paul\n"
	    			+ "	17. Springfield\n"
	    			+ "	18. Stevens Point\n"
	    			+ "	19. Waukesha\n"
	    			+ "	20. Whitewater\n"
	    			+ "	21. Wisconsin Dells\n"
	    			+ "\n"
	    			+ "You are in the Main Menu:\n"
	    			+ "Current Itinerary: Unset -> Unset\n"
	    			+ "Choose an option:\n"
	    			+ "	 1. List all cities\n"
	    			+ "	 2. Set current city\n"
	    			+ "	 3. Set target city\n"
	    			+ "	 4. Add stop\n"
	    			+ "	 5. Find route\n"
	    			+ "	 6. Reset stops\n"
	    			+ "	 7. Exit\n"
	    			+ "\n";
	    	
	     
	     assertEquals(expected, tester.checkOutput());
	    }
	    
		
		 /**
	     * this test checks if frontend is displaying the list of cities
	     * and then exiting the program
	     */
	    @Test
	    public void BackendDeveloperCodeReviewForFrontendDeveloper2()
	    {
	    	TextUITester tester = new TextUITester("2\nMadison\n7\n");
			 
			 Scanner scn = new Scanner(System.in);
			 
			 IBackend backend = new Backend();
			 
			 IFrontend frontend = new Frontend(backend, scn);
			 
			 frontend.runCommandLoop();
			
			 String expected = "Welcome to MadMapper\n"
			     		+ "—--------------------------------------------------\n"
			     		+ "\n"
			     		+ "You are in the Main Menu:\n"
			     		+ "Current Itinerary: Unset -> Unset\n"
			     		+ "Choose an option:\n"
			     		+ "	 1. List all cities\n"
			     		+ "	 2. Set current city\n"
			     		+ "	 3. Set target city\n"
			     		+ "	 4. Add stop\n"
			     		+ "	 5. Find route\n"
			     		+ "	 6. Reset stops\n"
			     		+ "	 7. Exit\n"
			     		+ "\n"
			     		+ "Enter City:\n"
			     		+ "\n"
			     		+ "You are in the Main Menu:\n"
			     		+ "Current Itinerary: Madison -> Unset\n"
			     		+ "Choose an option:\n"
			     		+ "	 1. List all cities\n"
			     		+ "	 2. Set current city\n"
			     		+ "	 3. Set target city\n"
			     		+ "	 4. Add stop\n"
			     		+ "	 5. Find route\n"
			     		+ "	 6. Reset stops\n"
			     		+ "	 7. Exit\n"
			     		+ "\n";
			 
	    	
	    	assertEquals(expected, tester.checkOutput());
	    }

}
