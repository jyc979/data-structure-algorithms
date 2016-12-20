package DS;
//mstw.java
//demonstrates minimum spanning tree with weighted GraphWs
//to run this program: C>java MSTWApp
////////////////////////////////////////////////////////////////
class Edge
{
public int srcVert;   // index of a VertexW starting edge
public int destVert;  // index of a VertexW ending edge
public int distance;  // distance from src to dest
//-------------------------------------------------------------
public Edge(int sv, int dv, int d)  // constructor
   {
   srcVert = sv;
   destVert = dv;
   distance = d;
   }
//-------------------------------------------------------------
}  // end class Edge
////////////////////////////////////////////////////////////////
class PriorQ
{
// array in sorted order, from max at 0 to min at size-1
private final int SIZE = 20;
private Edge[] queArray;
private int size;
//-------------------------------------------------------------
public PriorQ()            // constructor
   {
   queArray = new Edge[SIZE];
   size = 0;
   }
//-------------------------------------------------------------
public void insert(Edge item)  // insert item in sorted order
   {
   int j;

   for(j=0; j<size; j++)           // find place to insert
      if( item.distance >= queArray[j].distance )
         break;

   for(int k=size-1; k>=j; k--)    // move items up
      queArray[k+1] = queArray[k];

   queArray[j] = item;             // insert item
   size++;
   }
//-------------------------------------------------------------
public Edge removeMin()            // remove minimum item
   { return queArray[--size]; }
//-------------------------------------------------------------
public void removeN(int n)         // remove item at n
   {
   for(int j=n; j<size-1; j++)     // move items down
      queArray[j] = queArray[j+1];
   size--;
   }
//-------------------------------------------------------------
public Edge peekMin()          // peek at minimum item
   { return queArray[size-1]; }
//-------------------------------------------------------------
public int size()              // return number of items
   { return size; }
//-------------------------------------------------------------
public boolean isEmpty()      // true if queue is empty
   { return (size==0); }
//-------------------------------------------------------------
public Edge peekN(int n)      // peek at item n
   { return queArray[n]; }
//-------------------------------------------------------------
public int find(int findDex)  // find item with specified
   {                          // destVert value
   for(int j=0; j<size; j++)
      if(queArray[j].destVert == findDex)
         return j;
   return -1;
   }
//-------------------------------------------------------------
}  // end class PriorQ
////////////////////////////////////////////////////////////////
class VertexW
{
public char label;        // label (e.g. 'A')
public boolean isInTree;
//-------------------------------------------------------------
public VertexW(char lab)   // constructor
   {
   label = lab;
   isInTree = false;
   }
//-------------------------------------------------------------
}  // end class VertexW
////////////////////////////////////////////////////////////////
class GraphW
{
private final int MAX_VERTS = 20;
private final int INFINITY = 1000000;
private VertexW VertexWList[]; // list of vertices
private int adjMat[][];      // adjacency matrix
private int nVerts;          // current number of vertices
private int currentVert;
private PriorQ thePQ;
private int nTree;           // number of verts in tree
//-------------------------------------------------------------
public GraphW()               // constructor
   {
   VertexWList = new VertexW[MAX_VERTS];
                                       // adjacency matrix
   adjMat = new int[MAX_VERTS][MAX_VERTS];
   nVerts = 0;
   for(int j=0; j<MAX_VERTS; j++)      // set adjacency
      for(int k=0; k<MAX_VERTS; k++)   //    matrix to 0
         adjMat[j][k] = INFINITY;
   thePQ = new PriorQ();
   }  // end constructor
//-------------------------------------------------------------
public void addVertexW(char lab)
   {
   VertexWList[nVerts++] = new VertexW(lab);
   }
//-------------------------------------------------------------
public void addEdge(int start, int end, int weight)
   {
   adjMat[start][end] = weight;
   adjMat[end][start] = weight;
   }
//-------------------------------------------------------------
public void displayVertexW(int v)
   {
   System.out.print(VertexWList[v].label);
   }
//-------------------------------------------------------------
public void mstw()           // minimum spanning tree
   {
   currentVert = 0;          // start at 0

   while(nTree < nVerts-1)   // while not all verts in tree
      {                      // put currentVert in tree
      VertexWList[currentVert].isInTree = true;
      nTree++;

      // insert edges adjacent to currentVert into PQ
      for(int j=0; j<nVerts; j++)   // for each VertexW,
         {
         if(j==currentVert)         // skip if it's us
            continue;
         if(VertexWList[j].isInTree) // skip if in the tree
            continue;
         int distance = adjMat[currentVert][j];
         if( distance == INFINITY)  // skip if no edge
            continue;
         putInPQ(j, distance);      // put it in PQ (maybe)
         }
      if(thePQ.size()==0)           // no vertices in PQ?
            {
            System.out.println(" GraphW NOT CONNECTED");
            return;
            }
      // remove edge with minimum distance, from PQ
      Edge theEdge = thePQ.removeMin();
      int sourceVert = theEdge.srcVert;
      currentVert = theEdge.destVert;

      // display edge from source to current
      System.out.print( VertexWList[sourceVert].label );
      System.out.print( VertexWList[currentVert].label );
      System.out.print(" ");
      }  // end while(not all verts in tree)

   // mst is complete
   for(int j=0; j<nVerts; j++)     // unmark vertices
      VertexWList[j].isInTree = false;
   }  // end mstw
//-------------------------------------------------------------
public void putInPQ(int newVert, int newDist)
   {
   // is there another edge with the same destination VertexW?
   int queueIndex = thePQ.find(newVert);
   if(queueIndex != -1)              // got edge's index
      {
      Edge tempEdge = thePQ.peekN(queueIndex);  // get edge
      int oldDist = tempEdge.distance;
      if(oldDist > newDist)          // if new edge shorter,
         {
         thePQ.removeN(queueIndex);  // remove old edge
         Edge theEdge =
                     new Edge(currentVert, newVert, newDist);
         thePQ.insert(theEdge);      // insert new edge
         }
      // else no action; just leave the old VertexW there
      }  // end if
   else  // no edge with same destination VertexW
      {                              // so insert new one
      Edge theEdge = new Edge(currentVert, newVert, newDist);
      thePQ.insert(theEdge);
      }
   }  // end putInPQ()
//-------------------------------------------------------------
}  // end class GraphW
////////////////////////////////////////////////////////////////
class MSTWApp
{
public static void main(String[] args)
   {
   GraphW theGraphW = new GraphW();
 

  
   }  // end main()
}  // end class MSTWApp
////////////////////////////////////////////////////////////////
