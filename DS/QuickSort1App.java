package DS;
class ArrayIns1{
	private long[] theArray;
	private int nElems;
	//--------------------------------------
	public ArrayIns1(int max){
		theArray = new long[max];
		nElems =0;
	}
	//--------------------------------------
	public void insert(long value){
		theArray[nElems] = value;
		nElems++;
	}
	//--------------------------------------
	public void display(){
		System.out.print("A=");
		for(int j=0;j<nElems;j++)
			System.out.print(theArray[j]+" ");
		System.out.println("");
	}
	//--------------------------------------
	public void quickSort(){
		recQuickSort(0,nElems-1);
	}
	//--------------------------------------
	public void recQuickSort(int left, int right){
		if(right-left<=0) //if size<=1;
			return; //already sorted
		else{
			long pivot = theArray[right]; //rightmost item
										  // partition range
			int partition = partitionIt(left, right, pivot);
			recQuickSort(left, partition-1);//sort left side
			recQuickSort(partition+1,right);//sort right side
		}
	}//end recQuickSort()
	//--------------------------------------
	public int partitionIt(int left, int right, long pivot){
		int leftPtr = left-1; //left(after++)
		int rightPtr = right; //right-1(after--)
		while(true){ 
			while(theArray[++leftPtr]<pivot)//find bigger item
				;//(nop)
			while(rightPtr>0&&theArray[--rightPtr]>pivot)
				;//(nop)
			if(leftPtr>=rightPtr) //if pointers cross,
				break; //partition done
			else //not crossed, so
				swap(leftPtr,rightPtr);//swap elements
		}//end while(true)
		swap(leftPtr,right); //restore pivot
		return leftPtr;
	}//end partitionIt()
	public void swap(int dex1, int dex2){ //swap two elemts
		long temp = theArray[dex1]; //A into temp
		theArray[dex1] = theArray[dex2];//B into A
		theArray[dex2] = temp; //temp into B
	}//end swap
	
}//end class ArrayIns
//////////////////////////////////////////////////////////////////////////////
public class QuickSort1App {

	public static void main(String[] args) {
		int maxSize =8;
		ArrayIns1 arr;
		arr = new ArrayIns1(maxSize);
		
		for(int j=0;j<maxSize;j++){
			long n = (int)(java.lang.Math.random()*99);
			arr.insert(n);
		}
		arr.display();
		arr.quickSort();
		arr.display();

	}//end main()
}//end class QuickSort1App
