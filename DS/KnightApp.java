package DS;
import java.util.*;
class StackK
   {
   private final int SIZE = 25;
   private int[] st;
   private int top;
// ------------------------------------------------------------
   public boolean isFull()
   {return (top == SIZE-1);}
// ------------------------------------------------------------
   public StackK()           // constructor
      {
      st = new int[SIZE];    // make array
      top = -1;
      }
// ------------------------------------------------------------
   public void push(int j)   // put item on stack
      { st[++top] = j; }
// ------------------------------------------------------------
   public int pop()          // take item off stack
      { return st[top--]; }
// ------------------------------------------------------------
   public int peek()         // peek at top of stack
      { return st[top]; }
// ------------------------------------------------------------
   public boolean isEmpty()  // true if nothing on st	ack
      { return (top == -1); }
// ------------------------------------------------------------
   }  // end class StackK
////////////////////////////////////////////////////////////////

class VertexK
   {
   public String label;        // label (e.g. 'A')
   public boolean wasVisited;
   public int possibleVisit;
// ------------------------------------------------------------
   public VertexK()   // constructor
      {
      label = "O";
      wasVisited = false;
      possibleVisit = -1;
      }
// ------------------------------------------------------------
   }  // end class VertexK
////////////////////////////////////////////////////////////////
class GraphK
   {
   private final int SIZE = 5;
   private final int AREA = 5*5;
   private VertexK vertexList[]; // list of vertices
   private int adjMat[][];      // adjacency matrix
   private int nVerts;          // current number of vertices
   private StackK theStack;
 

// ------------------------------------------------------------
   public GraphK()               // constructor
      {
      vertexList = new VertexK[AREA];// chess board
      
                                          
      adjMat = new int[AREA][AREA];// adjacency matrix
      
      
      nVerts = 0; // number of vertices
      
      for(int y=0; y<SIZE; y++)      // set adjacency
         for(int x=0; x<SIZE; x++)   //    matrix to 0
            adjMat[x][y] = 0;
      
      theStack = new StackK(); //initialize stack
      
      
      for(int i=0;i<AREA;i++)  // make a chess board
		   addVertex();
 
      
      for(int i = 0; i < SIZE; i++) // add all possible moves to adjMat
			for(int j = 0; j < SIZE; j++)
				addMoves(i, j);
      }
// ---------------------------------------------------------------
   public void displayBoard(){
	   int count =0;
	   for(int i=0;i<5;i++){
		   for(int j=0;j<5;j++){
			   System.out.print(vertexList[(count+j)].label+" ");
		   }
		   count = count+5;
		   System.out.println("");
	   }
	   System.out.println("----------------------------------------------");
   }
// ---------------------------------------------------------------
   public void addMoves(int i, int j)
	{
		int currentRow = i*SIZE; //row
		int currentCol = j; //col
		int currentVertex = currentRow+currentCol;
		
		if(i-1>=0){
			if(j-2>=0){
				addEdge(currentVertex,currentVertex-SIZE-2);
			}
			if(j+2<SIZE){
				addEdge(currentVertex,currentVertex-SIZE+2);
			}
		}
		if(i+1<SIZE)
		{
			if(j-2>=0){
				addEdge(currentVertex, currentVertex+SIZE-2);
			}
			if(j+2<SIZE){
				addEdge(currentVertex, currentVertex+SIZE+2);
			}
		}
		if(i-2>=0){
			if(j-1>=0){
				addEdge(currentVertex, currentVertex-(SIZE*2)-1);
			}
			if(j+1<SIZE){
				addEdge(currentVertex,currentVertex-(SIZE*2)+1);
			}
				
		}
		if(i+2<SIZE){
			if(j-1>=0){
				addEdge(currentVertex,currentVertex+(SIZE*2)-1);
			}
			if(j+1<SIZE){
				addEdge(currentVertex,currentVertex+(SIZE*2)+1);
			}
		}
		
		
	}
   
   
// ------------------------------------------------------------
   public void addVertex()
      {
      vertexList[nVerts++] = new VertexK();
      }
// ------------------------------------------------------------
   public void addEdge(int start, int end)
      {
      adjMat[start][end] = 1;
      }
 

// ------------------------------------------------------------
   public void dfs()  // depth-first search
   {                                 
   vertexList[0].wasVisited = true;  
   vertexList[0].label = "V";                
   theStack.push(0);


   while( !theStack.isEmpty() )      
      {
	  
	  int stack = theStack.peek();
      int v = getNext( theStack.peek());
      
      if(theStack.isFull()){
		  System.out.println("You Win");
		  vertexList[stack].label = "V";
		  displayBoard();
		  return;
	  }
      if(v == -1){
    	 
    	 System.out.println("DEAD END HERE");
    	 int pop = theStack.pop();
         vertexList[pop].label = "O";
         vertexList[pop].wasVisited = false;
         vertexList[pop].possibleVisit = -1;
         displayBoard();
         
      }
      else                           
      {
    	 vertexList[v].wasVisited = true;  
    	 vertexList[stack].label = "V";
    	 vertexList[stack].possibleVisit = v;  
    	 displayBoard();              
         theStack.push(v);                
         }
      }  // end while

   // stack is empty, so we're done
   }

  // ------------------------------------------------------------
 // returns an unvisited VertexK adj to v
 public int getNext(int v)
   {
   for(int j=vertexList[v].possibleVisit+1; j<nVerts; j++)
      if(adjMat[v][j]==1 && vertexList[j].wasVisited==false)
         return j;
   return -1;
   }  // end getAdjUnvisitedVertexK()
 // ------------------------------------------------------------
 public void displayAdj()
 {
    for(int i=0;i<AREA;i++){
    	System.out.print(i+"th row: ");
        for(int k=0;k<AREA;k++)
            System.out.print(adjMat[i][k]);
    System.out.println("");
    }
 }
// ------------------------------------------------------------
   }  // end class GraphK

public class KnightApp
   {
   public static void main(String[] args)
      {
      GraphK k = new GraphK();
      
      //k.displayAdj();
      k.dfs();
    
      
      
      }  // end main()
   }  // end class DFSApp
