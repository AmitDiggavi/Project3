import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class LocationLoader implements ILocationLoader {
    IDijkStra<ILocation, Double> graph;

    public LocationLoader(IDijkStra<ILocation, Double> graph)
    {
        this.graph = graph;
    }
    /**
     * This method loads the list of locations from a DOT file.
     * @param filepathToDOT path to the DOT file
     * @return a list of locations
     * @throws FileNotFoundException
     */
    @Override
    public void loadLocations(String filePathToDOT) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(filePathToDOT));
        ArrayList<ArrayList<String>> locations = new ArrayList<>();
        LinkedHashSet<ILocation> lhs = new LinkedHashSet<>();
        while(scan.hasNextLine())
        {
            String str = scan.nextLine();
            if(str.contains("->"))
            {
                String name_from = str.substring(0, str.indexOf("-") - 1);
                ArrayList<String> str_list = new ArrayList<>();
                str_list.add(name_from);
                String name_to = str.substring(str.indexOf(">") + 1, str.indexOf(str.length() - 1));
                str_list.add(name_to);
                String weight = str.substring(str.indexOf("=") + 1, str.indexOf(str.length() - 1));
                str_list.add(weight);
                locations.add(str_list);

            }
        }

        for(ArrayList<String> str: locations)
        {
            for(int i = 0; i <= 2; i++)
            {
                lhs.add(new Location(str.get(i)));
            }
        }

        for(ILocation location : lhs)
        {
            graph.insertVertex(location);
        }
        for(ArrayList<String> location : locations)
        {
            ILocation from_location = (new Location(location.get(0)));
            ILocation to_location = (new Location(location.get(1)));
            double convert_to_double = (Double.parseDouble(location.get(3)));
            graph.insertEdge(from_location , to_location, convert_to_double);
        }

    }

}
