package DS;

// path.java
// demonstrates shortest path with weighted, directed GraphPs
// to run this program: C>java PathApp
////////////////////////////////////////////////////////////////
class DistPar             // distance and parent
   {                      // items stored in sPath array
   public int distance;   // distance from start to this VertexP
   public int parentVert; // current parent of this VertexP
// -------------------------------------------------------------
   public DistPar(int pv, int d)  // constructor
      {
      distance = d;
      parentVert = pv;
      }
// -------------------------------------------------------------
   }  // end class DistPar
///////////////////////////////////////////////////////////////
class VertexP
   {
   public char label;        // label (e.g. 'A')
   public boolean isInTree;
// -------------------------------------------------------------
   public VertexP(char lab)   // constructor
      {
      label = lab;
      isInTree = false;
      }
// -------------------------------------------------------------
   }  // end class VertexP
////////////////////////////////////////////////////////////////
class GraphP
   {
   private final int MAX_VERTS = 20;
   private final int INFINITY = 1000000;
   private VertexP vertexList[]; // list of vertices
   private int adjMat[][];      // adjacency matrix
   private int nVerts;          // current number of vertices
   private int nTree;           // number of verts in tree
   private DistPar sPath[];     // array for shortest-path data
   private int currentVert;     // current VertexP
   private int startToCurrent;  // distance to currentVert
// -------------------------------------------------------------
   public GraphP()               // constructor
      {
      vertexList = new VertexP[MAX_VERTS];
                                         // adjacency matrix
      adjMat = new int[MAX_VERTS][MAX_VERTS];
      nVerts = 0;
      nTree = 0;
      for(int j=0; j<MAX_VERTS; j++)     // set adjacency
         for(int k=0; k<MAX_VERTS; k++)  //     matrix
            adjMat[j][k] = INFINITY;     //     to infinity
      sPath = new DistPar[MAX_VERTS];    // shortest paths
      }  // end constructor
// -------------------------------------------------------------
   public void addVertexP(char lab)
      {
      vertexList[nVerts++] = new VertexP(lab);
      }
// -------------------------------------------------------------
   public void addEdge(int start, int end, int weight)
      {
      adjMat[start][end] = weight;  // (directed)
      }
// -------------------------------------------------------------
   public void path()                // find all shortest paths
      {
      int startTree =0;             // start at VertexP 0
      vertexList[startTree].isInTree = true;
      nTree = 1;                     // put it in tree

      // transfer row of distances from adjMat to sPath
      for(int j=0; j<nVerts; j++)
         {
         int tempDist = adjMat[startTree][j];
         sPath[j] = new DistPar(startTree, tempDist);
         }

      // until all vertices are in the tree
      while(nTree < nVerts)
         {
         int indexMin = getMin();    // get minimum from sPath
         int minDist = sPath[indexMin].distance;

         if(minDist == INFINITY)     // if all infinite
            {                        // or in tree,
            System.out.println("There are unreachable vertices");
            break;                   // sPath is complete
            }
         else
            {                        // reset currentVert
            currentVert = indexMin;  // to closest vert
            startToCurrent = sPath[indexMin].distance;
            // minimum distance from startTree is
            // to currentVert, and is startToCurrent
            }
         // put current VertexP in tree
         vertexList[currentVert].isInTree = true;
         nTree++;
         adjust_sPath();             // update sPath[] array
         }  // end while(nTree<nVerts)

      displayPaths();                // display sPath[] contents

      nTree = 0;                     // clear tree
      for(int j=0; j<nVerts; j++)
         vertexList[j].isInTree = false;
      }  // end path()

// -------------------------------------------------------------
   public void adjust_sPath()
      {
      // adjust values in shortest-path array sPath
      int column = 1;                // skip starting VertexP
      while(column < nVerts)         // go across columns
         {
         // if this column's VertexP already in tree, skip it
         if( vertexList[column].isInTree )
            {
            column++;
            continue;
            }
         // calculate distance for one sPath entry
                       // get edge from currentVert to column
         int currentToFringe = adjMat[currentVert][column];
                       // add distance from start
         int startToFringe = startToCurrent + currentToFringe;
                       // get distance of current sPath entry
         int sPathDist = sPath[column].distance;

         // compare distance from start with sPath entry
         if(startToFringe < sPathDist)   // if shorter,
            {                            // update sPath
            sPath[column].parentVert = currentVert;
            sPath[column].distance = startToFringe;
            }
         column++;
         }  // end while(column < nVerts)
   }  // end adjust_sPath()
// -------------------------------------------------------------
   public int getMin()               // get entry from sPath
      {                              //    with minimum distance
      int minDist = INFINITY;        // assume minimum
      int indexMin = 0;
      for(int j=1; j<nVerts; j++)    // for each VertexP,
         {                           // if it's in tree and
         if( !vertexList[j].isInTree &&  // smaller than old one
                               sPath[j].distance < minDist )
            {
            minDist = sPath[j].distance;
            indexMin = j;            // update minimum
            }
         }  // end for
      return indexMin;               // return index of minimum
      }  // end getMin()
// -------------------------------------------------------------
   /*public void shortTable()
    * pass int to path(), and make sPath's starting column =0;
   {
	   for(int i=0;i<nVerts;i++){
		   System.out.print(vertexList[i].label+": ");
	   		path(i);
	   		System.out.println("");
	   }
   }*/
// -------------------------------------------------------------
   public void displayPaths()
      {
      for(int j=0; j<nVerts; j++) // display contents of sPath[]
         {
         System.out.print(vertexList[j].label + "="); // B=
         if(sPath[j].distance == INFINITY)
            System.out.print("inf");                  // inf
         else
            System.out.print(sPath[j].distance);      // 50
         char parent = vertexList[ sPath[j].parentVert ].label;
         System.out.print("(" + parent + ") ");       // (A)
         }
      System.out.println("");
      }
// -------------------------------------------------------------
   }  // end class GraphP
////////////////////////////////////////////////////////////////
class PathApp
   {
   public static void main(String[] args)
      {
      GraphP theGraphP = new GraphP();
      theGraphP.addVertexP('A');     // 0  (start)
      theGraphP.addVertexP('B');     // 1
      theGraphP.addVertexP('C');     // 2
      theGraphP.addVertexP('D');     // 3
      theGraphP.addVertexP('E');     // 4

      theGraphP.addEdge(0, 1, 50);  // AB 50
      theGraphP.addEdge(0, 3, 80);  // AD 80
      theGraphP.addEdge(1, 2, 60);  // BC 60
      theGraphP.addEdge(1, 3, 90);  // BD 90
      theGraphP.addEdge(2, 4, 40);  // CE 40
      theGraphP.addEdge(3, 2, 20);  // DC 20
      theGraphP.addEdge(3, 4, 70);  // DE 70
      theGraphP.addEdge(4, 1, 50);  // EB 50

      System.out.println("Shortest paths");
      theGraphP.path();
      //theGraphP.shortTable();             
      System.out.println();
      }  // end main()
   }  // end class PathApp
////////////////////////////////////////////////////////////////


