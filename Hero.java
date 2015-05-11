import java.util.*;

/**
 * Write a description of class Hero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hero
{
    // instance variables - replace the example below with your own
    private String heroName;
    private double heroHealth;
    private int heroId;
    private int experiencePoints;
    private int level;
    private double attackDamage;
    private int inventorySize = 10;
    private ArrayList<Item> inventory = new ArrayList<>();

    /**
     * Constructor for objects of class Hero
     */
    public Hero(String name, int level)
    {
        // initialise instance variables
        heroHealth = 10;
        this.level = level;
        this.heroName = name;
        attackDamage = 2;
    }
    
    public void setId(int id)
    {
		 heroId = id;
	}
	
    public String getName()
    {
        return heroName;
    }
    
    public void addItem(Item item)
    {
        if (inventory.size() < inventorySize)
        {
            inventory.add(item);
        }
    }
    
    public int getId()
    {
		return heroId;
	}
    
    public void removeInventory(int invId)
    {
        inventory.remove(invId);
    }
    
    public String getInventoryDisplay()
    {
        String returnString = "";
        int i = 0;
        for (Item tempItem : inventory)
        {
            if (tempItem != null)
            {
                returnString = returnString + i + " - " +  tempItem.getDisplay()
                + "\n";
                i++;
            }
        }
        return returnString;
    }
    
    public Item getItem(int invPosition)
    {
        return inventory.get(invPosition);
    }
    
    public int getExperienceNeeded()
    {
        // EXP INCREMENTER
        // LEVEL 1: 5 + 5 = 20
        // LEVEL 10: 5 + 50 = 110
        // LEVEL 15: 5 + 75 = 160
        // LEVEL 50: 5 + 250 = 510
        int xpTemp = 5;
        xpTemp = xpTemp + (( level * xpTemp ) * 2);
        return xpTemp;
    }
    
    public double getDamage()
    {
        return attackDamage;
    }
    
    public String toString()
    {
        return "NAME: " + heroName + "[LEVEL/HEALTH/DAMAGE] : [" + level + "/" + heroHealth + "/" + attackDamage + "]";
    }
    
    public double getHealth()
    {
        return heroHealth;
    }
    
    public String getSkillsDisplay()
    {
        return "Hero Name:" + heroName +
        "\nLevel: " + level +
        "\nXP: " + experiencePoints +
        "\nHealth: " + heroHealth +
        "\nAttack: " + attackDamage;
    }
    
    public int takeDamage(double damage)
    {
        heroHealth = heroHealth - damage;
        if (heroHealth == 0)
        {
            //DEAD
            death();
            return 2;
        } else
        {
            return 1;
        }
    }
   
    public void death()
    {
        //DOSOMETHING
    }
    
    public String incrementXP(int xp)
    {
        experiencePoints = experiencePoints + xp;
        String returnString = "";
        if (experiencePoints >= getExperienceNeeded())
        {
            this.level ++;
            experiencePoints = 0;
            returnString = "Congratulations, you are now level " + level;
        } else
        {
            returnString = "You have gained " + xp + " EXP Points";
        }
        return returnString;
    }
}
