package DS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
////////////////////////////////////////////////////////////////
class DataItem2
   {
   public long dData;          // one data item
//--------------------------------------------------------------
   public DataItem2(long dd)    // constructor
      { dData = dd; }
//--------------------------------------------------------------
   public void displayItem()   // display item, format "/27"
      { System.out.print("/"+dData); }
//--------------------------------------------------------------
   }  // end class DataItem
////////////////////////////////////////////////////////////////
class Node2
   {
   private static final int ORDER = 4;
   private int numItems;
   private Node2 parent;
   private Node2 childArray[] = new Node2[ORDER];
   private DataItem2 itemArray[] = new DataItem2[ORDER-1];


// -------------------------------------------------------------
   // connect child to this node
   public void connectChild(int childNum, Node2 child)
      {
      childArray[childNum] = child;
      if(child != null)
         child.parent = this;
      }
// -------------------------------------------------------------
   // disconnect child from this node, return it
   public Node2 disconnectChild(int childNum)
      {
      Node2 tempNode = childArray[childNum];
      childArray[childNum] = null;
      return tempNode;
      }
// -------------------------------------------------------------
   public Node2 getChild(int childNum)
      { return childArray[childNum]; }
// -------------------------------------------------------------
   public Node2 getParent()
      { return parent; }
// -------------------------------------------------------------
   public boolean isLeaf()
      { return (childArray[0]==null) ? true : false; }
// -------------------------------------------------------------
   public int getNumItems()
     { return numItems; }
// -------------------------------------------------------------
   public DataItem2 getItem(int index)   // get DataItem at index
      { return itemArray[index]; }
// -------------------------------------------------------------
   public boolean isFull()
      { return (numItems==ORDER-1) ? true : false; }
// -------------------------------------------------------------
   public int findItem(long key)       // return index of
      {                                    // item (within node)
      for(int j=0; j<ORDER-1; j++)         // if found,
         {                                 // otherwise,
         if(itemArray[j] == null)          // return -1
            break;
         else if(itemArray[j].dData == key)
            return j;
         }
      return -1;
      }  // end findItem
// -------------------------------------------------------------
   public int insertItem(DataItem2 newItem)
      {
      // assumes node is not full
      numItems++;                          // will add new item
      long newKey = newItem.dData;         // key of new item

      for(int j=ORDER-2; j>=0; j--)        // start on right,
         {                                 //    examine items
         if(itemArray[j] == null)          // if item null,
            continue;                      // go left one cell
         else                              // not null,
            {                              // get its key
            long itsKey = itemArray[j].dData;
            if(newKey < itsKey)            // if it's bigger
               itemArray[j+1] = itemArray[j]; // shift it right
            else
               {
               itemArray[j+1] = newItem;   // insert new item
               return j+1;                 // return index to
               }                           //    new item
            }  // end else (not null)
         }  // end for                     // shifted all items,
      itemArray[0] = newItem;              // insert new item
      return 0;
      }  // end insertItem()
// -------------------------------------------------------------
   public DataItem2 removeItem()        // remove largest item
      {
      // assumes node not empty
      DataItem2 temp = itemArray[numItems-1];  // save item
      itemArray[numItems-1] = null;           // disconnect it
      numItems--;                             // one less item
      return temp;                            // return item
      }
// -------------------------------------------------------------
   public void displayNode()           // format "/24/56/74/"
      {
      for(int j=0; j<numItems; j++)
         itemArray[j].displayItem();   // "/56"
      System.out.println("/");         // final "/"
      }
// -------------------------------------------------------------
   }  // end class Node
////////////////////////////////////////////////////////////////
class Tree2
   {
   private Node2 root = new Node2();            // make root node
   public long sortArray[];
// -------------------------------------------------------------
   public Node2 getRoot()
	   {return root;}
// -------------------------------------------------------------
   public int find(long key)
      {
      Node2 curNode = root;
      int childNumber;
      while(true)
         {
         if(( childNumber=curNode.findItem(key) ) != -1)
            return childNumber;               // found it
         else if( curNode.isLeaf() )
            return -1;                        // can't find it
         else                                 // search deeper
            curNode = getNextChild(curNode, key);
         }  // end while
      }
// -------------------------------------------------------------
   public long minVal(){
	   Node2 curNode = root;
	   while(!curNode.isLeaf()){
		   curNode = curNode.getChild(0);
	   }
	   
	   return curNode.getItem(0).dData;
		   
   }
// -------------------------------------------------------------
   // insert a DataItem
   public void insert(long dValue)
      {
      Node2 curNode = root;
      DataItem2 tempItem = new DataItem2(dValue);

      while(true)
         {
         if( curNode.isFull() )               // if node full,
            {
            split(curNode);                   // split it
            curNode = curNode.getParent();    // back up
                                              // search once
            curNode = getNextChild(curNode, dValue);
            }  // end if(node is full)

         else if( curNode.isLeaf() )          // if node is leaf,
            break;                            // go insert
         // node is not full, not a leaf; so go to lower level
         else
            curNode = getNextChild(curNode, dValue);
         }  // end while

      curNode.insertItem(tempItem);       // insert new DataItem
      }  // end insert()
// -------------------------------------------------------------
   public long[] sort(long[] array)
   {
	   for(int i=0; i<array.length;i++)
		   insert(array[i]);
	 
	   sortArray = new long[array.length];
	   sortRec(root,0);
	   return sortArray;
	   
		   
	   
   }

// -------------------------------------------------------------
   public int sortRec(Node2 thisNode, int start){
	   if(thisNode.isLeaf()){
		   for(int i=0;i<thisNode.getNumItems();i++)
			   sortArray[start++] = thisNode.getItem(i).dData;
		   return start;
	   }
	   else{
		   for(int i=0;i<thisNode.getNumItems()+1;i++){
			   start = sortRec(thisNode.getChild(i),start);
			   if(i<thisNode.getNumItems())
				   sortArray[start++] =thisNode.getItem(i).dData;
		   }
		   return start;
	   }
   }
// -------------------------------------------------------------
   public void inOrder(Node2 thisNode)
   {
	  if(thisNode.isLeaf()){
		  for(int i=0;i<thisNode.getNumItems();i++)
			  System.out.println(thisNode.getItem(i).dData);
		  return;
	  }
	  else{
		  for(int i=0;i<thisNode.getNumItems()+1;i++){
			  inOrder(thisNode.getChild(i));
			  if(i<thisNode.getNumItems())
				  System.out.println(thisNode.getItem(i).dData);
			  
		  }
		  
	  }
	   
   }
// -------------------------------------------------------------
   public void split(Node2 thisNode)     // split the node
      {
      // assumes node is full
      DataItem2 itemB, itemC;
      Node2 parent, child2, child3;
      int itemIndex;

      itemC = thisNode.removeItem();    // remove items from
      itemB = thisNode.removeItem();    // this node
      child2 = thisNode.disconnectChild(2); // remove children
      child3 = thisNode.disconnectChild(3); // from this node

      Node2 newRight = new Node2();       // make new node

      if(thisNode==root)                // if this is the root,
         {
         root = new Node2();                // make new root
         parent = root;                    // root is our parent
         root.connectChild(0, thisNode);   // connect to parent
         }
      else                              // this node not the root
         parent = thisNode.getParent();    // get parent

      // deal with parent
      itemIndex = parent.insertItem(itemB); // item B to parent
      int n = parent.getNumItems();         // total items?

      for(int j=n-1; j>itemIndex; j--)          // move parent's
         {                                      // connections
         Node2 temp = parent.disconnectChild(j); // one child
         parent.connectChild(j+1, temp);        // to the right
         }
                                   // connect newRight to parent
      parent.connectChild(itemIndex+1, newRight);

      // deal with newRight
      newRight.insertItem(itemC);       // item C to newRight
      newRight.connectChild(0, child2); // connect to 0 and 1
      newRight.connectChild(1, child3); // on newRight
      }  // end split()
// -------------------------------------------------------------
   // gets appropriate child of node during search for value
   public Node2 getNextChild(Node2 theNode, long theValue)
      {
      int j;
      // assumes node is not empty, not full, not a leaf
      int numItems = theNode.getNumItems();
      for(j=0; j<numItems; j++)          // for each item in node
         {                               // are we less?
         if( theValue < theNode.getItem(j).dData )
            return theNode.getChild(j);  // return left child
         }  // end for                   // we're greater, so
      return theNode.getChild(j);        // return right child
      }
// -------------------------------------------------------------
   public void displayTree()
      {
      recDisplayTree(root, 0, 0);
      }
// -------------------------------------------------------------
   private void recDisplayTree(Node2 thisNode, int level,
                                              int childNumber)
      {
      System.out.print("level="+level+" child="+childNumber+" ");
      thisNode.displayNode();               // display this node

      // call ourselves for each child of this node
      int numItems = thisNode.getNumItems();
      for(int j=0; j<numItems+1; j++)
         {
         Node2 nextNode = thisNode.getChild(j);
         if(nextNode != null)
            recDisplayTree(nextNode, level+1, j);
         else
            return;
         }
      }  // end recDisplayTree()
// -------------------------------------------------------------\
   }  // end class Tree234
////////////////////////////////////////////////////////////////
public class Tree234Ex
   {
   public static void main(String[] args) 
      {
      Tree2 theTree = new Tree2();
      long[] ar = {2,4,1,5,6,7,0,9,8,11};
      long[] sort = theTree.sort(ar);
      for(int i=0;i<ar.length;i++)
    	  System.out.print(sort[i]+" ");
      
      


      
     
       
         
      }  // end main()

   }  // end class Tree234App
////////////////////////////////////////////////////////////////