import java.awt.Toolkit;
import java.awt.Dimension; 
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.WindowEvent; 

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer; 
import javax.swing.WindowConstants;

import java.text.DecimalFormat;

import java.util.Arrays;

/**
 * This program applies the FP Method to obtain
 * not adjusted and adjusted function points 
 * @author Mario Alonso N. Zavala
 *
 */
public class ProgramaB2
{
  public static void main(String[] args)
  { 
    Frame fp = new Frame();
    fp.setVisible(true);
    JOptionPane.showMessageDialog(fp, 
		    "Introduce your family functions categories (use integers!)",
                    "Tip",
                    JOptionPane.INFORMATION_MESSAGE);
    			 
  }
}

class Frame extends JFrame
{
   FrameFP fp;

  // Results from fp method
  static int fp_na;
  
  // Frame dimension
  static int frame_width, frame_height;

  // To get features from System Window 
  private Toolkit sys_window;  private Dimension screen;

  // To get the input function family and complexity of a system function
  private JLabel lbl_inputTitle;
  private JLabel lbl_inputSimple, lbl_inputMiddle, lbl_inputComplex;
  private JTextField txt_inputSimple, txt_inputMiddle, txt_inputComplex;

  // To get the output function family and complexity of a system function
  private JLabel lbl_outputTitle;
  private JLabel lbl_outputSimple, lbl_outputMiddle, lbl_outputComplex;
  private JTextField txt_outputSimple, txt_outputMiddle, txt_outputComplex;

  // To get the inquiry function family and complexity of a system function
  private JLabel lbl_inquiryTitle;
  private JLabel lbl_inquirySimple, lbl_inquiryMiddle, lbl_inquiryComplex;
  private JTextField txt_inquirySimple, txt_inquiryMiddle, txt_inquiryComplex;

  // To get the interface function family and complexity of a system function
  private JLabel lbl_interfaceTitle;
  private JLabel lbl_interfaceSimple, lbl_interfaceMiddle, lbl_interfaceComplex;
  private JTextField txt_interfaceSimple, txt_interfaceMiddle, txt_interfaceComplex;

  // To get the data file function family and complexity of a system function
  private JLabel lbl_dataFileTitle;
  private JLabel lbl_dataFileSimple, lbl_dataFileMiddle, lbl_dataFileComplex;
  private JTextField txt_dataFileSimple, txt_dataFileMiddle, txt_dataFileComplex;

  // Box Tip and Submit
  private Box tip, submit;
 
  // Buttons
  JButton tip_button; JButton submit_button; 

  // Boxes where to add the interface elements
  // Three boxes. One vertical, within it: two horizontals
  private Box vertical_inputBox, input_title, input_category;

  // Three boxes. One vertical, within it: two horizontals
  private Box vertical_outputBox, output_title, output_category;
  
  // Three boxes. One vertical, within it: two horizontals
  private Box vertical_inquiryBox, inquiry_title, inquiry_category;

  // Three boxes. One vertical, within it: two horizontals
  private Box vertical_interfaceBox, interface_title, interface_category;

  // Three boxes. One vertical, within it: two horizontals
  private Box vertical_dataFileBox, dataFile_title, dataFile_category;
 
  // To insert the rest of the boxes
  Box main_verticalBox;

  // Table to insert FP nor adjusted 
  private String [] fields_fpNA = {"FF", "Simple", "Middle", "Complex", "FP"};
  private Object[][] data_fpNA = {

      {"Input",     3,  4,  6, 0},
      {"Output",    4,  5,  7, 0},
      {"Inquiry",   3,  4,  6, 0},
      {"Interface", 7, 10, 15, 0}, 
      {"Data file", 5,  7, 10, 0},
      {"TOTAL",     0,  0,  0, 0}	   

  };

  // Table that'll contains all the values in FP NA 
  private JTable table_fpNA;

  // To scroll the table
  private JScrollPane scroll;
    
  // Box that contains the FP NA table
  private Box vertical_fpNABox; 

  // To centre some table columns
  static DefaultTableCellRenderer cr;
    
  Frame()
  {
    setTitle("FP Method");
    // Setting up the Frame
    sys_window = Toolkit.getDefaultToolkit();
    screen = sys_window.getScreenSize();
    frame_width = screen.width; 
    frame_height = screen.height;  
    // frame measurements
    setBounds(frame_width/3, frame_height/4, frame_width/4, frame_height/2);
    setResizable(true);
    // Set Operation when close the Frame
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Setting the FP NA table 
    vertical_fpNABox = Box.createHorizontalBox(); // my bad is horizontal_fpNABox
    table_fpNA = new JTable(data_fpNA, fields_fpNA);

    // Centre the column 1
    cr = new DefaultTableCellRenderer(); 
    cr.setHorizontalAlignment(SwingConstants.CENTER);
    table_fpNA.getColumnModel().getColumn(1).setCellRenderer(cr);
    // Centre the column 2
    cr = new DefaultTableCellRenderer(); 
    cr.setHorizontalAlignment(SwingConstants.CENTER);
    table_fpNA.getColumnModel().getColumn(2).setCellRenderer(cr);
    // Centre the column 3
    cr = new DefaultTableCellRenderer(); 
    cr.setHorizontalAlignment(SwingConstants.CENTER);
    table_fpNA.getColumnModel().getColumn(3).setCellRenderer(cr);
    // Centre the column 4
    cr = new DefaultTableCellRenderer(); 
    cr.setHorizontalAlignment(SwingConstants.CENTER);
    table_fpNA.getColumnModel().getColumn(4).setCellRenderer(cr);
 
    scroll = new JScrollPane(table_fpNA);
    vertical_fpNABox.add(scroll);
    
    // setting the Tip Button
    tip = Box.createHorizontalBox();
    tip_button = new JButton("Tip");  
    tip.add(tip_button);

    // adds action listener for the tip button
    tip_button.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent event)
      {
	JOptionPane.showMessageDialog(Frame.this, 
                        "Enter your family functions categories\nUSE integers!\n" +
                        "Recommended do not to modify the categories from the table\n" + 
		        "use the interface", 
	                "Tip",
                        JOptionPane.INFORMATION_MESSAGE);
      }
    });

    // setting the Submit button    
    submit = Box.createHorizontalBox();
    submit_button = new JButton("submit");  
    submit.add(submit_button);

    // adds action listener for the button
    submit_button.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent event)
      {
	submitEvent();
      }
    });    
    

    inputInterface();     
    outputInterface(); 
    inquiryInterface();
    interfaceInterface();
    dataFileInterface();

    main_verticalBox = Box.createVerticalBox(); 

    // Inserting all interfaces elements
    add(tip, BorderLayout.NORTH);
    main_verticalBox.add(vertical_inputBox);
    main_verticalBox.add(Box.createVerticalStrut(10));
    main_verticalBox.add(vertical_outputBox);
    main_verticalBox.add(Box.createVerticalStrut(10));
    main_verticalBox.add(vertical_inquiryBox);
    main_verticalBox.add(Box.createVerticalStrut(10));
    main_verticalBox.add(vertical_interfaceBox);
    main_verticalBox.add(Box.createVerticalStrut(10));
    main_verticalBox.add(vertical_dataFileBox);
    main_verticalBox.add(Box.createVerticalStrut(10));
    main_verticalBox.add(submit);
    main_verticalBox.add(Box.createVerticalStrut(10));
    main_verticalBox.add(scroll);     
    
    add(main_verticalBox);

    fp = new FrameFP(); 

  }

  public void inputInterface()
  {
    // The Box that'll contain the input category
    vertical_inputBox = Box.createVerticalBox();
    input_title = Box.createHorizontalBox();
    input_category = Box.createHorizontalBox();

    // The input Boxes fill up 
    lbl_inputTitle = new JLabel("Input Family Function");
    lbl_inputTitle.setForeground(Color.blue);
    input_title.add(lbl_inputTitle); 
     
    getInputFunctionFamilyComplexity();

    vertical_inputBox.add(input_title);
    vertical_inputBox.add(input_category);

  }

  // The interface is prepared to categorized the input functions to be programmed
  public void getInputFunctionFamilyComplexity()
  {
    lbl_inputSimple = new JLabel("Simple:"); 
    txt_inputSimple = new JTextField(3); 
    txt_inputSimple.setMaximumSize(txt_inputSimple.getPreferredSize());

    lbl_inputMiddle = new JLabel("Middle:"); 
    txt_inputMiddle = new JTextField(3); 
    txt_inputMiddle.setMaximumSize(txt_inputMiddle.getPreferredSize());

    lbl_inputComplex = new JLabel("Complex:"); 
    txt_inputComplex = new JTextField(3); 
    txt_inputComplex.setMaximumSize(txt_inputComplex.getPreferredSize());
      
    input_category.add(lbl_inputSimple);
    input_category.add(Box.createHorizontalStrut(5));
    input_category.add(txt_inputSimple);

    input_category.add(Box.createHorizontalStrut(10));
    
    input_category.add(lbl_inputMiddle);
    input_category.add(Box.createHorizontalStrut(5));
    input_category.add(txt_inputMiddle);

    input_category.add(Box.createHorizontalStrut(10));

    input_category.add(lbl_inputComplex);
    input_category.add(Box.createHorizontalStrut(5));
    input_category.add(txt_inputComplex); 

  }
   
  public void outputInterface()
  {
    // The Box that'll contain the output category
    vertical_outputBox = Box.createVerticalBox();
    output_title = Box.createHorizontalBox();
    output_category = Box.createHorizontalBox();

    // The output Boxes fill up  
    lbl_outputTitle = new JLabel("Output Family Function");
    lbl_outputTitle.setForeground(Color.blue);
    output_title.add(lbl_outputTitle);
     
    getOutputFunctionFamilyComplexity();

    vertical_outputBox.add(output_title);
    vertical_outputBox.add(output_category);

  } 

  // The interface is prepared to categorized the output functions to be programmed
  public void getOutputFunctionFamilyComplexity()
  {    
    lbl_outputSimple = new JLabel("Simple:"); 
    txt_outputSimple = new JTextField(3); 
    txt_outputSimple.setMaximumSize(txt_outputSimple.getPreferredSize());

    lbl_outputMiddle = new JLabel("Middle:"); 
    txt_outputMiddle = new JTextField(3); 
    txt_outputMiddle.setMaximumSize(txt_outputMiddle.getPreferredSize());

    lbl_outputComplex = new JLabel("Complex:"); 
    txt_outputComplex = new JTextField(3); 
    txt_outputComplex.setMaximumSize(txt_outputComplex.getPreferredSize());
      
    output_category.add(lbl_outputSimple);
    output_category.add(Box.createHorizontalStrut(5));
    output_category.add(txt_outputSimple);

    output_category.add(Box.createHorizontalStrut(10));
    
    output_category.add(lbl_outputMiddle);
    output_category.add(Box.createHorizontalStrut(5));
    output_category.add(txt_outputMiddle);

    output_category.add(Box.createHorizontalStrut(10));

    output_category.add(lbl_outputComplex);
    output_category.add(Box.createHorizontalStrut(5));
    output_category.add(txt_outputComplex); 

  }

  public void inquiryInterface()
  {
    // The Box that'll contain the inquiry category
    vertical_inquiryBox = Box.createVerticalBox();
    inquiry_title = Box.createHorizontalBox();
    inquiry_category = Box.createHorizontalBox();

    // The inquiry Boxes fill up  
    lbl_inquiryTitle = new JLabel("Inquiry Family Function");
    lbl_inquiryTitle.setForeground(Color.blue);
    inquiry_title.add(lbl_inquiryTitle);
     
    getInquiryFunctionFamilyComplexity();

    vertical_inquiryBox.add(inquiry_title);
    vertical_inquiryBox.add(inquiry_category);

  } 

  // The interface is prepared to categorized the inquiry functions to be programmed
  public void getInquiryFunctionFamilyComplexity()
  {    
    lbl_inquirySimple = new JLabel("Simple:"); 
    txt_inquirySimple = new JTextField(3); 
    txt_inquirySimple.setMaximumSize(txt_inquirySimple.getPreferredSize());

    lbl_inquiryMiddle = new JLabel("Middle:"); 
    txt_inquiryMiddle = new JTextField(3); 
    txt_inquiryMiddle.setMaximumSize(txt_inquiryMiddle.getPreferredSize());

    lbl_inquiryComplex = new JLabel("Complex:"); 
    txt_inquiryComplex = new JTextField(3); 
    txt_inquiryComplex.setMaximumSize(txt_inquiryComplex.getPreferredSize());
      
    inquiry_category.add(lbl_inquirySimple);
    inquiry_category.add(Box.createHorizontalStrut(5));
    inquiry_category.add(txt_inquirySimple);

    inquiry_category.add(Box.createHorizontalStrut(10));
    
    inquiry_category.add(lbl_inquiryMiddle);
    inquiry_category.add(Box.createHorizontalStrut(5));
    inquiry_category.add(txt_inquiryMiddle);

    inquiry_category.add(Box.createHorizontalStrut(10));

    inquiry_category.add(lbl_inquiryComplex);
    inquiry_category.add(Box.createHorizontalStrut(5));
    inquiry_category.add(txt_inquiryComplex); 

  }
  
  public void interfaceInterface()
  {
    // The Box that'll contain the interface category
    vertical_interfaceBox = Box.createVerticalBox();
    interface_title = Box.createHorizontalBox();
    interface_category = Box.createHorizontalBox();

    // The interface Boxes fill up  
    lbl_interfaceTitle = new JLabel("Interface Family Function");
    lbl_interfaceTitle.setForeground(Color.blue);
    interface_title.add(lbl_interfaceTitle);
     
    getInterfaceFunctionFamilyComplexity();

    vertical_interfaceBox.add(interface_title);
    vertical_interfaceBox.add(interface_category);

  } 

  // The interface is prepared to categorized the interface functions to be programmed
  public void getInterfaceFunctionFamilyComplexity()
  {    
    lbl_interfaceSimple = new JLabel("Simple:"); 
    txt_interfaceSimple = new JTextField(3); 
    txt_interfaceSimple.setMaximumSize(txt_interfaceSimple.getPreferredSize());

    lbl_interfaceMiddle = new JLabel("Middle:"); 
    txt_interfaceMiddle = new JTextField(3); 
    txt_interfaceMiddle.setMaximumSize(txt_interfaceMiddle.getPreferredSize());

    lbl_interfaceComplex = new JLabel("Complex:"); 
    txt_interfaceComplex = new JTextField(3); 
    txt_interfaceComplex.setMaximumSize(txt_interfaceComplex.getPreferredSize());
      
    interface_category.add(lbl_interfaceSimple);
    interface_category.add(Box.createHorizontalStrut(5));
    interface_category.add(txt_interfaceSimple);

    interface_category.add(Box.createHorizontalStrut(10));
    
    interface_category.add(lbl_interfaceMiddle);
    interface_category.add(Box.createHorizontalStrut(5));
    interface_category.add(txt_interfaceMiddle);

    interface_category.add(Box.createHorizontalStrut(10));

    interface_category.add(lbl_interfaceComplex);
    interface_category.add(Box.createHorizontalStrut(5));
    interface_category.add(txt_interfaceComplex); 

  }

  public void dataFileInterface()
  {
    // The Box that'll contain the dataFile category
    vertical_dataFileBox = Box.createVerticalBox();
    dataFile_title = Box.createHorizontalBox();
    dataFile_category = Box.createHorizontalBox();

    // The dataFile Boxes fill up  
    lbl_dataFileTitle = new JLabel("Data file Family Function");
    lbl_dataFileTitle.setForeground(Color.blue);
    dataFile_title.add(lbl_dataFileTitle);
     
    getDataFileFunctionFamilyComplexity();

    vertical_dataFileBox.add(dataFile_title);
    vertical_dataFileBox.add(dataFile_category);

  } 

  // The interface is prepared to categorized the interface functions to be programmed
  public void getDataFileFunctionFamilyComplexity()
  {    
    lbl_dataFileSimple = new JLabel("Simple:"); 
    txt_dataFileSimple = new JTextField(3); 
    txt_dataFileSimple.setMaximumSize(txt_dataFileSimple.getPreferredSize());

    lbl_dataFileMiddle = new JLabel("Middle:"); 
    txt_dataFileMiddle = new JTextField(3); 
    txt_dataFileMiddle.setMaximumSize(txt_dataFileMiddle.getPreferredSize());

    lbl_dataFileComplex = new JLabel("Complex:"); 
    txt_dataFileComplex = new JTextField(3); 
    txt_dataFileComplex.setMaximumSize(txt_dataFileComplex.getPreferredSize());
      
    dataFile_category.add(lbl_dataFileSimple);
    dataFile_category.add(Box.createHorizontalStrut(5));
    dataFile_category.add(txt_dataFileSimple);

    dataFile_category.add(Box.createHorizontalStrut(10));
    
    dataFile_category.add(lbl_dataFileMiddle);
    dataFile_category.add(Box.createHorizontalStrut(5));
    dataFile_category.add(txt_dataFileMiddle);

    dataFile_category.add(Box.createHorizontalStrut(10));

    dataFile_category.add(lbl_dataFileComplex);
    dataFile_category.add(Box.createHorizontalStrut(5));
    dataFile_category.add(txt_dataFileComplex); 

  }

  public void submitEvent()
  {
    try 
    {
      int fp_na1 = 0, fp_na2 = 0, hotizontal_subtotal = 0, vertical_subtotal = 0, i, j;
      int[][] ff = new int[5][3];

      // an array that picks up all the sum results of each input 
      // *at the beginnig is initialized with zero and at the time when submit button is clicked  
      for(int index = 0; index < ff.length; index ++)
        for(int inner_index = 0 ; inner_index < ff[0].length; inner_index ++)
          ff[index][inner_index] = 0;  

      // the array that collects all the inputs	
      Object [][] categories = {

       	{txt_inputSimple.getText(), txt_inputMiddle.getText(), txt_inputComplex.getText()}, 
	{txt_outputSimple.getText(), txt_outputMiddle.getText(), txt_outputComplex.getText()},
	{txt_inquirySimple.getText(), txt_inquiryMiddle.getText(), txt_inquiryComplex.getText()},
 	{txt_interfaceSimple.getText(), txt_interfaceMiddle.getText(), txt_interfaceComplex.getText()},
	{txt_dataFileSimple.getText(), txt_dataFileMiddle.getText(), txt_dataFileComplex.getText()} 

      }; 

      // the part of the data_fpNA array that stays with the centerboards 	
      Object[][] data_fpNACOPY =  {

      		{3,  4,  6},
      		{4,  5,  7},
      		{3,  4,  6},
      		{7, 10, 15}, 
      		{5,  7, 10},

      };	   
       
      // To fill the table FP na with functions category	   
      for(int index = 0; index <= data_fpNA.length - 2; index ++)
      {
        for(int inner_index = 0; inner_index <= data_fpNA[0].length - 3; inner_index ++)
          if(categories[index][inner_index].equals("")) 
            data_fpNA[index][inner_index + 1] = data_fpNACOPY[index][inner_index] + " * 0"; 
          else
          {   
            data_fpNA[index][inner_index + 1] = data_fpNACOPY[index][inner_index] + " * " + categories[index][inner_index];
            ff[index][inner_index] = (Integer)data_fpNACOPY[index][inner_index] * Integer.valueOf((String)categories[index][inner_index]); 	
          }       
      }
           
      // To fill the FF column from table_fpNA table and to get the total (Fp NA)
      for(i = 0; i <= ff.length - 1; i ++)
      {
        for(j = 0; j <= ff[0].length - 1; j ++)
          hotizontal_subtotal += ff[i][j];

        data_fpNA[i][j + 1] = hotizontal_subtotal;
        fp_na1 += hotizontal_subtotal; 
        hotizontal_subtotal = 0;
      }

      // To fill and calculate the TOTAL row from table_fpNA table
      i = 0; j = 0;
            
      while(j <= ff[0].length - 1)
      {
        while(i <= ff.length - 1)
	  vertical_subtotal += ff[i ++][j];
	
        data_fpNA[i][(j ++) + 1] = vertical_subtotal;
        fp_na2 += vertical_subtotal;
        vertical_subtotal = 0;
        i = 0;
      } 
      
      if(fp_na1 == fp_na2)
        fp_na = fp_na1;

      data_fpNA[5][4] = fp_na; // function points NOT ADJUSTED result!   

      main_verticalBox.add(scroll); // The table is included in its box
      
      int to_know = 0; 

      for(i = 0; i <= ff.length - 1; i++)
        for(j = 0; j <= ff[0].length - 1; j++)
        {
          to_know += ff[i][j];
 
          if(to_know == 0)
          {
            fp.setVisible(false);
            fp.dispose();
            fp.submit_button.setEnabled(true);
            setLocation(frame_width/3, frame_height/4);
          }
          else
	  { 
            fp.setVisible(true);
            setLocation(59, 98);
            fp.submit_button.setEnabled(true); 
            i = ff.length; j = ff[0].length;
          }

        }
 
    } 
    catch(Exception e) 
    {
      e.printStackTrace();
    }

  } // submitEvent Method ends;

   
}


// FrameFP class
class FrameFP extends JFrame
{
  SystemSize sys_cost; // System cost!

  int factor_summation; // all the factor values are summed 
  double adjustment_factor; // needs FPna value
  static double fp; // Final Method FP result  :} 

  // To get the Technical Complexity input and influence of a system function
  private JLabel fp_title;
  private JLabel lbl_dataCommunications, lbl_distributedData, lbl_performance;
  private JLabel lbl_heavlyUse, lbl_transactionRate, lbl_onLineData, lbl_userEfficiency;
  private JLabel lbl_onLineUpdate, lbl_complexProcessing, lbl_reusability, lbl_installationEase;
  private JLabel lbl_operationalEase, lbl_multipleSites, lbl_facilityChange;

  JTextField txt_dataCommunications, txt_distributedData, txt_performance, txt_heavlyUse;
  JTextField txt_transactionRate, txt_onLineData, txt_userEfficiency, txt_onLineUpdate;
  JTextField txt_complexProcessing, txt_reusability, txt_installationEase, txt_operationalEase;
  JTextField txt_multipleSites, txt_facilityChange;
 
  // Box Tip and Submit
  private Box tip, submit;
 
  // Buttons
  private JButton tip_button; JButton submit_button; 

  // Insert the interface in their own box
  private Box box_fpTitle, box1, box2, box3, box4;
  private Box get_fpBox;

  // Main Box
  Box main_verticalBox;     

  FrameFP()
  {
    factor_summation = 0;   
    fp = 0; 

    setTitle("FP Method");
    setBounds(Frame.frame_width/3 - 56, Frame.frame_height/3 - 180, Frame.frame_width/2 - 50, Frame.frame_height/3);
    setVisible(false);     

    // setting the Tip Button
    tip = Box.createHorizontalBox();
    tip_button = new JButton("Tip");  
    tip.add(tip_button);

    // adds action listener for the tip button
    tip_button.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent event)
      {
	JOptionPane.showMessageDialog(FrameFP.this, 
                        "insert a technical complexity in order to establish\n" + 
                        "the influence of adjustment factors.\n"+
			"Remeber: 0 to 5 depending on their strong influence on the project", 
	                "Tip",
                        JOptionPane.INFORMATION_MESSAGE);
      }
    });

    // setting the Submit button    
    submit = Box.createHorizontalBox();
    submit_button = new JButton("submit");  
    submit.add(submit_button);

    // adds action listener for the button
    submit_button.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent event)
      {
	submitEvent();
      }
    });    
   
    

    main_verticalBox = Box.createVerticalBox();

    fpInterface();  
 
    add(tip, BorderLayout.NORTH);      
    main_verticalBox.add(box_fpTitle);
    main_verticalBox.add(Box.createVerticalStrut(10));
    main_verticalBox.add(box1);
    main_verticalBox.add(Box.createVerticalStrut(10));
    main_verticalBox.add(box2);
    main_verticalBox.add(Box.createVerticalStrut(10));
    main_verticalBox.add(box3);
    main_verticalBox.add(Box.createVerticalStrut(10));
    main_verticalBox.add(box4);      	
    main_verticalBox.add(submit);

    add(main_verticalBox);

    
    sys_cost = new SystemSize();
 
  }

  public void fpInterface()
  {
    // The Box that'll contain the input technical complexity to get FP
    box_fpTitle = Box.createHorizontalBox();
    box1 = Box.createHorizontalBox();
    box2 = Box.createHorizontalBox();
    box3 = Box.createHorizontalBox();
    box4 = Box.createHorizontalBox();

    fp_title = new JLabel("Correction Factor");
    fp_title.setForeground(Color.blue);
    box_fpTitle.add(fp_title);    
 
    getFPComplexity();

  }

  // The interface is prepared to get FP  
  public void getFPComplexity()
  {
    lbl_dataCommunications = new JLabel("Data Communications:");
    txt_dataCommunications = new JTextField(2);
    txt_dataCommunications.setMaximumSize(txt_dataCommunications.getPreferredSize());
 	
    lbl_distributedData = new JLabel("Distributed data processing:"); 
    txt_distributedData = new JTextField(2);
    txt_distributedData.setMaximumSize(txt_distributedData.getPreferredSize());    

    lbl_performance = new JLabel("Performance:"); 
    txt_performance = new JTextField(2);
    txt_performance.setMaximumSize(txt_performance.getPreferredSize());    

    lbl_heavlyUse = new JLabel("Heavly use Configuration:"); 
    txt_heavlyUse = new JTextField(2);
    txt_heavlyUse.setMaximumSize(txt_heavlyUse.getPreferredSize());    

    lbl_transactionRate = new JLabel("Transaction Rate:"); 
    txt_transactionRate = new JTextField(2);
    txt_transactionRate.setMaximumSize(txt_transactionRate.getPreferredSize());    

    lbl_onLineData = new JLabel("On-line data entry:"); 
    txt_onLineData = new JTextField(2);
    txt_onLineData.setMaximumSize(txt_onLineData.getPreferredSize());    
    
    lbl_userEfficiency = new JLabel("End-user efficiency:"); 
    txt_userEfficiency = new JTextField(2);
    txt_userEfficiency.setMaximumSize(txt_userEfficiency.getPreferredSize());    
     
    lbl_onLineUpdate = new JLabel("On-line update:"); 
    txt_onLineUpdate = new JTextField(2);
    txt_onLineUpdate.setMaximumSize(txt_onLineUpdate.getPreferredSize());    
   
    lbl_complexProcessing = new JLabel("Complex processing:"); 
    txt_complexProcessing = new JTextField(2);
    txt_complexProcessing.setMaximumSize(txt_complexProcessing.getPreferredSize());    
   
    lbl_reusability = new JLabel("Reusability:"); 
    txt_reusability = new JTextField(2);
    txt_reusability.setMaximumSize(txt_reusability.getPreferredSize());    
   
    lbl_installationEase = new JLabel("Installation Ease:"); 
    txt_installationEase = new JTextField(2);
    txt_installationEase.setMaximumSize(txt_installationEase.getPreferredSize());    
       
    lbl_operationalEase = new JLabel("Operational Ease:"); 
    txt_operationalEase = new JTextField(2);
    txt_operationalEase.setMaximumSize(txt_operationalEase.getPreferredSize());    
    
    lbl_multipleSites = new JLabel("Multiple Sites:"); 
    txt_multipleSites = new JTextField(2);
    txt_multipleSites.setMaximumSize(txt_multipleSites.getPreferredSize());    
    
    lbl_facilityChange = new JLabel("Facility change:"); 
    txt_facilityChange = new JTextField(2);
    txt_facilityChange.setMaximumSize(txt_facilityChange.getPreferredSize());    

    // add four
    box1.add(lbl_dataCommunications);
    box1.add(Box.createHorizontalStrut(5));
    box1.add(txt_dataCommunications);

    box1.add(Box.createHorizontalStrut(5));

    box1.add(lbl_distributedData);
    box1.add(Box.createHorizontalStrut(5));
    box1.add(txt_distributedData);

    box1.add(Box.createHorizontalStrut(5)); 
 
    box1.add(lbl_performance);
    box1.add(Box.createHorizontalStrut(5));
    box1.add(txt_performance);
 
    box1.add(Box.createHorizontalStrut(5));

    box1.add(lbl_heavlyUse);
    box1.add(Box.createHorizontalStrut(5));
    box1.add(txt_heavlyUse);

    // add four
    box2.add(lbl_transactionRate);
    box2.add(Box.createHorizontalStrut(5));
    box2.add(txt_transactionRate);

    box2.add(Box.createHorizontalStrut(5));

    box2.add(lbl_onLineData);
    box2.add(Box.createHorizontalStrut(5));
    box2.add(txt_onLineData);

    box2.add(Box.createHorizontalStrut(5));

    box2.add(lbl_userEfficiency);
    box2.add(Box.createHorizontalStrut(5));
    box2.add(txt_userEfficiency);

    box2.add(Box.createHorizontalStrut(5));

    box2.add(lbl_onLineUpdate);
    box2.add(Box.createHorizontalStrut(5));
    box2.add(txt_onLineUpdate);

    
    // add four
    box3.add(lbl_complexProcessing);
    box3.add(Box.createHorizontalStrut(5));
    box3.add(txt_complexProcessing);

    box3.add(Box.createHorizontalStrut(5));

    box3.add(lbl_reusability);
    box3.add(Box.createHorizontalStrut(5));
    box3.add(txt_reusability);

    box3.add(Box.createHorizontalStrut(5));

    box3.add(lbl_installationEase);
    box3.add(Box.createHorizontalStrut(5));
    box3.add(txt_installationEase);

    box3.add(Box.createHorizontalStrut(5));

    box3.add(lbl_operationalEase);
    box3.add(Box.createHorizontalStrut(5));
    box3.add(txt_operationalEase);
   
    // add four
    box4.add(lbl_multipleSites);
    box4.add(Box.createHorizontalStrut(5));
    box4.add(txt_multipleSites);

    box4.add(Box.createHorizontalStrut(5));

    box4.add(lbl_facilityChange);
    box4.add(Box.createHorizontalStrut(5));
    box4.add(txt_facilityChange);    
  } 
  
  public void submitEvent()
  {
    try 
    {
      factor_summation = 0; // Initialized the acummuluation factor 
      int index = 0; 
      String error = "EACH correction factor must be between 0 and 5\nCorrect it";
      boolean wrong_factor = true;

      String[] factors = {
 
        txt_dataCommunications.getText(),
        txt_distributedData.getText(),
	txt_performance.getText(),
	txt_heavlyUse.getText(),
	txt_transactionRate.getText(),
	txt_onLineData.getText(),
	txt_userEfficiency.getText(),
	txt_onLineUpdate.getText(),
	txt_complexProcessing.getText(),
	txt_reusability.getText(),
	txt_installationEase.getText(),
	txt_operationalEase.getText(),
	txt_multipleSites.getText(),
	txt_facilityChange.getText()           

      };

      int[] factorsCPY = new int[factors.length];
     
      while(index <= factorsCPY.length-1)
        factorsCPY[index ++] = 0;         		   

      for(index = 0; index <=factors.length-1; index++)
        if(!factors[index].equals(""))
          factorsCPY[index] = Integer.valueOf(factors[index]);         
      
      for(index = 0; index <= factorsCPY.length - 1; index ++)
      {  
         if(factorsCPY[index] < 0 || factorsCPY[index] > 5)
         {
	   JOptionPane.showMessageDialog(FrameFP.this, error, 
	                  "Out of bonds", JOptionPane.WARNING_MESSAGE);
           index = factorsCPY.length; 
           wrong_factor = true;   
         }
         else
         { 
           factor_summation += factorsCPY[index]; 
           wrong_factor = false;
         }   
      }

      if(!wrong_factor)
      {        
        JOptionPane.showMessageDialog(FrameFP.this, "Funtions Points to be programmed: " + Statistic.toRondDecimals(getFP()),
                        "FP RESULT", JOptionPane.INFORMATION_MESSAGE);    
        //get sys size (LOC Avagare per function point, effort avarage productivity Fp, COST salary))
        if(fp != 0)
          sys_cost.setVisible(true);
      }
      
    } 
    catch(Exception e) 
    {
      e.printStackTrace();
    }

  } // submitEvent Method ends;

  public double getFP()
  {
    adjustment_factor = 0.65f + (0.01f *  factor_summation);
    fp = Frame.fp_na * adjustment_factor;        

    return fp;
  }

}  // FrameFP class ends

// SystemSize class begin
class SystemSize extends JFrame 
{
  private double avarage_perFP, avarage_pro, salary, sys_cost;

  private JLabel title_systemSize, lbl_avarage_perFP, lbl_avarage_pro, lbl_salary;
  private JTextField txt_avarage_perFP, txt_avarage_pro, txt_salary;

  private JButton tip_button, submit_button;
  
  private Box main_verticalBox, box_title, box_interface, box_tip, box_submit;
  
  // Table that'll contains all the system reults  
  private JTable table_final;

  // To scroll the table
  private JScrollPane scroll;
    
  // Box that contains the FP NA table
  private Box sysSize_table;   

    private String[] fields = {"Size", "Result", "Total"};
  private Object[][] data = {

      {"LOC", Statistic.toRondDecimals(FrameFP.fp) + " * avarage per FP", 0},
      {"Effort", Statistic.toRondDecimals(FrameFP.fp) + " / avarage productivity", 0},
      {"COST", Statistic.toRondDecimals(FrameFP.fp) + " * cost", 0}      

    };
   
  SystemSize()
  {

    setTitle("System Size");
    setBounds(Frame.frame_width/2 - 195, Frame.frame_height/2 - 24, Frame.frame_width/3 + 12, Frame.frame_height/4 + 12);
    setVisible(false);   

    main_verticalBox = Box.createVerticalBox();
    box_title = Box.createHorizontalBox();
    box_interface = Box.createHorizontalBox();
    box_tip = Box.createHorizontalBox();
    box_submit = Box.createHorizontalBox();

    sysSize_table = Box.createHorizontalBox(); 
    table_final = new JTable(data, fields);
    scroll = new JScrollPane(table_final);

    // Centre the last 3 columns
    Frame.cr = new DefaultTableCellRenderer(); 
    Frame.cr.setHorizontalAlignment(SwingConstants.CENTER);
    table_final.getColumnModel().getColumn(1).setCellRenderer(Frame.cr);

    Frame.cr = new DefaultTableCellRenderer(); 
    Frame.cr.setHorizontalAlignment(SwingConstants.CENTER);
    table_final.getColumnModel().getColumn(2).setCellRenderer(Frame.cr);

    // Setting buttons
    tip_button = new JButton("Tip");
    box_tip.add(tip_button);
    tip_button.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent event)
      {
	PLTable pl = new PLTable();
      }
    }); 

    submit_button = new JButton("submit");
    box_submit.add(submit_button);
    submit_button.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent event)
      {
        submitEvent(); 
      }
    });

    title_systemSize = new JLabel("System Size");
    title_systemSize.setForeground(Color.blue);
    box_title.add(title_systemSize);

    lbl_avarage_perFP = new JLabel("Avarage per FP:"); 
    txt_avarage_perFP = new JTextField(5);
    txt_avarage_perFP.setMaximumSize(txt_avarage_perFP.getPreferredSize());
    lbl_avarage_pro = new JLabel("Productivity FP:"); 
    txt_avarage_pro = new JTextField(3);
    txt_avarage_pro.setMaximumSize(txt_avarage_pro.getPreferredSize());
    lbl_salary = new JLabel("Salary"); 
    txt_salary = new JTextField(6);
    txt_salary.setMaximumSize(txt_salary.getPreferredSize());

    box_interface.add(lbl_avarage_perFP); 
    box_interface.add(Box.createHorizontalStrut(5));
    box_interface.add(txt_avarage_perFP);

    box_interface.add(Box.createHorizontalStrut(5));

    box_interface.add(lbl_avarage_pro);
    box_interface.add(Box.createHorizontalStrut(5));
    box_interface.add(txt_avarage_pro);

    box_interface.add(Box.createHorizontalStrut(5));

    box_interface.add(lbl_salary);
    box_interface.add(Box.createHorizontalStrut(5));
    box_interface.add(txt_salary);  

    add(box_tip, BorderLayout.NORTH);
    main_verticalBox.add(box_title);  
    main_verticalBox.add(Box.createVerticalStrut(5));
    main_verticalBox.add(box_interface);
    main_verticalBox.add(Box.createVerticalStrut(5));
    main_verticalBox.add(box_submit);
    main_verticalBox.add(Box.createVerticalStrut(5));
    main_verticalBox.add(scroll);
  
    add(main_verticalBox);     

  }
   

  public void submitEvent()
  {
     try
     {	
       int index = 0; 

       String[] inputs_size = {
  
         txt_avarage_perFP.getText(), txt_avarage_pro.getText(), txt_salary.getText()
  
       };

       double[] inputs_sizeCPY = new double[inputs_size.length];
     
       while(index <= inputs_sizeCPY.length-1)
         inputs_sizeCPY[index ++] = 0.0d;         		   

       for(index = 0; index <=inputs_size.length-1; index++)
         if(!inputs_size[index].equals(""))
           inputs_sizeCPY[index] = Double.valueOf(inputs_size[index]);
              
       
       avarage_perFP = FrameFP.fp * inputs_sizeCPY[0];
       avarage_pro = FrameFP.fp / inputs_sizeCPY[1]; 
       salary = inputs_sizeCPY[2];
       sys_cost = (FrameFP.fp * (salary / inputs_sizeCPY[1]));
       
       data[0][1] = Statistic.toRondDecimals(FrameFP.fp) + " * " + txt_avarage_perFP.getText();
       data[1][1] = Statistic.toRondDecimals(FrameFP.fp) + " / " + txt_avarage_pro.getText();
       data[2][1] = Statistic.toRondDecimals(FrameFP.fp) + " * " + txt_salary.getText();
  

       // RESULTS :_)
       data[0][2] = Statistic.toRondDecimals(avarage_perFP);  
       data[1][2] = Statistic.toRondDecimals(avarage_pro);       
       data[2][2] = Statistic.toRondDecimals(sys_cost); // IVA no including 
       
       setSize(Frame.frame_width/3 + 12, Frame.frame_height/4 + 12);   
       main_verticalBox.add(scroll);
        
       if(sys_cost > 0){  
       // FrameFP.fp and data[2][2];
       double percentage_complex = ((FrameFP.fp * 100) / Frame.fp_na) - 100;
       String message_complexity = "";
       
       if(percentage_complex < 0)
       {
         percentage_complex = percentage_complex * - 1;
         message_complexity = "The dificult of the proyect  is " + Statistic.toRondDecimals(percentage_complex) + "% percent less than what FPna had set";
       }
       else  
         message_complexity = "The dificult of the proyect  is " + Statistic.toRondDecimals(percentage_complex) + "% percent bigger than what FPna had set";   

       JOptionPane.showMessageDialog(SystemSize.this,   message_complexity);
       }      
     }
     catch(Exception e)
     {
       e.printStackTrace();
     }

  }  

} // class SystemSize end 

// Table Programming language
class PLTable extends JFrame
{
  public PLTable()
  {
    setTitle("LOC's according Programming languages");
    setBounds(Frame.frame_width/2 - 195, Frame.frame_height/2 - 24, Frame.frame_width / 4, Frame.frame_height/5 - 17);
    setVisible(true);

    JTable pl_table = new JTable(data, fields);

    // to centre the loc avarage per function point column
    Frame.cr = new DefaultTableCellRenderer(); 
    Frame.cr.setHorizontalAlignment(SwingConstants.CENTER);
    pl_table.getColumnModel().getColumn(1).setCellRenderer(Frame.cr);

    add(new JScrollPane(pl_table), BorderLayout.CENTER);
 
  }

  private String[] fields = {"Programming Language", "LOC Avarage per FP"}; 
  private Object[][] data = {

	{"Assembly", 320},
	{"C", 128},
	{"Ada", 70},
	{"Java, C++", 53},
        {"Code Generator", 15},
	{"Data Sheet", 6},
	{"Visual Programming", 4}

  };

}
