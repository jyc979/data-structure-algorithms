package DS;
//bfs.java
//demonstrates breadth-first search
//to run this program: C>java BFSApp
////////////////////////////////////////////////////////////////
class QueueB
{
private final int SIZE = 20;
private int[] queArray;
private int front;
private int rear;
//-------------------------------------------------------------
public QueueB()            // constructor
   {
   queArray = new int[SIZE];
   front = 0;
   rear = -1;
   }
//-------------------------------------------------------------
public void insert(int j) // put item at rear of QueueB
   {
   if(rear == SIZE-1)
      rear = -1;
   queArray[++rear] = j;
   }
//-------------------------------------------------------------
public int remove()       // take item from front of QueueB
   {
   int temp = queArray[front++];
   if(front == SIZE)
      front = 0;
   return temp;
   }
//-------------------------------------------------------------
public boolean isEmpty()  // true if QueueB is empty
   {
   return ( rear+1==front || (front+SIZE-1==rear) );
   }
//-------------------------------------------------------------
}  // end class QueueB
////////////////////////////////////////////////////////////////

class GraphB
{
private final int MAX_VERTS = 20;
private Vertex vertexList[]; // list of vertices
private int adjMat[][];      // adjacency matrix
private int nVerts;          // current number of vertices
private QueueB theQueueB;
//------------------------------------------------------------
public GraphB()               // constructor
   {
   vertexList = new Vertex[MAX_VERTS];
                                       // adjacency matrix
   adjMat = new int[MAX_VERTS][MAX_VERTS];
   nVerts = 0;
   for(int j=0; j<MAX_VERTS; j++)      // set adjacency
      for(int k=0; k<MAX_VERTS; k++)   //    matrix to 0
         adjMat[j][k] = 0;
   theQueueB = new QueueB();
   }  // end constructor
//-------------------------------------------------------------
public void addVertex(char lab)
   {
   vertexList[nVerts++] = new Vertex(lab);
   }
//-------------------------------------------------------------
public void addEdge(int start, int end)
   {
   adjMat[start][end] = 1;
   adjMat[end][start] = 1;
   }
//-------------------------------------------------------------
public void displayVertex(int v)
   {
   System.out.print(vertexList[v].label);
   }
//-------------------------------------------------------------
public void mst()	// minimum spanning tree using bfs
	{
	vertexList[0].wasVisited = true;
	theQueueB.insert(0);
	int v2;
	
	while(!theQueueB.isEmpty())
		{
		int v1 = theQueueB.remove();
		while((v2=getAdjUnvisitedVertex(v1))!=-1)
			{
			displayVertex(v1);
			vertexList[v2].wasVisited = true;
			displayVertex(v2);
			System.out.print(" ");
			theQueueB.insert(v2);
			}
		}
	for(int j=0;j<nVerts;j++)
		vertexList[j].wasVisited = false;
	
	}
//-------------------------------------------------------------
public void bfs()                   // breadth-first search
   {                                // begin at vertex 0
   vertexList[0].wasVisited = true; // mark it
   displayVertex(0);                // display it
   theQueueB.insert(0);              // insert at tail
   int v2;

   while( !theQueueB.isEmpty() )     // until QueueB empty,
      {
      int v1 = theQueueB.remove();   // remove vertex at head
      // until it has no unvisited neighbors
      while( (v2=getAdjUnvisitedVertex(v1)) != -1 )
         {                                  // get one,
         vertexList[v2].wasVisited = true;  // mark it
         displayVertex(v2);                 // display it
         theQueueB.insert(v2);               // insert it
         }   // end while
      }  // end while(QueueB not empty)

   // QueueB is empty, so we're done
   for(int j=0; j<nVerts; j++)             // reset flags
      vertexList[j].wasVisited = false;
   }  // end bfs()
//-------------------------------------------------------------
// returns an unvisited vertex adj to v
public int getAdjUnvisitedVertex(int v)
   {
   for(int j=0; j<nVerts; j++)
      if(adjMat[v][j]==1 && vertexList[j].wasVisited==false)
         return j;
   return -1;
   }  // end getAdjUnvisitedVertex()
//-------------------------------------------------------------
}  // end class GraphB
////////////////////////////////////////////////////////////////
class BFSApp
{
public static void main(String[] args)
   {
   GraphB theGraph = new GraphB();
   theGraph.addVertex('A');    // 0  (start for bfs)
   theGraph.addVertex('B');    // 1
   theGraph.addVertex('C');    // 2
   theGraph.addVertex('D');    // 3
   theGraph.addVertex('E');    // 4
 

   theGraph.addEdge(0, 1);     // AB
   theGraph.addEdge(1, 2);     // BC
   theGraph.addEdge(0, 3);     // AD
   theGraph.addEdge(3, 4);     // DE
  

   System.out.print("Visits: ");
   theGraph.bfs();             // breadth-first search
   System.out.println();
   
   System.out.print("Mst: ");
   theGraph.mst();
   }  // end main()
}  // end class BFSApp
////////////////////////////////////////////////////////////////


