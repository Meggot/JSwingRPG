import java.util.*;
/**
 * Write a description of class Battle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Battle
{
    private Hero[] heroes = new Hero[5];
    private String[] heroCommands;
    private Monster[] monsters = new Monster[5];
    private String[] monsterCommands;
    private int turnNumber;
    private boolean gameState;
    
    public Battle(Hero leadHero, Monster leadMonster)
    {
        turnNumber = 0;
        addHero(leadHero);
        addMonster(leadMonster);
        gameState = true;
    }
    
    public String strike(int a,int b)
    {
		String returnString = "";
		try {
			if (getHero(a) != null)
			{
				Hero attacker = getHero(a);
				defender = getMonster(b);
				defender.recieveStrike(attacker.getDamage());
				returnString = "> " + attacker.getName() + " strikes " + defender.getName() + " for " + attacker.getDamage();
			} else 
			{
				Monster attacker = getMonster(a);
				Hero defender = getHero(b);
				defender.recieveStrike(attacker.getDamage());
				returnString = "< " + attacker.getName() + " strikes " + defender.getName() + " for " + attacker.getDamage();
			}
		} catch (NullPointerException e)
		{
			returnString = "ERROR";
		}
		return returnString;
	}
    
    public void turn()
    {
		//this will initiate all the commands stored and run them based on character speed.
	}
    
    public boolean isOver()
    {
		return gameState;
	}
    
    private void registerCommandHero(int heroId, String command)
    {
		int i = 0;
		for (Hero tempHero : heroes)
		{
			if (tempHero.getId() == heroId)
			{
				heroCommands[i] = command;
				break;
			}
			i++;
		}
	}
    
    private void removeMonster(int groupIndex)
    {
		monsters[groupIndex] = null;
	}
	
	private void removeHero(int groupIndex)
	{
		heroes[groupIndex] = null;
	}
    
    private void addHero(Hero hero)
    {
		int i = 0;
		for (Hero tempHero : heroes)
		{
			if (tempHero == null)
			{
				heroes[i] = hero;
				break;
			}
			i++;
		}
	}
	
	private void addMonster(Monster monster)
	{
		int i = 0;
		for (Monster tempMonster : monsters)
		{
			if (tempMonster == null)
			{
				monsters[i] = tempMonster;
				break;
			}
			i++;
		}
	}
	
	private Monster getMonter(int monsterID)
	{
		for (Monster tempMonster : monsters)
		{
			if (tempMonster.getId() == monsterID)
			{
				return tempMonster;
			}
		}
		return null;
	}
	
	private Hero getHero(int heroID)
	{
		for (Hero tempHero : heroes)
		{
			if (tempHero.getId() == heroID)
			{
				return tempHero;
			}
		}
		return null;
	} 
	
    public String getAlliedPane()
    {
		int i = 0;
		String returnString = "";
		for (Hero tempHero : heroes)
		{
			if (tempHero != null)
			{
				returnString = returnString + heroes[i].getId() + " " + heroes[i].getName() + "/n";
			}
			i++;
		}
		
		return returnString;
	}
    
    public String getEnemyPane()
    {
		int i = 0;
		String returnString = "";
		for (Monster tempMonster : monsters)
		{
			returnString = returnString + monsters[i].getId() + " " + monsters[i].getName() + "/n";
		}
		return returnString;
	}
	
    public int getTurnNumber()
    {
        return turnNumber;
    }
    
   }

