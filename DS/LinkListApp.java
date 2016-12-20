package DS;
class Link{
	public int iData; // data
	public double dData; // data
	public Link next; //reference to next link
	//----------------------------------
	public Link(int id, double dd){
		iData = id; //initialize data
		dData = dd;// 'next' is automatically set to null.
	}
	//----------------------------------
	public void displayLink(){
		System.out.print("{"+iData+", "+dData+"} ");
	}
	//----------------------------------
}//end class Link


class LinkList{
	private Link first; //ref to first link on list
	//----------------------------------
	public void LinkList(){
		first = null;//no items on list yet
	}
	//----------------------------------
	public boolean isEmpty(){
		return (first == null);
	}
	//----------------------------------
	public void insertFirst(int id, double dd){ //insert at start of list, make new link
		Link newLink = new Link(id,dd); 
		newLink.next = first; //newLink --> old first
		first = newLink;// first --> newLink
		
	}

	//----------------------------------
	public Link deleteFirst(){ //delete first item
		Link temp = first; //save first's reference to Link temp
		first = first.next; //delete it: first --> old next
		return temp; //return deleted Link
	}
	//----------------------------------
	public Link find(int key){
		Link current = first; //start at 'first'
		while(current.iData !=key){//while no match
			if(current.next==null)     //if end of list
				return null;		   //didn't find it
			else 				       // not end of list,
				current=current.next;  //go to next link
		}
		return current; //found it 
	}
	//----------------------------------
	public Link delete(int key){
		Link current = first;//search for link
		Link previous = first; 
		while(current.iData!=key){
			if(current.next ==null)
				return null; //didn't find it
			else{
				previous = current;
				current = current.next;
			}
		} //found it
		if(current == first)		//if first Link,
			first = first.next;		//change first
		else						//otherwise,
			previous.next = current.next;//bypass it
		return current;
	}
	//----------------------------------
	public void displayList(){
		System.out.print("List(first-->last): ");
		Link current = first; //starts at beginning of list(NEWEST guy added)
		while(current != null){ // until end of list,
			current.displayLink(); //print data
			current = current.next; //move to next link
		}
		System.out.println(" ");
	}
	//----------------------------------
	
} //end class LinkList

public class LinkListApp {

	public static void main(String[] args) {
		LinkList a = new LinkList();
		a.insertFirst(22, 2.99);
		a.insertFirst(44, 4.99);
		a.insertFirst(66, 6.99);
		a.insertFirst(88, 8.99);
		
		a.displayList();
		
		Link f = a.find(44);
		
		
	}//end class main
}// end class LinkListApp
