import java.util.ArrayList;

public class Location implements ILocation{
    String name;
    ArrayList<ILocation> targets;

    ArrayList<Double> weight;


    public Location(String location, ArrayList<ILocation> targets, ArrayList<Double> weight) {
        this.name = location;
        this.targets = targets;
        this.weight = weight;
    }

    @Override
    public String getLocation() {
        return name;
    }

    @Override
    public ArrayList<ILocation> getTargets()
    {
        return targets;
    }




    @Override
    public ArrayList<Double> getWeight() {

        return weight;
    }
}
