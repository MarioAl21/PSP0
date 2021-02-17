import javax.swing.JOptionPane;
import static java.lang.Math.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Statistic
{
  // Methods
  public static float toCalculateAvarage(List data_list)
  {
    int index, lis_sz = data_list.getListSize();
    float avg = 0.0f; 
    Node runner_list = data_list.getHead();
    
    for(index = 0; index <= lis_sz - 1; index ++)
    {
      try
      {
        avg += runner_list.item;
        runner_list = runner_list.next;   
      }
      catch(NumberFormatException e)
      {
          JOptionPane.showMessageDialog(
	    null, "Trouble " + e.getMessage(), "Number Format Error", JOptionPane.ERROR_MESSAGE);
      }  	
    } // end of the loop for   
     
    return (avg / lis_sz); 
  } // end of method toCalculateAvarage

  public static double toCalculateStandardDeviation(List data_list)
  {
    int index = 0, lis_sz = data_list.getListSize();
    float main = toCalculateAvarage(data_list);
    float numerator = 0.0f, denominator = lis_sz - 1;
    double deviation = 0.0d; 
    Node list_runner = data_list.getHead();
    
    while(list_runner != null && index <= lis_sz - 1)
    {
      numerator += Math.pow((list_runner.item - main), 2);	
      list_runner = list_runner.next;
      index ++; 
    }   
 
    deviation = numerator / denominator;       
  
    return (Math.sqrt(deviation));   
  }
  public static String toRondDecimals(double number)
  {
    return new DecimalFormat("###.##").format(number);
  }  
} // Class close



