import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface ILocationLoader {

    void loadLocations(String filePathToDOT) throws FileNotFoundException;
}
