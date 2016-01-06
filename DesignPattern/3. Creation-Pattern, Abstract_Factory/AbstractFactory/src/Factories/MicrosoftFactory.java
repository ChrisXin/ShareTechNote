package Factories;

import Products.HighEndProduct;
import Products.LowEndProduct;
import Products.Xbox360;
import Products.XboxOne;

public class MicrosoftFactory implements AbstractFactory {

	@Override
	public LowEndProduct createLowEndProduct() {
		// note that this method could have some switch case logic (like in Single Factory Pattern), if Microsoft has multiple low end products which it can create
		// for now, hard coded to return a 360.
		return new Xbox360();
	}

	@Override
	public HighEndProduct createHighEndProduct() {
		// similarly, it can also have switch case logic to create various high end microsoft products
		return new XboxOne();
	}

}