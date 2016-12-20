package DS;
class ordArray2{
	private long[] a;
	private int nElems;
	//---------------------------------
	public ordArray2(int max){
		a = new long[max];
		nElems=0;
	}
	//---------------------------------
	public int size(){
		return nElems;
	}
	//---------------------------------
	public int find(long searchKey){
		return recFind(searchKey,0,nElems-1);
	}
	//---------------------------------
	private int recFind(long searchKey, int lowerBound, int upperBound){
		int curIn;
		
		curIn = (lowerBound+upperBound)/2;
		if(a[curIn]==searchKey){
			return curIn;
		}
		else if(lowerBound>upperBound){
			return -1;
		}
		else{
			
			if(a[curIn]<searchKey)
				return recFind(searchKey, curIn+1,upperBound);
			else
				return recFind(searchKey,lowerBound,curIn-1);
		}
	}//end recFind
	//---------------------------------
	public void insert(long value){
		int j;
		for(j=0;j<nElems;j++)
			if(a[j]>value)
				break;
		for(int k=nElems;k>j;k--)
			a[k] =a[k-1];
		a[j] = value;
		nElems++;
	}// end insert
	//---------------------------------
	public void display(){
		for(int j=0;j<nElems;j++)
			System.out.print(a[j]+" ");
		System.out.println(" ");
	}
	//---------------------------------
}

public class BinarySearchApp2 {

	public static void main(String[] args) {
		ordArray2 arr = new ordArray2(100);
		arr.insert(72);
		arr.insert(90);
		arr.insert(100);
		arr.display();
		System.out.print(arr.find(100));

	}

}
