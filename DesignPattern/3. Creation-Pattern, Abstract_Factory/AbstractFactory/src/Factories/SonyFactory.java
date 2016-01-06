package Factories;

import Products.HighEndProduct;
import Products.LowEndProduct;
import Products.PS3;
import Products.PS4;

public class SonyFactory implements AbstractFactory {

	@Override
	public LowEndProduct createLowEndProduct() {
		// hard coded to only create a PS3, see explanation in Microsoft Factory
		return new PS3();
	}

	@Override
	public HighEndProduct createHighEndProduct() {
		return new PS4();
	}

}