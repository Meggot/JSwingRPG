
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.text.NumberFormat;

/**
 * Write a description of class UserIO here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GUI
{

    private JFrame myFrame = new JFrame("Bytes and Pieces"); //Main Panel //border
    private JPanel commandPanel = new JPanel(); //Stores the objects in the page_end section //flow
    private JPanel sidePanel = new JPanel(); //Stores the objects in the Line_End section //flow
    private JPanel mainPanel = new JPanel(); //stores the objects in the CENTER section //box
    private JPanel locationPanel = new JPanel(); //stores the objects to do with location //flow
    private JPanel partyPanel = new JPanel(); //stores the party information and hero placecards //flow
    private JPanel sideButtons = new JPanel();
    
    private JTextArea textLog = new JTextArea(); //text area to output logs
    private JTextArea locInfo = new JTextArea();
    private JTextArea locObjects = new JTextArea();
    private JScrollPane textLogPanel = new JScrollPane(textLog); //ScrollPane to contain textLog
    private JScrollPane locInfoPanel = new JScrollPane(locInfo);
    private JScrollPane locObjectsPanel = new JScrollPane(locObjects);
    private JTextArea textStats = new JTextArea(); //text area to output inventory + stats
    private JTextField commandLine = new JTextField(); //text field to take in commands
 
    private JLabel[] partyLabels = new JLabel[3];

    private JButton inventoryButton = new JButton("Inventory");
    private JButton skillsButton = new JButton("Skills");
    private JButton commandButton = new JButton("Send");
    
    /**
     * Constructor for objects of class UserIO
     */
    public GUI()
    { 
        myFrame.setLayout(new BorderLayout()); //Set panel layout managers
        commandPanel.setLayout(new FlowLayout());
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.PAGE_AXIS));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        locationPanel.setLayout(new FlowLayout());
        partyPanel.setLayout(new FlowLayout());
        sideButtons.setLayout(new FlowLayout());
        
        makeMenuBar();
        
        commandPanel.add(commandLine);
        commandPanel.add(commandButton);
        sideButtons.add(inventoryButton);
        sideButtons.add(skillsButton);
        sidePanel.add(sideButtons);
        sidePanel.add(textStats);
        locationPanel.add(locInfoPanel);
        locationPanel.add(locObjectsPanel);
        mainPanel.add(locationPanel);
        mainPanel.add(textLogPanel);
        
        locInfoPanel.setPreferredSize(new Dimension(500, 200));
        locObjectsPanel.setPreferredSize(new Dimension(500, 200));
        Font f = new Font("Times New Roman", Font.BOLD, 16);
        Font l = new Font("Times New Roman", Font.PLAIN, 16);
        textLog.setFont(f);
        locInfo.setFont(l);
        locObjects.setFont(l);
        locInfo.setVisible(true);
        locObjects.setVisible(true);
        locInfo.setEditable(false);
        locObjects.setEditable(false);
        locInfo.setLineWrap(true);
        
        textLog.setEditable(false);
        textLog.setLineWrap(true);
        textLog.setFocusable(true);
        commandLine.setBackground(Color.BLACK);
        commandLine.setForeground(Color.GREEN);    
        commandLine.setVisible(true);
        commandLine.setText("This_Is_Your_Command_Line"); //Makes the commandline visible from the start.
        myFrame.add(partyPanel, BorderLayout.PAGE_START);
        myFrame.add(commandPanel, BorderLayout.PAGE_END);
        myFrame.add(mainPanel, BorderLayout.CENTER);
        myFrame.add(sidePanel, BorderLayout.LINE_END);
        myFrame.setVisible(true);
        myFrame.pack();
        myFrame.setSize(800, 450);
        commandLine.setPreferredSize(new Dimension(250, 20));
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void makeMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        myFrame.setJMenuBar(menuBar);
        
        JMenu GameMenu = new JMenu("Game");
        JMenu CraftingMenu = new JMenu("Crafting");
        JMenu QuestsMenu = new JMenu("Quests");
        
        JMenuItem saveGame = new JMenuItem("Save");
        JMenuItem loadGame = new JMenuItem("Load");
        JMenuItem newGame = new JMenuItem("New");
        JMenuItem cheats = new JMenuItem("Cheats");
        
        GameMenu.add(saveGame);
        GameMenu.add(loadGame);
        GameMenu.add(newGame);
        GameMenu.add(cheats);
        
        menuBar.add(GameMenu);
        menuBar.add(CraftingMenu);
        menuBar.add(QuestsMenu); 
        
        //newGame.addActionListener(newGameListener);
    }
    //ACCESSOR METHODS
    
    public String getCommandText()
    {
        return commandLine.getText();
    }
    
    public JButton getInventoryButton()
    {
        return inventoryButton;
    }
    
    public JButton getSkillsButton()
    {
        return skillsButton;
    }
    
    public JButton getCommandButton()
    {
        return commandButton;
    }
    
    public JPanel getPartyPanel()
    {
        return partyPanel;
    }
    
    //PARTY METHODS
    public void setPartyLabel(String string, int pos)
    {
        JLabel newLabel = new JLabel(string, JLabel.CENTER);
        partyLabels[pos] = newLabel;
        reloadPartyPanel();
    }
    
    public void reloadPartyPanel()
    {
        partyPanel.removeAll(); 
        partyPanel.add(partyLabels[0]);
        //partyPanel.add(partyLabels[1]);
        //partyPanel.add(partyLabels[2]);
        //partyPanel.add(partyLabels[3]);
        myFrame.pack();
    }
    
    //PRINT METHODS
    public void print(String printText)
    {
        textLog.setText(printText);
        textLog.setVisible(true);
        textLogPanel.setVisible(true);
        myFrame.pack();
    }
    
    public void printLn(String line)
    {
        textLog.setText(textLog.getText() + "\n" + line);
        textLog.setVisible(true);
        textLogPanel.setVisible(true);
        myFrame.pack();
    }
    
    public void printStat(String printed)
    {
        textStats.setText(printed);
        textStats.setVisible(true);
        myFrame.pack();
    }
    
    public void printLocationPanels(String locationInfo, String locationObjects)
    {
        locInfo.setText(locationInfo);
        locObjects.setText(locationObjects);
        myFrame.pack();
    }
    //STATE METHODS
    public void clearCmdLine()
    {
        commandLine.setText(" ");
    }
    
    public void setDefault()//sets up the standard gui state
    {
        commandLine.setVisible(true);
        commandPanel.setVisible(true);
        sidePanel.setVisible(true);
        locationPanel.setVisible(true);
        partyPanel.setVisible(true);
        sideButtons.setVisible(true);
        textLog.setVisible(true);
        myFrame.pack();
    }
    
    public void setBattleState()//sets up the battle GUI state
    {
        
    }
}

