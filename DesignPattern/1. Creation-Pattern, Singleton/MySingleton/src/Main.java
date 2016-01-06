public class Main {

	public static void main(String[] args) {
		// create two references to the single copy of this object
		MySingleton x = MySingleton.getInstance();
		MySingleton y = MySingleton.getInstance();
		
		// update the stored data using x reference
		x.setData("whatsup");
		
		// retrieve the data using y reference
		System.out.println(y.getData());
		
		y.setData("canada day");
		// console output: whatsup
		
		System.out.println(MySingleton.getInstance().getData());
	}
}