
/**
 * Write a description of class objGateway here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class objGateway extends locObject
{
    private String a;
    private String b;
    
    public objGateway(String doorname, String location1, String location2)
    {
        super(doorname, 1);
        a = location1;
        b = location2;
    }
    
    public String footnote()
    {
        return getName() + " leads to " + b;
    }
    
    public String goThrough(String currentLocationName)
    {
        if (currentLocationName.equals(a))
        {
            return b;
        } else if (currentLocationName.equals(b))
        {
            return a;
        }
        return b;
    }
    
    public void setDesc()
    {
        
    }
}
