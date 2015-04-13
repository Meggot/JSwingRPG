import java.util.*;
/**
 * Write a description of class objContainer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class objContainer extends locObject 
{
    private int maxSize = 10;
    private ArrayList<Item> contents = new ArrayList<>();
    
    public objContainer(String chestName)
    {
        super(chestName, 0);
    }
    
    public void setMaxSize(int size)
    {
        this.maxSize = size;
    }
    
    public int itemAmount()
    {
        return contents.size();
    }
    
    public void addItem(Item item)
    {
        if (contents.size() < maxSize)
        {
            contents.add(item);
        } else
        {
            System.out.println("##container is full"); //system message
        }
    }
    
    public boolean containsItem(int itemId)
    {
        for (Item tempItem : contents)
        {
            if (tempItem.getId() == itemId)
            {
                return true;
            }
        }
        return false;
    }
    
    public void addItemString(String itemName, int itemID)
    {
        Item newItem = new Item(itemName, itemID);
        contents.add(newItem);
    }
    
    public Item lootItem(int itemIndex)
    {
        try {
            Item tempItem = contents.get(itemIndex);
            contents.remove(itemIndex);
            return tempItem;
        } catch (IndexOutOfBoundsException e)
        {
            return null;
        }
    }
    
    public String footnote()
    {
        return getName() + " (" + contents.size() + "/" + maxSize + ")" ;
    }
    
    public String getContentsDisplay()
    {
        String returnString = "";
        int i = 0;
        for (Item tempItem : contents)
        {
            returnString = returnString + 
            "\n" + i + ": " + tempItem.getDisplay();
            i++;
        }
        return returnString;
    }
}
