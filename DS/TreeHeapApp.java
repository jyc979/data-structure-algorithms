package DS;

import java.util.Stack;

class NodeTH{
	public NodeTH rightChild;
	public NodeTH leftChild;
	public NodeTH parent;
	private int key;
	//-------------------------------------------------------------
	public NodeTH(int key){
		this.key = key;
	}
	//-------------------------------------------------------------
	public int getKey(){
		return key;
	}
	//-------------------------------------------------------------
	public void setKey(int key)
	{this.key = key;}
	//-------------------------------------------------------------
	
}


////////////////////////////////////////////////////////////////
class TreeHeap
{
private NodeTH root;             // first node of tree
private int currentSize;

//-------------------------------------------------------------
public String binaryT(int numb)
{

	String path = "";
	int j=0;
	while(numb>=1)
	{
		path += String.valueOf(numb%2);
		numb = numb/2;
		j++;
	}
	return path;
}

//-------------------------------------------------------------
public TreeHeap()                  
   { 
	 root = null; 
     currentSize =0;
   }

//-------------------------------------------------------------
public void insert(int key)
	{
	NodeTH newNode = new NodeTH(key);
	
	if(root == null)
		root = newNode;
	
	else{
		String bin = binaryT(currentSize+1);
		NodeTH current = root;
			for(int i=bin.length()-2;i>=0;i--)
			{
			newNode.parent = current;
			if(bin.charAt(i)=='0')
				current = current.leftChild;
			else
				current = current.rightChild;
			}
		
		if(newNode.parent.leftChild == null)
			newNode.parent.leftChild  = newNode;
		else if(newNode.parent.rightChild == null)
			newNode.parent.rightChild = newNode;
		trickleUp(newNode);
	}
	currentSize++;
	}
//-------------------------------------------------------------
public void remove()
{
	String bin = binaryT(currentSize--);
	NodeTH current = root;
	for(int i=bin.length()-2;i>=0;i--)
	{
		if(bin.charAt(i)=='0')
			current = current.leftChild;
		else
			current = current.rightChild;
	}
		
	if(current == root)
		root = null;
	else{
		
		root.setKey(current.getKey());
		if(current.parent.rightChild == current)
			current.parent.rightChild = null;
		else
			current.parent.leftChild = null;
		trickleDown(root);
	}
	
}
//-------------------------------------------------------------
public void trickleDown(NodeTH node)
{
	int temp = node.getKey();
	NodeTH largerChild = new NodeTH(1);
	NodeTH current = node;
	while(current.leftChild!=null||current.rightChild!=null){
		if(current.rightChild!=null&&current.rightChild.getKey()>current.leftChild.getKey())
			largerChild = current.rightChild;
		else
			largerChild = current.leftChild;
		if(largerChild.getKey()<=temp)
			break;
		current.setKey(largerChild.getKey());
		current = largerChild;
		
		
	}
	current.setKey(temp);
	
	
	
}
//-------------------------------------------------------------
public void trickleUp(NodeTH newNode)
{
	int temp = newNode.getKey();
	NodeTH current = newNode;
	while(current.parent != null &&temp>current.parent.getKey())
	{
		current.setKey(current.parent.getKey());
		current = current.parent;
	}
	current.setKey(temp);
	
}
//-------------------------------------------------------------
public boolean change(int nodeNumb, int newValue)
{
	if(nodeNumb>currentSize||nodeNumb<0)
		return false;
	NodeTH change = root;
	String bin = binaryT(nodeNumb);
	for(int i=bin.length()-2;i>=0;i--)
	{
		if(bin.charAt(i)=='0')
			change = change.leftChild;
		else
			change = change.rightChild;
	}
	int temp = change.getKey();
	change.setKey(newValue);
	if(temp>newValue)
		trickleDown(change);
	else
		trickleUp(change);
	
	return true;
}
//-------------------------------------------------------------


public void displayTree()
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
      NodeTH temp = (NodeTH)globalStack.pop();
      if(temp != null)
         {
         System.out.print(temp.getKey());
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
}  // end displayTree()
}  // end class Tree
public class TreeHeapApp {

	public static void main(String[] args) {
		TreeHeap tree = new TreeHeap();
		tree.insert(90);
		tree.insert(80);
		tree.insert(100);
		tree.insert(150);
		tree.insert(120);
		tree.insert(170);
		tree.change(3, 15);
		tree.change(5, 60);
		tree.insert(60000);
		
		
		
		tree.displayTree();
		
	
		
	
	
	}

}
