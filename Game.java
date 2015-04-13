import java.util.*;
/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Game
{
    private Hero currentHero;
    private Region currentRegion;
    private Location currentLocation;

    public Game(String heroName)
    {
        Hero hero = loadHero(heroName);
        if (hero == null)
        {
            hero = new Hero(heroName, 0);
        }
        currentHero = hero;
    }

    public Hero loadHero(String heroName)
    {
        return null;
    }

    public void setCurrentLocation(Location location)
    {
        this.currentLocation = location;
    }

    public void setCurrentRegion(Region region)
    {
        this.currentRegion = region;
    }
    
    public Location getCurrentLocation()
    {
        return currentLocation;
    }
    
    public String getCurrentLocationName()
    {
        return currentLocation.getName();
    }
    
    public String getCurrentRegionName()
    {
        return currentRegion.getName();
    }
    
    public Region getCurrentRegion()
    {
        return currentRegion;
    }

    public Hero getHero()
    {
        return currentHero;
    }

    public String choiceCreator(String Choice1, String Choice2, String Choice3)
    {
        String returnString;
        returnString = "What do you do next?" +
        "/n|0. " + Choice1 +
        "/n|1. " + Choice2 +
        "/n|2. " + Choice3 +
        "/n| [0-2] Select an option." +
        "/n";
        return returnString;
    }

    public String choiceCreator(String Choice1, String Choice2)
    {
        String returnString;
        returnString = "What do you do next?" +
        "/n|0. " + Choice1 +
        "/n|1. " + Choice2 +
        "/n| [0-1] Select an option." +
        "/n";
        return returnString;
    }
    
    public String getPartyPane()
    {
        return currentHero.getName() + " HP: " + currentHero.getHealth() +
        "\n @" + currentLocation.getName();
    }
}
