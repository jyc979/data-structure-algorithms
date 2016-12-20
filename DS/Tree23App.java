package DS;
////////////////////////////////////////////////////////////////
class DataItem23
   {
   public long dData;          // one data item
//--------------------------------------------------------------
   public DataItem23(long dd)    // constructor
      { dData = dd; }
//--------------------------------------------------------------
   public void displayItem()   // display item, format "/27"
      { System.out.print("/"+dData); }
//--------------------------------------------------------------
   }  // end class DataItem
////////////////////////////////////////////////////////////////
class Node23
   {
   private static final int ORDER = 3;
   private int numItems;
   private Node23 parent;
   private Node23 childArray[] = new Node23[ORDER];
   private DataItem23 itemArray[] = new DataItem23[ORDER-1];


// -------------------------------------------------------------

   // connect child to this node
   public void connectChild(int childNum, Node23 child)
      {
      childArray[childNum] = child;
      if(child != null)
         child.parent = this;
      }
// -------------------------------------------------------------
   // disconnect child from this node, return it
   public Node23 disconnectChild(int childNum)
      {
      Node23 tempNode = childArray[childNum];
      childArray[childNum] = null;
      return tempNode;
      }
// -------------------------------------------------------------
   public Node23 getChild(int childNum)
      { return childArray[childNum]; }
// -------------------------------------------------------------
   public Node23 getParent()
      { return parent; }
// -------------------------------------------------------------
   public boolean isLeaf()
      { return (childArray[0]==null) ? true : false; }
// -------------------------------------------------------------
   public int getNumItems()
     { return numItems; }
// -------------------------------------------------------------
   public DataItem23 getItem(int index)   // get DataItem at index
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
   public int insertItem(DataItem23 newItem)
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
   public DataItem23 removeItem()        // remove largest item
      {
      // assumes node not empty
      DataItem23 temp = itemArray[numItems-1];  // save item
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
class Tree23
   {
   private Node23 root = new Node23();            // make root node
 

   
// -------------------------------------------------------------
   public int find(long key)
      {
      Node23 curNode = root;
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

   // insert a DataItem
   public void insert(long dValue)
      {
	   Node23 curNode = root;
	   DataItem23 tempItem = new DataItem23(dValue);
	   
	   while(!curNode.isLeaf())//down to leaf
	   		curNode = getNextChild(curNode,dValue);	
	   
	   if(!curNode.isFull())
		   curNode.insertItem(tempItem);
	   else{
		   split(curNode,tempItem);
		   
	   }
      }  // end insert()

// -------------------------------------------------------------
   public Node23 split(Node23 thisNode,DataItem23 tempItem)     // split the node
      {
      // assumes node is full
	   Node23 newRight = new Node23();
	   Node23 parent = thisNode.getParent();
	   Node23 sibling = new Node23();
	   DataItem23[] array = {tempItem,thisNode.getItem(0),thisNode.getItem(1)};
	   DataItem23[] sorted = sort(array);
	   if(thisNode==this.root){ //if curNode is root
		   
		   Node23 newRoot = new Node23();
		   newRoot.insertItem(sorted[1]);
		   newRoot.connectChild(0, thisNode);
		   newRoot.connectChild(1, newRight);
		   root = newRoot;
		   newRight.insertItem(sorted[2]);
		   thisNode.removeItem();
		   thisNode.removeItem();
		   thisNode.insertItem(sorted[0]);
		   return newRight;
		  
	   }
	   else{ //not root
		   if(!parent.isFull()){ //if parent is not full
			   int index = parent.insertItem(sorted[1]);
			   newRight.insertItem(sorted[2]);
			   thisNode.removeItem();
			   thisNode.removeItem();
		   	   thisNode.insertItem(sorted[0]);
		   	   int n = parent.getNumItems();
		   	   for(int i = n-1;i>index;i--){ //<< this part is important
		   		   Node23 temp = parent.disconnectChild(i);
		   		   parent.connectChild(i+1, temp);
		   	   }
		   	   parent.connectChild(index+1, newRight);
		   	   return newRight;
		   }
		   
		   else{ ///if parent is full
			   newRight.insertItem(sorted[2]);
			   thisNode.removeItem();
			   thisNode.removeItem();
			   thisNode.insertItem(sorted[0]);
			   sibling = split(parent,sorted[1]); //get the sibling of parent with this
			   if(thisNode==parent.getChild(0)){ //if leftmost child is full 
				   Node23 temp1 = parent.disconnectChild(1);
				   Node23 temp2 = parent.disconnectChild(2);
				   parent.connectChild(1, newRight);
				   sibling.connectChild(0, temp1);
				   sibling.connectChild(1, temp2);
				   
				   
			   }
			   else if(thisNode == parent.getChild(1)){ //if middle child is full
				   
				   Node23 temp = parent.disconnectChild(2);
				   sibling.connectChild(0, newRight);
				   sibling.connectChild(1, temp);
			   }
			   else if(thisNode == parent.getChild(2)){ //if rightmost child is full
				   Node23 temp = parent.disconnectChild(2);
				   sibling.connectChild(0, temp);
				   sibling.connectChild(1, newRight);
				   
			   }
			   return newRight;
		   }
	   }
	   
	   
	   		
	   	
      }  // end split()
// -------------------------------------------------------------
   public DataItem23[] sort(DataItem23[] array){
	   if(array[0].dData>array[1].dData)
		   swap(0,1,array);
	   if(array[0].dData>array[2].dData)
		   swap(0,2,array);
	   if(array[1].dData>array[2].dData)
		   swap(1,2,array);
	   return array;
   }

// -------------------------------------------------------------
   public void swap(int index1, int index2, DataItem23[] array){
	   DataItem23  temp;
	   temp = array[index1];
	   array[index1] = array[index2];
	   array[index2] = temp;
	   
   }
// -------------------------------------------------------------

   // gets appropriate child of node during search for value
   public Node23 getNextChild(Node23 theNode, long theValue)
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
   private void recDisplayTree(Node23 thisNode, int level,
                                              int childNumber)
      {
      System.out.print("level="+level+" child="+childNumber+" ");
      thisNode.displayNode();               // display this node

      // call ourselves for each child of this node
      int numItems = thisNode.getNumItems();
      for(int j=0; j<numItems+1; j++)
         {
         Node23 nextNode = thisNode.getChild(j);
         if(nextNode != null)
            recDisplayTree(nextNode, level+1, j);
         else
            return;
         }
      }  // end recDisplayTree()
// -------------------------------------------------------------\
   }  // end class Tree234
////////////////////////////////////////////////////////////////
public class Tree23App
   {
   public static void main(String[] args) 
      {
      Tree23 theTree = new Tree23();
      theTree.insert(60);
      theTree.insert(80);
      theTree.insert(40);
      theTree.insert(20);
   
 
      
    
     
      
   
      theTree.displayTree();
     
      
      


      
     
       
         
      }  // end main()

   }  // end class Tree234App
////////////////////////////////////////////////////////////////