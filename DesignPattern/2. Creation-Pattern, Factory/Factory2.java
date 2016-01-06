/* Factory Pattern: 
Use when: 
1. creating objects that require a lot of setup configuration,
2. to have flexibility in creating many different kinds of similar classes without having lots of duplicate code. 
*/


/*3 parts:
◦A FactoryClass is responsible for determining which particular object to create, and to create it
◦A common Interface defines the kinds of objects that this Factory can create
◦A set of Classes which implement the Interface, in turn the Factory will create objects of these classes
*/


public Rectangle createRectangle() {
	return new RectangleImpl();
}
public FileExample createFileExample() {
	FileExample file = new FileExample();
	file.setOwner(Environment.getInstance().getOwner());
	file.setCreationDate(CalendarDate.today());
}