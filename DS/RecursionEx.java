package DS;
import java.io.*;

public class RecursionEx {

	public int triangleLoop(int n){  //triangular number using forloop
		int ans=0;
		int i;
		for(i=1;i<=n;i++){
			ans += i;
		}
		return ans;
	}
	//----------------------------------
	public int triangleLoop2(int n){ // triangular number using while loop
		int total =0;
		
		while(n>0){
			total = total+n;
			--n;
		}
		return total;
	}
	//----------------------------------
	public int triangleRe(int n){ //traignular number using recursion
		System.out.println("Entering: n="+n);
		if(n==1){
			System.out.println("Returning 1");
			return 1;
		}
		else{
			int temp = n+triangleRe(n-1);
			System.out.println("Returning " + temp);
			return temp;
		}
	}
	//----------------------------------
	public int factorial(int n){  //factorial recursion
		if(n==0){
			return 1;
		}
		else{
			return n*factorial(n-1);
		}
	}
	//----------------------------------
	public int power(int x, int y){
		if(y==1)
			return x;
		else if(y%2==0){
			return power(x*x,y/2);
		}
		else{
			return x*power(x,y-1);
		}
	}
	//----------------------------------
	public int mult(int x, int y){
		if(y==1){
			return x;
		}
		else{
			return x+mult(x,y-1);
		}
	}
	//----------------------------------
	

	
	//////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) throws IOException{
		RecursionEx q = new RecursionEx();
		System.out.print(q.power(3, 18));
		
		
		
	}
	public static String getString() throws IOException{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

}//end class RecursionEx
