package DS;
class MLink{
	public MLink nextCol;
	public MLink nextRow;
	public long data;
	//----------------------------------
	MLink(long data){
		this.data = data;
		nextCol = null;
		nextRow = null;
	}
	//----------------------------------
	public void displayLink(){
		System.out.print("{"+data+"} ");
	}
	//----------------------------------
}// end class MList
class MLinkList{
	private MLink first;
	private MLink current;
	private int rows;
	private int cols;
	//----------------------------------
	MLinkList(int rows, int cols){
		this.rows = rows;
		this.cols = cols;
		
		MLink newLink = new MLink(0);
		first = newLink;
		current = first;
		
		MLink currentCol;
		for(int i=0;i<rows;i++){
			currentCol = current;
			for(int j=1;j<cols;j++){
				currentCol.nextCol = new MLink(0);
				currentCol = currentCol.nextCol;
			}
			if(i<rows-1)
			current.nextRow = new MLink(0);
			current = current.nextRow;
		}
	}
	//----------------------------------
	public boolean isEmpty(){
		return (first==null);
	}
	//----------------------------------
	public void insert(long dd, int rows, int cols){
		current = first;
		for(int i=0;i<rows;i++){
			current = current.nextRow;
		}
		for(int j=0;j<cols;j++){
			current = current.nextCol;
		}
		current.data = dd;
	}
	//----------------------------------
	public void display(){
		current =first;
		MLink currentCol;
		for(int i=0;i<rows;i++){
			currentCol = current;
			System.out.print(i+1+"th row: ");
			for(int j=0;j<cols;j++){
				currentCol.displayLink();
				currentCol = currentCol.nextCol;
			}
			System.out.println(" ");
			current = current.nextRow;
		}
	}
	//----------------------------------
	

	
	
	
}//end class MLinkList
public class MatrixListApp {

	public static void main(String[] args) {
		MLinkList q = new MLinkList(2,2);
		q.insert(10, 1, 1);
		q.insert(20, 0, 1);
		q.insert(30, 0, 0);
		q.insert(40, 1, 0);
		q.display();
	}

}
