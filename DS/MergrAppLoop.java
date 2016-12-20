package DS;

public class MergrAppLoop {

	public static void main(String[] args) {
		int[] A ={23,47,81,95};
		int[] B ={7,14,39,55,62,74};
		int[] C = new int[A.length+B.length];
		
		merge(A,4,B,6,C);
		display(C,10);

	}
	public static void merge(int[] arrayA,int sizeA, int[] arrayB, int sizeB, int[] arrayC){
		int aDex =0, bDex=0, cDex=0;
		
		while(aDex<sizeA&&bDex<sizeB)
			if(arrayA[aDex]<arrayB[bDex])
				arrayC[cDex++] = arrayA[aDex++];
			else
				arrayC[cDex++] =arrayB[bDex++];
		
		while(aDex<sizeA) //arrayB is empty
			arrayC[cDex++] = arrayA[aDex++];
		while(bDex<sizeB)
			arrayC[cDex++] = arrayB[bDex++];
	}//end merge
	public static void display(int[] theArray, int size){
		for(int j=0;j<size;j++)
			System.out.print(theArray[j]+" ");
		System.out.println("");
	}

}
