import org.junit.Test;
import static org.junit.Assert.*;
import javax.swing.JOptionPane;

public class TestProgramme
{
  public static double getAvarage(List list_numbers, List list_indexes) {
    double avarage = 0.0;
    float[] nums = ProgramaA1.dataToStore(); 
    Node index_runner = list_indexes.getHead();
    int index = (int)index_runner.item;
    int until = 0;
    int denominator = list_numbers.getListSize(); 
    
    while(index_runner != null && nums[index] != list_numbers.getHead().item) {
      index_runner = index_runner.next;
      index = (int)index_runner.item;       
    }
 
    if(index_runner != null) { 
      until = (index + (denominator - 1));

      for(; index <= until; index ++)
        avarage += nums[index];
    }      
    
    return (avarage / denominator); 
  
  } 
  @Test
  public static void testAvarageResult(List lis_numbers, List lis_indexes, double result)
  {
    double to_evaluate = Double.parseDouble(Statistic.toRondDecimals(getAvarage(lis_numbers, lis_indexes)));
    
    try
    {
      assertEquals(to_evaluate, result, 0);
      
      JOptionPane.showMessageDialog(null, "TEST PASSED\n" + "Expected value: " + to_evaluate + "\nObtained value: " + result,  
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    catch(AssertionError e){ 
      JOptionPane.showMessageDialog
      (
        null, 
        "TEST FAILED\n" + 
	"Expected value: " + to_evaluate + "\nObtained value: " + result,  
        "Error",
	JOptionPane.WARNING_MESSAGE
      );
    }
  }
}