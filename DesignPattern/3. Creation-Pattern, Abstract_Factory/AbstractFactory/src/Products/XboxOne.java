package Products;

public class XboxOne implements HighEndProduct {

	// hard coded default values for simple demo purposes
	@Override
	public String getName() {
		return "XBOX ONE";
	}

	@Override
	public int getPrestige() {
		return 2000;
	}

}