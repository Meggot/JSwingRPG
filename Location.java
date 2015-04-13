import java.util.*;

/**
 * Write a description of class Location here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Location
{
    // instance variables - replace the example below with your own
    private String name;
    private int type;
    private String desc = "";;
    
    private ArrayList<locObject> objects = new ArrayList<>();
    
    public Location(String locationName, int locType)
    {
        this.name = locationName;
        this.type = locType;
    }
    
    public void addObject(locObject object)
    {
        objects.add(object);
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getInfoDisplay()
    {
        return "Location: " + name +
        "\n" + desc;
    }
    
    public String getDesc()
    {
        return desc;
    }
    
    public boolean isObject(int objectID)
    {
        for (locObject tempObject : objects)
        {
            if (tempObject.getId() == objectID)
            {
                return true;
            }
        }
        return false;
    }
    
    public locObject getLocObj(int objectID)
    {
        for (locObject tempObject : objects)
        {
            if (tempObject.getId() == objectID)
            {
                return tempObject;
            }
        }
        return null;
    }
    
    public String getObjectsDisplay()
    {
        String returnString = "";
        for (locObject tempObject : objects)
        {
            returnString = returnString +
            tempObject.getDisplayNote() + "\n";
        }
        return returnString;
    }
    
    public void setDesc(String locationDescription)
    {
        this.desc = locationDescription;
    }
}
