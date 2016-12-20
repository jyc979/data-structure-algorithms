package DS;
// DHeap.java
// demonstrates DHeaps
// to run this program: C>java DHeapApp

/*
 * Write a descending heap
 */
import java.io.*;
////////////////////////////////////////////////////////////////
class DNode
   {
   private int iData;             // data item (key)
// -------------------------------------------------------------
   public DNode(int key)           // constructor
      { iData = key; }
// -------------------------------------------------------------
   public int getKey()
      { return iData; }
// -------------------------------------------------------------
   public void setKey(int id)
      { iData = id; }
// -------------------------------------------------------------
   }  // end class DNode
////////////////////////////////////////////////////////////////
class DHeap
   {
   private DNode[] DHeapArray;
   private int maxSize;           // size of array
   private int currentSize;       // number of DNodes in array
// -------------------------------------------------------------
   public DHeap(int mx)            // constructor
      {
      maxSize = mx;
      currentSize = 0;
      DHeapArray = new DNode[maxSize];  // create array
      }
// -------------------------------------------------------------
   public boolean isEmpty()
      { return currentSize==0; }
// -------------------------------------------------------------
   public boolean insert(int key)
      {
      if(currentSize==maxSize)
         return false;
      DNode newDNode = new DNode(key);
      DHeapArray[currentSize] = newDNode;
      trickleUp(currentSize++);
      return true;
      }  // end insert()
// -------------------------------------------------------------
   public void trickleUp(int index)
      {
      int parent = (index-1) / 2;
      DNode bottom = DHeapArray[index];

      while( index > 0 &&
             DHeapArray[parent].getKey() > bottom.getKey() )
         {
         DHeapArray[index] = DHeapArray[parent];  // move it down
         index = parent;
         parent = (parent-1) / 2;
         }  // end while
      DHeapArray[index] = bottom;
      }  // end trickleUp()
// -------------------------------------------------------------
   public DNode remove()           // delete item with max key
      {                           // (assumes non-empty list)
      DNode root = DHeapArray[0];
      DHeapArray[0] = DHeapArray[--currentSize];
      trickleDown(0);
      return root;
      }  // end remove()
// -------------------------------------------------------------
   public void trickleDown(int index)
      {
      int largerChild;
      DNode top = DHeapArray[index];       // save root
      while(index < currentSize/2)       // while DNode has at
         {                               //    least one child,
         int leftChild = 2*index+1;
         int rightChild = leftChild+1;
                                         // find larger child
         if(rightChild < currentSize &&  // (rightChild exists?)
                             DHeapArray[leftChild].getKey() >
                             DHeapArray[rightChild].getKey())
            largerChild = rightChild;
         else
            largerChild = leftChild;
                                         // top >= largerChild?
         if( top.getKey() <= DHeapArray[largerChild].getKey() )
            break;
                                         // shift child up
         DHeapArray[index] = DHeapArray[largerChild];
         index = largerChild;            // go down
         }  // end while
      DHeapArray[index] = top;            // root to index
      }  // end trickleDown()
// -------------------------------------------------------------
   public boolean change(int index, int newValue)
      {
      if(index<0 || index>=currentSize)
         return false;
      int oldValue = DHeapArray[index].getKey(); // remember old
      DHeapArray[index].setKey(newValue);  // change to new

      if(oldValue > newValue)             // if raised,
         trickleUp(index);                // trickle it up
      else                                // if lowered,
         trickleDown(index);              // trickle it down
      return true;
      }  // end change()
// -------------------------------------------------------------
   public void displayDHeap()
      {
      System.out.print("DHeapArray: ");    // array format
      for(int m=0; m<currentSize; m++)
         if(DHeapArray[m] != null)
            System.out.print( DHeapArray[m].getKey() + " ");
         else
            System.out.print( "-- ");
      System.out.println();
                                          // DHeap format
      int nBlanks = 32;
      int itemsPerRow = 1;
      int column = 0;
      int j = 0;                          // current item
      String dots = "...............................";
      System.out.println(dots+dots);      // dotted top line

      while(currentSize > 0)              // for each DHeap item
         {
         if(column == 0)                  // first item in row?
            for(int k=0; k<nBlanks; k++)  // preceding blanks
               System.out.print(' ');
                                          // display item
         System.out.print(DHeapArray[j].getKey());

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
      }  // end displayDHeap()
// -------------------------------------------------------------
   }  // end class DHeap
////////////////////////////////////////////////////////////////
class HeapDApp //Ascending heap
   {
   public static void main(String[] args) throws IOException
      {
      int value, value2;
      DHeap theHeap = new DHeap(31);  // make a DHeap; max size 31
      boolean success;

      theHeap.insert(70);           // insert 10 items
      theHeap.insert(40);
      theHeap.insert(50);
      theHeap.insert(20);
      theHeap.insert(60);
      theHeap.insert(100);
      theHeap.insert(80);
      theHeap.insert(30);
      theHeap.insert(10);
      theHeap.insert(90);
      
      theHeap.displayDHeap();
      while(!theHeap.isEmpty()){
    	  System.out.println(theHeap.remove().getKey());
      }


      }  // end main()

  }  // end class DHeapApp
////////////////////////////////////////////////////////////////