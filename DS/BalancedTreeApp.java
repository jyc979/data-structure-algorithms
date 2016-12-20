package DS;
import java.io.*;
import java.util.*;
class AbcNode
{
	public String sData;			//data item (key)
	public AbcNode leftChild;
	public AbcNode rightChild;
	
	public void displayNode()
	{
		System.out.print("{" + sData + "}");
	}
}
class BalancedTree
{
	private AbcNode root;			//first node of tree
	
	public BalancedTree(String initString)
		{ 
			root = null;
			int nElems = 0; //# of elements in the "forest"/node-array
			
			//Parse the input string into an array of single-letter trees
			AbcNode[] nodeArray = new AbcNode[initString.length()];
			for(int i = 0; i < initString.length(); i++)
			{
				nodeArray[i] = new AbcNode();
				nodeArray[i].sData = String.valueOf(initString.charAt(i));
				nElems++;
			}
			
			//Keep combining nodes into trees until there is only one left
			while(nElems > 1)
			{
				int fillCounter = 0;
				for(int j = 0; j < nElems; j++)
				{
					if(j%2 == 1)
					{
						AbcNode tempNode = new AbcNode();
						tempNode.sData = "+";
						tempNode.leftChild = nodeArray[j-1];
						tempNode.rightChild = nodeArray[j];
						nodeArray[fillCounter] = tempNode;
						fillCounter++;
					}
					if(j%2 == 0 && j == nElems-1) //if there's a leftover node
					{
						nodeArray[fillCounter] = nodeArray[j];
					}
				}
				//account for combined items
				nElems -= fillCounter;
			}
			root = nodeArray[0];
		}
	
	public void traverse(int traverseType)
	{
		switch(traverseType)
		{
		case 1: System.out.print("\nPreorder traversal: ");
				preOrder(root);
				break;
		case 2: System.out.print("\nInorder traversal: ");
				inOrder(root);
				break;
		case 3: System.out.print("\nPostorder traversal: ");
				postOrder(root);
				break;
		}
		System.out.println("");
	}
	
	private void preOrder(AbcNode localRoot)
	{
		if(localRoot != null)
		{
			System.out.print(localRoot.sData + " ");
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
	}
	
	private void inOrder(AbcNode localRoot)
	{
		if(localRoot != null)
		{
			inOrder(localRoot.leftChild);
			System.out.print(localRoot.sData + " ");
			inOrder(localRoot.rightChild);
		}
	}
	
	private void postOrder(AbcNode localRoot)
	{
		if(localRoot != null)
		{
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			System.out.print(localRoot.sData + " ");
		}
	}
	
	public void displayTree()
	{
		Stack<AbcNode> globalStack = new Stack<AbcNode>();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out.println(
		".......................................................");
		while(isRowEmpty==false)
		{
			Stack<AbcNode> localStack = new Stack<AbcNode>();
			isRowEmpty = true;
			
			for(int j = 0; j < nBlanks; j++)
				System.out.print(" ");
			
			while(globalStack.isEmpty() == false)
			{
				AbcNode temp = globalStack.pop();
				if(temp != null)
				{
					System.out.print(temp.sData);
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
				for(int j = 0; j < nBlanks*2 - 2; j++)
					System.out.print(" ");
			} //end while globalStack not empty
			System.out.println();
			nBlanks /= 2;
			while(localStack.isEmpty() == false)
				globalStack.push( localStack.pop() );
		} //end while isRowEmpty is false
		System.out.println(
		".......................................................");
	} //end displayTree()
} //end class AbcTree

class BalancedTreeApp
{
	public static void main(String[] args) throws IOException
	{
		int value;
		String initString = "ABCDE";
		BalancedTree theTree = new BalancedTree(initString);
		theTree.displayTree();
		
	} // end main()
	
	public static String getString() throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
	
	public static char getChar() throws IOException
	{
		String s = getString();
		return s.charAt(0);
	}
	
	public static int getInt() throws IOException
	{
		String s = getString();
		return Integer.parseInt(s);
	}
}