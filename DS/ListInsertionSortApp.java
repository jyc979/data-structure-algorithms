package DS;
// listInsertionSort.java
// demonstrates sorted list used for sorting
// to run this program: C>java ListInsertionSortApp
////////////////////////////////////////////////////////////////
class Link6
   {
   public long dData;                  // data item
   public Link6 next;                   // next link in list
// -------------------------------------------------------------
   public Link6(long dd)                // constructor
      { dData = dd; }
// -------------------------------------------------------------
   }  // end class Link
////////////////////////////////////////////////////////////////
class SortedList2
   {
   private Link6 first;            // ref to first item on list
// -------------------------------------------------------------
   public SortedList2()            // constructor (no args)
      { first = null; }                    // initialize list
// -------------------------------------------------------------
   public SortedList2(Link6[] linkArr)  // constructor (array
      {                               // as argument)
      first = null;                        // initialize list
       for(int j=0;j<linkArr.length;j++)// copy array
                 insert(linkArr[j]);// to list
      }
// -------------------------------------------------------------
   public void insert(Link6 k)     // insert (in order)
      {
      Link6 previous = null;            // start at first
      Link6 current = first;
                                       // until end of list,
      while(current != null && k.dData > current.dData)
         {                             // or key > current,
         previous = current;
         current = current.next;       // go to next item
         }
      if(previous==null)               // at beginning of list
         first = k;                    // first --> k
      else                             // not at beginning
         previous.next = k;            // old prev --> k
      k.next = current;                // k --> old currnt
      }  // end insert()
// -------------------------------------------------------------
   public Link6 remove()           // return & delete first link
      {                           // (assumes non-empty list)
      Link6 temp = first;               // save first
      first = first.next;              // delete first
      return temp;                     // return value
      }
// -------------------------------------------------------------
   }  // end class SortedList
////////////////////////////////////////////////////////////////
class ListInsertionSortApp
   {
   public static void main(String[] args)
      {
      int size = 10;
                                 // create array of links
      Link6[] linkArray = new Link6[size];
      
      for(int j=0;j<size;j++){//fill array with links
    	  int n = (int)(java.lang.Math.random()*99);
    	  Link6 newLink = new Link6(n); //make link
    	  linkArray[j] = newLink; //put in array
      }
      System.out.print("Unsorted array: ");
      for(int j=0;j<size;j++)
    	  System.out.print(linkArray[j].dData+" ");
      System.out.println(" ");
      						//create new list
      						//initialized with array
      SortedList2 theSorted = new SortedList2(linkArray);
      
      for(int j=0;j<size;j++) // links from list to array
    	  linkArray[j]=theSorted.remove();
      					//display array contents
      System.out.print("Sorted Array:   ");
      for(int j=0;j<size;j++)
    	  System.out.print(linkArray[j].dData+" ");
      System.out.println(" ");
      }  // end main()
   }  // end class ListInsertionSortApp
////////////////////////////////////////////////////////////////