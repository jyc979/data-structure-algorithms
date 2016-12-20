package DS;

class ArrayPar{
	private long[] theArray;
	private int nElems;
	//----------------------------------------
	public ArrayPar(int max){
		theArray = new long[max];
		nElems =0;
	}
	//----------------------------------------
	public void insert(long value) //put elements into array
	{
		theArray[nElems] = value;
		nElems++;
	}
	//----------------------------------------
	public int size(){
		return nElems;
	}
	//----------------------------------------
	public void display(){
		System.out.print("A=");
		for(int j=0;j<nElems;j++)
			System.out.print(theArray[j]+" ");
		System.out.println("");
	}
	//----------------------------------------
	public int partitionIt(int left, int right, long pivot){
		int leftPtr = left -1; //right of first elem
		int rightPtr = right+1; //left of pivot
		while(true)
		{
			while(leftPtr<right&&theArray[++leftPtr]<pivot)//find bigger item
				;// (nop)
			while(rightPtr>left&&theArray[--rightPtr]>pivot) //find smaller item
				;//(nop)
			if(leftPtr>=rightPtr) //if pointers cross,
				break; //partition done
			else
				swap(leftPtr,rightPtr);
		}// end while(true)
		return leftPtr; //return partition
	}//end partitionIt()
	//----------------------------------------
	public int partitionIt2(int left, int right){
		int leftPtr = left -1;
		int rightPtr = right;
		long pivot = theArray[right];
		while(true){
			while(theArray[++leftPtr]<pivot)
				;
			while(rightPtr>left&&theArray[--rightPtr]>pivot)
				;
			if(leftPtr>=rightPtr)
				break;
			else
				swap(leftPtr,rightPtr);
			
		}
		swap(leftPtr,right);
		return leftPtr;
	}
	//----------------------------------------
	public long findMedian(int left, int right){
		int index = partitionIt2(left,right);
		int center = nElems/2;
		if(index == center)
			return theArray[index];
		else if(index>center)
			return findMedian(left,index-1);
		else{
			return findMedian(index+1,right);
		}
	}
	//----------------------------------------
	public long selection(int index,int left, int right){
		int partition = partitionIt2(left,right);
		if(index == partition)
			return theArray[partition];
		else if(partition>index)
			return selection(index,left,partition-1);
		else{
			return selection(index,partition+1,right);
		}
	}
	//----------------------------------------
	public void swap(int dex1, int dex2){
		long temp;
		temp = theArray[dex1]; //A into temp
		theArray[dex1] = theArray[dex2];
		theArray[dex2] = temp;
	}//end swap()
	//-----------------------------------------
	
}//end class ArrayPar
//////////////////////////////////////////////////////
	public class PartitionApp {
	public static void main(String[] args) {
		int maxSize = 7;
		ArrayPar arr;
		arr = new ArrayPar(maxSize);
		
		for(int i=0;i<maxSize;i++){
			arr.insert((long)(Math.random()*99));
		}
		arr.display();
		System.out.println(arr.selection(2, 0, maxSize-1));
		
		
	
	}

}
