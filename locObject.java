
/**
 * Abstract class locObject - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class locObject
{
    private String name;
    private int type;
    private int id;
    public static int nextobjId = 0;

    public locObject(String objName, int objType) 
    {
        this.name = objName;
        this.type = objType;
        this.id = nextobjId;
        nextobjId++;
    }
    
    public int getType()
    {
        return type;
    }
    
    public int getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public abstract String footnote(); //This is the footnote to be included in lists and men
    
    public String getDisplayNote()
    {
        String typeName = "#";
        if (type == 0)
        {
            typeName = "Container";
        } else if (type == 1)
        {
            typeName = "Gateway";
        } else if (type == 2)
        {
            typeName = "Creature";
        }
        return id + ": [" + typeName + "]  " + footnote();
    }
}
