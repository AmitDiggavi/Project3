public interface IFrontend {
  /**
   * This method starts the command loop for the frontend, and will
   * terminate when the user exists the app.
   */
  void runCommandLoop();

  /**
   * This method displays the app's main menu
   */
  void displayMainMenu();

  /**
   * This method lists all cities in the database
   */
  void listCities();

  /**
   * This method prompts the user for the starting city in their route
   */
  void setCurrentCity();

  /**
   * This method prompts the user for the destination city in their route
   */
  void setTargetCity();

  /**
   * This method prompts the user for an intermediate city in their route
   */
  void addStop();

  /**
   * This method displays the shortest route given the starting, intermediate, and destination cities
   */
  void calculateRoute();

  /**
   * This method resets the current route
   */
  void resetRoute();
}
