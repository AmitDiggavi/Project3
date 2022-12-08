import java.util.ArrayList;
import java.util.Scanner;

public class Frontend implements IFrontend {
    private final IBackend backend;
    private final ArrayList<String> cityNames;
    private final Scanner scn;

    public static void main(String[] args) {
        Frontend frontend = new Frontend(new BackendPlaceholder(), new Scanner(System.in));
        frontend.runCommandLoop();
    }

    public Frontend(IBackend backend, Scanner scn) {
        this.backend = backend;

        this.cityNames = new ArrayList<>();
        for (ILocation city : backend.listCity()) {
            cityNames.add(city.getLocation());
        }

        this.scn = scn;
    }

    public void runCommandLoop() {
        System.out.println("Welcome to MadMapper\n"
                + "—--------------------------------------------------\n");

        displayMainMenu();
    }

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

    public void setCurrentCity() {
        System.out.println("Enter City:");
        scn.nextLine();
        String city = scn.nextLine().trim();
        if (this.cityNames.contains(city)) {
            backend.setCurrentLocation(city);
        } else {
            System.out.println("Invalid city, please enter a valid city");
            setCurrentCity();
        }

        System.out.println();
        displayMainMenu();
    }

    public void setTargetCity() {
        System.out.println("Enter City:");
        scn.nextLine();
        String city = scn.nextLine().trim();
        if (this.cityNames.contains(city)) {
            backend.setTargetLocation(city);
        } else {
            System.out.println("Invalid city, please enter a valid city");
            setCurrentCity();
        }

        System.out.println();
        displayMainMenu();
    }

    public void addStop() {
        System.out.println("Enter City:");
        scn.nextLine();
        String city = scn.nextLine().trim();
        if (this.cityNames.contains(city)) {
            backend.addStops(new ArrayList<>() {{
                add(city);
            }});
        } else {
            System.out.println("Invalid city, please enter a valid city");
            addStop();
        }

        System.out.println();
        displayMainMenu();
    }

    public void calculateRoute() {
        System.out.println("The shortest route is:");
        ArrayList<ILocation> route = backend.calculateRoute();

        StringBuilder stringifiedRoute = new StringBuilder();
        for (ILocation location : route) {
            stringifiedRoute.append(
                    location.getLocation() +
                            " (" + location.getWeight() + " Miles)")
                    .append(" -> ");
        }
        stringifiedRoute.delete(stringifiedRoute.length() - 4, stringifiedRoute.length());

        System.out.println("Route: " + stringifiedRoute);
        System.out.println("\nSafe travels!\n");
        displayMainMenu();
    }

    public void resetRoute() {
        backend.resetRoute();
        displayMainMenu();
    }

}