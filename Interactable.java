
/**
 * Write a description of class Interactable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Interactable extends Item
{
	private String interactableText;
    public Interactable(String interactableName, int itemID)
    {
        super(interactableName, itemID);
    }
    
    public String returnInteractableText()
    {
		String returnString = interactableText;
		return returnString;
	}
}
