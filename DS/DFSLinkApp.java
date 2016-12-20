package DS;
class LinkV{
	public int ver;
	public LinkV next; //reference to next LinkV
	//----------------------------------
	public LinkV(int ver){
		this.ver = ver;
	}
	//----------------------------------
	public void displayLinkV(){
		System.out.print("{"+ver+"} ");
	}
	//----------------------------------
}//end class LinkV


class LinkListV{
	private LinkV first; //ref to first LinkV on list
	//----------------------------------
	public void LinkListV(){
		first = null;//no items on list yet
	}
	//----------------------------------
	public boolean isEmpty(){
		return (first == null);
	}
	//----------------------------------
	public void insertFirst(int v){ //insert at start of list, make new LinkV
		LinkV newLinkV = new LinkV(v); 
		newLinkV.next = first; //newLinkV --> old first
		first = newLinkV;// first --> newLinkV
		
	}



	//----------------------------------
	public boolean find(int j){
		LinkV current = first;
		while(current.ver!=j){
			if(current.next == null)
				return false;
			current = current.next;
		}
		return true;
	}
	//----------------------------------
	public LinkV delete(int v){
		LinkV current = first;//search for LinkV
		LinkV previous = first; 
		while(current.ver!=v){
			if(current.next ==null)
				return null; //didn't find it
			else{
				previous = current;
				current = current.next;
			}
		} //found it
		if(current == first)		//if first LinkV,
			first = first.next;		//change first
		else						//otherwise,
			previous.next = current.next;//bypass it
		return current;
	}
	//----------------------------------
	public void displayList(){
		System.out.print("List(first-->last): ");
		LinkV current = first; //starts at beginning of list(NEWEST guy added)
		while(current != null){ // until end of list,
			current.displayLinkV(); //print data
			current = current.next; //move to next LinkV
		}
		System.out.println(" ");
	}
}
////////////////////////////////////////////////////////////////
class GraphL
{
private final int MAX_VERTS = 20;
private Vertex vertexList[]; // list of vertices
public LinkListV[] adjList;
private int nVerts;          // current number of vertices
private StackD theStack;
//------------------------------------------------------------
public GraphL()               // constructor
   {
   vertexList = new Vertex[MAX_VERTS];
                                       // adjacency matrix
   adjList = new LinkListV[MAX_VERTS];
   for(int i=0;i<adjList.length;i++)
	   adjList[i] = new LinkListV();
   nVerts = 0;
   
   theStack = new StackD();
   }  // end constructor
//------------------------------------------------------------
public void addVertex(char lab)
   {
   vertexList[nVerts++] = new Vertex(lab);
   }
//------------------------------------------------------------
public void addEdge(int start, int end)
   {
   adjList[start].insertFirst(end);
   adjList[end].insertFirst(start);
   }
//------------------------------------------------------------
public void displayVertex(int v)
   {
   System.out.print(vertexList[v].label);
   }
//------------------------------------------------------------

public void dfs()  // depth-first search
   {                                 // begin at vertex 0
   vertexList[0].wasVisited = true;  // mark it
   displayVertex(0);                 // display it
   theStack.push(0);                 // push it

   while( !theStack.isEmpty() )      // until stack empty,
      {
      // get an unvisited vertex adjacent to stack top
      int v = getAdjUnvisitedVertex( theStack.peek() );
      if(v == -1)                    // if no such vertex,
         theStack.pop();
      else                           // if it exists,
         {
         vertexList[v].wasVisited = true;  // mark it
         displayVertex(v);                 // display it
         theStack.push(v);                 // push it
         }
      }  // end while

   // stack is empty, so we're done
   for(int j=0; j<nVerts; j++)          // reset flags
      vertexList[j].wasVisited = false;
   }  // end dfs


//------------------------------------------------------------
// returns an unvisited vertex adj to v
public int getAdjUnvisitedVertex(int v)
   {
   for(int j=0; j<nVerts; j++)
      if(adjList[v].find(j) && vertexList[j].wasVisited==false)
         return j;
   return -1;
   }  // end getAdjUnvisitedVertex()
//------------------------------------------------------------
}  // end class GraphL
////////////////////////////////////////////////////////////////
public class DFSLinkApp
{
public static void main(String[] args)
   {
   GraphL theGraph = new GraphL();
   theGraph.addVertex('A');    // 0  (start for dfs)
   theGraph.addVertex('B');    // 1
   theGraph.addVertex('C');    // 2
   theGraph.addVertex('D');    // 3

   theGraph.addEdge(0, 1);     // AB
   theGraph.addEdge(1,3);     // BC
   theGraph.addEdge(0,3);     // AD
   theGraph.addEdge(0,2);     // DE
   theGraph.adjList[0].displayList();


   System.out.print("Visits: ");
   theGraph.dfs();             // depth-first search
  
   System.out.println();
   
	
   //////////////////////////////////////////////////////////////////
   
  
   }  // end main()
}  // end class DFSApp
////////////////////////////////////////////////////////////////