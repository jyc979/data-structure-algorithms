package DS;
import java.io.*;

class StackZ{
	private int maxSize;
	private char[] stackArray;
	private int top;
//----------------------------------------
	public StackZ(int s){
		maxSize =s;
		stackArray = new char[maxSize];
		top=-1;
	}
//----------------------------------------
	public void push(char j){
		
		stackArray[++top]=j;
	}
//----------------------------------------
	public char pop(){
		return stackArray[top--];
	}
//----------------------------------------
	public char peek(){
		return stackArray[top];
	}
//----------------------------------------	
	public boolean isEmpty(){
		return (top==-1);
	}
//----------------------------------------
	public boolean isFull(){
		return (top==maxSize-1);
	}
//----------------------------------------
} // end class StackZ
class BracketChecker
	{
	private String input;
	//----------------------------------------
	public BracketChecker(String in)
	{input = in;}
	//----------------------------------------
	public void check(){
		int stackSize = input.length();
		StackZ theStack = new StackZ(stackSize);
		
		for(int j=0; j<input.length();j++){
			char ch = input.charAt(j);
			switch(ch)
			{
			case'{':
			case'[':
			case'(':
				theStack.push(ch);
				break;
			case'}':
			case']':
			case')':
				if(!theStack.isEmpty()){
					char chx = theStack.pop();
					if((ch=='}'&& chx!='{')||(ch==']'&&chx!='[')||(ch==')'&&chx!='('))
						System.out.println("Eror: " +ch +" at "+j);
				}
				else
					System.out.println("Error: "+ ch+" at "+j);
				break;
				default:
					break;
			}//end switch
		}// end for
		if(!theStack.isEmpty())
			System.out.println("Error:missing right delimiter");
	}//end check()
	//----------------------------------------
	}//end of class BracketChecker
public class BracketsApp {

	public static void main(String[] args) throws IOException{
		String input;
		while(true){
			System.out.print("Enter string containing delimiters: ");
			System.out.flush();
			input = getString();
			if(input.equals(" "))
				break;
			BracketChecker theChecker = new BracketChecker(input);
			theChecker.check();
		}//end while

	}//end of main
	//----------------------------------------
	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
}//end of BracketsApp
