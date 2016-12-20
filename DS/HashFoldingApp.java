package DS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class HashFoldTable
   {
   private HDataItem[] hashArray;    // array holds hash table
   private int arraySize;
   private HDataItem nonItem;        // for deleted items
   private int numItems;
// -------------------------------------------------------------
   public double getloadF(){
	   return ((double)(numItems)/(double)(arraySize));
   }
// -------------------------------------------------------------
   private int getPrime(int min)
   {
	   for(int j=min+1;true;j++)
		   if(isPrime(j))
			   return j;
   }
// -------------------------------------------------------------
   private boolean isPrime(int n)
   {
	   for(int j=2;(j*j<=n);j++)
		   if(n%j==0)
			   return false;
	   return true;
   }
// -------------------------------------------------------------
   public HashFoldTable(int size)       // constructor
      {
      arraySize = size;
      hashArray = new HDataItem[arraySize];
      nonItem = new HDataItem(-1);   // deleted item key is -1
      }
// -------------------------------------------------------------
   public void displayTable()
      {
      System.out.print("Table: ");
      for(int j=0; j<arraySize; j++)
         {
         if(hashArray[j] != null)
            System.out.print(hashArray[j].getKey() + " ");
         else
            System.out.print("** ");
         }
      System.out.println("");
      }

   
// -------------------------------------------------------------
   public int folding(int key){
		String sKey = Integer.toString(key); //key to string
		
		int digit =1;
		int length = arraySize;
		int hashVal =0;
		
		while(length>0){ //get the digit
			digit*=10;
			length /=10;
		}
		
		String sDigit = Integer.toString(digit);
		int countZ =0; //2 for 15
		for(int i=0;i<sDigit.length();i++){ //count how many digits
			if(sDigit.charAt(i)=='0')
				countZ++;
		}
		
		int index =0;
		while(index+countZ<sKey.length()){ 
			String sub = sKey.substring(index,index+countZ);
			hashVal += Integer.parseInt(sub);
			index +=countZ;
		}
		
		String sub = sKey.substring(index,sKey.length());
		int remainder = Integer.parseInt(sub);
		hashVal += remainder;
		
		hashVal = hashVal%arraySize;
		
		
		
		return hashVal;
		
	}
// -------------------------------------------------------------
   public int hashFunc(int key)
      {
      return key % arraySize;       // hash function
      }

   
// -------------------------------------------------------------
   public void rehash(){
	 
	   int newArraySize = getPrime(arraySize*2);
	   HDataItem[] newArray = new HDataItem[newArraySize];
	   for(int i=0;i<arraySize;i++){
		   if(hashArray[i]!=null&&hashArray[i].getKey()!=-1){
			   int hashVal = hashFunc(hashArray[i].getKey());
			   newArray[hashVal] = hashArray[i];
		   }
		   
	   }
	   System.out.println(newArraySize);
	   arraySize = newArraySize;
	   hashArray = newArray;
	   
   }
// -------------------------------------------------------------
   public void insert(HDataItem item) // insert a DataItem
   // (assumes table not full)
      {
      int key = item.getKey();      // extract key
      int hashVal = hashFunc(key);  // hash the key
                                    // until empty cell or -1,
      while(hashArray[hashVal] != null &&
                      hashArray[hashVal].getKey() != -1)
         {
         ++hashVal;                 // go to next cell
         hashVal %= arraySize;      // wraparound if necessary
         }
      hashArray[hashVal] = item;
      numItems++;
      double loadFactor = getloadF();
      if(loadFactor>0.5){
    	  rehash();
      }
   
      }  // end insert()
// -------------------------------------------------------------
   public HDataItem delete(int key)  // delete a DataItem
      {
      int hashVal = hashFunc(key);  // hash the key

      while(hashArray[hashVal] != null)  // until empty cell,
         {                               // found the key?
         if(hashArray[hashVal].getKey() == key)
            {
        	numItems--;
            HDataItem temp = hashArray[hashVal]; // save item
            hashArray[hashVal] = nonItem;       // delete item
            return temp;                        // return item
            }
         ++hashVal;                 // go to next cell
         hashVal %= arraySize;      // wraparound if necessary
         }
      return null;                  // can't find item
      }  // end delete()
// -------------------------------------------------------------
   public HDataItem find(int key)    // find item with key
      {
      int hashVal = hashFunc(key);  // hash the key

      while(hashArray[hashVal] != null)  // until empty cell,
         {                               // found the key?
         if(hashArray[hashVal].getKey() == key)
            return hashArray[hashVal];   // yes, return item
         ++hashVal;                 // go to next cell
         hashVal %= arraySize;      // wraparound if necessary
         }
      return null;                  // can't find item
      }
// -------------------------------------------------------------
   }  // end class HashFoldTable
////////////////////////////////////////////////////////////////
public class HashFoldingApp
   {
   public static void main(String[] args) throws IOException
      {
      HDataItem aDataItem;
      int aKey, size, n, keysPerCell;
                                    // get sizes
      size =17;
      n =30;
      keysPerCell = 10;
                                    // make table
      HashFoldTable theTable = new HashFoldTable(size);

      for(int j=0; j<n; j++)        // insert data
         {
         aKey = (int)(java.lang.Math.random() *
                                         keysPerCell * size);
         aDataItem = new HDataItem(aKey);
         theTable.insert(aDataItem);
         }
      
    
     theTable.displayTable();

      
      }  // end main()
//--------------------------------------------------------------
   public static String getString() throws IOException
      {
      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(isr);
      String s = br.readLine();
      return s;
      }
//--------------------------------------------------------------
   public static char getChar() throws IOException
      {
      String s = getString();
      return s.charAt(0);
      }
//-------------------------------------------------------------
   public static int getInt() throws IOException
      {
      String s = getString();
      return Integer.parseInt(s);
      }
//--------------------------------------------------------------
   }  // end class HashFoldTableApp
////////////////////////////////////////////////////////////////
