public class Main {

	public static void main(String[] args) {
		// make a single copy of the prototype as x
		PrototypeOne x = new PrototypeOne();
		
		// update the values stored in that prototype
		x.setName("first name");
		x.setType("proto original");
		
		// clone it as y
		PrototypeOne y = x.clone();
		
		// display the values
		System.out.println("original: ");
		System.out.println("name: " + x.getName() + " type: " + x.getType());
		
		System.out.println("cloned results: ");
		System.out.println("name: " + y.getName() + " type: " + y.getType());
		
		// update y's values to something else
		y.setName("sceond name");
		y.setType("proto copy");
		
		// display all values
		System.out.println("original: ");
		System.out.println("name: " + x.getName() + " type: " + x.getType());
		
		System.out.println("updated clone: ");
		System.out.println("name: " + y.getName() + " type: " + y.getType());
		
		// You can also declare other prototypes with different values, 
		// each prototype would extend AbstractPrototype
	}
}