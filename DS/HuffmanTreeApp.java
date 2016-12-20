package DS;
import java.util.*;
import java.io.*;
class HuffNode{
	public String iData;
	public int frequency;
	public HuffNode leftChild;
	public HuffNode rightChild;
	// ---------------------------------------------------------
	HuffNode(String d){
		this.iData = d;
		this.frequency = 0;
	}
	
	// ---------------------------------------------------------
	public void displayNode(){
		System.out.print('{');
	      System.out.print(iData);
	      System.out.print(", ");
	      System.out.print(frequency);
	      System.out.print("} ");
	}
	// ---------------------------------------------------------
}//end class HuffNode()
////////////////////////////////////////////////////////////////////
class HuffTree{
	public HuffNode root;
	public String sentence;
	public String[] table;
	// ---------------------------------------------------------
	public HuffTree(){
		root = null;
		table = new String[128];
		for(int i=0;i<table.length;i++)
			table[i] = "";
	}
	// ---------------------------------------------------------
	public boolean isLeaf(HuffNode localRoot){
		if(localRoot.leftChild == null && localRoot.rightChild == null)
			return true;
		else
			return false;
	}
	// ---------------------------------------------------------
	
	public void makeTree(){
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		for(int i=0;i<sentence.length();i++){ // make hashmap with characters.
			String ch = Character.toString(sentence.charAt(i));
			if(!map.containsKey(ch))
				map.put(ch, 1);
			else{
				int count = map.get(ch);
				count++;
				map.put(ch,count);
			}
		}//end for loop 
		// unknown error needs to be fixed.
		
		PriorityQueue<HuffNode> q = new PriorityQueue<HuffNode>(1,new Comparator<HuffNode>(){ //compare by frequency
			@Override public int compare(HuffNode a, HuffNode b){
				return Integer.compare(a.frequency,b.frequency);
			}
		});
		
		Set<String>keys = map.keySet(); //iterator
		Iterator<String> it = keys.iterator();
		while(it.hasNext()){ //add nodes into the que by frequency
			String key = it.next();
		
			HuffNode node = new HuffNode(key);
			node.frequency = map.get(key);
			q.add(node);
		}
		
		
		int size = 0; // make a subtree out of each pair of smaller subtrees until one.
		while(size!=1){
			HuffNode a = q.remove();
			HuffNode b = q.remove();
			int sum = a.frequency + b.frequency;
			HuffNode r = new HuffNode("NODE");
			r.leftChild = a;
			r.rightChild = b;
			r.frequency = sum;
			q.add(r);
			size = q.size();
		}
		root = q.peek();
		
		
		
		
			
	}//end makeTree()
	// ---------------------------------------------------------
	public void createTable(HuffNode localRoot,String code)
	{
		
		if(localRoot!= null){
			
			if(localRoot.iData != "NODE")
				table[(int)(localRoot.iData.charAt(0))] = code;
			
			if(!isLeaf(localRoot)){
				createTable(localRoot.leftChild,code+"0");
				createTable(localRoot.rightChild,code+"1");
			}
			
			
			
		}
	}
	
	// ---------------------------------------------------------
	public String encode(String msg){
		String encoded = "";
		for(int i=0;i<msg.length();i++){
			char ch = msg.charAt(i);
			encoded += table[(int)(ch)];
		}
		return encoded;
	}
	// ---------------------------------------------------------
	public String decode(String encoded){
		HuffNode current = root;
	
		String result = "";
		int subIndex =0; // substring index
		
		
		while(subIndex < encoded.length()){
			if(!isLeaf(current)){
				if(encoded.charAt(subIndex)=='0'){current = current.leftChild;}
				else if(encoded.charAt(subIndex)=='1'){current = current.rightChild;}
				subIndex++;
			}
			else{
				result += current.iData;
				current = root;
			}
		}
		result += current.iData;
		return result;
	}
	// ---------------------------------------------------------
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
	// ---------------------------------------------------------
	 private void preOrder(HuffNode localRoot)
     {
     if(localRoot != null)
        {
        System.out.print(localRoot.iData + " ");
        preOrder(localRoot.leftChild);
        preOrder(localRoot.rightChild);
        }
     }
//-------------------------------------------------------------
  private void inOrder(HuffNode localRoot)
     {
     if(localRoot != null)
        {
   	 System.out.print("(");
        inOrder(localRoot.leftChild);
        System.out.print(localRoot.iData + " ");
        inOrder(localRoot.rightChild);
        System.out.print(")");
        }
     }
//-------------------------------------------------------------
  private void postOrder(HuffNode localRoot)
     {
     if(localRoot != null)
        {
        postOrder(localRoot.leftChild);
        postOrder(localRoot.rightChild);
        System.out.print(localRoot.iData + " ");
        }
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
        HuffNode temp = (HuffNode)globalStack.pop();
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
  }  // end displayTree()
//-------------------------------------------------------------
  public void displayTable(){
	  for(int i=0;i<table.length;i++){
		  System.out.println(table[i]);
	  }
  }
//-------------------------------------------------------------
}//end class HuffTree();

public class HuffmanTreeApp {

	public static void main(String[] args) throws IOException {
		HuffTree theTree = new HuffTree();
		
		theTree.sentence = "Classic Act, MY man!"+"\n"+"hehe";
		theTree.makeTree();
		theTree.createTable(theTree.root, "");
		theTree.displayTable();
		String encode = theTree.encode(theTree.sentence);
		System.out.println(encode);
		System.out.println(theTree.decode(encode));
		
		
		
		
		

	}
	// -------------------------------------------------------------
	   public static String getString() throws IOException
	      {
	      InputStreamReader isr = new InputStreamReader(System.in);
	      BufferedReader br = new BufferedReader(isr);
	      String s = br.readLine();
	      return s;
	      }
	// -------------------------------------------------------------
	   public static char getChar() throws IOException
	      {
	      String s = getString();
	      return s.charAt(0);
	      }
	//-------------------------------------------------------------
	   public static int getInt() throws IOException
	      {
	      String s = getString();
	      return Integer.parseInt(s);
	      }
	// -------------------------------------------------------------

}
