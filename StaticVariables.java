
public class StaticVariables
{
    private static int objID;
    private static int itemID;
    private static int heroID;
    private static int monsterID;

    public StaticVariables()
    {
        this.objID = 0;
        this.itemID = 0;
        this.heroID = 0;
        this.monsterID = 0;
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
