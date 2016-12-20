package DS;
// insertSort.java
// demonstrates insertion sort
// to run this program: C>java InsertSortApp
//--------------------------------------------------------------
class ArrayIns
   {
   private long[] a;                 // ref to array a
   private int nElems;               // number of data items
//--------------------------------------------------------------
   public ArrayIns(int max)          // constructor
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
//--------------------------------------------------------------
   public void insertionSort()
      {
      int in, out;
      //int comp=0;
      //int copy=0;
     // int count =0;
    		  

      for(out=1; out<nElems; out++)     // out is dividing line
         {
         long temp = a[out];  
         //copy++;
         in = out;    
         
         while(in>0 && a[in-1] >= temp) // until one is smaller,
            {
        	 
        	//comp++;
        	
            a[in] = a[in-1];// shift item to right
            //copy++;
            --in;            
            //if(in==0)comp++;
            
            }
         a[in] = temp;// insert marked item
         //copy++;
         }  // end for
      
      //System.out.println(copy+": copies "+comp+": comps ");
      }  // end insertionSort()
 //------------------------------------------------------------
   public void insertionSortNodups()
   {
   int in, out;
   int comp=0;
   int copy=0;
   int count =0;
 		  

   for(out=1; out<nElems; out++)     // out is dividing line
      {
      long temp = a[out];  
      //copy++;
      in = out;    
      
      while(in>0 && a[in-1] >= temp) // until one is smaller,
         {
     	 if(temp==a[in-1]&&temp!=-1){
     		 temp=-1;
     		 count++;
     	 }
     	//comp++;
     	
         a[in] = a[in-1];// shift item to right
         //copy++;
         --in;            
         //if(in==0)comp++;
         }
      	
      a[in] = temp;// insert marked item
      //copy++;
      }  // end for
   nElems -=count;
   for(int i=0;i<nElems;i++){
 	  a[i]=a[count];
 	  count++;
   }
   System.out.println(copy+": copies "+comp+": comps ");
   }  // end insertionSort()
 //------------------------------------------------------------
   public static long median(ArrayIns arr){
	   arr.insertionSort();
	   long median =0;
	   
	   if(arr.nElems%2==0){
		   long half = arr.a[arr.nElems/2];
		   long half2 = arr.a[arr.nElems/2-1];
		   median = (half+half2)/2;
	   }
	   else if(arr.nElems%2==1){
		   long half = arr.a[arr.nElems/2];
		   median = half;
	   }
	  
	   return median;
   }
//--------------------------------------------------------------
  public void noDups(){
	  int total = nElems-1;
	  int amount =0;
	  for(int i=0;i<total;i++){
		  if(a[i]==a[i+1]){
			  amount++;
			  nElems--;
		  }
		  else if(a[i]!=a[i+1]){
			  a[i-amount] = a[i];	  
		  }
	  }
  }

//--------------------------------------------------------------
   }  // end class ArrayIns
////////////////////////////////////////////////////////////////
class InsertSortApp
   {
   public static void main(String[] args)
      {
      int maxSize = 150;            // array size
      ArrayIns arr;                 // reference to array
      arr = new ArrayIns(maxSize);  // create the array
                 
      
      for(int i=0;i<100;i++){
    	  long numb = (long)(Math.random()*150+1);
    	  arr.insert(numb);
      }
     
             
      arr.insertionSort();
      
      
                     // display them again
      }  // end main()
   }  // end class InsertSortApp
