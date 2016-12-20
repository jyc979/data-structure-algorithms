package DS;
import java.io.*;
class Link0{
	public long dData;
	public Link0 next;
	//------------------------
	public Link0(long dd){
		dData = dd;
	}
	//------------------------
	public void displayLink(){
		System.out.print(dData+" ");
	}
	//------------------------
}//end class Link
class LinkList2{
	private Link0 first;
	//------------------------
	public LinkList2(){
		first = null;
	}
	//------------------------
	public Link0 getFirst(){
		return first;
	}
	//------------------------
	public void setFirst(Link0 f){
		first =f;
	}
	//------------------------
	public boolean isEmpty(){
		return first == null;
	}
	//------------------------
	public ListIterator getIterator(){ //initialized with this list
		return new ListIterator(this);
	}
	//------------------------
	public void displayList(){
		Link0 current = first;
		while(current != null){
			current.displayLink();
			current = current.next;
		}
		System.out.println(" " );
	}
	//------------------------
}//end class LinkList
////////////////////////////////////
class ListIterator{
	private Link0 current;
	private Link0 previous;
	private LinkList2 ourList;
	//------------------------
	public ListIterator(LinkList2 list){
		ourList = list;
		reset();
	}
	//------------------------
	public void reset(){
		current = ourList.getFirst();
		previous = null;
	}
	//------------------------
	public boolean atEnd(){
		return (current.next == null);
	}
	//------------------------
	public void nextLink(){
		previous = current;
		current = current.next;
	}
	//------------------------
	public Link0 getCurrent(){
		return current;
	}
	//------------------------
	public void insertAfter(long dd){ //insert after current Link
		Link0 newLink = new Link0(dd);
		
		if(ourList.isEmpty()){
			ourList.setFirst(newLink);
			current = newLink;
		}
		else{
			newLink.next = current.next;
			current.next = newLink;
			nextLink();
		}
	}
	//------------------------
	public void insertBefore(long dd){
		Link0 newLink = new Link0(dd);
		
		if(previous == null) //beginning of list (or empty list)
		{
			newLink.next = ourList.getFirst();
			ourList.setFirst(newLink);
			reset();
		}
		else{
			newLink.next = previous.next;
			previous.next = newLink;
			current = newLink;
		}
	}
	//------------------------
	public long deleteCurrent(){ //delete item at current
		long value = current.dData;
		if(previous ==null)//beginning of list
		{
			ourList.setFirst(current.next);
			reset();
		}
		else{
			previous.next = current.next;
			if(atEnd())
				reset();
			else
				current = current.next;
		}
		return value;
	}
	//------------------------
}//end class iterator
public class InterIterApp {

	public static void main(String[] args) throws IOException {
		LinkList2 theList = new LinkList2();
		ListIterator iter1 = theList.getIterator();
		long value;
		
		iter1.insertAfter(20);
		iter1.insertAfter(40);
		iter1.insertAfter(80);
		iter1.insertBefore(60);
		
		while(true){
			System.out.print("Enter first letter of show, reset, ");
			System.out.print("next, get, before, after, delete: ");
			System.out.flush();
			int choice = getChar();
			switch(choice)
            {
            case 's':                    // show list
               if( !theList.isEmpty() )
                  theList.displayList();
               else
                  System.out.println("List is empty");
               break;
            case 'r':                    // reset (to first)
               iter1.reset();
               break;
            case 'n':                    // advance to next item
               if( !theList.isEmpty() && !iter1.atEnd() )
                  iter1.nextLink();
               else
                  System.out.println("Can't go to next link");
               break;
            case 'g':                    // get current item
               if( !theList.isEmpty() )
                  {
                  value = iter1.getCurrent().dData;
                  System.out.println("Returned " + value);
                  }
               else
                  System.out.println("List is empty");
               break;
            case 'b':                    // insert before current
               System.out.print("Enter value to insert: ");
               System.out.flush();
               value = getInt();
               iter1.insertBefore(value);
               break;
            case 'a':                    // insert after current
               System.out.print("Enter value to insert: ");
               System.out.flush();
               value = getInt();
               iter1.insertAfter(value);
               break;
            case 'd':                    // delete current item
               if( !theList.isEmpty() )
                  {
                  value = iter1.deleteCurrent();
                  System.out.println("Deleted " + value);
                  }
               else
                  System.out.println("Can't delete");
               break;
            default:
               System.out.println("Invalid entry");
            }  // end switch
		}//end while
		
	}// end main
	public static String getString() throws IOException{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
	//------------------------
	public static char getChar() throws IOException{
		String s = getString();
		return s.charAt(0);
	}
	//------------------------
	public static int getInt() throws IOException{
		String s = getString();
		return Integer.parseInt(s);
	}
	//------------------------
}// end iterator app
//////////////////////////////////////////////////////


