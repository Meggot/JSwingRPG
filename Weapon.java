import java.util.*;

/**
 * Write a description of class Weapon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Weapon extends Item
{
	
	private String description;
	double attackDamage;
	//private ArrayList<String> stats = new ArrayList<>();
	
    public Weapon(String name,int itemId, int[] stats, String desc)
    {
        super(name, itemId);
        this.description = desc;
    }
    
    public void generateID()
    {
		
	}
    public double getAttack()
    {
		return attackDamage;
	}
}
