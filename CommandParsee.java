import java.util.*;

/**
 * Write a description of class CommandParsee here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CommandParsee
{
    private String command;
    private String[] cmdArray;
    private int maxLimit = 15;
    
    public CommandParsee(String commandString)
    {
        this.command = commandString.toLowerCase();
        this.cmdArray = parseeCommand(command);
    }

    public String[] parseeCommand(String command)
    {
        int i = 0;
        String[] commandChunks = new String[maxLimit];
        StringTokenizer stringTokens = new StringTokenizer(command);
        while(stringTokens.hasMoreTokens()) {
            commandChunks[i] = stringTokens.nextToken();
            i++;
        }
        return commandChunks;
    }
    
    public String getBlock(int wordPosition)
    {
        return cmdArray[wordPosition];
    }
    
    public String getCommand()
    {
        return command;
    }
}
