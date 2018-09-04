import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Time extends JFrame
{
    private JTextField startNumField, timeField;
    private int startNum;
    private double time;
    private TimeComparator timeCmp= new TimeComparator();
    private Marathon m;
    
    public Time(Marathon m)
    {
        this.m= m;
                
        JPanel form= new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        
        JPanel question1= new JPanel();
        question1.add(new JLabel("Startnr:"));
        startNumField= new JTextField(5);
        question1.add(startNumField);
        form.add(question1);
        
        JPanel question2= new JPanel();
        question2.add(new JLabel("Time:"));
        timeField= new JTextField(6);
        question2.add(timeField);
        form.add(question2);
        
        for(;;)
        {
            try
            {
                boolean fieldEmpty=true;
                while(fieldEmpty)
                {
                    int option= JOptionPane.showConfirmDialog(m, form, "Register new participant's time", JOptionPane.OK_CANCEL_OPTION);

                    if(option == JOptionPane.OK_OPTION)
                    {
                        startNum= Integer.parseInt(startNumField.getText());
                        time= Double.parseDouble(timeField.getText());
                    
                        if(startNum > m.getList().size() || startNum <1) 
                        { JOptionPane.showMessageDialog(m,"The participant whith this startnr doesn't exist!","Wrong", JOptionPane.ERROR_MESSAGE); }
                        else 
                        { fieldEmpty=false; }
                        
                        Participant p;
                        for(int i=0; i<m.getList().size(); i++)
                        {
                            p= (Participant)m.getList().get(i);
                            if(startNum == p.getStartNum())
                            {    
                                if(p.getTime() == 00.00)
                                { 
                                    p.setTime(time);
                                    
                                    int position= Collections.binarySearch(m.getTimeList(), p, timeCmp);
                                    if(position < 0)
                                    {    position= -position -1; }
                                    
                                    m.getTimeList().add(position, (Person)p);
                                    break; //for in the participants list
                                }
                                else
                                {
                                    int opcion=JOptionPane.showConfirmDialog(m,"This participant has already a registered time ("+p.getTime()+").\nDo you want to change it anyway?",
                                       "New time", JOptionPane.YES_OPTION);                            
                                
                                    if(opcion == JOptionPane.YES_OPTION)
                                    { 
                                        p.setTime(time); 
                                        Collections.sort(m.getTimeList(), timeCmp);
                                        break; //for in the participants list
                                    }
                                    else //no_option
                                    { return; }
                                }
                            }//if
                        }//for arrayList.size()
                    }//if
                    else //CANCEL_OPTION
                    { return; }
               }//while
               break;//
            }//while
            catch(NumberFormatException e)
            { JOptionPane.showMessageDialog(m,"Error input at startnr's field or time's field","Wrong", JOptionPane.ERROR_MESSAGE); }
        }//for
    }//constructor
    
    public class TimeComparator implements Comparator<Participant>
    {
        @Override
        public int compare(Participant p1, Participant p2) 
        { return (int) (p1.getTime() - p2.getTime()); }  
    }
}