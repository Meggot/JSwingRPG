import java.util.*;
/**
 * Write a description of class Region here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Region
{
    // instance variables - replace the example below with your own
    private String name;
    private ArrayList<Location> locations = new ArrayList<>();
    private Location startLocation;
    
    
    public Region(String regionName, String startLocationName)
    {
        this.name = regionName;
        this.startLocation = new Location(startLocationName, 0);
        locations.add(startLocation);
    }
    
    public void addLocation(Location location)
    {
        locations.add(location);
    }
    
    public void addLocation(String locationName, int type)
    {
        Location newLocation = new Location(locationName, type);
        locations.add(newLocation);
    }
    
    public Location getLocation(String locationName)
    {
        for (Location tempLocation : locations)
        {
            if (tempLocation.getName().equals(locationName))
            {
                return tempLocation;
            }
        }
        return null;
    }
    
    public boolean isLocation(String locationName)
    {
        for (Location tempLocation : locations)
        {
            if (tempLocation.getName().equals(locationName))
            {
                return true;
            }
        }
        return false;
    }
    
    public String getLocations()
    {
        String returnString = "";
        int i = 0;
        for (Location temps : locations)
        {
            returnString = returnString + 
            "\n" + i + ": " + temps.getName();
            i++;
        }
        return returnString;
    }
    
    public String getName()
    {
        return name;
    }
    
    public Location getStartLocation()
    {
        return startLocation;
    }
}
