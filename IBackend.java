import java.util.ArrayList;
import java.util.List;

public interface IBackend {
  /**
   * This method adds an ICity object to the graph
   */
  public void addCity(ILocation city);

  /**
   * This method is used to set the current location
   * 
   * @param currentCity is the string that that current location must equal
   */
  public void setCurrentLocation(String currentCity);

  /**
   * Returns the string used as the current location
   * 
   * @return current location
   */
  public String getCurrentLocation();

  /**
   * Returns the full list of cities stored in the database
   * 
   * @return the list of all cities
   */
  public ArrayList<ILocation> listCity();

  /**
   * This method is used to set the target location
   * 
   * @param TargetCity is the string that that target location must equal
   */
  public void setTargetLocation(String TargetCity);

  /**
   * returns the string used as the target location
   * 
   * @return the target location
   */
  public String getTargetLocation();

  /**
   * This method is used to add additional stops
   * The array list additonalStops contains the list of addtional stops
   * 
   * @param the city to add as a stop
   */
  public void addStops(ArrayList<String> additionalStops);

  /**
   * Returns the list of cities added as additional stops
   * 
   * @return the list of cities as stops
   */
  public ArrayList<String> getStops();

  /**
   * Finds the shortest path between a set of cities
   * 
   * @return an array of cities which demonstrates the short path
   */
  public ArrayList<String> calculateRoute();

  /**
   * Finds the distance between the set of cities directly correlated with the calculateRoute()
   * array
   * 
   * @return an array of distances which demonstrates the short path
   */
  public ArrayList<Double> calculateRouteDistance();

  /*
   * Resets the route to null.
   */
  public void resetRoute();

}

