package com.patterns.design.behavioral;

//the visitor design pattern is a way of separating an algorithm from an object structure on which it operates
//A practical result of this separation is the ability to add new operations to existing object structures without modifying those structures
//this design flexibility allows to add methods to any object hierarchy without modifying the code written for hierarchy
//double dispatch mechanism is used to implement this facility
//Double dispatch is a special mechanism that dispatches a function call to different concrete functions depending on the runtime types of two objects involved in the call
//In Visitor pattern, we use a visitor class which changes the executing algorithm of an element class.
//By this way, execution algorithm of element can vary as and when visitor varies
//As per the pattern, element object has to accept the visitor object so that visitor object handles the operation on the element object
//Visitor pattern is used when we have to perform an operation on a group of similar kind of Objects. 
//With the help of visitor pattern, we can move the operational logic from the objects to another class.
//The benefit of this pattern is that if the logic of operation changes, then we need to make change only in the visitor implementation rather than doing it in all the item classes.
//Another benefit is that adding a new item to the system is easy, it will require change only in visitor interface and implementation and existing item classes will not be affected

public class VisitorDesignPatternTester {

	public static void main(String[] args) {
		ItemElement[] items = new ItemElement[]{new Book(20, "1234"),new Book(100, "5678"),
				new Fruit(10, 2, "Banana"), new Fruit(5, 5, "Apple")};
		
		int total = calculatePrice(items);
		System.out.println("Total Cost = "+total);
	}
	
	private static int calculatePrice(ItemElement[] items) {
		ShoppingCartVisitor visitor = new ShoppingCartVisitorImpl();
		int sum=0;
		for(ItemElement item : items){
			sum = sum + item.accept(visitor);
		}
		return sum;
	}

}

interface ItemElement {

	public int accept(ShoppingCartVisitor visitor);
}

class Book implements ItemElement {

	private int price;
	private String isbnNumber;
	
	public Book(int cost, String isbn){
		this.price=cost;
		this.isbnNumber=isbn;
	}
	
	public int getPrice() {
		return price;
	}

	public String getIsbnNumber() {
		return isbnNumber;
	}

	@Override
	public int accept(ShoppingCartVisitor visitor) {
		return visitor.visit(this);
	}

}

class Fruit implements ItemElement {
	
	private int pricePerKg;
	private int weight;
	private String name;
	
	public Fruit(int priceKg, int wt, String nm){
		this.pricePerKg=priceKg;
		this.weight=wt;
		this.name = nm;
	}
	
	public int getPricePerKg() {
		return pricePerKg;
	}


	public int getWeight() {
		return weight;
	}

	public String getName(){
		return this.name;
	}
	
	@Override
	public int accept(ShoppingCartVisitor visitor) {
		return visitor.visit(this);
	}

}

interface ShoppingCartVisitor {

	int visit(Book book);
	int visit(Fruit fruit);
}

class ShoppingCartVisitorImpl implements ShoppingCartVisitor {

	@Override
	public int visit(Book book) {
		int cost=0;
		//apply 5$ discount if book price is greater than 50
		if(book.getPrice() > 50){
			cost = book.getPrice()-5;
		}else cost = book.getPrice();
		System.out.println("Book ISBN::"+book.getIsbnNumber() + " cost ="+cost);
		return cost;
	}

	@Override
	public int visit(Fruit fruit) {
		int cost = fruit.getPricePerKg()*fruit.getWeight();
		System.out.println(fruit.getName() + " cost = "+cost);
		return cost;
	}

}
