import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface ILocationLoader {

    ArrayList<ILocation> loadLocations(String filePathToDOT) throws FileNotFoundException;
}
