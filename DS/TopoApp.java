package DS;
//topo.java
//demonstrates topological sorting
//to run this program: C>java TopoApp
////////////////////////////////////////////////////////////////


class GraphT
{
private final int MAX_VERTS = 20;
private Vertex vertexList[]; // list of vertices
private int adjMat[][];      // adjacency matrix
private int nVerts;          // current number of vertices
private char sortedArray[];
//-------------------------------------------------------------
public GraphT()               // constructor
   {
   vertexList = new Vertex[MAX_VERTS];
                                       // adjacency matrix
   adjMat = new int[MAX_VERTS][MAX_VERTS];
   nVerts = 0;
   for(int j=0; j<MAX_VERTS; j++)      // set adjacency
      for(int k=0; k<MAX_VERTS; k++)   //    matrix to 0
         adjMat[j][k] = 0;
   sortedArray = new char[MAX_VERTS];  // sorted vert labels
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
   }
//-------------------------------------------------------------
public void displayVertex(int v)
   {
   System.out.print(vertexList[v].label);
   }
//-------------------------------------------------------------
public void topo()  // toplogical sort
   {
   int orig_nVerts = nVerts;  // remember how many verts

   while(nVerts > 0)  // while vertices remain,
      {
      // get a vertex with no successors, or -1
      int currentVertex = noSuccessors();
      if(currentVertex == -1)       // must be a cycle
         {
         System.out.println("ERROR: GraphT has cycles");
         return;
         }
      // insert vertex label in sorted array (start at end)
      sortedArray[nVerts-1] = vertexList[currentVertex].label;

      deleteVertex(currentVertex);  // delete vertex
      }  // end while

   // vertices all gone; display sortedArray
   System.out.print("Topologically sorted order: ");
   for(int j=0; j<orig_nVerts; j++)
      System.out.print( sortedArray[j] );
   System.out.println("");
   }  // end topo
//-------------------------------------------------------------
public int noSuccessors()  // returns vert with no successors
   {                       // (or -1 if no such verts)
   boolean hasEdge;  // edge from row to column in adjMat

   for(int row=0; row<nVerts; row++)  // for each vertex,
      {
      hasEdge = false;                 // check edges
      for(int col=0; col<nVerts; col++)
         {
         if( adjMat[row][col] > 0 )   // if edge to
            {                         // another,
            hasEdge = true;
            break;                    // this vertex
            }                         //    has a successor
         }                            //    try another
      if( !hasEdge )                   // if no edges,
         return row;                  //    has no successors
      }
   return -1;                         // no such vertex
   }  // end noSuccessors()
//-------------------------------------------------------------

public void deleteVertex(int delVert)
   {
   if(delVert != nVerts-1)      // if not last vertex,
      {                         // delete from vertexList
      for(int j=delVert; j<nVerts-1; j++)
         vertexList[j] = vertexList[j+1];
                                // delete row from adjMat
      for(int row=delVert; row<nVerts-1; row++)
         moveRowUp(row, nVerts);
                                // delete col from adjMat
      for(int col=delVert; col<nVerts-1; col++)
         moveColLeft(col, nVerts-1);
      }
   nVerts--;                    // one less vertex
   }  // end deleteVertex
//-------------------------------------------------------------
private void moveRowUp(int row, int length)
   {
   for(int col=0; col<length; col++)
      adjMat[row][col] = adjMat[row+1][col];
   }
//-------------------------------------------------------------
private void moveColLeft(int col, int length)
   {
   for(int row=0; row<length; row++)
      adjMat[row][col] = adjMat[row][col+1];
   }
//-------------------------------------------------------------
}  // end class GraphT
////////////////////////////////////////////////////////////////
class TopoApp
{
public static void main(String[] args)
   {
   GraphT theGraph = new GraphT();
   theGraph.addVertex('A');    // 0
   theGraph.addVertex('B');    // 1
   theGraph.addVertex('C');    // 2
   theGraph.addVertex('D');    // 3
   theGraph.addVertex('E');    // 4
   theGraph.addVertex('F');    // 5
   theGraph.addVertex('G');    // 6
   theGraph.addVertex('H');    // 7

   theGraph.addEdge(0, 3);     // AD
   theGraph.addEdge(0, 4);     // AE
   theGraph.addEdge(1, 4);     // BE
   theGraph.addEdge(2, 5);     // CF
   theGraph.addEdge(3, 6);     // DG
   theGraph.addEdge(4, 6);     // EG
   theGraph.addEdge(5, 7);     // FH
   theGraph.addEdge(6, 7);     // GH

   theGraph.topo();            // do the sort
   }  // end main()
}  // end class TopoApp
////////////////////////////////////////////////////////////////

