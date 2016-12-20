package DS;
import java.util.*;

//Traveling Sales Person


////////////////////////////////////////////////////////////////
class GraphTS
{
private final int MAX_VERTS = 20;
private final int INFINITY = 1000000;
private VertexW vertexList[]; // list of vertices
private int adjMat[][];      // adjacency matrix
private int nVerts;          // current number of vertices
public String[] permut;
private char[] arrchar;
private int counter;
private int[] dist;

//for anagram, size = nVerts+1;

//-------------------------------------------------------------
public GraphTS()               // constructor
{
vertexList = new VertexW[MAX_VERTS];
                                    // adjacency matrix
adjMat = new int[MAX_VERTS][MAX_VERTS];
nVerts = 0;
for(int j=0; j<MAX_VERTS; j++)      // set adjacency
   for(int k=0; k<MAX_VERTS; k++)   //    matrix to 0
      adjMat[j][k] = INFINITY;
arrchar = new char[10];
counter =0;

}  // end constructor

//-------------------------------------------------------------
public void doAnagram(int newSize){
	
	if(newSize ==1) //if too small
		return; //go no further
	for(int j=0;j<newSize;j++) // for each position,
	{
		doAnagram(newSize -1); // anagram remaining
		if(newSize==2){
			for(int i=0; i<arrchar.length;i++)
				permut[counter] += arrchar[i];
			counter +=1;
		}
		rotate(newSize); //rotate word
	}
}

//-------------------------------------------------------------
	public void rotate(int newSize){
	int j;
	int position = (nVerts-1) - newSize;
	char temp = arrchar[position]; //save first letter
	for(j=position+1;j<nVerts-1;j++) //shift others left
		arrchar[j-1] =arrchar[j]; 
	arrchar[j-1] =temp; //put first on right
	}

//-------------------------------------------------------------
	public void tps(char lab) //choose the starting vertex 
	{
		int count =0;
		for(int i=0;i<nVerts;i++)  // array with characters except starting node
			if(vertexList[i].label != lab)
				arrchar[count++] = vertexList[i].label;
			
				
		int nChar = factorial(nVerts-1);  //make permut list
		permut = new String[nChar];
		for(int i=0;i<permut.length;i++)
			permut[i] = "";
		doAnagram(nVerts-1); //save permutations in permut list
		
		
		
		
		for(int i=0;i<permut.length;i++){ // trim permut list
			String append = lab+permut[i].substring(0, nVerts-1)+lab;
			permut[i] = append;
		}
		
		
		
		dist = new int[nChar]; // make distance list 
		for(int i=0;i<permut.length;i++)
			dist[i] = getDist(permut[i]); //get distance of each combination
		
		
		int index = findMin(dist); // find the minimum distance
		System.out.println("TSP of this graph is: "+permut[index]+" with "+dist[index]); //result
		
		
		
		
		
	}
//-------------------------------------------------------------
	public void hamil(){ // run it after running tps()
		LinkedList<Integer> indices = new LinkedList<Integer>();
		for(int i=0;i<dist.length;i++) // add all the indices that have cycle into a linked list
			if(dist[i]<INFINITY)
				indices.add(i);
		
		
		for(int i=0;i<indices.size();i++)
			System.out.println("Cycles: "+permut[indices.get(i)]+" with dist "+dist[indices.get(i)]);
	
	}
//-------------------------------------------------------------
	public int findMin(int[] dist){
		int min = dist[0];
		int index = 0;
		for(int i=0;i<dist.length;i++){
			if(min>dist[i]){
				min = dist[i];
				index = i;
			}
		}
		return index;
			
	}
//-------------------------------------------------------------
	public int getDist(String s)
	{
		int sum =0;
		for(int i=0;i<nVerts;i++)
			sum += adjMat[(int)(s.charAt(i)-vertexList[0].label)][(int)(s.charAt(i+1)-vertexList[0].label)];
		
		return sum;
	}
//-------------------------------------------------------------
public int factorial(int numb)
{
	if(numb ==1)
		return 1;
	else
	{
		return numb*factorial(numb-1);
	}
}

//-------------------------------------------------------------
public void addVertex(char lab)
   {
   vertexList[nVerts++] = new VertexW(lab);
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
   System.out.print(vertexList[v].label);
   }

}  // end class GraphTS
////////////////////////////////////////////////////////////////
public class TSPApp
{
public static void main(String[] args)
   {
   GraphTS t = new GraphTS();
   t.addVertex('A');
   t.addVertex('B');
   t.addVertex('C');
   t.addVertex('D');
   t.addVertex('E');
   
   t.addEdge(0, 1, 91);
   t.addEdge(0, 2, 62);
   t.addEdge(0, 3, 55);
   t.addEdge(1, 2, 44);
   t.addEdge(1, 4, 31);
   t.addEdge(2, 4, 45);
   t.addEdge(2, 3, 52);
   t.addEdge(3, 4, 83);
   
   t.tps('A');
   t.hamil();
   
   
   
  
 

  
   }  // end main()
}  // end class MSTWApp
////////////////////////////////////////////////////////////////