import java.util.*;
/**
 * Write a description of class Battle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Battle
{
    private Hero hero;
    private Monster monster;
    private int turnNumber;
    private GUI battleGUI;
    public Battle(Hero hero, Monster monster)
    {
        //First, we must initilize the Battle.
        battleGUI = new GUI();
        //battleUI.setupBattleMode();
        battleGUI.printLn("You are under attack by a " + monster.getName());
        turnNumber = 1;
        turn();
    }
    private int getTurnNumber()
    {
        return turnNumber;
    }
    
    
    public String turn()
    {
        int returnInt = 0;
        battleGUI.printLn("------------ TURN " + turnNumber + " ------------");
        battleGUI.printLn("What do you do?");
        battleGUI.printLn("[1] Attack            [2] Item");
        battleGUI.printLn("[3] Run               [4] Magika");
        battleGUI.printLn("Type [1-4] Below:");
        int choice = 0;
        if (choice == 1)
        {
            returnInt = Attack(1);
            if (returnInt == 1)
            {
                //The Attack Has Hit But Not Slain
            }
            if (returnInt == 2)
            {
                //The Attack Has Hit And Slain
            }
        } else if (choice == 2)
        {
            returnInt = Item();
            if (returnInt == 1)
            {
                //The Item Has Been Used
            }
            if (returnInt == 2)
            {
                //You Cannot Use That Item
            }
            if (returnInt == 3)
            {
                //You don't have an item in that slot.
            }
        } else if (choice == 3)
        {
            returnInt = Run();
            if (returnInt == 1)
            {
                //You have managed to run away.
            }
            if (returnInt == 2)
            {
                //You have failed to escape
            }
            if (returnInt == 3)
            {
                //You cannot back down from this fight
            }
        } else if (choice == 4)
        {
            returnInt = Magika();
            if (returnInt == 1)
            {
                //You have used the Magick
            }
            if (returnInt == 2)
            {
                //You do not have a Magick in that Slot.
            }
            if (returnInt == 3)
            {
                //You cannot use that Magick
            }
        }
        String errorMessage = error(returnInt);
        if (errorMessage != null)
        {
            battleGUI.printLn(errorMessage);
        } else
        {
        }
        return null;
    }
    
    //ATTACK, ITEM, RUN, MAGIKA
    
    public int Attack(int type)
    {
        //ATTACK TYPES: 1 = HERO -> MONSTER, 2 = MONSTER -> HERO
        int returnInt = 404;
        if (type == 1)
        {
            returnInt = monster.takeDamage(hero.getDamage());
            if (returnInt == 1)
            {
                //HIT NOT SLAIN
                battleGUI.printLn("[->] YOU DEAL " + hero.getDamage() + " TO " + monster.getName()); 
            }
            if (returnInt == 2)
            {
                //SLAIN
                battleGUI.printLn("[++] YOU HAVE SLAIN " + monster.getName());
            }
        } else if (type == 2)
        {
            returnInt = hero.takeDamage(monster.getDamage());
            if (returnInt == 1)
            {
                //HIT NOT SLAIN
                battleGUI.printLn("[<-] YOU RECIEVE " + monster.getDamage() + " FROM " + monster.getName());
            }
            if (returnInt == 2)
            {
                //SLAIN
                battleGUI.printLn("[--] YOU HAVE BEEN SLAIN BY " + monster.getName());
            }
        }
        return returnInt;
    }
    
    public int Item()
    {
        return 505;
    }
    public int Run()
    {
        return 505;
    }
    public int Magika()
    {
        return 404;
    }
    
    public String error(int returnInt)
    {
        String returnString = "";
        if (returnInt > 100)
        {
            if (returnInt == 404)
            {
                returnString = "[ERROR CODE: 404] Invalid parameter entered.";
            }
            if (returnInt == 505)
            {
                returnString = "[ERROR CODE: 505] That feature is still under work.";
            } else  
            {
                returnString = "[ERROR CODE: ???] We don't know what went wrong, we're working on it.     :(";
            }
            return returnString;
        }
        return null;
    }
    }


