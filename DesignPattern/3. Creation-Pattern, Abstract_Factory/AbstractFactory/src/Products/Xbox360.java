package Products;

public class Xbox360 implements LowEndProduct {

	// hard coded default values for simple demo purposes
	@Override
	public String getName() {
		return "XBOX 360";
	}

	@Override
	public int getSwagger() {
		return 360;
	}

}