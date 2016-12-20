package DS;
////////////////////////////////////////////////////////////////
/*
 * add a toss() method and restoreHeap()
 */
class HeapEx
   {
   private HeapNode[] HeapArray;
   private int maxSize;           // size of array
   private int currentSize;       // number of HeapNodes in array
// -------------------------------------------------------------
   public int getTop(){
	   return HeapArray[0].getKey();
   }
// -------------------------------------------------------------
   public HeapEx(int mx)            // constructor
      {
      maxSize = mx;
      currentSize = 0;
      HeapArray = new HeapNode[maxSize];  // create array
      }
// -------------------------------------------------------------
   public boolean isFull()
   {return currentSize == maxSize;}
// -------------------------------------------------------------
   public boolean isEmpty()
      { return currentSize==0; }
// -------------------------------------------------------------
   public void restoreHeap()
   {
	   int size = currentSize;
	   int j;
	   for(j=size/2-1;j>=0;j--)
		   trickleDown(j);
   }
// -------------------------------------------------------------
   public boolean toss(int key)
   {
	   if(currentSize == maxSize)
		   return false;
	   HeapNode newHeapNode = new HeapNode(key);
	   HeapArray[currentSize++] = newHeapNode;
	   return true;
   }
// -------------------------------------------------------------
   public boolean insert(int key)
      {
      if(currentSize==maxSize)
         return false;
      HeapNode newHeapNode = new HeapNode(key);
      HeapArray[currentSize] = newHeapNode;
      trickleUp(currentSize++);
      return true;
      }  // end insert()
// -------------------------------------------------------------
   public void trickleUp(int index)
      {
      int parent = (index-1) / 2;
      HeapNode bottom = HeapArray[index];

      while( index > 0 &&
             HeapArray[parent].getKey() < bottom.getKey() )
         {
         HeapArray[index] = HeapArray[parent];  // move it down
         index = parent;
         parent = (parent-1) / 2;
         }  // end while
      HeapArray[index] = bottom;
      }  // end trickleUp()
// -------------------------------------------------------------
   public HeapNode remove()           // delete item with max key
      {                           // (assumes non-empty list)
      HeapNode root = HeapArray[0];
      HeapArray[0] = HeapArray[--currentSize];
      trickleDown(0);
      return root;
      }  // end remove()
// -------------------------------------------------------------
   public void trickleDown(int index)
      {
      int largerChild;
      HeapNode top = HeapArray[index];       // save root
      while(index < currentSize/2)       // while HeapNode has at
         {                               //    least one child,
         int leftChild = 2*index+1;
         int rightChild = leftChild+1;
                                         // find larger child
         if(rightChild < currentSize &&  // (rightChild exists?)
                             HeapArray[leftChild].getKey() <
                             HeapArray[rightChild].getKey())
            largerChild = rightChild;
         else
            largerChild = leftChild;
                                         // top >= largerChild?
         if( top.getKey() >= HeapArray[largerChild].getKey() )
            break;
                                         // shift child up
         HeapArray[index] = HeapArray[largerChild];
         index = largerChild;            // go down
         }  // end while
      HeapArray[index] = top;            // root to index
      }  // end trickleDown()
// -------------------------------------------------------------
   public boolean change(int index, int newValue)
      {
      if(index<0 || index>=currentSize)
         return false;
      int oldValue = HeapArray[index].getKey(); // remember old
      HeapArray[index].setKey(newValue);  // change to new

      if(oldValue < newValue)             // if raised,
         trickleUp(index);                // trickle it up
      else                                // if lowered,
         trickleDown(index);              // trickle it down
      return true;
      }  // end change()
// -------------------------------------------------------------
   public void displayHeapEx()
      {
      System.out.print("HeapArray: ");    // array format
      for(int m=0; m<currentSize; m++)
         if(HeapArray[m] != null)
            System.out.print( HeapArray[m].getKey() + " ");
         else
            System.out.print( "-- ");
      System.out.println();
                                          // HeapEx format
      int nBlanks = 32;
      int itemsPerRow = 1;
      int column = 0;
      int j = 0;                          // current item
      String dots = "...............................";
      System.out.println(dots+dots);      // dotted top line

      while(currentSize > 0)              // for each HeapEx item
         {
         if(column == 0)                  // first item in row?
            for(int k=0; k<nBlanks; k++)  // preceding blanks
               System.out.print(' ');
                                          // display item
         System.out.print(HeapArray[j].getKey());

         if(++j == currentSize)           // done?
            break;

         if(++column==itemsPerRow)        // end of row?
            {
            nBlanks /= 2;                 // half the blanks
            itemsPerRow *= 2;             // twice the items
            column = 0;                   // start over on
            System.out.println();         //    new row
            }
         else                             // next item on row
            for(int k=0; k<nBlanks*2-2; k++)
               System.out.print(' ');     // interim blanks
         }  // end for
      System.out.println("\n"+dots+dots); // dotted bottom line
      }  // end displayHeapEx()
// -------------------------------------------------------------
   }  // end class HeapEx
class HeapPriorityQue
{
	HeapEx theHeap;
	HeapPriorityQue(){
		theHeap = new HeapEx(31);
	}
	// -------------------------------------------------------------
	public void insert(int key)
	{theHeap.insert(key);}
	// -------------------------------------------------------------
	public int remove()
	{return theHeap.remove().getKey();}
	// -------------------------------------------------------------
	public int peekMax()
	{return theHeap.getTop();}
	// -------------------------------------------------------------
	public boolean isEmpty()
	{return theHeap.isEmpty();}
	// -------------------------------------------------------------
	public boolean isFull()
	{return theHeap.isFull();}
}
////////////////////////////////////////////////////////////////
class HeapExApp
   {
   public static void main(String[] args)
      {

      HeapEx theHeap = new HeapEx(31);  // make a HeapEx; max size 31
      boolean success;
      HeapPriorityQue hq = new HeapPriorityQue();
      hq.insert(5);
      hq.insert(6);
      hq.insert(10);
      hq.insert(8);
      hq.insert(1);
      hq.insert(9);
      System.out.print(hq.remove());
      
     
      

 
      }  // end main()

  }  // end class HeapExApp
////////////////////////////////////////////////////////////////
