public class Item
{
    private String itemName;
    private int itemId;
    
    public Item(String name, int id)
    {
        this.itemName = name;
    }

    public String getName()
    {
        return itemName;
    }
    
    public String getDisplay()
    {
        return itemName;
    }
    
    public int getId()
    {
        return itemId;
    }
}
