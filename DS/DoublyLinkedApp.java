package DS;

	class Link9{
		public long dData; //data
		public Link9 next; // next link
		public Link9 previous; //previous link
		//----------------------------------------
		public Link9(long d)
		{dData =d;}
		//----------------------------------------
		public void displayLink()
		{System.out.print(dData+ " ");}
		//----------------------------------------
	}//end class Link

	/////////////////////////////////////////////////
	class DoublyLinkedList{
		private Link9 first; // ref to first item
		private Link9 last; // ref to last item
		//----------------------------------------
		public DoublyLinkedList(){ //constructor
			first = null;
			last = null;
		}
		//----------------------------------------
		public boolean isEmpty()
		{return first == null;}
		//----------------------------------------
		public void insertFirst(long dd){// insert at front of list
			Link9 newLink = new Link9(dd); //make new link
			
			if(isEmpty()) //if empty list
				last = newLink; //newLink <-- last
			else
				first.previous = newLink; // newLink <-- old first
			newLink.next = first; // newLink --> old first
			first = newLink; //first --> newLink
		}
		//----------------------------------------
		public void insertLast(long dd){
			Link9 newLink = new Link9(dd);//make new link
			
			if(isEmpty())//if empty
				first = newLink; // first --> newLink
			else{
				last.next = newLink; // old last --> newLink
				newLink.previous = last; // old last <-- newLink
			}
			last = newLink; //newLink <-- last
		}
		//----------------------------------------
		public long deleteFirst(){//delete firstLink
			Link9 temp = first; 
			if(first.next == null) //if only one item
				last = null; //null <-- last
			else
				first.next.previous = null; // null <-- old next
			first = first.next; //first --> old next
			return temp.dData;
		}
		//----------------------------------------
		public long deleteLast(){
			Link9 temp = last;
			if(first.next == null) //if only one item
				first = null; // first --> null
			else
				last.previous.next = null; //old previous --> null
			last = last.previous; // old previous <-- last
			return temp.dData;
		}
		//----------------------------------------
				//insert dd just after key
		public boolean insertAfter(long key, long dd){ //assumes non-empty list
			Link9 current = first; // start at beginning
			while(current.dData!= key){
				current = current.next;
				if(current == null)
					return false;
			}
			
			Link9 newLink = new Link9(dd); //make new link
			if(current == last){ //if last link,
				newLink.next = null; //newLink --> null
				last = newLink; //newLink <-- last
			}
			else{ //if not last link,
				newLink.next = current.next; //newLink --> old next
				
				current.next.previous = newLink;//newLink<-- old next
			}
			newLink.previous = current;//old current <-- newLink
			current.next = newLink; //old current--> newLink
			return true; //found it, did insertion
			
		}
		//----------------------------------------
		public Link9 deleteKey(long key){
			Link9 current = first;
			while(current.dData!=key){
				current = current.next;
				if(current== null)
					return null;
			}
			if(current == first) // found it; first item?
				first = current.next; //first --> old next
			else //not first
				current.previous.next = current.next;//old previous --> old next
			if(current == last) //last item?
				last = current.previous; //old pervious <-- last
			else //not last item
				current.next.previous = current.previous; //old previous <-- old next
			return current;
		}
		//----------------------------------------
		public void displayForward(){
			System.out.print("List (first-->last): ");
			Link9 current = first;
			while(current!= null){
				current.displayLink();
				current = current.next;
			}
			System.out.println();
		}
		//----------------------------------------
		public void displayBackward(){
			System.out.print("List (last --> first): ");
			Link9 current = last; //start at end
			while(current !=null){
				current.displayLink();
				current = current.previous;
			}
			System.out.println(" ");
		}
		//----------------------------------------
	}//end class DoublyLinkedList
	class Deque2{
		DoublyLinkedList dl;
		//----------------------------------------
		Deque2(){
			dl = new DoublyLinkedList();
		}
		//----------------------------------------
		public boolean isEmpty(){
			return dl.isEmpty();
		}
		//----------------------------------------
		public void insertLeft(long item){
			dl.insertFirst(item);
		}
		//----------------------------------------
		public void insertRight(long item){
			dl.insertLast(item);
		}
		//----------------------------------------
		public void removeLeft(){
			dl.deleteFirst();
		}
		//----------------------------------------
		public void removeRight(){
			dl.deleteLast();
		}
		//----------------------------------------
		public void display(){
			dl.displayForward();
		}
	}
	public class DoublyLinkedApp {
		public static void main(String[] args) {
			Deque2 q = new Deque2();
			q.insertLeft(50);
			q.insertLeft(60);
			q.insertRight(70);
			q.insertRight(80);
			q.display();
			q.removeLeft();
			q.display();
			q.removeRight();
			q.display();
			

		}//end main
	}// end class DoublyLinkedApp
