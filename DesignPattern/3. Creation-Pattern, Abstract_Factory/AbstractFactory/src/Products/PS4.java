package Products;

public class PS4 implements HighEndProduct {

	// hard coded default values for simple demo purposes
	@Override
	public String getName() {
		return "PS4";
	}

	@Override
	public int getPrestige() {
		return 1000;
	}

}