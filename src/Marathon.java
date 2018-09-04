import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Marathon extends JFrame
{
    private ArrayList<Person> allParticipants= new ArrayList<Person>();
    private ArrayList<Person> participantsByName= new ArrayList<Person>();
    private ArrayList<Person> participantsByAge= new ArrayList<Person>();
    private ArrayList<Person> participantsByTime= new ArrayList<Person>();
    
    private ArrayList<Person> lastArray= new ArrayList<Person>();
    private JTextArea display;
    
    public Marathon()
    {
        super("DSV Kista Marathon");
        setLayout(new BorderLayout());
        
        JPanel panelNorth= new JPanel();
        panelNorth.add(new JLabel("DSV Kista Marathon"));
        add(panelNorth, BorderLayout.NORTH);
        
        display= new JTextArea();
        display.setEditable(false);
        JScrollPane scroll= new JScrollPane();
        add(scroll);
        display.setText(someParticipants());
        add(display);
        
        JPanel panelEast= new JPanel();
        panelEast.add(new JLabel("Sorting"));
        panelEast.setLayout(new BoxLayout(panelEast, BoxLayout.Y_AXIS));
        ButtonGroup grupp= new ButtonGroup();
        JRadioButton showPerStartNum= new JRadioButton("Startnr", true);
        showPerStartNum.addActionListener(new ShowByStartNum());
        grupp.add(showPerStartNum);
        panelEast.add(showPerStartNum);
        JRadioButton showPerName= new JRadioButton("Name");
        showPerName.addActionListener(new ShowByName());
        grupp.add(showPerName);
        panelEast.add(showPerName);
        JRadioButton showPerAge= new JRadioButton("Age");
        showPerAge.addActionListener(new ShowByAge());
        grupp.add(showPerAge);
        panelEast.add(showPerAge);
        JRadioButton showPerTime= new JRadioButton("Time");
        showPerTime.addActionListener(new ShowByTime());
        grupp.add(showPerTime);
        panelEast.add(showPerTime);
        add(panelEast, BorderLayout.EAST);
        
        JPanel panelSouth= new JPanel();
        JButton newPersonButton= new JButton("New Participant");
        newPersonButton.addActionListener(new NewParticipantLyss());
        panelSouth.add(newPersonButton);
        JButton timeButton= new JButton("Time");
        timeButton.addActionListener(new TimeLyss());
        panelSouth.add(timeButton);
        add(panelSouth, BorderLayout.SOUTH);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350,400);
        setLocation(400,300);
        setVisible(true);
    }
    
    public void addParticipants(Participant ny)
    {
        allParticipants.add(ny);
        
        int pos= Collections.binarySearch(participantsByName, ny, new NameComparator());
        if(pos < 0)
        {    pos= -pos -1; }
        participantsByName.add(pos, ny);
        
        int posi= Collections.binarySearch(participantsByAge, ny, new AgeComparator());
        if(posi < 0)
        {    posi= -posi -1; }
        participantsByAge.add(posi, ny);
    }
    
    private void refresh(ArrayList<Person> list)
    {
        display.setText("");
            for(Person p : list)
                display.append(p.toString()+"\n"); 
    }
    
    
    private class NewParticipantLyss implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ave) 
        {
            new Registration(Marathon.this);
            refresh(lastArray);
        }
    }
    
    private class TimeLyss implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ave) 
        {
            new Time(Marathon.this);
            refresh(lastArray);
        }
    }
    
    
    private class ShowByStartNum implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ave) 
        { 
            lastArray= allParticipants;
            refresh(lastArray); 
        }        
    }
    
    private class ShowByName implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {   
            lastArray= participantsByName;
            refresh(lastArray); 
        }        
    }
    
    private class ShowByAge implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ave) 
        {   
            lastArray=participantsByAge;
            refresh(lastArray); 
        }       
    }
    
    private class ShowByTime implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ave) 
        {
            lastArray=participantsByTime;
            refresh(lastArray);
        }
    } 
    
    
    public class NameComparator implements Comparator<Person>
    {
        @Override
        public int compare(Person p1, Person p2) 
        { return p1.getName().compareTo(p2.getName()); }
    }
     
    public class AgeComparator implements Comparator<Person>
    {
        @Override
        public int compare(Person p1, Person p2) 
        { return p1.getAge() - p2.getAge(); }
    }
      
    
    public ArrayList getList() { return allParticipants; }
    
    public ArrayList getTimeList() { return participantsByTime; }
    
    
    private String someParticipants()
    {
        String str=""; 
        addParticipants(new Participant("Haile", "Etiopien", 35, 1));
        addParticipants(new Participant("Paul", "Kenya", 39, 2));
        addParticipants(new Participant("Irina", "Ryssland", 48, 3));
        addParticipants(new Participant("Paula", "UK", 30, 4));
        addParticipants(new Participant("Mizuki", "Japan", 48, 5));
        addParticipants(new Participant("Catherine", "Marocko", 32, 6));
        addParticipants(new Participant("Jeff", "USA", 62, 7));
        addParticipants(new Participant("Kjell-Erik", "Sverge", 28, 8));
        addParticipants(new Participant("Alfred", "Finland", 27, 9));
        addParticipants(new Participant("David", "Spanien", 51, 10));
         
        for(Person p : allParticipants)
        { str+= p.toString()+"\n";  }
        
        lastArray= allParticipants;
        return str;
    }
    
    public static void main(String[] args)
    {   new Marathon(); }
}