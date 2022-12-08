import java.util.ArrayList;

public class Location implements ILocation{
    String name;
    ArrayList<ILocation> targets;
    ArrayList<Double> weight;


    public Location(String location) {
        this.name = location;
        targets = new ArrayList<>();
        weight = new ArrayList<>();
    }

    @Override
    public String getLocation() {
        return name;
    }

    @Override
    public ArrayList<ILocation> getTargets()
    {
        return null;
    }
    // returning null because we don't really use this.

    @Override
    public ArrayList<Double> getWeight() {

        return null;
    }
    // returning null because we don't really use this.
}
