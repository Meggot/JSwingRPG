
/**
 * Write a description of class objMonster here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class objMonster extends locObject 
{
    private Monster monster;

    /**
     * Constructor for objects of class objMonster
     */
    public objMonster(String monsterName)
    {
        super(monsterName, 2);
        monster = new Monster(monsterName, 0, 0);
    }
    
    public String footnote()
    {
        return monster.getAsString();
    }
    
    public Monster getMonster()
    {
        return monster;
    }

}
