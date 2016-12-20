package DS;

// path.java
// demonstrates shortest path with weighted, directed GraphFs
// to run this program: C>java PathApp
////////////////////////////////////////////////////////////////
class DistParF             // distance and parent
   {                      // items stored in sPath array
   public int distance;   // distance from start to this VertexF
   public int parentVert; // current parent of this VertexF
// -------------------------------------------------------------
   public DistParF(int pv, int d)  // constructor
      {
      distance = d;
      parentVert = pv;
      }
// -------------------------------------------------------------
   }  // end class DistParF
///////////////////////////////////////////////////////////////
class VertexF
   {
   public char label;        // label (e.g. 'A')
   public boolean isInTree;
// -------------------------------------------------------------
   public VertexF(char lab)   // constructor
      {
      label = lab;
      isInTree = false;
      }
// -------------------------------------------------------------
   }  // end class VertexF
////////////////////////////////////////////////////////////////
class GraphF
   {
   private final int MAX_VERTS = 20;
   private final int INFINITY = 1000000;
   private VertexF vertexList[]; // list of vertices
   private int adjMat[][];      // adjacency matrix
   private int nVerts;          // current number of vertices

// -------------------------------------------------------------
   public GraphF()               // constructor
      {
      vertexList = new VertexF[MAX_VERTS];
                                         // adjacency matrix
      adjMat = new int[MAX_VERTS][MAX_VERTS];
      nVerts = 0;
    
      for(int j=0; j<MAX_VERTS; j++)     // set adjacency
         for(int k=0; k<MAX_VERTS; k++)  //     matrix
            adjMat[j][k] = INFINITY;     //     to infinity
   
      }  // end constructor
// -------------------------------------------------------------
   public void addVertex(char lab)
      {
      vertexList[nVerts++] = new VertexF(lab);
      }
// -------------------------------------------------------------
   public void addEdge(int start, int end, int weight)
      {
      adjMat[start][end] = weight;  // (directed)
      }
// -------------------------------------------------------------
   public int[][] floyd()
   {
	   int mat[][] = adjMat;
		for(int y=0;y<nVerts;y++)
		{
			for(int x=0;x<nVerts;x++)
			{
				if(mat[y][x] !=INFINITY)
				{
					for(int z=0;z<nVerts;z++)
					{
						if(mat[z][y]!=INFINITY)
							mat[z][x] = mat[y][x]+mat[z][y];
					}
				}
			}
		}
		return mat;
   }
// -------------------------------------------------------------
   public void displayMat(int[][] mat)
   {
	   for(int i=0;i<nVerts;i++){
		   for(int j=0;j<nVerts;j++)
			   System.out.print(mat[i][j]+" ");
	   System.out.println("");
	   }
   }

   }  // end class GraphF
////////////////////////////////////////////////////////////////
class FloydApp
   {
   public static void main(String[] args)
      {
      GraphF f = new GraphF();
      f.addVertex('A');     // 0  (start)
      f.addVertex('B');     // 1
      f.addVertex('C');     // 2
      f.addVertex('D');     // 3


      f.addEdge(1, 0, 70);
      f.addEdge(1, 3, 10);
      f.addEdge(2, 0, 30);
      f.addEdge(3, 2, 20);
     
      
      f.displayMat(f.floyd());

      
      }  // end main()
   }  // end class PathApp
////////////////////////////////////////////////////////////////