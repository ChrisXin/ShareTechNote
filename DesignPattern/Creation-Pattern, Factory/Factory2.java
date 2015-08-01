/* Factory Pattern: 
Use when: 
1. creating objects that require a lot of setup configuration,
2. to have flexibility in creating many different kinds of similar classes without having lots of duplicate code. 
*/

public Rectangle createRectangle() {
	return new RectangleImpl();
}
public FileExample createFileExample() {
	FileExample file = new FileExample();
	file.setOwner(Environment.getInstance().getOwner());
	file.setCreationDate(CalendarDate.today());
}