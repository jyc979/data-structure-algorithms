package DS;

public class teamApp {

	public static void main(String[] args) {
		int groupSize =5;
		int teamSize = 3;
		char start = 'A';
		String sequence = "";
		showTeam(sequence,start,groupSize,teamSize);

	}
	public static void showTeam(String sequence,char start, int n, int k){
		if(n==0||k==0||k>n){
			if(k==0)
			System.out.println(sequence);
		}
		else{
		showTeam(sequence+start,(char)(start+1),n-1,k-1);
		
		showTeam(sequence,(char)(start+1),n-1,k);
		}
		
	}

}
