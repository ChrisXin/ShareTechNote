package Program;

import java.util.ArrayList;

import Factories.AbstractFactory;
import Factories.MicrosoftFactory;
import Factories.SonyFactory;
import Products.HighEndProduct;
import Products.LowEndProduct;

public class Main {

	public static void main(String[] args) {
		// stores our factories
		ArrayList<AbstractFactory> factories = new ArrayList<AbstractFactory>();
		
		// add both factories to the list of factories
		factories.add(new SonyFactory());
		factories.add(new MicrosoftFactory());
		factories.add(new SonyFactory());
		
		ArrayList<HighEndProduct> highEnd = new ArrayList<HighEndProduct>();
		ArrayList<LowEndProduct> lowEnd = new ArrayList<LowEndProduct>();
		
		// in each factory, create both a high end and a low end product
		for(AbstractFactory factory : factories)
		{
			highEnd.add(factory.createHighEndProduct());
			lowEnd.add(factory.createLowEndProduct());
		}
		
		// display the high end product names
		for(HighEndProduct product : highEnd)
		{
			System.out.println("High end product " + product.getName() + " has " + product.getPrestige() + " prestige points.");
		}
		
		// display the low end product names
		for(LowEndProduct product : lowEnd)
		{
			System.out.println("Low end product " + product.getName() + " has " + product.getSwagger() + " swagger points.");
		}
		
		
	}

}