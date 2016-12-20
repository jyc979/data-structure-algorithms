package DS;
class HighArrayApp
   {
   private long[] a;                 // ref to array a
   private int nElems;               // number of data items
   //-----------------------------------------------------------
   public HighArrayApp(int max)         // constructor
      {
      a = new long[max];                 // create the array
      nElems = 0;                        // no items yet
      }
   //-----------------------------------------------------------
   public boolean find(long searchKey)
      {                              // find specified value
      int j;
      for(j=0; j<nElems; j++)            // for each element,
         if(a[j] == searchKey)           // found item?
            break;                       // exit loop before end
      if(j == nElems)                    // gone to end?
         return false;                   // yes, can't find it
      else
         return true;                    // no, found it
      }  // end find()
   //-----------------------------------------------------------
   public void insert(long value)    // put element into array
      {
      a[nElems] = value;             // insert it
      nElems++;                      // increment size
      }
   //-----------------------------------------------------------
   public boolean delete(long value)
      {
      int j;
      for(j=0; j<nElems; j++)        // look for it
         if( value == a[j] )
            break;
      if(j==nElems)                  // can't find it
         return false;
      else                           // found it
         {
         for(int k=j; k<nElems; k++) // move higher ones down
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
   public long getMax(){
	   if(nElems ==0){
		   return -1;
	   }
	   else{
		   long temp =a[0];
	   for(int j=0;j<nElems;j++){
		   if(temp<a[j]){
			   temp = a[j];
		   }
		 
		   
	   	}
	   return temp;
	   }
   }
   
//------------------------------------------------------------
	public long removeMax(){
		long max = getMax();
		int j;
		for(j=0;j<nElems;j++){
			if(a[j]==max){
				break;
			}
		}
		for(int i=j;i<nElems;i++){
			a[i]=a[i+1];
		}
		return max;
		
	}
	public int length(){
		return nElems;
	}
//----------------------------------------------------------------
	public void nodups(){
		int j;
		int i;
		long temp;
		for(i=0;i<nElems;i++){
			
			for(j=i+1;j<nElems;j++){
				if(a[i]==a[j]){
					for(int k= j;k<nElems;k++){
						a[k] =a[k+1];
					}
					nElems--;
					j--;
					
				}
			}
		}
		
	}
   }
////////////////////////////////////////////////////////////////
public class HighArrayApplication
   {
   public static void main(String[] args)
      {
      int maxSize = 100;            // array size
      HighArrayApp arr;                // reference to array
      arr = new HighArrayApp(maxSize); // create the array

      arr.insert(77);
      arr.insert(11);
      arr.insert(99);
      arr.insert(44);
      arr.insert(11);
      arr.insert(55);
      arr.insert(22);
      arr.insert(88);
      arr.insert(11);
      arr.insert(11);
      arr.insert(00);
      arr.insert(00);
      arr.insert(66);
      arr.insert(11);
      arr.insert(33);
      arr.insert(11);
      arr.nodups();
      arr.display();
      
      //insertion
      
                     // display items
      
     
            
      }  // end main()
   }  // end class HighArrayApp
