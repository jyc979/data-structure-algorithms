package DS;

import java.util.*;

class Queue{
	private int maxSize;
	private long[] queArray;
	private int front;
	private int rear;
	private int nItems;
	//----------------------------------------
	public Queue(int s){
		maxSize = s;
		queArray = new long[maxSize];
		front =0;
		rear =-1;
		nItems =0;
	}
	//----------------------------------------
	public void replace(long value){
		queArray[front] = value;
	}
	//----------------------------------------
	public void insert(long j){
		if(rear == maxSize-1)
			rear=-1;
		queArray[++rear] = j;
		nItems++;
	}
	//----------------------------------------
	public long remove(){
		long temp = queArray[front++];
		if(front == maxSize)
			front =0;
		nItems--;
		return temp;
	}
	//----------------------------------------
	public long peekFront(){
		return queArray[front];
	}
	//----------------------------------------
	public boolean isEmpty(){
		return (nItems==0);
	}
	//----------------------------------------
	public boolean isFull(){
		return (nItems == maxSize);
	}
	//----------------------------------------
	public int size(){
		return nItems;
	}
	//----------------------------------------
	public void display(){
		if(rear>=front){ // contiguous sequence 
		for(int i=front;i<rear+1;i++){
			System.out.print(queArray[i]+" ");
		}//close for
		System.out.println();
		}//close if
		
		
		else{ // broken sequence
			int total = maxSize;
			for(int i=front; i<total;i++){
				System.out.print(queArray[i]+" ");
				if(i==total-1){
					front=0;
					total = rear+1;
					for(int j=front; j<total;j++){
						System.out.print(queArray[j]+" ");
					}//close inner for
				}//close inner if
			}//close for
			System.out.println();
		}//close else
	}
	//----------------------------------------
}// end class Queue
class deQue{
	private int maxSize;
	private int nItems;
	private long[] deArray;
	private int right;
	private int left;
	//----------------------------------------
	deQue(int s){
		maxSize =s;
		deArray = new long[maxSize];
		nItems =0;
		left=1;
		right =-1;
	}
	//----------------------------------------
	boolean isFull(){
		return (nItems==maxSize);
	}
	//----------------------------------------
	boolean isEmpty(){
		return(nItems ==0);
	}
	//----------------------------------------
	void insertRight(long j){
		if(!isFull()){
			right++;
			if(right==maxSize){right=0;}
			deArray[right] = j;
			nItems++;
		}
		else{
			System.out.println("Array is Full");
		}
	}
	//----------------------------------------
	void insertLeft(long j){
		if(!isFull()){
			left--;
			if(left==-1){left=maxSize-1;}
			deArray[left] = j;
			nItems++;
		}
		else{
			System.out.println("Array is Full");
		}
	}
	//----------------------------------------
	long removeRight(){
		if(!isEmpty()){
			right--;
			if(right==-1){right=maxSize-1;}
			nItems--;
			return deArray[right];
		}
		else{
			System.out.println("Array is Empty");
			return -1;
		}
	}
	//----------------------------------------
	long removeLeft(){
		if(!isEmpty()){
			left++;
			if(left==maxSize){left=0;}
			nItems--;
			return deArray[left];
		}
		else{
			System.out.println("Array is Empty");
			return -1;
		}
	}
	//----------------------------------------
	long peekRight(){
		return deArray[right];
	}
	//---------------------------------------
	void display(){
		int index = left-1;
		System.out.println("right is "+right+" and left is "+left);
		for(int i=0;i<nItems;i++){
			
			if(index>=maxSize) index=0;
			System.out.print(deArray[index]+" ");
			index++;
		}
		System.out.println("");
	}
	//----------------------------------------
}//end of class deQue
class deStack{
	deQue de;
	deStack(int s){
	de = new deQue(s);
	}
	//---------------------------------------
	void push(long j){
		de.insertRight(j);
	}
	//---------------------------------------
	long pop(){
		return de.removeRight();
	}
	//---------------------------------------
	long peek(){
		return de.peekRight();
	}
	//---------------------------------------
	boolean isFull(){
		return de.isFull();
	}
	//---------------------------------------
	boolean isEmpty(){
		return de.isEmpty();
	}
}//end of class deStack(stack using deQue)
class checkout{
	int numQue; // number of queues;
	Queue q[];
	int maxSize; // max Customer per que
	int count =0;
	int countCus =0;
	checkout(int s, int g){//s = number of Queues, g = max Customer per que.
		numQue =s;
		maxSize = g;
		q = new Queue[numQue];
		for(int i=0;i<numQue;i++){
			q[i] = new Queue(maxSize);
		}//end for
	}//end constructor
	//---------------------------------------
	void addCustomer(){
		long numbOfItems = (long)(Math.random()*10+1);
		int min = q[0].size(); //find minimum queue
		int index =0;
		for(int i=0; i<numQue;i++){
			if(min>q[i].size()){
				min = q[i].size();
				index = i;
				
			}
		}
		
		System.out.println("Min index is " + index);
		q[index].insert(numbOfItems); //add a customer to the queue w/ lowest number of items.
		countCus++;
	}
	//---------------------------------------
	void process(){
		for(int i=0;i<numQue;i++){
			long numbOfItems = q[i].peekFront();
			numbOfItems--;
			q[i].replace(numbOfItems);
			if(numbOfItems ==0){
				q[i].remove();
				count++;
			}
			else if(numbOfItems<0){
				numbOfItems=0;
				q[i].replace(numbOfItems);
			}
		}
	}
	//---------------------------------------
	void display(){
		for(int i=0;i<numQue;i++){
			q[i].display();
			System.out.println();
		}
		System.out.println("------------------------------");
	}
	//---------------------------------------
}

public class QueueApp {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		checkout c = new checkout(3,5);
		for(int i=0;i<13;i++){
			c.addCustomer();
		}
		c.display();
		while(true){
		System.out.print("Time? ");
		int time = scan.nextInt(); 
		for(int i=0; i<time;i++){
			c.process();
		}
		if(c.count==c.countCus){
			c.display();
			System.out.println("All Customers have been checkedout");
			break;
		}
		c.display();
		}
	
	
	}

}
