package DS;
public class RadixSortApp extends DoublyLinkedApp{
	DoublyLinkedList[] list;
	
	//--------------------------------
	RadixSortApp(){
		list = new DoublyLinkedList[10]; 
		for(int i=0;i<10;i++)  //create an Array "list" that holds ten linked lists.
			list[i] = new DoublyLinkedList();
		
	
	}//constructor end
	//--------------------------------
	
	public long[] sort(long[] array, int digit,int start){ //recursion radix
		if(digit==0) //base case
			return  array;
		
		else{ //when digit is not 0
			
		
		for(int i=0;i<array.length;i++){ //copy elements from array to list
			list[(int)(array[i]/start%10)].insertLast(array[i]); 
		}
				
		int arrayCounter =0;
		for(int i=0;i<10;i++){
				while(!list[i].isEmpty()) //if list is not empty
					array[arrayCounter++] = list[i].deleteFirst(); //copy it into copy list.
		} //end for loop
		
		

		
		return sort(array,digit-1,start*10); //recursion
		
		}// end else
	}//end sort()
	//--------------------------------
	public long[] sort2(long[] array, int digit){ //loop radix
		
		
			
		for(int j=0;j<digit;j++){
			int start = (int)Math.pow(10, j);
			for(int i=0;i<array.length;i++){ //copy elements from array to list
				list[(int)(array[i]/start%10)].insertLast(array[i]); 
			}
				
			int arrayCounter =0;
			for(int i=0;i<10;i++){
				while(!list[i].isEmpty()) //if list is not empty
					array[arrayCounter++] = list[i].deleteFirst(); //copy it into copy list.
			} //end for loop
		
			
		}
		return array;
		
		

	}
	//--------------------------------
	public void display(long[] Array){
		for(int i=0;i<Array.length;i++){
			System.out.print(Array[i]+" ");
		}
	}
	//--------------------------------

	public static void main(String[] args) {
		long[] g = {5,2,8,2,4,11,17,19,34,23,53,77,43,54};
		RadixSortApp v = new RadixSortApp();
		int longestDigit = 2;
		int start = 1;
	    v.display(v.sort2(g, longestDigit));

	}

}
