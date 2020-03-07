package com.patterns.design.behavioral;

import java.util.ArrayList;
import java.util.List;

//design pattern where we choose a specific implementation of algorithm or task in run time – out of multiple other implementations for same task
//The important point is that these implementations are interchangeable – based on task a implementation may be picked without disturbing the application workflow
//Strategy pattern involves removing an algorithm from its host class and putting it in separate class so that in the same programming context there might be different algorithms (i.e. strategies), which can be selected in runtime.
//Strategy pattern enables a client code to choose from a family of related but different algorithms and gives it a simple way to choose any of the algorithm in runtime depending on the client context.
//we first create an abstraction of algorithm. This is an interface having the abstract operation. Then we create implementations of this abstraction and these are called strategies
//A client will always call the abstraction, and will pass a context object. This context object will decide which strategy to use.
//Context contains state as instance variable and there can be multiple tasks whose implementation can be dependent on the state whereas in strategy pattern strategy is passed as argument to the method and context object doesn’t have any variable to store it.
//Strategy pattern is useful when we have multiple algorithms for specific task and we want our application to be flexible to chose any of the algorithm at runtime for specific task.

public class StrategyDesignPatternTester {

	public static void main(String[] args) {
		ShoppingCart cart = new ShoppingCart();
		
		Item item1 = new Item("1234",10);
		Item item2 = new Item("5678",40);
		
		cart.addItem(item1);
		cart.addItem(item2);
		
		//pay by paypal
		cart.pay(new PaypalStrategy("guru@todur.com", "12345"));
		
		//pay by credit card
		cart.pay(new CreditCardStrategy("Guruprasad Todur", "1234567890123456", "123", "12/15"));
	}

}

interface PaymentStrategy {

	public void pay(int amount);
}

class CreditCardStrategy implements PaymentStrategy {

	private String name;
	private String cardNumber;
	private String cvv;
	private String dateOfExpiry;
	
	public CreditCardStrategy(String nm, String ccNum, String cvv, String expiryDate){
		this.name=nm;
		this.cardNumber=ccNum;
		this.cvv=cvv;
		this.dateOfExpiry=expiryDate;
	}
	@Override
	public void pay(int amount) {
		System.out.println(amount +" paid with credit/debit card");
	}

}

class PaypalStrategy implements PaymentStrategy {

	private String emailId;
	private String password;
	
	public PaypalStrategy(String email, String pwd){
		this.emailId=email;
		this.password=pwd;
	}
	
	@Override
	public void pay(int amount) {
		System.out.println(amount + " paid using Paypal.");
	}

}

class Item {

	private String upcCode;
	private int price;
	
	public Item(String upc, int cost){
		this.upcCode=upc;
		this.price=cost;
	}

	public String getUpcCode() {
		return upcCode;
	}

	public int getPrice() {
		return price;
	}
	
}

class ShoppingCart {

	//List of items
	List<Item> items;
	
	public ShoppingCart(){
		this.items=new ArrayList<Item>();
	}
	
	public void addItem(Item item){
		this.items.add(item);
	}
	
	public void removeItem(Item item){
		this.items.remove(item);
	}
	
	public int calculateTotal(){
		int sum = 0;
		for(Item item : items){
			sum += item.getPrice();
		}
		return sum;
	}
	
	public void pay(PaymentStrategy paymentMethod){
		int amount = calculateTotal();
		paymentMethod.pay(amount);
	}
}
