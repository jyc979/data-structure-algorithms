package DS;

// demonstrates ordered array class
// to run this program: C>java OrderedApp
////////////////////////////////////////////////////////////////
class OrdArray
   {
   private long[] a;                 // ref to array a
   private int nElems;               // number of data items
   //-----------------------------------------------------------
   public OrdArray(int max)          // constructor
      {
      a = new long[max];             // create array
      nElems = 0;
      }
   //-----------------------------------------------------------
   public int size()
      { return nElems; }
   public long get(int index){
	   return a[index];
   }
   
   //-----------------------------------------------------------
   public int find(long searchKey)
      {
      int lowerBound = 0;
      int upperBound = nElems-1;
      int curIn;

      while(true)
         {
         curIn = (lowerBound + upperBound ) / 2;
         if(a[curIn]==searchKey)
            return curIn;              // found it
         else if(lowerBound > upperBound)
            return nElems;             // can't find it
         else                          // divide range
            {
            if(a[curIn] < searchKey)
               lowerBound = curIn + 1; // it's in upper half
            else
               upperBound = curIn - 1; // it's in lower half
            }  // end else divide range
         }  // end while
      }  // end find()
   //-----------------------------------------------------------
   public void insert(long value)    // put element into array
      {
	  int lowerBound =0;
	  int upperBound = nElems-1;
	  int index =0;
	  while(true){
		  if(lowerBound>upperBound)
			  break;
		  index = (lowerBound+upperBound)/2;
		  if(a[index]<value){
			  lowerBound = index+1;
			  index++;
		  }
		  else if(a[index]>value){
			  upperBound = index-1;
			  
		  }
	  }
	  
	  for(int j=nElems;j>index;j--){
		  a[j] = a[j-1];
	  }
	  a[index] = value;
	  nElems++;
     
      }  // end insert()
   //-----------------------------------------------------------
   public boolean delete(long value)
      {
      int j = find(value);
      if(j==nElems)                  // can't find it
         return false;
      else                           // found it
         {
         for(int k=j; k<nElems; k++) // move bigger ones down
            a[k] = a[k+1];
         nElems--;                   // decrement size
         return true;
         }
      }  // end delete()
   //-----------------------------------------------------------
   public void display()             // displays array contents
      {
      for(int j=0; j<nElems; j++)       // for each element,
         System.out.print(a[j] + " ");  // display it
      System.out.println("");
      }
   //-----------------------------------------------------------
   public static OrdArray merge(OrdArray ord1, OrdArray ord2){
	   OrdArray ord3;
	   ord3 = new OrdArray(ord1.size()+ord2.size());
	   int r =0; //ord3 index
	   int i=0; // ord1 index
	   int j=0; //ord2 index
	   while(i<ord1.size()&&j<ord2.size()){
		   if(ord1.a[i]<ord2.a[j]){
			   ord3.a[r] = ord1.a[i];
			   r++;
			   i++;
			   ord3.nElems++;
		   }
		   else if(ord1.a[i]>ord2.a[j]){
			   ord3.a[r] = ord2.a[j];
			   r++;
			   j++;
			   ord3.nElems++;
		   }
	   }
	   
	  while(i<ord1.size()){
		   ord3.a[r] = ord1.a[i];
		   r++;
		   i++;
		   ord3.nElems++;
	   }
	   while(j<ord2.size()){
		   ord3.a[r] = ord2.a[j];
		   r++;
		   j++;
		   ord3.nElems++;
	   }
	   
	   return ord3;
   }
   }  // end class OrdArray
////////////////////////////////////////////////////////////////
public class OrderedApp
   {
   public static void main(String[] args)
      {
      int maxSize = 100;             // array size
      OrdArray arr;                  // reference to array
      arr = new OrdArray(maxSize);   // create the array

      arr.insert(77);              // insert 10 items
      arr.insert(99);
      arr.insert(44);
      arr.insert(55);
      arr.insert(22);
      arr.insert(88);
      arr.display();
      
      OrdArray arr2 = new OrdArray(maxSize);
      arr2.insert(11);
      arr2.insert(0);
      arr2.insert(33);
      arr2.insert(66);
      
      arr2.display();
      
      OrdArray arr3 = OrdArray.merge(arr,arr2);
      arr3.display();
      
      
 
      
            // display items again
      }  // end main()
   }  // end class OrderedApp
