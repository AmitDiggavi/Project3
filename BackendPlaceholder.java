import java.util.ArrayList;

public class BackendPlaceholder implements IBackend {
    private ArrayList<ILocation> cities = new ArrayList<>();
    private String currentCity;
    private String targetCity;
    private ArrayList<String> stops = new ArrayList<>();

    public void addCity(ILocation city) {}

    public void setCurrentLocation(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getCurrentLocation() {
        return currentCity;
    }

    public ArrayList<ILocation> listCity() {
        cities.add(new LocationPlaceholder("New York"));
        cities.add(new LocationPlaceholder("Chicago"));
        cities.add(new LocationPlaceholder("Los Angeles"));
        cities.add(new LocationPlaceholder("San Francisco"));
        return cities;
    }

    public void setTargetLocation(String targetCity) {
        this.targetCity = targetCity;
    }

    public String getTargetLocation() {
        return targetCity;
    }

    public void addStops(ArrayList<String> additionalStops) {
        stops.addAll(additionalStops);
    }

    public ArrayList<String> getStops() {
        return stops;
    }

    public ArrayList<ILocation> calculateRoute() {
        return new ArrayList<>();
    }

    public ArrayList<Double> calculateRouteDistance() {
        return new ArrayList<>();
    }

    public void resetRoute() {
        currentCity = null;
        targetCity = null;
        stops = new ArrayList<>();
    }
}
