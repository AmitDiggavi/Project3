import java.util.ArrayList;
import java.util.List;

public class Backend implements IBackend
{
	ArrayList<ILocation> listCity; 
	
	ArrayList<ILocation> listStops = new ArrayList<>();
	
    protected IDijkstra<String, Double> graph;
    
    ILocation target;
    
    ILocation current;
    
    public Backend()
    {
    	this.graph = new Dijkstra<>();
    	
    	listCity = new ArrayList<>();
    
	LocationLoader load  = new LocationLoader();
	
	for(ILocation l: load.loadLocations())
        {
		listCity.add(l);
	}
        
        for(ILocation l: listCity)
        {
        	addCity(l);
        	
        }
        
        for(ILocation l: listCity)
        {
        	addEdge(l);
        }
    	
    }

	@Override
	public void addCity(ILocation city) 
	{	
		   graph.insertVertex(city.getLocation());
	}
	
	public void addEdge(ILocation edge)
	{
	
		for(int i = 0; i < edge.getWeights().size(); i++)
		{
			ILocation target = edge.getTargets().get(i);
			
			Double weight = edge.getWeights().get(i);
			
			graph.insertEdge(edge.getLocation(), target.getLocation(), weight);
		}

	}

	@Override
	public void setCurrentLocation(String currentCity)
	{
		if(currentCity == null)
		{
			return;
		}
		
        for(ILocation l : listCity)
        {
          if(l.getLocation().equals(currentCity))
          {
        	  current = l;
        	  
        	  return;
          }
        }
		
	}

	@Override
	public String getCurrentLocation()
	{
		return current.getLocation();
	}

	@Override
	public ArrayList<ILocation> getlistCity()
	{
	   return listCity;
	}

	@Override
	public void setTargetLocation(String TargetCity) 
	{
		if(TargetCity == null)
		{
			return;
		}
		
		 for(ILocation l : listCity)
	        {
	          if(l.getLocation().equals(TargetCity))
	          {
	        	  target = l;
	        	  
	        	  return;
	          }
	        }
		
	}

	@Override
	public String getTargetLocation()
	{
		return target.getLocation();
	}

	@Override
	public void addStops(ArrayList<String> additionalStops)
	{
		
		for(String l : additionalStops)
		{
			for(int i = 0; i< listCity.size(); i++) 
			{
				if(l.equals(listCity.get(i).getLocation()))
				{
                  listStops.add(listCity.get(i));
				}
			}
		}
		
	}

	@Override
	public ArrayList<String> getStops()
	{
		ArrayList<String> str = new ArrayList<>();
		
		for(ILocation s : listStops)
		{
			str.add(s.getLocation());
		}
		return str;
	}

	@Override
	public ArrayList<ILocation> calculateRoute() 
	{
		ArrayList<ILocation> shortestpath = new ArrayList<>();
		
		if(getCurrentLocation() != null && getTargetLocation() != null ) 
		{
			List<String> sp = graph.shortestPath(getCurrentLocation(), getTargetLocation());
			
			if(getStops().size() > 0)
			{
				String temp1 = getTargetLocation();
				
				for(int i = 0; i < getStops().size(); i++)
				{
				  String temp2 = getStops().get(i);
					
				   List<String> path =  graph.shortestPath(temp1, temp2);
				   
				   temp1 = temp2;
				  
				   for(int j = 0; j < path.size()-1; j++)
				   {
					   sp.add(path.get(j+1));
					   
				   }
				}
				 
				
			}
			
			Location previous = null;
			
			for(String s: sp)
			{
				Location l = new Location(s, new ArrayList<ILocation>(), new ArrayList<Double>());
				
				shortestpath.add(l);
				
				if(previous != null)
				{
					previous.getTargets().add(l);
					
					previous.getWeights().add(graph.getWeight(previous.getLocation(), l.getLocation()));
				}
				
				previous = l;
				
			}
			
		}
	
		return shortestpath;
	
	}

	@Override
	public Double calculateRouteDistance() 
	{
	   ArrayList<ILocation> shortestpath = calculateRoute();
	   
	   
	   double routeDistance = 0.0;
	   
	   for(ILocation l : shortestpath)
	   {
		   if(l.getWeights().size() > 0)
		   {
		        routeDistance = routeDistance + l.getWeights().get(0);
		   }
	   }
	   
	   return routeDistance;
	}

	@Override
	public void resetRoute() 
	{ 
		target = null;
		
		current = null;
		
		listStops = null;
		
	}
	
	public static void main(String[] args)
	{
		Backend backend = new Backend();
		
		ArrayList<ILocation> path2 = new ArrayList<>();
		
		backend.setCurrentLocation("A");
		
		backend.setTargetLocation("D");
		
		ArrayList<String> stops = new ArrayList<>();
		
        stops.add("C");
    	
    	stops.add("B");
    	
    	backend.addStops(stops);
    	
    	Double distance = backend.calculateRouteDistance();
    	
    	System.out.println(distance);
    	
    	path2 = backend.calculateRoute();
    	
    	 String Calculatedroute = "";
    	
    	for(ILocation p: path2)
    	{
			Calculatedroute = Calculatedroute + p.getLocation();
		
    	}
    	
    	System.out.println(Calculatedroute);
    	
    	
		
		
	}

}

