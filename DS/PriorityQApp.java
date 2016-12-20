package DS;
class PriorityQ{
	//array in sorted order, from max at 0 to min at size-1
	private int maxSize;
	private long[] queArray;
	private int nItems;
	//----------------------------------------
	public PriorityQ(int s){
		maxSize = s;
		queArray = new long[maxSize];
		nItems = 0;
	}
	//-----------------------------------------
	public void insert(long item){
		int j;
		
		if(nItems ==0)
			queArray[nItems++]=item;
		else{
			for(j=nItems-1;j>=0;j--){
				if(item>queArray[j])
					queArray[j+1] =queArray[j];
				else
					break;
			}//end for
			queArray[j+1]=item;
			nItems++;
		}// end else
	} //end insert()
	//----------------------------------------
	public long remove()
	{return queArray[--nItems];}
	//----------------------------------------
	public long peekMin()
	{return queArray[nItems-1];}
	//----------------------------------------
	public boolean isEmpty()
	{return (nItems==0);}
	//----------------------------------------
	public boolean isFull()
	{return (nItems==maxSize);}
	//----------------------------------------
}//end class PriorityQ
class RevisedQ{
	private int maxSize;
	private long queArray[];
	private int nItems;
	//---------------------------------------
	public RevisedQ(int s){
		maxSize =s;
		queArray = new long[maxSize];
		nItems =0;
	}
	//---------------------------------------
	public void insert(long item){
		queArray[nItems] = item;
		nItems++;
		if(nItems == maxSize+1){
			System.out.println("Array is Full");
			nItems = maxSize-1;
		}
	}
	//---------------------------------------
	public long remove(){
		int i;
		long min = queArray[0];
		for(i =0; i<nItems;i++){
			if(min>queArray[i]){
				min=queArray[i];
			}
		}
		int j;
		for(j=0; j<nItems;j++){
			if(queArray[j]==min){
				nItems--;
				break;
			}
		}
		for(int k=j;k<nItems;k++){
			queArray[k] = queArray[k+1];
		}
		
		return min;
	}
	//---------------------------------------
	public boolean isEmpty()
	{return (nItems==0);}
	//---------------------------------------
	public boolean isFull()
	{return (nItems==maxSize);}
	//---------------------------------------
	public void display(){
		for(int i=0;i<nItems;i++){
			System.out.print(queArray[i]+" ");
		}
		System.out.println();
	}
}// end class Revised Q
///////////////////////////////////////////////////////
public class PriorityQApp {

	public static void main(String[] args) {
		PriorityQ q = new PriorityQ(5);
		q.insert(10);
		q.insert(20);
		q.insert(3);
		q.insert(6);
		q.insert(580);
	
		
		while(!q.isEmpty()){
			long n =q.remove();
			System.out.print(n+" ");
		}
		System.out.println();
	
		
	}//end main
} // end class PriorityQApp


