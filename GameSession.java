
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/** GameSession will load all the information about the current game playing.
 * It will contain all the Regions, and the locations in those regions.
 * 
 * TOFIX:
 * -Cannot go to Locations, only Regions
 * -Commandline is v small on load in, fixes itself after a while
 * 
 * TODO:
 * -Load Regions and Locations from a text file, as well as a way to create
 * regions and locations with descriptions easily (commandline interface?)
 * -Menu button functionality.
 * -Skills button
 * -Party interface to show players/parties health
 * -Battle functionality
 * -reduce coupiling
 **/
public class GameSession extends GUI
{
    private ArrayList<Region> regions = new ArrayList<>();
    private Game game;
    String availableCommands = "";

    //Action Listeners
    private newGame newGameListener = new newGame();
    private newGameProcess newGamePListener = new newGameProcess();
    private commandInput commandInputListener = new commandInput();
    private viewInventory inventoryListener = new viewInventory();
    private viewSkills viewSkillsListener = new viewSkills();
    private lootContainer commandLootListener = new lootContainer();
    private battleInput battleInputListener = new battleInput();
    
    //static variables
    private StaticVariables idVariables = new StaticVariables();
    //objects
    private objContainer tempContainer;
    private Battle activeBattle;
    
    public GameSession()
    {
        print("Welcome to Bytes and Pieces, please type your heroes name below in the command line..");
        availableCommands = "'travel <location/region name>', 'fight <target>', 'open <container>', 'use <item>', 'do <interactable>', 'map', 'go <gateway>'";
        loadMap();
        getCommandButton().addActionListener(newGamePListener);
        getInventoryButton().addActionListener(inventoryListener);
        getSkillsButton().addActionListener(viewSkillsListener);
    }
    
    public void battleTurn()
    {
		String allyInfo = activeBattle.getAlliedPane();
		String enemyInfo = activeBattle.getEnemyPane();
		printLocationPanels(allyInfo, enemyInfo);
		if (activeBattle.isOver())
		{
			
			//gameOver
		}else {
		print("<> You have initiated a battle!");
		printLn(activeBattle.getTurnNumber() + " \n" + availableCommands);
	}
	}
    
    public void initiateBattle(String objId)
    {
        try { 
            Integer.parseInt(objId); 
        } catch(NumberFormatException e) { 
            printLn("~ You must input the monsters object index");
            return;
        } catch(NullPointerException e) {
        }
        int monsterId = Integer.parseInt(objId);
        Location currentLoc = game.getCurrentLocation();
        if (currentLoc.isObject(monsterId))
        {
            locObject objTemp = currentLoc.getLocObj(monsterId);
            if (objTemp.getType() == 2)
            {
                objMonster Monster = (objMonster)objTemp;
                activeBattle = new Battle(game.getHero(), Monster.getMonster());
                availableCommands = "strike <target>, defend <target>, use <inventory index>, cast <spell>";
                printLn("< You have initiated a battle!");
                getCommandButton().removeActionListener(commandInputListener);
                getCommandButton().addActionListener(battleInputListener);
                battleTurn();
            } else
            {
                printLn("~ You cannot fight that..");
            }
        } else {
            printLn("~ That is not a valid id..");
        }
    }

    public void createPartyPanel()
    {
        //include here loop to put in party members too
        setPartyLabel(game.getPartyPane(), 0);
        //setPartyLabel("EMPTY", 1);
        //setPartyLabel("EMPTY", 2);
        //setPartyLabel("EMPTY", 3);
        reloadPartyPanel();
    }

    public void addRegion(String regionName, String startingLocation)
    {
        Region newRegion = new Region(regionName, startingLocation);
        regions.add(newRegion);
    }

    public void turn() //This method will run every turn the player makes (a turn is an action completed)
    {
        createPartyPanel();
        setDefault();
        clearCmdLine();
        Location currentLocation = game.getCurrentLocation();
        Region currentRegion = game.getCurrentRegion();
        printLocationPanels("Region: " + currentRegion.getName() + "\n" + currentLocation.getInfoDisplay(), currentLocation.getObjectsDisplay());
        //print(currentLocation.getDesc());
        //printLn(availableCommands);
    }

    public void travel(String placeName)
    {
        if (isRegion(placeName))
        {
            travelRegion(placeName);
        } else if (isLocation(game.getCurrentRegion().getName(), placeName))
        {
            travelLocation(placeName);
        }
        turn();
    }

    public boolean isLocation(String regionName, String locationName)
    {
        if (isRegion(regionName))
        {
            Region region = getRegion(regionName);
            if (region.isLocation(locationName))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isRegion(String regionName)
    {
        for (Region tempRegion : regions)
        {
            if (tempRegion.getName().equals(regionName))
            {
                return true;
            }
        }
        return false;
    }

    //Active METHODS
    
    public void useItem(int inventoryIndex)
    {
        Item item = game.getHero().getItem(inventoryIndex);
    }

    public void openContainer(String containerIdString)
    {
        try { 
            Integer.parseInt(containerIdString); 
        } catch(NumberFormatException e) { 
            printLn("~ You must input the object ID number");
            return;
        } catch(NullPointerException e) {
        }
        int containerId = Integer.parseInt(containerIdString);
        Location currentLocation = game.getCurrentLocation();
        if (currentLocation.isObject(containerId))
        {
            locObject tempObject = currentLocation.getLocObj(containerId);
            if (tempObject.getType() == 0) //is a container and is here.
            {
                objContainer container = (objContainer)tempObject;
                print("> You open the " + container.getName() + ".." +
                    "\n" + container.getContentsDisplay() +
                    "\n");
                printLn("~ 'lootall','loot <index>','close'");
                getCommandButton().removeActionListener(commandInputListener);
                getCommandButton().addActionListener(commandLootListener);
                tempContainer = container;
            } else {
                printLn("~ An object by that ID could not be found..");
            }
        } else
        {
            printLn("~ That object could not be opened");
        }
    }

    public void travelRegion(String regionName)
    {
        if (isRegion(regionName))
        {
            Region tempRegion = getRegion(regionName);
            game.setCurrentRegion(tempRegion);
            game.setCurrentLocation(tempRegion.getStartLocation());
            print("< You have travelled to the Region: " + regionName);
        } else
        {
            printLn("~ A region by that name '" + regionName + "' could not be found");
        }
    }

    public void travelLocation(String locationName) //validated by caller
    {
        Location tempLocation = game.getCurrentRegion().getLocation(locationName);
        game.setCurrentLocation(tempLocation);
        printLn("< You have travelled to the Location: " + locationName);
        //printLn("~ A location by that name '" + locationName +"' could not be found");
    }

    public void loadMap()
    {
        addRegion("dustwallow" , "overgrowth");
        addRegion("mines", "landing");
        addRegion("catacombs", "atrium");
        addRegion("cottage", "hall");
        Region cottage = getRegion("cottage");
        Region dustwallow = getRegion("dustwallow");
        //DUSTWALLOW
        Location overgrowth = dustwallow.getLocation("overgrowth");
        overgrowth.setDesc("The cottage is vast, and contains many fauna scattered around " +
            "\nits mossy visage. Each blade of grass swims with termites and little scarab " +
            "\nbeetles feasting on the summer dew");
        dustwallow.addLocation("marshes", 0);
        dustwallow.addLocation("gato", 0);
        //COTTAGE
        Location hall = cottage.getLocation("hall");
        cottage.addLocation("garden", 0);
        Location garden = cottage.getLocation("garden");
        objMonster monster = new objMonster("Berkly");
        monster.getMonster().setId(idVariables.nextMonsterID());
        garden.addObject(monster);
        hall.setDesc("A fireplace crackles ambiently on the east wall, " +
            " that is adorned sporadically with many shelves and a circular window" + 
            " that lets in the morning sunlight, catching the light smoke that" +
            " forever hangs in the air. " +
            " In the center of the room there is a grand oak table that seats 6 with" +
            " each tableplace occupanied by a modest armchair, each one bearing " +
            " mead stains and light scuffs from stray swords. The room is untidy" +
            " and books are strewn around the place along with wooden plates" +
            " and month-old leftovers.");
        garden.setDesc("The garden is a large fenced region surrounded by a deep, " +
        " penetrating forest of pine trees and conifers, with a fierce unforigving " +
        " undegrowth. The entire estate shares this tall wooden farming fence entwined " +
        " with iron mesh wire and ivy leaves. The garden itself features 3 alotments " +
        " that have clearly been worked on. They are surrounded by various buckets and tools. " +
        " An animal trough and a dishelved dog house sit in the far corner, shaded by an  " +
        " ancient great oak tree with amber leaves it stands alone as the only tree within" +
        " your walls.");
        objContainer chest = new objContainer("Bookcase");
        Item newdf = new Item("A Tale of Two Fish", idVariables.nextItemID());
        chest.addItem(newdf);
        Item ds = new Item("Yohan's Story", idVariables.nextItemID());
        chest.addItem(ds);
        objContainer chest2 = new objContainer("Shelves");
        objGateway doorway = new objGateway("Grand Walkway", "hall", "garden");
        hall.addObject(chest);
        hall.addObject(chest2);
        hall.addObject(doorway);
    }
    
    public void goObject(String objectIDString)
    {
        try { 
            Integer.parseInt(objectIDString); 
        } catch(NumberFormatException e) { 
            printLn("~ You must input the object ID number");
            return;
        } catch(NullPointerException e) {
        }
        int objectID = Integer.parseInt(objectIDString);
        Location currentLocation = game.getCurrentLocation();
        if (currentLocation.isObject(objectID))
        {
            locObject tempObject = currentLocation.getLocObj(objectID);
            if (tempObject.getType() == 1) //Is A Doorway
            {
                objGateway tempGateway = (objGateway)tempObject;
                String destinationName = tempGateway.goThrough(currentLocation.getName());
                printLn("> You go through the " + tempGateway.getName());
                travel(destinationName);
            } else {
                printLn("~ You cannot use the 'go' command on that object");
                return;
            }
        }
            
    }

    public Region getRegion(String regionName)
    {
        for (Region tempRegion : regions)
        {
            if (tempRegion.getName().equals(regionName))
            {
                return tempRegion;
            }
        }
        return null;
    }

    public String viewRegionsDisplay()
    {
        String returnString = "";
        int i = 0;
        for (Region tempRegion : regions)
        {
            returnString = returnString + "\n" + i + ": " + tempRegion.getName();
            i++;
        }
        return returnString;
    }

    public String viewLocationDisplay()
    {
        return game.getCurrentRegion().getLocations();
    }

    public String viewLocationsDisplay(String regionName)
    {
        if (isRegion(regionName))
        {
            Region tempRegion = getRegion(regionName);
            return tempRegion.getLocations();
        } else {
            return null;
        }
    }
	private class battleInput implements ActionListener
	{
		public void actionPerformed(ActionEvent e) throws NullPointerException
		{
			CommandParsee cmdInput = new CommandParsee(getCommandText());
			printLn("$ " + cmdInput.getCommand());
			if (cmdInput.getBlock(0).equals("strike"))
			{
				
			} else if(cmdInput.getBlock(0).equals("defend"))
			{
				
			} else if(cmdInput.getBlock(0).equals("use"))
			{
				
			} else if(cmdInput.getBlock(0).equals("cast"))
			{
				
			} else 
			{
				//bad command
			}
		}
			
		}
    private class commandInput implements ActionListener
    {
        public void actionPerformed(ActionEvent e) throws NullPointerException
        {
            CommandParsee cmdInput = new CommandParsee(getCommandText());//loaded string parsee
            printLn("$ " + cmdInput.getCommand());
            if (cmdInput.getBlock(0).equals("travel"))
            {
                travel(cmdInput.getBlock(1));
            } else if (cmdInput.getBlock(0).equals("fight"))
            {
                initiateBattle(cmdInput.getBlock(1));
            } else if (cmdInput.getBlock(0).equals("open"))
            {
                openContainer(cmdInput.getBlock(1));
            } else if (cmdInput.getBlock(0).equals("use"))
            {
                
            } else if (cmdInput.getBlock(0).equals("do"))
            {
                
            } else if (cmdInput.getBlock(0).equals("map"))
            {
                print("> You open your map, and take notice of the key locations.." +
                    "\nRegions: " +
                    "\n" +viewRegionsDisplay() +
                    "\n" +
                    "\nNearby Locations:" +
                    "\n" + viewLocationDisplay());
            } else if(cmdInput.getBlock(0).equals("go"))
            {
                goObject(cmdInput.getBlock(1));
            } else if(cmdInput.getBlock(0).equals("help"))
            {
                print("Available commands: " + availableCommands);
            } else {
                printLn("~ That command is not recognized..");
                printLn(availableCommands);
            }
        }
    }

    private class viewInventory implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            printStat(game.getHero().getInventoryDisplay());
        }
    }

    private class viewSkills implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            printStat(game.getHero().getSkillsDisplay());
        }
    }

    private class newGameProcess implements ActionListener
    {
        public void actionPerformed(ActionEvent e) //newGamePListener
        {
            getCommandButton().removeActionListener(newGamePListener);
            getCommandButton().addActionListener(commandInputListener);
            print("~ Welcome to Bytes and Pieces, a project created by: " +
                "\n~ Meggot. Version = V2.1" +
                "\n");
            printLn("In order to navigate this world, you must issue commands to the" +
                    "\nsystem through your useful and limitless cmd line! Type" +
                    "\n'travel cottage' to travel to your home");
            game = new Game(getCommandText());
            game.getHero().setId(idVariables.nextHeroID());
            setDefault();
        }
    }

    private class newGame implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            print("~ Welcome to Bytes and Pieces, a project created by: " +
                "\n~ Meggot. Version = V2.1" +
                "\"Enter the Heroes name below:");
            getCommandButton().addActionListener(newGamePListener);
        }
    }

    private class lootContainer implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            CommandParsee cmdInput = new CommandParsee(getCommandText());//loaded string parsee
            printLn("$ " + cmdInput.getCommand());
            if (cmdInput.getBlock(0).equals("lootall"))
            {
                int i = 0;
                int itemAmt = tempContainer.itemAmount();
                printLn("< You take " + itemAmt + " items from the " + tempContainer.getName());
                while (i < itemAmt)
                {
                    Item item = tempContainer.lootItem(0);
                    printLn(" < " + item.getName());
                    game.getHero().addItem(item);
                    i++;
                }
                turn();
                return;
            } else if (cmdInput.getBlock(0).equals("loot"))
            {
                try {
                    int itemId = Integer.parseInt(cmdInput.getBlock(1));
                    Item item = tempContainer.lootItem(itemId);
                    game.getHero().addItem(item);
                    printLn("< You take '" + item.getName() + "'" +//and quantity
                    "\n" +
                    "\n>" + tempContainer.getName() + "" +
                    "\n" + tempContainer.getContentsDisplay() +
                    "\n");
                    printLn("~ 'lootall','loot <index>','close', 'put <inventory position>'");
                    turn();
                    return;
                }catch (NumberFormatException er)
                {
                    printLn("~ You must input a number..");
                }catch (NullPointerException er2)
                {
                    printLn("~ No item exists with that id..");
                }
            } else if (cmdInput.getBlock(0).equals("close"))
            {
                printLn("> You close the " + tempContainer.getName());
            } else if (cmdInput.getBlock(0).equals("put"))
            {
                try {
                    int inventoryIndex = Integer.parseInt(cmdInput.getBlock(1));
                    Item inventoryItem = game.getHero().getItem(inventoryIndex);
                    game.getHero().removeInventory(inventoryIndex);
                    tempContainer.addItem(inventoryItem);
                    printLn("> You put '" + inventoryItem.getName() + "' into the " + tempContainer.getName() +//and quantity
                    "\n" +
                    "\n>" + tempContainer.getName() + "" +
                    "\n" + tempContainer.getContentsDisplay() +
                    "\n");
                    printLn("~ 'lootall','loot <index>','close', 'put <inventory position>'");
                    turn();
                    return;
                } catch (NumberFormatException er4)
                {
                    printLn("~ You must input a number! (The position of the item in your inventory)");
                } catch (NullPointerException er3)
                {
                    printLn("~ An item with that inventory position could not be found");
                }
            } else {
                printLn("~ That command is not recognized..");
            }
            getCommandButton().addActionListener(commandInputListener);
            getCommandButton().removeActionListener(commandLootListener);
            tempContainer = null;
            turn();
        }
    }
}
