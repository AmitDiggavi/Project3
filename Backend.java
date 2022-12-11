import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Backend implements IBackend {
    ArrayList<ILocation> listCity;

    ArrayList<ILocation> listStops = new ArrayList<>();

    protected IGraph<ILocation, Double> graph;

    ILocation target;

    ILocation current;

    public Backend() {
        this.graph = new Graph<>();

        listCity = new ArrayList<>();

        try {
            ILocationLoader loader = new LocationLoader(graph);
            loader.loadLocations("p3dot.dot");
        } catch (FileNotFoundException e) {
            System.out.println("Could not load file");
        }
    }

    public void addCity(ILocation city) {
        graph.insertVertex(city);
    }

    public void setCurrentLocation(String currentCity) {
        if (currentCity == null) {
            return;
        }

        for (ILocation l : listCity) {
            if (l.getLocation().equals(currentCity)) {
                current = l;

                return;
            }
        }

    }

    public String getCurrentLocation() {
        return current.getLocation();
    }

    public ArrayList<ILocation> listCity() {
        return listCity;
    }

    public void setTargetLocation(String targetCity) {
        if (targetCity == null) {
            return;
        }

        for (ILocation l : listCity) {
            if (l.getLocation().equals(targetCity)) {
                target = l;

                return;
            }
        }

    }

    public String getTargetLocation() {
        return target.getLocation();
    }

    public void addStops(ArrayList<String> additionalStops) {

        for (String l : additionalStops) {
            for (int i = 0; i < listCity.size(); i++) {
                if (l.equals(listCity.get(i).getLocation())) {
                    listStops.add(listCity.get(i));
                }
            }
        }

    }

    public ArrayList<String> getStops() {
        ArrayList<String> str = new ArrayList<>();

        for (ILocation s : listStops) {
            str.add(s.getLocation());
        }
        return str;
    }

    public ArrayList<ILocation> calculateRoute() {
        if (getStops().size() == 0) {
            return new ArrayList<>(graph.shortestPath(current, target));
        } else {
            List<ILocation> route = graph.shortestPath(current, listStops.get(0));
            for (ILocation stop : listStops) {
                if (listStops.indexOf(stop) == 0) continue;
                // if last stop
                if (listStops.indexOf(stop) == listStops.size() - 1) {
                    route.addAll(graph.shortestPath(stop, target));
                } else {
                    route.addAll(graph.shortestPath(stop, listStops.get(listStops.indexOf(stop) + 1)));
                }
            }
            return new ArrayList<>(route);
        }
    }

    private ILocation getLocation(String city) {
        for (ILocation l : listCity) {
            if (l.getLocation().equals(city)) {
                return l;
            }
        }

        return null;
    }

    public Double calculateRouteDistance() {
        ArrayList<ILocation> shortestPath = calculateRoute();


        double routeDistance = 0.0;

        for (ILocation location : shortestPath) {
            // if not last location
            if (shortestPath.indexOf(location) != shortestPath.size() - 1) {
                routeDistance += graph.getPathCost(
                        location,
                        shortestPath.get(shortestPath.indexOf(location) + 1)
                );
            }
        }

        return routeDistance;
    }

    public void resetRoute() {
        target = null;

        current = null;

        listStops = null;

    }
}

