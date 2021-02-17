import javax.swing.JOptionPane;

public class List
{
  // Data
  Node head;
  int list_size;
  
  // Methods
  List() { list_size = 0; }
  public void insertAtTheEnd(float value)
  {
    Node end = new Node(value);

    if(listEmpty())
    { 
      head = end;
      if(head != null) list_size ++;
    }
    else
    {
      Node find_last = head;
      while(find_last.next != null)
        find_last = find_last.next;
      find_last.next = end;  
      list_size ++;
    } 
  }
  public Node getHead()
  {
    return head;
  }
  public int getListSize()
  {
    return list_size;
  } 
  public boolean listEmpty()
  {
    if(getHead() != null) 
      return false;
    
    return true;	
  }
  public void printAllItems(int num_list)
  {
    String list_items = "";
    Node runner = head;
    
    while(runner != null)
    {
      if(runner.next != null)
        list_items += runner.item + ", ";
      else
        list_items += runner.item;
  
      runner = runner.next; 
    }
    
    JOptionPane.showMessageDialog
    (
      null, list_items, "Items in the list " + num_list, JOptionPane.INFORMATION_MESSAGE
    ); 
  }
  public void dropList()
  {
    head = null;
    if(listEmpty()) list_size = 0;
  }
}
