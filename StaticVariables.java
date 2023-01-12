
public class StaticVariables
{
    private static int objID = 0;
    private static int itemID = 0;
    private static int heroID = 0;
    private static int monsterID = 0;
    private static StaticVariables instance;

    private StaticVariables()
    {
    }
    
    public static StaticVariables getInstance()
    {
        if (instance != null)
        {
            return instance;
        } else {
            instance = new StaticVariables();
            return instance;
        }
    }
    
    public int nextObjID()
    {
        objID++;
        return objID;
    }
    
    public int nextItemID()
    {
        itemID++;
        return itemID;
    }
    
    public int nextHeroID()
    {
        heroID++;
        return heroID;
    }
    
    public int nextMonsterID()
    {
        monsterID++;
        return monsterID;
    }
}
