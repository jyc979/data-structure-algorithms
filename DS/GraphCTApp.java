package DS;

/*
 * Ch 13 EX 3,4 Connectivity table and Warshall algorithm
 */


class GraphA
{
private final int MAX_VERTS = 20;
private Vertex vertexList[]; // list of vertices
public int adjMat[][];      // adjacency matrix
private int nVerts;          // current number of vertices
private StackD theStack;
//------------------------------------------------------------
public GraphA()               // constructor
   {
   vertexList = new Vertex[MAX_VERTS];
                                       // adjacency matrix
   adjMat = new int[MAX_VERTS][MAX_VERTS];
   nVerts = 0;
   for(int y=0; y<MAX_VERTS; y++)      // set adjacency
      for(int x=0; x<MAX_VERTS; x++)   //    matrix to 0
         adjMat[x][y] = 0;
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
   adjMat[start][end] = 1;
   }
//------------------------------------------------------------
public int[][] warshall()
{
	int mat[][] = adjMat;
	for(int y=0;y<nVerts;y++)
	{
		for(int x=0;x<nVerts;x++)
		{
			if(mat[y][x] ==1)
			{
				for(int z=0;z<nVerts;z++)
				{
					if(mat[z][y]==1)
						mat[z][x] =1;
				}
			}
		}
	}
	return mat;
}
//------------------------------------------------------------
public void displayVertex(int v)
   {
   System.out.print(vertexList[v].label);
   }
//------------------------------------------------------------

public void dfs(int startV)  // depth-first search
   {                                 // begin at vertex 0
   vertexList[startV].wasVisited = true;  // mark it
   displayVertex(startV);                 // display it
   theStack.push(startV);                 // push it

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
public void connectivityTable()
{
	for(int i=0;i<nVerts;i++){
		dfs(i);
		System.out.println("");
	}
	
}
//------------------------------------------------------------
public void displayAdj()
{
	for(int i=0;i<nVerts;i++){
		for(int j=0;j<nVerts;j++)
			System.out.print(adjMat[i][j]+" ");
		System.out.println(" ");
	}
}

//------------------------------------------------------------
// returns an unvisited vertex adj to v
public int getAdjUnvisitedVertex(int v)
   {
   for(int j=0; j<nVerts; j++)
      if(adjMat[v][j]==1 && vertexList[j].wasVisited==false)
         return j;
   return -1;
   }  // end getAdjUnvisitedVertex()
//------------------------------------------------------------
}  // end class GraphA
public class GraphCTApp {

	public static void main(String[] args) {
		GraphA g = new GraphA();
		g.addVertex('A');
		g.addVertex('B');
		g.addVertex('C');
		g.addVertex('D');
		g.addVertex('E');
		
		g.addEdge(1,0);
		g.addEdge(0, 2);
		g.addEdge(4, 2);
		g.addEdge(1, 4);
		g.addEdge(3, 4);
		
		
		
	}

}
