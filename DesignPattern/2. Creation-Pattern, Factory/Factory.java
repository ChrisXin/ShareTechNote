/* Factory Pattern: 
Use when: 
1. creating objects that require a lot of setup configuration,
2. to have flexibility in creating many different kinds of similar classes without having lots of duplicate code. 
*/




/*3 parts:
◦A FactoryClass is responsible for determining which particular object to create, and to create it
◦A common Interfacedefines the kinds of objects that this Factory can create
◦A set of Classeswhich implementthe Interface, in turn the Factory will create objects of these classes
*/
// Step 1 Create an interface.
public interface Shape {
   void draw();
}

// Step 2 Create concrete classes implementing the same interface.
public class Rectangle implements Shape {
   @Override
   public void draw() {
      System.out.println("Inside Rectangle::draw() method.");
   }
}

public class Square implements Shape {
   @Override
   public void draw() {
      System.out.println("Inside Square::draw() method.");
   }
}

public class Circle implements Shape {
   @Override
   public void draw() {
      System.out.println("Inside Circle::draw() method.");
   }
}

// Step 3  Create a Factory to generate object of concrete class based on given information
public class ShapeFactory {
   //use getShape method to get object of type shape 
   public Shape getShape(String shapeType){
      if(shapeType == null){
         return null;
      }		
      if(shapeType.equalsIgnoreCase("CIRCLE")){
         return new Circle();
         
      } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
         return new Rectangle();
         
      } else if(shapeType.equalsIgnoreCase("SQUARE")){
         return new Square();
      }
      return null;
   }
}

// Step 4 Use the Factory to get object of concrete class by passing an information such as type
public class FactoryPatternDemo {
   public static void main(String[] args) {
      ShapeFactory shapeFactory = new ShapeFactory();

      //get an object of Circle and call its draw method.
      Shape shape1 = shapeFactory.getShape("CIRCLE");

      //call draw method of Circle
      shape1.draw();

      //get an object of Rectangle and call its draw method.
      Shape shape2 = shapeFactory.getShape("RECTANGLE");

      //call draw method of Rectangle
      shape2.draw();

      //get an object of Square and call its draw method.
      Shape shape3 = shapeFactory.getShape("SQUARE");

      //call draw method of circle
      shape3.draw();
   }
}



