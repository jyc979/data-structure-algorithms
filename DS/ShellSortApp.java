package DS;
class ArraySh{
	private long[] theArray; //ref to array theArray
	private int nElems; //number of data items
	//----------------------------------------
	public ArraySh(int max){
		theArray = new long[max];
		nElems = 0;
	}
	//----------------------------------------
	public void insert(long value){
		theArray[nElems] = value;
		nElems++;
	}
	//----------------------------------------
	public void display(){
		System.out.print("A=");
		for(int j=0;j<nElems; j++)
			System.out.print(theArray[j]+" ");
		System.out.println("");
	}
	//----------------------------------------
	public void shellSort(){
		int inner, outer;
		long temp;
		
		int h=1; //initial value of h
		while(h<=nElems/3)
			h=h*3+1; //(1,4,13,40,121,...)
		while(h>0) //decreasing h, until h=1
		{
			//h-sort the file
			for(outer=h; outer<nElems;outer++){
				temp = theArray[outer];
				inner = outer;
				//one subpass(eg 0,4,8)
				while(inner>h-1&& theArray[inner-h]>=temp){
					theArray[inner] = theArray[inner-h];
					inner -=h;
				}
				theArray[inner] = temp;
			}//end for
			//System.out.print(h+"-sort "); // print
			//display();					  // after n-sort
			h = (h-1)/3; //decrease h
		}//end while(h>0)
	}//end shellSort()
	//----------------------------------------
}//end class ArraySh
//////////////////////////////////////////////////////
public class ShellSortApp {

	public static void main(String[] args) {
		int maxSize =100;
		ArraySh arr;
		arr = new ArraySh(maxSize);
		
		for(int j=0;j<maxSize;j++){
			long n = (int)(Math.random()*99);
			arr.insert(n);
		}
	
		arr.shellSort();
	

	}//end main()
}//end class ShellSortApp
