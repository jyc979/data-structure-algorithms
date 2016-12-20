package DS;

import java.util.Stack;


class TreePQ
{
private Node root;             // first node of TreePQ

//-------------------------------------------------------------
public TreePQ()                  // constructor
   { root = null; }            // no nodes in TreePQ yet
//-------------------------------------------------------------
public boolean isEmpty()
{return root==null;}
//-------------------------------------------------------------
public Node find(int key)      // find node with given key
   {                           // (assumes non-empty TreePQ)
   Node current = root;               // start at root
   while(current.iData != key)        // while no match,
      {
      if(key < current.iData)         // go left?
         current = current.leftChild;
      else                            // or go right?
         current = current.rightChild;
      if(current == null)             // if no child,
         return null;                 // didn't find it
      }
   return current;                    // found it
   }  // end find()
//-------------------------------------------------------------
public int removeMax()
{
	Node current = root;
	Node parent = current;
	
	while(current !=null)
	{
		
		parent = current;
		current = current.rightChild;
	}
	int max = parent.iData;
	delete(parent.iData);
	
	return max;
	
	
	
	
}
//-------------------------------------------------------------
public void insert(int id, double dd)
   {
   Node newNode = new Node();    // make new node
   newNode.iData = id;           // insert data
   newNode.dData = dd;
   if(root==null)                // no node in root
      root = newNode;
   else                          // root occupied
      {
      Node current = root;       // start at root
      Node parent;
      while(true)                // (exits internally)
         {
         parent = current;
         if(id < current.iData)  // go left?
            {
            current = current.leftChild;
            if(current == null)  // if end of the line,
               {                 // insert on left
               parent.leftChild = newNode;
               return;
               }
            }  // end if go left
         else                    // or go right?
            {
            current = current.rightChild;
            if(current == null)  // if end of the line
               {                 // insert on right
               parent.rightChild = newNode;
               return;
               }
            }  // end else go right
         }  // end while
      }  // end else not root
   }  // end insert()
//-------------------------------------------------------------
public boolean delete(int key) // delete node with given key
   {                           // (assumes non-empty list)
   Node current = root;
   Node parent = root;
   boolean isLeftChild = true;

   while(current.iData != key)        // search for node
      {
      parent = current;
      if(key < current.iData)         // go left?
         {
         isLeftChild = true;
         current = current.leftChild;
         }
      else                            // or go right?
         {
         isLeftChild = false;
         current = current.rightChild;
         }
      if(current == null)             // end of the line,
         return false;                // didn't find it
      }  // end while
   // found node to delete

   // if no children, simply delete it
   if(current.leftChild==null &&
                                current.rightChild==null)
      {
      if(current == root)             // if root,
         root = null;                 // TreePQ is empty
      else if(isLeftChild)
         parent.leftChild = null;     // disconnect
      else                            // from parent
         parent.rightChild = null;
      }

   // if no right child, replace with left subTreePQ
   else if(current.rightChild==null)
      if(current == root)
         root = current.leftChild;
      else if(isLeftChild)
         parent.leftChild = current.leftChild;
      else
         parent.rightChild = current.leftChild;

   // if no left child, replace with right subTreePQ
   else if(current.leftChild==null)
      if(current == root)
         root = current.rightChild;
      else if(isLeftChild)
         parent.leftChild = current.rightChild;
      else
         parent.rightChild = current.rightChild;

   else  // two children, so replace with inorder successor
      {
      // get successor of node to delete (current)
      Node successor = getSuccessor(current);

      // connect parent of current to successor instead
      if(current == root)
         root = successor;
      else if(isLeftChild)
         parent.leftChild = successor;
      else
         parent.rightChild = successor;

      // connect successor to current's left child
      successor.leftChild = current.leftChild;
      }  // end else two children
   // (successor cannot have a left child)
   return true;                                // success
   }  // end delete()
//-------------------------------------------------------------
// returns node with next-highest value after delNode
// goes to right child, then right child's left descendents
private Node getSuccessor(Node delNode)
   {
   Node successorParent = delNode;
   Node successor = delNode;
   Node current = delNode.rightChild;   // go to right child
   while(current != null)               // until no more
      {                                 // left children,
      successorParent = successor;
      successor = current;
      current = current.leftChild;      // go to left child
      }
                                        // if successor not
   if(successor != delNode.rightChild)  // right child,
      {                                 // make connections
      successorParent.leftChild = successor.rightChild;
      successor.rightChild = delNode.rightChild;
      }
   return successor;
   }
//-------------------------------------------------------------
public void traverse(int traverseType)
   {
   switch(traverseType)
      {
      case 1: System.out.print("\nPreorder traversal: ");
              preOrder(root);
              break;
      case 2: System.out.print("\nInorder traversal:  ");
              inOrder(root);
              break;
      case 3: System.out.print("\nPostorder traversal: ");
              postOrder(root);
              break;
      }
   System.out.println();
   }
//-------------------------------------------------------------
private void preOrder(Node localRoot)
   {
   if(localRoot != null)
      {
      System.out.print(localRoot.iData + " ");
      preOrder(localRoot.leftChild);
      preOrder(localRoot.rightChild);
      }
   }
//-------------------------------------------------------------
private void inOrder(Node localRoot)
   {
   if(localRoot != null)
      {
      inOrder(localRoot.leftChild);
      System.out.print(localRoot.iData + " ");
      inOrder(localRoot.rightChild);
      }
   }
//-------------------------------------------------------------
private void postOrder(Node localRoot)
   {
   if(localRoot != null)
      {
      postOrder(localRoot.leftChild);
      postOrder(localRoot.rightChild);
      System.out.print(localRoot.iData + " ");
      }
   }
//-------------------------------------------------------------
public void displayTreePQ()
   {
   Stack globalStack = new Stack();
   globalStack.push(root);
   int nBlanks = 32;
   boolean isRowEmpty = false;
   System.out.println(
   "......................................................");
   while(isRowEmpty==false)
      {
      Stack localStack = new Stack();
      isRowEmpty = true;

      for(int j=0; j<nBlanks; j++)
         System.out.print(' ');

      while(globalStack.isEmpty()==false)
         {
         Node temp = (Node)globalStack.pop();
         if(temp != null)
            {
            System.out.print(temp.iData);
            localStack.push(temp.leftChild);
            localStack.push(temp.rightChild);

            if(temp.leftChild != null ||
                                temp.rightChild != null)
               isRowEmpty = false;
            }
         else
            {
            System.out.print("--");
            localStack.push(null);
            localStack.push(null);
            }
         for(int j=0; j<nBlanks*2-2; j++)
            System.out.print(' ');
         }  // end while globalStack not empty
      System.out.println();
      nBlanks /= 2;
      while(localStack.isEmpty()==false)
         globalStack.push( localStack.pop() );
      }  // end while isRowEmpty is false
   System.out.println(
   "......................................................");
   }  // end displayTreePQ()
//-------------------------------------------------------------
}  // end class TreePQ
class PriorityQBST
{
	public TreePQ tree;
	PriorityQBST(){
		tree = new TreePQ();
	}
	// -------------------------------------------------------------
	public void insert(int key)
	{tree.insert(key,0);}
	// -------------------------------------------------------------
	public int remove()
	{return tree.removeMax();}
	// -------------------------------------------------------------
	
	public boolean isEmpty()
	{return tree.isEmpty();}
	// -------------------------------------------------------------
	public void change(int oldV, int newV)
	{
		if(tree.delete(oldV))
			tree.insert(newV, 0);
		else
			System.out.println("Can't, non-existant");
	}
	//--------------------------------------------------------------
	public void display()
	{tree.displayTreePQ();}
	//--------------------------------------------------------------
	
}
public class PriorityQBSTApp {

	public static void main(String[] args) {
		PriorityQBST pq = new PriorityQBST();
		pq.insert(50);
		pq.insert(60);
		pq.insert(70);
		pq.insert(40);
		pq.insert(30);
		pq.insert(90);
		pq.insert(10);
		pq.insert(80);
		pq.display();
		pq.change(80, 85);
		System.out.println(pq.remove());
		pq.display();
		System.out.println(pq.remove());
		pq.display();
		System.out.println(pq.remove());
		pq.display();
		System.out.println(pq.remove());
		pq.display();
		System.out.println(pq.remove());
		pq.display();
		
		

	}

}