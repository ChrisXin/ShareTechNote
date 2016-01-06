package Factories;

import Products.HighEndProduct;
import Products.LowEndProduct;

// A factory is able to create either a high end or a low end product
public interface AbstractFactory {
	public LowEndProduct createLowEndProduct();
	public HighEndProduct createHighEndProduct();
}