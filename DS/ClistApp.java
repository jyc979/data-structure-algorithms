package DS;
class CLink{
	public double data;
	public CLink next;
	//----------------------------------------
	public CLink(Double data){
		this.data = data;
	}
	//----------------------------------------
	public void displayLink(){
		System.out.print("{"+data+"} ");
	}
	//----------------------------------------
}//end class CLink
class CLinkList{
	private CLink current;
	private int count;
	//----------------------------------------
	CLinkList(){
		current = null;
	}
	//----------------------------------------
	public int getCount(){
		return count;
	}
	//----------------------------------------
	public boolean isEmpty(){
		return (current == null||count==0);
	}
	//----------------------------------------
	public void step(){
		if(isEmpty())
			System.out.println("List is empty");
		else{
			current = current.next;
		}
	}
	//----------------------------------------
	public void insert(double dd){
		CLink newLink = new CLink(dd);
		if(isEmpty()){
			current = newLink;
			current.next = current;
		}
		else{
			newLink.next = current.next;
			current.next = newLink;
			current = newLink;
		}
		count++;
	}
	//----------------------------------------
	public CLink find(double dd){
		int findcount =0;
		if(isEmpty()){
			System.out.print("Can not be found");
			return null;
		}
		else{
			while(findcount != count){
				if(current.data == dd)
					return current;
				else
					step();
				findcount++;
			}
			return null;
		}
	}
	//----------------------------------------
	public void display(){
		int displaycount =0;
		if(isEmpty())
			System.out.println("List is empty");
		else{
			step();
			while(displaycount != count){
				current.displayLink();
				step();
				displaycount++;
			}
		}
	}
	//----------------------------------------
	public double getCurrent(){
		return current.data;
	}
	//----------------------------------------
	public void displayCurrent(){
		System.out.println("current is "+current.data);
	}
	//----------------------------------------
	public double delete(){
		double store = current.data;
		if(isEmpty()){
			System.out.println("List empty");
			return -1;
		}
		else if(count==1){
			current = null;
			count--;
		}
		else{
			//current.next = current.next.next; < this method deletes next to current
			
			//this one deletes current.
			CLink temp = current.next;
			for(int i=0;i<count-1;i++) //get to the link right before current
				step();
			
			current.next = temp;
			count--;
			
		}
		return store;
	}
	//----------------------------------------
}//end class CLinkList
class Joseph{
	private CLinkList cl;
	private int numb,pattern,start;
	//----------------------------------------
	Joseph(int numb, int pattern, int start){
		this.numb = numb;
		this.pattern = pattern;
		this.start = start;
		
		cl = new CLinkList();
		
		for(int i=0;i<numb;i++) //form a circle with number of people
			cl.insert((double)(i+1));
		cl.step();// current at 0
	}
	//----------------------------------------
	public double kill(){
		for(int i=start-1;i<pattern;i++){
			cl.step();
		}
		double store = cl.getCurrent();
		cl.delete();
		cl.step();
		return store;
	}
	//----------------------------------------
	public void survival(){
		while(cl.getCount()!=1){
			System.out.println((int)kill()+"th person was killed.");
		}
		System.out.println("Last person to survive is "+(int)cl.getCurrent()+"th person.");
	}
	//----------------------------------------
}//end class Joseph
class CStack{
	CLinkList cl;
	//----------------------------------------
	CStack(){
		cl = new CLinkList();
	}
	//----------------------------------------
	public void push(double dd){
		cl.insert(dd);
	}
	//----------------------------------------
	public double pop(){
		return cl.delete();
	}
	//----------------------------------------
	public double peek(){
		return cl.getCurrent();
	}
	//----------------------------------------
	public boolean isEmpty(){
		return cl.isEmpty();
	}
	//----------------------------------------
	public void displayS(){
		cl.display();
	}
	//----------------------------------------
		
} //end class CStack
public class ClistApp {

	public static void main(String[] args) {
		Joseph q = new Joseph(10,3,1);
		q.survival();
		
		
		
		
		
		
	}//end main
}//end class ClistApp
