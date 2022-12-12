import java.util.ArrayList;
import java.util.Scanner;

/**
 * src.Frontend class for the MadMapper program.
 */
public class Frontend implements IFrontend {
    private final IBackend backend;
    private final ArrayList<String> cityNames;
    private final Scanner scn;

    public static void main(String[] args) {
        IBackend backend = new Backend();
        Scanner scn = new Scanner(System.in);
        IFrontend frontend = new Frontend(backend, scn);
        frontend.runCommandLoop();
    }

    /**
     * Constructor for the src.Frontend class.
     *
     * @param backend the backend to use
     * @param scn     the scanner to use
     */
    public Frontend(IBackend backend, Scanner scn) {
        this.backend = backend;

        this.cityNames = new ArrayList<>();
        for (ILocation city : backend.listCity()) {
            cityNames.add(city.getLocation());
        }

        this.scn = scn;
    }

    /**
     * Run the dialog.
     */
    public void runCommandLoop() {
        System.out.println("Welcome to MadMapper\n"
                + "—--------------------------------------------------\n");

        displayMainMenu();
    }

    /**
     * Display the main menu.
     */
    public void displayMainMenu() {
        System.out.println("You are in the Main Menu:");

        displayCurrentItinerary();
        System.out.println("Choose an option:\n" +
                "\t 1. List all cities\n" +
                "\t 2. Set current city\n" +
                "\t 3. Set target city\n" +
                "\t 4. Add stop\n" +
                "\t 5. Find route\n" +
                "\t 6. Reset stops\n" +
                "\t 7. Exit\n");

        switch (scn.nextInt()) {
            case 1:
                listCities();
                break;
            case 2:
                setCurrentCity();
                break;
            case 3:
                setTargetCity();
                break;
            case 4:
                addStop();
                break;
            case 5:
                calculateRoute();
                break;
            case 6:
                resetRoute();
                break;
            case 7: return;
            default:
                System.out.println("Invalid option, please choose from displayed options");
                displayMainMenu();
        }
    }

    /**
     * Display the current itinerary.
     */
    public void displayCurrentItinerary() {
        String currentCity = backend.getCurrentLocation() == null
                ? "Unset"
                : backend.getCurrentLocation();

        String targetCity = backend.getTargetLocation() == null
                ? "Unset"
                : backend.getTargetLocation();

        String stops = "";
        if (!backend.getStops().isEmpty()) {
            stops = String.join(" -> ", backend.getStops());
            stops += " -> ";
        }

        System.out.println("Current Itinerary: " + currentCity + " -> " + stops + targetCity);
    }

    public void listCities() {
        System.out.println("List of cities:");

        for (int i = 1; i < cityNames.size() + 1; i++) {
            System.out.println("\t" + i + ". " + cityNames.get(i - 1));
        }

        System.out.println();
        displayMainMenu();
    }

    /**
     * Set the current city.
     */
    public void setCurrentCity() {
        backend.setCurrentLocation(promptAndCheckCity());

        System.out.println();
        displayMainMenu();
    }

    /**
     * Set the target city.
     */
    public void setTargetCity() {
        backend.setTargetLocation(promptAndCheckCity());

        System.out.println();
        displayMainMenu();
    }

    /**
     * Add a stop.
     */
    public void addStop() {
        backend.addStops(new ArrayList<>() {{
            add(promptAndCheckCity());
        }});

        System.out.println();
        displayMainMenu();
    }

    private String promptAndCheckCity() {
        System.out.println("Enter City:");
        scn.nextLine();
        String city = null;
        while (city == null) {
            city = scn.nextLine().trim();
            if (!cityNames.contains(city)) {
                System.out.println("Invalid city, please enter a valid city");
                city = null;
            }
        }

        return city;
    }

    /**
     * Calculate the route.
     */
    public void calculateRoute() {
        ArrayList<ILocation> route = backend.calculateRoute();
        if (route == null) {
            System.out.println("No route found");
            displayMainMenu();
            return;
        }

        System.out.println("The shortest route is:");
        ArrayList<Double> distanceBetweenStops = backend.calculateRouteDistance();

        StringBuilder stringifiedRoute = new StringBuilder();
        stringifiedRoute.append(route.get(0).getLocation() + " -> ");
        for (int i = 1; i < route.size(); i++) {
            stringifiedRoute.append(route.get(i).getLocation())
                    .append(" (" + distanceBetweenStops.get(i - 1) + " Miles)")
                    .append(" -> ");
        }
        stringifiedRoute.delete(stringifiedRoute.length() - 4, stringifiedRoute.length());

        System.out.println("Route: " + stringifiedRoute);
        System.out.println("Total Distance: " + backend.calculateRouteDistance()
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum() + " Miles");
        System.out.println("\nSafe travels!\n");
        displayMainMenu();
    }

    /**
     * Reset the route.
     */
    public void resetRoute() {
        backend.resetRoute();
        displayMainMenu();
    }

}