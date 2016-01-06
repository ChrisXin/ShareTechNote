public abstract class AbstractPrototype implements Cloneable {
	// Fields
	private String name;

	// Abstract Methods for Child Classes to define
	abstract String getType();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AbstractPrototype clone() {
		AbstractPrototype clone = null;
		// normally to clone an object, we would create a new object then copy the current object's 
		// field values to the new one,
		// but we can't create a new object of AbstractShape using the new keyword since it is an 
		// abstract class
		
		try {
			// java provides a clone method which can work
			clone = (AbstractPrototype) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return clone;
	}
}