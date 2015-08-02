public class MySingleton {
	// single static field storing the reference to a single copy of the object
	private static MySingleton instance;
	private String data;
	
	// private constructor, which can only be called by other methods of this class
	private MySingleton(String d)
	{
		data = d;
	}
	
	// public method for the rest of the program to get a reference to the single copy of the object
	public static MySingleton getInstance()
	{
		if(instance == null){
			instance = new MySingleton("default");
		}
		return instance;
	}
	
	// public method to "delete" the single copy of the object
	// 		in java the garbage collector destroys the object
	// 		in other OO languages, you may have to implement object deletion
	public static void removeInstance()
	{
		if(instance != null)
			instance = null;	// setting it to null queues up the garbage collector
	}
	
	// disable object cloning
	@Override
	public Object clone() throws CloneNotSupportedException {
	    throw new CloneNotSupportedException(); 
	}
	
	// setter for data
	public void setData(String s)
	{
		data = s;
	}
	
	// getter for data
	public String getData()
	{
		return data;
	}
}