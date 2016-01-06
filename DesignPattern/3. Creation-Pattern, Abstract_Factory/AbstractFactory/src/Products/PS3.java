package Products;

public class PS3 implements LowEndProduct {

	// hard coded default values for simple demo purposes
	@Override
	public String getName() {
		return "PS3";
	}

	@Override
	public int getSwagger() {
		return 720;
	}

}