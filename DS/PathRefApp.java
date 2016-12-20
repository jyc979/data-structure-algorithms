package DS;
class StackR
{
	  private final int SIZE = 20;
	   private VertexR[] st;
	   private int top;
	// ------------------------------------------------------------
	   public StackR()           // constructor
	      {
	      st = new VertexR[SIZE];    // make array
	      top = -1;
	      }
	// ------------------------------------------------------------
	   public void push(VertexR j)   // put item on stack
	      { st[++top] = j; }
	// ------------------------------------------------------------
	   public VertexR pop()          // take item off stack
	      { return st[top--]; }
	// ------------------------------------------------------------
	   public VertexR peek()         // peek at top of stack
	      { return st[top]; }
	// ------------------------------------------------------------
	   public boolean isEmpty()  // true if nothing on stack
	      { return (top == -1); }
}
class EdgeR
{
	public int weight;
	public VertexR dest;
	EdgeR(VertexR dest, int distance)
	{
		this.dest = dest;
		this.weight = distance;
	}
	
}
//----------------------------------------------------
class VertexR
{
	public EdgeR[] startFrom;
	public char label;
	public boolean isInTree;
	public int nEdges;
	public boolean wasVisited;
	//----------------------------------------------------
	VertexR(char lab)
	{
		startFrom = new EdgeR[20];
		isInTree = false;
		label = lab;
		nEdges =0;
		wasVisited = false;
	}
	
	
}

//----------------------------------------------------
class GraphR
{
	private final int MAX_VERTS = 20;
	private VertexR vertexList[];
	private int nVerts;
	private StackR theStack;
	//----------------------------------------------------
	GraphR()
	{
		vertexList = new VertexR[MAX_VERTS];
		nVerts =0;	
		theStack = new StackR();
	}
	//----------------------------------------------------
	public void addVertex(char lab)
	{
		vertexList[nVerts++] = new VertexR(lab);
	}
	//----------------------------------------------------
	public void addEdge(int start, int end,int weight)
	{
		vertexList[start].startFrom[vertexList[start].nEdges++] = new EdgeR(vertexList[end],weight);
	}
	//----------------------------------------------------
	public void displayVertex(VertexR v){
		System.out.print(v.label);
	}
	//----------------------------------------------------
	public void dfs(int index)
	{
		vertexList[index].wasVisited = true; 
		displayVertex(vertexList[index]);
	    theStack.push(vertexList[index]);                 
	      while( !theStack.isEmpty() )      
	         {
	         
	         VertexR v = getAdjUnvisitedVertex( theStack.peek() );
	         if(v == null)                    
	            theStack.pop();
	         else                          
	            {
	            v.wasVisited = true; 
	            displayVertex(v);
	            theStack.push(v);                
	            }
	         }  // end while

	      // stack is empty, so we're done
	      for(int j=0; j<nVerts; j++)          // reset flags
	         vertexList[j].wasVisited = false;
	}
	//----------------------------------------------------
	public VertexR getAdjUnvisitedVertex(VertexR v)
	{
		for(int i=0;i<v.nEdges;i++)
			if(v.startFrom[i].dest.wasVisited == false)
				return v.startFrom[i].dest;
		return null;
		
	}
	//----------------------------------------------------
	public  void displayCT()
	{
		for(int i=0; i<nVerts;i++){
			
			dfs(i);
			System.out.println("");
		}
		
	}
	
	
}
//----------------------------------------------------
public class PathRefApp {

	public static void main(String[] args) {
		GraphR r = new GraphR();
	      r.addVertex('A');     // 0  (start)
	      r.addVertex('B');     // 1
	      r.addVertex('C');     // 2
	      r.addVertex('D');     // 3
	      r.addVertex('E');     // 4

	      r.addEdge(0, 1, 50);  // AB 50
	      r.addEdge(0, 3, 80);  // AD 80
	      r.addEdge(1, 2, 60);  // BC 60
	      r.addEdge(1, 3, 90);  // BD 90
	      r.addEdge(2, 4, 40);  // CE 40
	      r.addEdge(3, 2, 20);  // DC 20
	      r.addEdge(3, 4, 70);  // DE 70
	      r.addEdge(4, 1, 50);  // EB 50
	      
	      r.displayCT();

	}

}
