package DS;

import java.util.*;               // for Stack class
////////////////////////////////////////////////////////////////
class StringNode
   {
   public char iData;              // data item (key)
   public StringNode leftChild;         // this node's left child
   public StringNode rightChild;        // this node's right child
   StringNode(char d){
	   iData = d;
   }

   public void displayNode()      // display ourself
      {
      System.out.print('{');
      System.out.print(iData);
      System.out.print("} ");
      }
   }  // end class Node
////////////////////////////////////////////////////////////////
class STree
   {
   public StringNode root;             // first node of tree
   public String sequence;

// -------------------------------------------------------------
   public STree()                  // constructor
      { root = null; }            // no nodes in tree yet

// -------------------------------------------------------------
   public void makeStringTree()
   {
	  
	   StringNode plus = new StringNode('+');
	  root = plus; //root is always +
	  StringNode current = root; //current position root
	  
	  
	  int index = sequence.length() -1;
	 ;
	  while(index>-1){
		  
		  if(index-1 ==0){
			  current.rightChild = new StringNode(sequence.charAt(index--));
			  current.leftChild = new StringNode(sequence.charAt(index--));
		  }
		  else{
			  current.rightChild = new StringNode(sequence.charAt(index--));
			  current.leftChild = new StringNode('+');
		  }
		  current = current.leftChild;
	  }
	  
		  
		  
			   
   }
// -------------------------------------------------------------
   public void makeBalanceTree()
   {
	   StringNode array[] = new StringNode[sequence.length()]; //make an array of nodes made of sequence
	   
	   for(int i=0;i<sequence.length();i++)
		   array[i] = new StringNode(sequence.charAt(i)); //fill array with node holding each character as key
	   
	   
	   STree forest[] = new STree[array.length]; //make a forest of trees
	   for(int j=0;j<array.length;j++){ //store each node as the root of the tree
		  forest[j] = new STree();
		  forest[j].root = array[j]; 
	   }
	   
	   
	   int count = sequence.length();
	   while(count!=1){
		   int fill =0;
		   for(int i=0;i<count;i++){
			   if(i%2==1){
				   StringNode plus = new StringNode('+');
				   plus.rightChild = forest[i].root;
				   plus.leftChild = forest[i-1].root;
				   forest[fill].root = plus;
				   fill++;
			   }
			   if(i%2 ==0 && i==count-1){
				   forest[fill].root = forest[i].root;
				   fill++;
			   }
			   
				   
			   
		   }
		   count = fill;
		   
	   }
	   root = forest[0].root;
	
		   
	   
   }
// -------------------------------------------------------------
   public StringNode buildStringNode(int start){
	   if(start>sequence.length()-1)
		   return null;
	   
	   StringNode result = new StringNode(sequence.charAt(start));
	   result.leftChild = buildStringNode(start*2+1);
	   result.rightChild = buildStringNode(start*2+2);
	   return result;
	   
   }
   
// -------------------------------------------------------------
   
   public void makeFullTree(){
	   root = buildStringNode(0);
   }
   
// -------------------------------------------------------------
   public void makeParseTree(){
	   
	   Stack theStack = new Stack();
	   for(int i=0;i<sequence.length();i++){
		   char ch = sequence.charAt(i);
		   if(ch>='A' && ch<='Z'){ //when character is numbers(operands)
			   StringNode operand = new StringNode(ch);
			   theStack.push((StringNode)operand);
		   }
		   
		   else{ //when its operators
			   StringNode b = (StringNode)theStack.pop(); //C
			   StringNode c = (StringNode)theStack.pop(); //B
			   StringNode root = new StringNode(ch);
			   root.leftChild = c;
			   root.rightChild = b;
			   theStack.push(root);
			   
		   }
	   }// end forloop
	   root = (StringNode)theStack.pop();
   } //end makeParseTree
// -------------------------------------------------------------
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
// -------------------------------------------------------------
   private void preOrder(StringNode localRoot)
      {
      if(localRoot != null)
         {
         System.out.print(localRoot.iData + " ");
         preOrder(localRoot.leftChild);
         preOrder(localRoot.rightChild);
         }
      }
// -------------------------------------------------------------
   private void inOrder(StringNode localRoot)
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
// -------------------------------------------------------------
   private void postOrder(StringNode localRoot)
      {
      if(localRoot != null)
         {
         postOrder(localRoot.leftChild);
         postOrder(localRoot.rightChild);
         System.out.print(localRoot.iData + " ");
         }
      }
// -------------------------------------------------------------
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
            StringNode temp = (StringNode)globalStack.pop();
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
// -------------------------------------------------------------
   }  // end class Tree
////////////////////////////////////////////////////////////////
public class StringTreeApp
   {
   public static void main(String[] args) {
      int value;
      STree theTree = new STree();
      theTree.sequence = "ABCD+*+";
      theTree.makeParseTree();
      theTree.displayTree();
      
      theTree.traverse(1);
      theTree.traverse(2);
      theTree.traverse(3);
     
     

    
      }  // end main()

   }  // end class TreeApp
////////////////////////////////////////////////////////////////