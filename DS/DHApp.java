package DS;

public class DHApp {
	public int DHCalc(int base, int modul)
	{
		int private1 = 50;
		int private2 = 90;
		
		System.out.println("A's private: "+private1+", B's private: "+private2);
		
		int A = (int)(Math.pow(base, private1)%modul);
		int B = (int)(Math.pow(base, private2)%modul);
		
		int secret = (int)(Math.pow(B, private1)%modul);
		int secret2 = (int)(Math.pow(A, private2)%modul);
		
		System.out.println(A+","+B+","+secret+","+secret2);
		
		if(secret==secret2)
			return secret;
		else
			return -1;
	}

	public static void main(String[] args) {
		DHApp b = new DHApp();
		System.out.print(b.DHCalc(5, 23));
		

	}

}
