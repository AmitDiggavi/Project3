import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Backend implements IBackend {
    protected IGraph<ILocation, Double> graph;
    ILocation current;
    ILocation target;
    ArrayList<ILocation> listStops = new ArrayList<>();
    ArrayList<ILocation> listCity;

    public Backend() {
        this.graph = new Graph<>();
        listCity = new ArrayList<>();

        try {
            ILocationLoader loader = new LocationLoader(graph);
            loader.loadLocations("p3dot.dot");
        } catch (FileNotFoundException e) {
            System.out.println("Could not load file");
        }

        listCity.addAll(graph.getVertices());
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
        if (current == null) return null;

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
        if (target == null) return null;

        return target.getLocation();
    }

    public void addStops(ArrayList<String> additionalStops) {

        for (String stop : additionalStops) {
            for (ILocation location : listCity) {
                if (stop.equals(location.getLocation())) {
                    listStops.add(location);
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
        if (current == null || target == null) {
            return null;
        }

        if (getStops().size() == 0) {
            return new ArrayList<>(graph.shortestPath(current, target));
        }

        List<ILocation> route = graph.shortestPath(current, listStops.get(0));
        for (ILocation stop : listStops) {
            int currentStopIndex = listStops.indexOf(stop);
            List<ILocation> routeToNextStop;
            // if last stop
            if (currentStopIndex == listStops.size() - 1) {
                routeToNextStop = graph.shortestPath(stop, target);
            } else {
                routeToNextStop = graph.shortestPath(stop, listStops.get(currentStopIndex + 1));
            }

            for (int i = 1; i < routeToNextStop.size(); i++) {
                route.add(routeToNextStop.get(i));
            }
        }

        return new ArrayList<>(route);
    }

    public ArrayList<Double> calculateRouteDistance() {
        ArrayList<ILocation> shortestPath = calculateRoute();
        ArrayList<Double> legs = new ArrayList<>();

        for (ILocation location : shortestPath) {
            // if not last location
            if (shortestPath.indexOf(location) != shortestPath.size() - 1) {
                legs.add(graph.getPathCost(
                        location,
                        shortestPath.get(shortestPath.indexOf(location) + 1)
                ));
            }
        }

        return legs;
    }

    public void resetRoute() {
        target = null;
        current = null;
        listStops = new ArrayList<>();;
    }
}

