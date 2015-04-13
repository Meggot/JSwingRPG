
/**
 * Write a description of class Consumable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Consumable extends Item
{
    private String effectCode;
    
    public Consumable(String itemName, int itemID, String effectCode)
    {
        super(itemName, itemID);
    }
}
