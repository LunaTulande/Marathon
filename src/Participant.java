
public class Participant extends Person
{
    private double time=00.00;
    private int startNum;
    
    public Participant (String name, String country, int age, int startNum)
    {
      super (name, country, age) ;
      this.startNum=startNum;  
    }
    
    public int getStartNum()
    { return startNum; }
    
    public void setTime(double time)
    { this.time=time; }

    public double getTime()
    {  return time; }
    
    public String toString()
    { 
        if(time==00.00)
            return startNum +". "+ super.toString() +". Time: --";
        else
            return startNum +". "+ super.toString() +". Time: "+ time;
    }
}
