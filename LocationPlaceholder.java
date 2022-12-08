import java.util.ArrayList;

public class LocationPlaceholder implements ILocation {
    private String location;

    public LocationPlaceholder(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<ILocation> getTargets() {
        return null;
    }

    public ArrayList<Double> getWeight() {
        return null;
    }
}
