package DS;

public class ArrayEx {

	public static void main(String[] args) {
		int[] arr = new int[100];
		arr[0] = 77;                 // insert 10 items
	      arr[1] = 99;
	      arr[2] = 44;
	      arr[3] = 55;
	      arr[4] = 22;
	      arr[5] = 88;
	      arr[6] = 11;
	      arr[7] = 00;
	      arr[8] = 66;
	      arr[9] = 33;
	      
		int search;
		int numb =0;
		numb =10;
		search = 66;
		int j=0;
		for(j=0;j<numb;j++){
			if(arr[j]==search)
				break;
		}
		System.out.println(arr[50]);
		for(int k=j;k<numb-1;k++){
			arr[k]= arr[k+1];
		}
		numb--;
			

	}

}
