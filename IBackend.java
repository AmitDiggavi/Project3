import java.util.ArrayList;
import java.util.List;

public interface IBackend 
{
	/*
	 * This method is used to set the current location
	 * @param currentCity is the string that that current location must equal
	 */
	public void setCurrentLocation(String currentCity);
	
	/*
	 * returns the string used as the current location
	 */
	public String getCurrentLocation();	
	
	/*
	 * Returns the full list of cities stored in the database
	 */
	public ArrayList<ICity> listCity();
	
	/*
	 * This method is used to set the target location
	 * @param TargetCity is the string that that target location must equal
	 */
	public void setTargetLocation(String TargetCity);
	
	/*
	 * returns the string used as the target location
	 */
	public String getTargetLocation();
	
	/*
	 * This method is used to add additional stops
	 * The array list additonalStops contains the list of addtional stops 
	 */
	public void addStops(ArrayList<ICity> additonalStops);
	
	/*
	 * returns the list of cities added as additional stops
	 */
	public ArrayList<ICity> getStops();
	
	
	/*
	 * Finds the shortest path between a set of cities
	 * returns an array of cities which demonstrates the short path 
	 */
	public ArrayList<ICity> calculateRoute();
	
	/*
	 * Resets the route to null.
	 */
	public void resetRoute();
	
	
	

}
