package DS;

public class BinaryFind {
		public int find(int searchKey, int[] numbs){
			int min=0;
			int max = numbs.length-1;
			int index;
			while(true){
				index = (max+min)/2;
				if(numbs[index]==searchKey){
					return index;
				}
				else if(min>max){
					return -1;
				}
				else{
					if(searchKey>numbs[index]){
						min = index+1;
					}
					else{
						max=index-1;
					}
				}
			}
		}
	public static void main(String[] args) {
		BinaryFind b = new BinaryFind();
		int[] numbs ={2,19,52,131,252,291,382,397,422,447,460,465,478,489,506,508,523,552,559,580,616,619,
				663,678,714,728,780,821,865,988};
		System.out.println(b.find(552, numbs));

	}

}
