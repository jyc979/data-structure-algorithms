package DS;
import java.io.*;
class Params{
	public int n;
	public int returnAddress;
	public Params(int nn, int ra){
		n = nn;
		returnAddress = ra;
	}
} //end class Params
//-------------------------------------------
class StackM{
	private int maxSize;
	private Params[] stackArray;
	private int top;
	//-------------------------------------------
	public StackM(int s){
		maxSize = s;
		stackArray = new Params[maxSize];
		top = -1;
	}
	//-------------------------------------------
	public void push(Params p){
		stackArray[++top] = p;
	}
	//-------------------------------------------
	public Params pop(){
		return stackArray[top--];
	}
	//-------------------------------------------
	public Params peek(){
		return stackArray[top];
	}
	//-------------------------------------------
}//end class StackM

public class StackTriangleApp {
	static int theNumber;
	static int theAnswer;
	static StackM theStack;
	static int codePart;
	static Params theseParams;
	//-------------------------------------------
	

	public static void main(String[] args) throws IOException{
		System.out.print("Enter a number: ");
		theNumber = getInt();
		recTriangle();
		System.out.println("Triangle="+theAnswer);

	}//end main()
	//-------------------------------------------
	public static void recTriangle(){
		theStack = new StackM(10000);
		codePart =1;
		while(step() == false)
			;
	}
	//-------------------------------------------
	public static boolean step(){
		switch(codePart){
		case 1: //initial call
			theseParams = new Params(theNumber, 6);
			theStack.push(theseParams);
			codePart =2;
			break;
		case 2: //method entry
			theseParams = theStack.peek();
			if(theseParams.n ==1){ //test
				theAnswer =1;
				codePart =5;
			}
			else
				codePart =3;//recursive call
			break;
		case 3: //method call
			Params newParams = new Params(theseParams.n-1, 4);
			theStack.push(newParams);
			codePart=2;
			break;
		case 4: //Calculation
			theseParams = theStack.peek();
			theAnswer = theAnswer + theseParams.n;
			codePart =5;
			break;
		case 5:
			theseParams = theStack.peek();
			codePart = theseParams.returnAddress;
			theStack.pop();
			break;
		case 6:
			return true;
		}//end switch
		return false;
	} //end step
	//-------------------------------------------
	public static String getString() throws IOException{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
	//-------------------------------------------
	public static int getInt() throws IOException{
		String s = getString();
		return Integer.parseInt(s);
	}
	//-------------------------------------------
}//end class StackTriangleApp

