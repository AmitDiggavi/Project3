import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Interface for the DataWrangler
 */

public interface ILocation {

    String getLocation();

    ArrayList<ILocation> getTargets();

    ArrayList<Double> getWeight();


}
