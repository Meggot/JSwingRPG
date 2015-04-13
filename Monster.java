import java.util.*;
/**
 * Write a description of class Monster here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Monster
{
    // instance variables - replace the example below with your own
    private double attackDamage;
    private double monsterHealth;
    private int monsterLevel;
    private ArrayList<String> md = new ArrayList<>();
    private String monsterName;

    /**
     * Constructor for objects of class Monster
     */
    public Monster(String Breed, int monsterLevel, int monsterType)
    {
        // initialise instance variables
        this.monsterName = monsterNameGenerator(Breed);
        this.monsterLevel = monsterLevel;
        double AttackMultiplier = 0;
        double HealthMultiplier = 0;
        if (monsterType == 0)
        {
            AttackMultiplier = 1.5;
            HealthMultiplier = 1.5;
        }
        if (monsterType == 1)
        {
            AttackMultiplier = 1.75;
            HealthMultiplier = 1.25;
        }
        if (monsterType == 2)
        {
            AttackMultiplier = 1.25;
            HealthMultiplier = 1.75;
        }
        this.attackDamage = 0 + monsterLevel * AttackMultiplier;
        this.monsterHealth = 10 + monsterLevel * HealthMultiplier;
    }
    
    public String getAsString()
    {
        String returnString = monsterName + "  [LEVEL/DAMAGE/HEALTH] : [" + monsterLevel + "/" + attackDamage + "/" + monsterHealth + "]";
        return returnString;
    }
    
     public double getDamage()
    {
        return attackDamage;
    }
    public String getName()
    {
        return monsterName;
    }
    public int takeDamage(double damage)
    {
        monsterHealth = monsterHealth - damage;
        if (monsterHealth == 0)
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
        //dosomething
    }
    
    public String monsterNameGenerator(String Animal)
    {
        md.add("Green Eyed");
        md.add("Blue Eyed");
        md.add("Yellow Eyed");
        md.add("Red Eyed");
        md.add("Blood Splattered");
        md.add("Disease Ridden");
        md.add("Tormented");
        String returnString = "";
        Random rand = new Random();
        int  n = rand.nextInt(50) + 1;
        int roller =(int) Math.ceil(n / md.size());
        returnString = md.get(roller) + " " + Animal;
        return returnString;
    }
}
