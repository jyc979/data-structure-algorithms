package DS;
// HSHeapSort.java
// demonstrates HSHeap sort
// to run this program: C>java HSHeapSortApp
import java.io.*;
////////////////////////////////////////////////////////////////
class HSNode
   {
   private int iData;             // data item (key)
// -------------------------------------------------------------
   public HSNode(int key)           // constructor
      { iData = key; }
// -------------------------------------------------------------
   public int getKey()
      { return iData; }
// -------------------------------------------------------------
   }  // end class HSNode
////////////////////////////////////////////////////////////////
class HSHeap
   {
   private HSNode[] HSHeapArray;
   private int maxSize;           // size of array
   private int currentSize;       // number of items in array
// -------------------------------------------------------------
   public HSHeap(int mx)            // constructor
      {
      maxSize = mx;
      currentSize = 0;
      HSHeapArray = new HSNode[maxSize];
      }
// -------------------------------------------------------------
   public HSNode remove()           // delete item with max key
      {                           // (assumes non-empty list)
      HSNode root = HSHeapArray[0];
      HSHeapArray[0] = HSHeapArray[--currentSize];
      trickleDown(0);
      return root;
      }  // end remove()
// -------------------------------------------------------------
   public void trickleDown(int index)
      {
      int largerChild;
      HSNode top = HSHeapArray[index];        // save root
      while(index < currentSize/2)        // not on bottom row
         {
         int leftChild = 2*index+1;
         int rightChild = leftChild+1;
                                          // find larger child
         if(rightChild < currentSize &&   // right ch exists?
                             HSHeapArray[leftChild].getKey() <
                             HSHeapArray[rightChild].getKey())
            largerChild = rightChild;
         else
            largerChild = leftChild;
                                          // top >= largerChild?
         if(top.getKey() >= HSHeapArray[largerChild].getKey())
            break;
                                          // shift child up
         HSHeapArray[index] = HSHeapArray[largerChild];
         index = largerChild;             // go down
         }  // end while
      HSHeapArray[index] = top;             // root to index
      }  // end trickleDown()
// -------------------------------------------------------------
   public void displayHSHeap()
      {
      int nBlanks = 32;
      int itemsPerRow = 1;
      int column = 0;
      int j = 0;                          // current item
      String dots = "...............................";
      System.out.println(dots+dots);      // dotted top line

      while(currentSize > 0)              // for each HSHeap item
         {
         if(column == 0)                  // first item in row?
            for(int k=0; k<nBlanks; k++)  // preceding blanks
               System.out.print(' ');
                                          // display item
         System.out.print(HSHeapArray[j].getKey());

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
      }  // end displayHSHeap()
// -------------------------------------------------------------
   public void displayArray()
      {
      for(int j=0; j<maxSize; j++)
         System.out.print(HSHeapArray[j].getKey() + " ");
      System.out.println("");
      }
// -------------------------------------------------------------
   public void insertAt(int index, HSNode newHSNode)
      { HSHeapArray[index] = newHSNode; }
// -------------------------------------------------------------
   public void incrementSize()
      { currentSize++; }
// -------------------------------------------------------------
   }  // end class HSHeap
////////////////////////////////////////////////////////////////
class HeapSortApp
   {
   public static void main(String[] args) throws IOException
      {
      int size, j;

      System.out.print("Enter number of items: ");
      size = getInt();
      HSHeap theHSHeap = new HSHeap(size);

      for(j=0; j<size; j++)       // fill array with
         {                        //    random HSNodes
         int random = (int)(java.lang.Math.random()*100);
         HSNode newHSNode = new HSNode(random);
         theHSHeap.insertAt(j, newHSNode);
         theHSHeap.incrementSize();
         }

      System.out.print("Random: ");
         theHSHeap.displayArray();  // display random array

      for(j=size/2-1; j>=0; j--)  // make random array into HSHeap
         theHSHeap.trickleDown(j);

      System.out.print("HSHeap:   ");
      theHSHeap.displayArray();     // dislay HSHeap array
      theHSHeap.displayHSHeap();      // display HSHeap

      for(j=size-1; j>=0; j--)    // remove from HSHeap and
         {                        //    store at array end
         HSNode biggestHSNode = theHSHeap.remove();
         theHSHeap.insertAt(j, biggestHSNode);
         }
      System.out.print("Sorted: ");
      theHSHeap.displayArray();     // display sorted array
      }  // end main()
// -------------------------------------------------------------
   public static String getString() throws IOException
      {
      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(isr);
      String s = br.readLine();
      return s;
      }
//-------------------------------------------------------------
   public static int getInt() throws IOException
      {
      String s = getString();
      return Integer.parseInt(s);
      }
// -------------------------------------------------------------
  }  // end class HSHeapSortApp
////////////////////////////////////////////////////////////////
