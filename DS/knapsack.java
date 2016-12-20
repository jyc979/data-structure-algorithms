package DS;

public class knapsack {
	
	

	public static void main(String[] args) {
		int sack[] = {1,5,6};
		int target = 16;
		knapsack a = new knapsack();
		System.out.print(a.knap(sack, target, 0));
		

	}
public boolean knap(int[] sack, int target, int start){
		if(start>=sack.length)
			return (target==0);
		
		if(knap(sack,target-sack[start],start+1))
			return true;
			
		if(knap(sack,target,start+1))
			return true;
		
		return false;
	
	}

}
