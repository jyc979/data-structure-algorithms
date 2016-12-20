package DS;
class ArrayBub
   {
   private long[] a;                 // ref to array a
   private int nElems;               // number of data items
//--------------------------------------------------------------
   public ArrayBub(int max)          // constructor
      {
      a = new long[max];                 // create the array
      nElems = 0;                        // no items yet
      }
//--------------------------------------------------------------
   public void insert(long value)    // put element into array
      {
      a[nElems] = value;             // insert it
      nElems++;                      // increment size
      }
//--------------------------------------------------------------
   public void display()             // displays array contents
      {
      for(int j=0; j<nElems; j++)       // for each element,
         System.out.print(a[j] + " ");  // display it
      System.out.println("");
      }
//----------------------------------------------------------------
   public void bubbleSort(){
	   int out, in;
	   for(out = nElems -1;out>=1;out--){
		   for(in =0; in<out; in++){
			   if(a[in]>a[in+1])
				   swap(in,in+1);
		   }
	   }
   }
//----------------------------------------------------------------
   public void bidirBubbleSort(){
	   int out1, out2, in; //out1 for high, out2 for low
	   out1 = nElems-1;
	   out2 = 0;
	   in =0;
	   while(out1>out2){
		   while(in<out1){
			   if(a[in]>a[in+1]){
				   swap(in,in+1);
				   in++;
			   }
			   else{
				   in++;
			   }
		   }
		   out1--;
		   while(in>out2){
			   if(a[in]<a[in-1]){
				   swap(in,in-1);
				   in--;
				   
			   }
			   else{
				   in--;
			   }
		   }
		   out2++;
	   }
	   
   }
//----------------------------------------------------------------
   public void oddEvenSort(){
	   int out,in;
	   int total = nElems-1;
	   boolean sorted= false;
	   while(!sorted){
		   sorted= true;
		   for(in =1; in<total; in+=2){
			   if(a[in]>a[in+1]){
				swap(in,in+1);
				sorted= false;
			   } 
		   
		   }
	   
		   for(in =0; in<total; in+=2){
			   if(a[in]>a[in+1]){
				   swap(in,in+1);
				   sorted= false;
			   }
			   
		   
		   }
	   }
	   
   }
	   
//----------------------------------------------------------------
   public void swap(int one, int two){
	   long temp = a[one];
	   a[one] = a[two];
	   a[two] = temp;
   }
//----------------------------------------------------------------
   }
public class BubbleSortApp {

	public static void main(String[] args) {
		int maxSize = 20;            // array size
	      ArrayBub arr;                 // reference to array
	      arr = new ArrayBub(maxSize);  // create the array

	      arr.insert(50);
	      arr.insert(30);
	      arr.insert(10);
	      
	      
	      
	      arr.bubbleSort();
	      arr.display();
	      
	      //arr.bidirBubbleSort();
	      

	                

	}

}
