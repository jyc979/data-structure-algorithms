package DS;
// sortedList.java
// demonstrates sorted list
// to run this program: C>java SortedListApp
////////////////////////////////////////////////////////////////
class Link5
   {
   public long dData;                  // data item
   public Link5 next;                   // next link in list
// -------------------------------------------------------------
   public Link5(long dd)                // constructor
      { dData = dd; }
// -------------------------------------------------------------
   public void displayLink()           // display this link
      { System.out.print(dData + " "); }
   }  // end class Link
////////////////////////////////////////////////////////////////
class SortedList
   {
   private Link5 first;                 // ref to first item
// -------------------------------------------------------------
   public long getFirst(){
	   return first.dData;
   }
// -------------------------------------------------------------
   public SortedList()                 // constructor
      { first = null; }
// -------------------------------------------------------------
   public boolean isEmpty()            // true if no links
      { return (first==null); }
// -------------------------------------------------------------
   public void insert(long key)        // insert, in order
      {
      Link5 newLink = new Link5(key); // make new link
      Link5 previous = null; // start at first
      Link5 current = first;
      										//until end of list,
      while(current!=null&&key>current.dData){ //or key>current,
    	previous = current;
    	current = current.next;
      }
      if(previous==null) //at beginning of list
    	  first = newLink; // first--> newLink
      else //not at beginning
    	  previous.next = newLink; //old prev--> newLink
      newLink.next = current;
      
      }  // end insert()
// -------------------------------------------------------------
   public Link5 remove()           // return & delete first link
      {                           // (assumes non-empty list)
      Link5 temp = first;               // save first
      first = first.next;              // delete first
      return temp;                     // return value
      }
// -------------------------------------------------------------
   public void displayList()
      {
      System.out.print("List (first-->last): ");
      Link5 current = first;       // start at beginning of list
      while(current != null)      // until end of list,
         {
         current.displayLink();   // print data
         current = current.next;  // move to next link
         }
      System.out.println("");
      }
   }  // end class SortedList
class PriorityQue{
	SortedList q;
	PriorityQue(){
		q = new SortedList();
	}
	public void insert(long item){
		q.insert(item);
	}
	public long remove(){
		return q.remove().dData;
	}
	public long peekMin(){
		return q.getFirst();
	}
	
	public boolean isEmpty(){
		return q.isEmpty();
	}
}
////////////////////////////////////////////////////////////////
public class SortedListApp
   {
   public static void main(String[] args)
      {                            
	   PriorityQue q = new PriorityQue();
	   q.insert(30);
	   q.insert(50);
	   q.insert(10);
	   q.insert(40);
	   q.insert(20);
	   System.out.println("min value is "+q.peekMin());
	   while(!q.isEmpty()){
		   long item = q.remove();
		   System.out.print(item + " ");
	   }
	   System.out.println(" ");
      }  // end main()
   }  // end class SortedListApp
////////////////////////////////////////////////////////////////