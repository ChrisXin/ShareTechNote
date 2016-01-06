public class PrototypeOne extends AbstractPrototype {

	private String type;
	
	public PrototypeOne()
	{
		type = "proto default";
	}
	
	public void setType(String s)
	{
		type = s;
	}
	
	// cloning of just this object
	public PrototypeOne clone() {
		// Generally to clone, we make a new copy of the object and copy every field value
		// Java provides a clone method implementation as part of Object.clone() that does 
		// this automatically, even for private fields of the child class
		PrototypeOne clone = (PrototypeOne) super.clone();
		
		// additional modifications or copying if needed of child fields
		// System.out.println("original: " this.type + " clone: " + clone.type);
		return clone;
	}

	@Override
	String getType() {
		return type;
	}

}