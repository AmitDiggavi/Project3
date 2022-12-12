import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class LocationLoader implements ILocationLoader {
    IGraph<ILocation, Double> graph;

    public LocationLoader(IGraph<ILocation, Double> graph)
    {
        this.graph = graph;
    }
    /**
     * This method loads the list of locations from a DOT file.
     * @param filePathToDOT path to the DOT file
     */
    @Override
    public void loadLocations(String filePathToDOT) throws FileNotFoundException {


        Scanner scan = new Scanner(new File(filePathToDOT));


            ArrayList<ArrayList<String>> locations = new ArrayList<>();
            LinkedHashSet<String> lhs = new LinkedHashSet<>();
            while (scan.hasNextLine()) {
                String str = scan.nextLine();
                if (str.contains("->")) {

                    String[] b = str.split("->");
                    ArrayList<String> str_list = new ArrayList<>();
                    String from = b[0].trim();
                    str_list.add(from);
                    String to = b[1].substring(0, b[1].indexOf("[")).trim();
                    str_list.add(to);
                    String weight = b[1].substring(b[1].indexOf("=") + 1).trim().replaceAll("[^\\d.]", "");// replace all non digits
                    str_list.add(weight);
                    locations.add(str_list);

                }
            }

            for (ArrayList<String> str : locations) {
                for (int i = 0; i < 2; i++) {
                    lhs.add((str.get(i)));
                }
            }

            for (String location : lhs) {
                graph.insertVertex(new Location(location.replace("\"", "")));
            }

            for (ArrayList<String> location : locations) {
                ILocation from_location = (new Location(location.get(0).replace("\"", "")));
                ILocation to_location = (new Location(location.get(1).replace("\"", "")));
                double convert_to_double = (Double.parseDouble(location.get(2)));
                graph.insertEdge(from_location, to_location, convert_to_double);
            }
    }

}
