import javax.swing.JOptionPane;

public class ProgramaA1
{
  static List index_list = new List();

  public static String menu()
  {
    String op = JOptionPane.showInputDialog
    (
      null,
      "S. See the data to store\n" +
      "C. Create linked lists to store the data\n" +
      "L. Show the lists that have been created\n" +	 
      "E. Exit",
      "Menu", 
      JOptionPane.QUESTION_MESSAGE 	  
    ); 
    
    return op.toUpperCase();
  }
  public static String menuList(int num)
  {
    String op = JOptionPane.showInputDialog
    (
      null,
      "P. Print all list items\n" +
      "I. Insert a number in the list\n" +
      "D. Drop list(Caution! Bare option. Even yet you can still insert values)\n" +
      "M. Get Avarage\n" +
      "G. Get standard deviation\n" +  	 
      "R. Return to the main menu",
      "List " + num,
      JOptionPane.QUESTION_MESSAGE 
    );

    return op.toUpperCase();
  }
  
  
 
  public static List listToManipulate(List lis, int num)
  { 
    String option = "";
    int lis_len = 0;
    float number = 0.0f;
    double standard_deviation = 0.0d;
    double main = 0.0d;    

    do
    {
      option = menuList(num);

      switch(option)
      {
        case "P":
          if(!lis.listEmpty())
	    lis.printAllItems(num);
	  else
            JOptionPane.showMessageDialog(null, "There are no values to print");
        break;
        case "I":
          try{
            number = Float.parseFloat(JOptionPane.showInputDialog
	    (
	      null, "Insert your number:", "Insert a number",
	      JOptionPane.INFORMATION_MESSAGE
	    ));

            lis_len = lis.getListSize(); 
	    lis.insertAtTheEnd(number);

            if((lis.getListSize() - lis_len) == 1)
            { 
              JOptionPane.showMessageDialog
	      (
                null, "Number " + number + " was inserted in the " 
                + lis.getListSize() + " position successfully", 
	        "Number introduced successfully", 
	        JOptionPane.INFORMATION_MESSAGE 
	      );
	      lis_len = lis.getListSize();
	    }
            else
              JOptionPane.showMessageDialog(null, "Trouble to insert " + number + " occurred");		
          } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
	  } 
	break;
        case "D":
          if(!lis.listEmpty())
	  {
	    lis.dropList();
              if(lis.listEmpty())
	        JOptionPane.showMessageDialog(null, "List " + num + " delete successfully");
	  }
          else
	    JOptionPane.showMessageDialog(null, "There are no numbers in List " + num + " to drop");  
	break;
        case "M":
          if(!lis.listEmpty())
	  {
            main = Double.parseDouble(Statistic.toRondDecimals(Statistic.toCalculateAvarage(lis)));

            JOptionPane.showMessageDialog(
	      null, "Avarage: " + main, "Avarage", 
              JOptionPane.INFORMATION_MESSAGE);

	    // Test the expected value
	    TestProgramme.testAvarageResult(lis, index_list, main);
          }
	  else
	    JOptionPane.showMessageDialog(
              null, "List " + num + " has no numbers!", "Error", JOptionPane.ERROR_MESSAGE); 
	break;
	case "G":
	  if(!lis.listEmpty())
	  {
	    standard_deviation = Statistic.toCalculateStandardDeviation(lis);
            JOptionPane.showMessageDialog(
	      null, "Result " + Statistic.toRondDecimals(standard_deviation), "Standard deviation", 
              JOptionPane.INFORMATION_MESSAGE);

	    
            // Test Standard Deviation *(Not built yet)
	    // TestProgramme.testStardardDeviationResult(lis, index_list, result);   
          }
	  else
	    JOptionPane.showMessageDialog(
              null, "List " + num + " has no numbers!", "Error", JOptionPane.ERROR_MESSAGE);
	break;
        case "R":
        break;
        default:
          JOptionPane.showMessageDialog
            (null, "Invalid option", "List " + num, JOptionPane.WARNING_MESSAGE); 
      }      
  
    } while(!option.equals("R"));

    return lis;
  }
  public static float[] dataToStore()
  {
    float[] data = 
    {
      160, 591, 114, 229, 230, 270, 128, 1657, 624, 1503,
      186, 699, 132, 272, 291, 331, 199, 1890, 788, 1601,
      15.0f, 69.9f, 6.5f, 22.4f, 28.4f, 65.9f, 19.4f, 198.7f, 38.8f, 138.2f
    };

    return data;
  }
  /* 
    Here, the linked lists are created given the value that keep the variable slice_of.
    And linked lists are filled
  */
  public static List[] throwTheData(float[] data, int data_len, int slice_of)
  {
    List[] lists = new List[slice_of];
    int index_top, index_lower = 0, size_list = data_len / slice_of;

    for(index_top = 0; index_top <= lists.length - 1; index_top ++)
    {
      lists[index_top] = new List();
      
      // To get the start point in the test 
      index_list.insertAtTheEnd(index_lower); 

      while(index_lower <= ((size_list * (index_top + 1)) - 1))
        lists[index_top].insertAtTheEnd(data[index_lower ++]);
    }  

    return lists; 
  }  
  public static Object possibleValues(Object [] possible_values, String message1, String message2)
  {
    Object selected_value = JOptionPane.showInputDialog
    (
      null,
      message1,
      message2,
      JOptionPane.INFORMATION_MESSAGE, null,
      possible_values, possible_values[0]
    );
    
    return selected_value;
  }
 
  public static void main(String[] args)
  {
    mainMenu(); 
  }
  
  public static void mainMenu()
  {
    List[] my_lists = new List[index_list.getListSize()];
    Object [] possible_values = null;
    Object selected_val = null;
    String option = "";
    float number = 0.0f; 
    float[] data_to_store = dataToStore();
    int yes_no_option = 1, num_of_lists = 0, data_len = data_to_store.length, index, num_of_list;	
    
    do
    {
      try{
        option = menu();
      }catch(NullPointerException e){
        option="CANCELAR"; 
      }
 
      switch(option)
      { 
        
	case "S": 
          if(data_len > 0)
          {
            String data = "";   
            for(index = 0; index <= data_len - 1; index ++)
            {
	      if(index < data_len - 1)       
                data += data_to_store[index] + ", ";
              else
                data += data_to_store[index];
              if((index + 1) % 10 == 0)
                data += "\n";
	    }
            JOptionPane.showMessageDialog
             (null, data, "Data to store", JOptionPane.INFORMATION_MESSAGE);           
          } 
          else
            JOptionPane.showMessageDialog
	    (
	      null, "There is no any value to store", "Data to store", 
              JOptionPane.INFORMATION_MESSAGE
            ); 
	break;
        case "C":
          if(data_len > 0)
          {  
            if(data_len % 2 == 0 && data_len % 3 == 0)
            {
              possible_values = new Object[2]; 
              possible_values[0] = "2";
              possible_values[1] = "3"; 
	      selected_val = possibleValues
              (possible_values, "Given the data, you can create two end three lists.\nChoose one"
              , "Number of lists to create");
   
              if(selected_val == "2") num_of_lists = 2;
              if(selected_val == "3") num_of_lists = 3;
	          	
            }
            else if(data_len % 2 == 0)
            {
              yes_no_option = JOptionPane.showConfirmDialog
	      (
	        null, "Given the data to store, once can make two lists", 
                "Create lists",
  	        JOptionPane.YES_NO_OPTION
	      );
              if(yes_no_option == 0) num_of_lists = 2;
	      yes_no_option = 1;
            }
            else 
            {
	      yes_no_option = JOptionPane.showConfirmDialog
	      (
	        null, "Given the data to store, one can make one list", 
                "Create lists",
  	        JOptionPane.YES_NO_OPTION
	      );
              if(yes_no_option == 0){ num_of_lists = data_len % 2; }
	      yes_no_option = 1;
            }
	    if(num_of_lists > 0)
            {	
              my_lists = throwTheData(data_to_store, data_len, num_of_lists);
              if(my_lists.length == num_of_lists) 
                JOptionPane.showMessageDialog
                (null, my_lists.length + " lists created successfully", 
                "Lists created", JOptionPane.INFORMATION_MESSAGE);
            }     
          }
          else
	    JOptionPane.showMessageDialog
	    (
	      null, "There is no data to store", 
              "Create lists",
  	      JOptionPane.INFORMATION_MESSAGE
	    );   	        
        break;
        case "L":
         if(my_lists[0] != null)
          { 
            possible_values = new Object[my_lists.length];
            index = 0;
            while(index <= my_lists.length - 1)
            {
              possible_values[index] = Integer.toString(index + 1);
              index ++;  
            }
            selected_val = possibleValues
              (possible_values, "There exist " + my_lists.length + " lists.\nChoose one"
              , "Choose the list to manipulate");
                          
            if(selected_val != null)
            {
              num_of_list = Integer.valueOf((String)selected_val) - 1;  
              my_lists[num_of_list] = listToManipulate(my_lists[num_of_list], (num_of_list +  1));
	    }
          }
          else
           JOptionPane.showMessageDialog
	     (null, "No litst have been created yet", "Show lists", JOptionPane.INFORMATION_MESSAGE);
        break;   
        case "E":
          JOptionPane.showMessageDialog(null, "Bye ;)");
        break;
        case "CANCELAR":
          yes_no_option = JOptionPane.showConfirmDialog
	  (
	    null, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION
          );
        break; 
	default: 
	  JOptionPane.showMessageDialog(null, "Invalid Option", "Warning", 
	  JOptionPane.WARNING_MESSAGE);
      } // Switch end
 
    }while(!option.equals("E") && yes_no_option != 0);
 
  }
}

