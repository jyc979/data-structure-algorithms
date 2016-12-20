package DS;
class Person{
   private String lastName;
   private String firstName;
   private int age;
//--------------------------------------------------------------
   public Person(String last, String first, int a)
      {                               // constructor
      lastName = last;
      firstName = first;
      age = a;
      }
//--------------------------------------------------------------
   public void displayPerson()
      {
      System.out.print("   Last name: " + lastName);
      System.out.print(", First name: " + firstName);
      System.out.println(", Age: " + age);
      }
//--------------------------------------------------------------
   public String getLast()           // get last name
      { return lastName; }
   
}  // end class Person
class DataArray{
	private Person1[] a;
	private int nElems;
	DataArray(int size){
		a = new Person1[size];
		nElems =0;
	}
	public Person1 find(String searchName){
		int j;
		for(j=0;j<nElems;j++)
			if(a[j].getLast().equals(searchName))
				break;
		if(j==nElems)
			return null;
		else 
			return a[j];
	}
	public void insert(String last, String first, int age){
		a[nElems] = new Person1(last,first,age);
		nElems++;
	}
	public boolean delete(String searchName){
		int j;
		for(j=0;j<nElems;j++){
			if(a[j].getLast().equals(searchName))
				break;
		}
		if(j==nElems)
			return false;
		else{
			for(int k=j;k<nElems;k++)
				a[k] = a[k+1];
			nElems--;
			return true;
		}
	}
	public void displayA(){
		for(int j=0;j<nElems;j++){
			a[j].displayPerson();
		}
	}
	
}


public class DataApp {

	public static void main(String[] args) {
		int maxSize =100;
		DataArray arr;
		arr= new DataArray(100);
		arr.insert("Evans", "Patty", 24);
	      arr.insert("Smith", "Lorraine", 37);
	      arr.insert("Yee", "Tom", 43);
	      arr.insert("Adams", "Henry", 63);
	      arr.insert("Hashimoto", "Sato", 21);
	      arr.insert("Stimson", "Henry", 29);
	      arr.insert("Velasquez", "Jose", 72);
	      arr.insert("Lamarque", "Henry", 54);
	      arr.insert("Vang", "Minh", 22);
	      arr.insert("Creswell", "Lucinda", 18);
	      
	      arr.displayA();
	      String searchKey ="Stimson";
	      Person1 found = arr.find(searchKey);
	      if( found!=null)
	      {
	    	  System.out.print("Found ");
	    	  found.displayPerson();
	      }
	      else
	    	  System.out.println("Can't find "+searchKey);
	      
	      System.out.println("Deleting Smith, Yee, and Creswell");
	      arr.delete("Smith");
	      arr.delete("Yee");
	      arr.delete("Creswell");
	      
	      arr.displayA();

	}

}
