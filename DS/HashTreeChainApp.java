package DS;
import java.io.BufferedReader;

class CNode
{
public int iData;              // data item (key)
public CNode leftChild;         // this CNode's left child
public CNode rightChild;        // this CNode's right child
//------------------------------------------------------------------
public int getKey()
	{return iData;}
//------------------------------------------------------------------
}
////////////////////////////////////////////////////////////////
class CTree
{
private CNode root;             // first CNode of CTree
private int numItems = 0;
//-------------------------------------------------------------
public CNode getRoot(){
	return root;
}
//-------------------------------------------------------------
public CNode find(int key)      // find CNode with given key
   {
	if(numItems>0){
   CNode current = root;               // start at root
   while(current.iData != key)        // while no match,
      {
      if(key < current.iData)         // go left?
         current = current.leftChild;
      else                            // or go right?
         current = current.rightChild;
      if(current == null)             // if no child,
         return null;                 // didn't find it
      }
   return current;
	}
	else
		return null;
   }  // end find()
//-------------------------------------------------------------
public void insert(int id)
   {
   CNode newCNode = new CNode();    // make new CNode
   newCNode.iData = id;           // insert data
   if(root==null)                // no CNode in root
      root = newCNode;
   else                          // root occupied
      {
      CNode current = root;       // start at root
      CNode parent;
      while(true)                // (exits internally)
         {
         parent = current;
         if(id < current.iData)  // go left?
            {
            current = current.leftChild;
            if(current == null)  // if end of the line,
               {                 // insert on left
               parent.leftChild = newCNode;
               return;
               }
            }  // end if go left
         else                    // or go right?
            {
            current = current.rightChild;
            if(current == null)  // if end of the line
               {                 // insert on right
               parent.rightChild = newCNode;
               return;
               }
            }  // end else go right
         }  // end while
      }  // end else not root
   numItems++;
   }  // end insert()



   
//-------------------------------------------------------------
public void inOrder(CNode localRoot)
   {
   if(localRoot != null)
      {
      inOrder(localRoot.leftChild);
      System.out.print(localRoot.iData + " ");
      inOrder(localRoot.rightChild);
      }
   }


//-------------------------------------------------------------
}  // end class CTree
////////////////////////////////////////////////////////////////
class HashTreeChainTable
   {
   private CTree[] hashArray;   // array of lists
   private int arraySize;
// -------------------------------------------------------------
   public HashTreeChainTable(int size)        // constructor
      {
      arraySize = size;
      hashArray = new CTree[arraySize];  // create array
      for(int j=0; j<arraySize; j++)          // fill array
         hashArray[j] = new CTree();     // with lists
      }
// -------------------------------------------------------------
   public void displayTable()
      {
      for(int j=0; j<arraySize; j++) // for each cell,
         {
         System.out.print("("+j+")" + ". "); // display cell number
         
         hashArray[j].inOrder(hashArray[j].getRoot()); // display list
         }
      }
// -------------------------------------------------------------
   public int hashFunc(int key)      // hash function
      {
      return key % arraySize;
      }
// -------------------------------------------------------------
   public void insert(CNode node)  // insert a link
      {
      int key = node.getKey();
      int hashVal = hashFunc(key);   // hash the key
      if(find(key)!=null)
    	  System.out.println("No duplicates");
      else
      hashArray[hashVal].insert(key); // insert at hashVal
      }  // end insert()
      
// -------------------------------------------------------------
   public CNode find(int key)         // find link
      {
      int hashVal = hashFunc(key);   // hash the key
      CNode node = hashArray[hashVal].find(key);  // get link
      return node;                // return link
      }
// -------------------------------------------------------------
   }  // end class HashTable
////////////////////////////////////////////////////////////////
class HashTreeChainApp
   {
   public static void main(String[] args) 
      {
      int aKey;
      CNode aDataItem;
      int size, n, keysPerCell = 100;
                                     // get sizes
    
      size = 7;
      
      n = 13;
                                     // make table
      HashTreeChainTable theTable = new HashTreeChainTable(size);

      for(int j=0; j<n; j++)         // insert data
         {
         aKey = (int)(java.lang.Math.random() *
                                          keysPerCell * size);
         System.out.println(aKey);
         aDataItem = new CNode();
         aDataItem.iData = aKey;
         theTable.insert(aDataItem);
         }
      aDataItem = new CNode();
      aDataItem.iData = 15;
      theTable.insert(aDataItem);
      CNode cDataItem = new CNode();
      cDataItem.iData = 15;
      theTable.insert(cDataItem);
      
      theTable.displayTable();
    
      }  // end main()

   }  // end class HashChainApp