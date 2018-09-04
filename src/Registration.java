import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Registration extends JFrame
{
    private JTextField nameField, countryField, ageField;
    private String name, country;
    private int age;
    private static int contador=10;
    private Marathon m;
    
    public Registration(Marathon m)
    {
        this.m= m;
        
        JPanel form= new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        
        JPanel showStartNum= new JPanel();
        showStartNum.add(new JLabel("Startnm "+ ++contador));
        form.add(showStartNum);
        
        JPanel question1= new JPanel();
        question1.add(new JLabel("   Name:"));
        nameField= new JTextField(10);
        question1.add(nameField);
        form.add(question1);
        
        JPanel question2= new JPanel();
        question2.add(new JLabel("Country:"));
        countryField= new JTextField(10);
        question2.add(countryField);
        form.add(question2);
        
        JPanel question3= new JPanel();
        question3.add(new JLabel("Age:"));
        ageField= new JTextField(2);
        question3.add(ageField);
        form.add(question3);
        
        for(;;)
        {
            try
            {
                boolean fieldEmpty=true;
                while(fieldEmpty)
                {
                    int option= JOptionPane.showConfirmDialog(m, form, "Register new participant", JOptionPane.OK_CANCEL_OPTION);
               
                    if(option == JOptionPane.OK_OPTION)
                    {
                        name= nameField.getText();
                        country= countryField.getText();
                        age= Integer.parseInt(ageField.getText());
                    
                        if(!name.equals("") || !country.equals(""))
                        {
                            fieldEmpty=false;
                            name= upperFirstCharacter(name);
                            country= upperFirstCharacter(country);
                            
                            m.addParticipants(new Participant(name, country, age, contador));
                        }
                        else
                        {    JOptionPane.showMessageDialog(m,"Name's field or country's field is empty","Wrong", JOptionPane.ERROR_MESSAGE); }
                    }
                    else //CANCEL_OPTION
                    { return; }  
                }//while
                break; //para el for
            }
            catch(NumberFormatException e)
            { JOptionPane.showMessageDialog(m,"Error input at age's field","Wrong", JOptionPane.ERROR_MESSAGE); }
        }
    }//constructor
    
    private String upperFirstCharacter(String word)
    { 
       String capitalLetter= word.charAt(0)+"";
       capitalLetter= capitalLetter.toUpperCase();
       return word.replaceFirst(word.charAt(0)+"", capitalLetter); 
    }
}