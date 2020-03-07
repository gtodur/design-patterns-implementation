package com.patterns.design.behavioral;

import java.util.ArrayList;
import java.util.List;

//mediator pattern defines an object that encapsulates how a set of objects interact. 
//Mediator promotes loose coupling by keeping objects from referring to each other explicitly, and it lets us vary their interaction independently
//Mediator helps to facilitate the interaction between objects in a manner in that objects are not aware of the existence of other objects. 
//Objects depend only on a single mediator class instead of being coupled to dozens of other objects
//if you encounter a situation where multiple objects need to interact with each other to process the request, but direct communication may create a complex system, you can consider using mediator pattern
//The pattern lets you extract all the relationships between classes into a separate class, isolating any changes to a specific component from the rest of the components
//

public class MediatorDesignPatternTester {

	public static void main(String[] args) {
		ChatMediator mediator = new ChatMediatorImpl();
		User user1 = new UserImpl(mediator, "Pankaj");
		User user2 = new UserImpl(mediator, "Lisa");
		User user3 = new UserImpl(mediator, "Saurabh");
		User user4 = new UserImpl(mediator, "David");
		mediator.addUser(user1);
		mediator.addUser(user2);
		mediator.addUser(user3);
		mediator.addUser(user4);
		
		user1.send("Hi All");
		user3.send("Bye All");
	}

}

interface ChatMediator {

	public void sendMessage(String msg, User user);

	void addUser(User user);
}

abstract class User {
	protected ChatMediator mediator;
	protected String name;
	
	public User(ChatMediator med, String name){
		this.mediator=med;
		this.name=name;
	}
	
	public abstract void send(String msg);
	
	public abstract void receive(String msg);
}

class ChatMediatorImpl implements ChatMediator {

	private List<User> users;
	
	public ChatMediatorImpl(){
		this.users=new ArrayList<>();
	}
	
	@Override
	public void addUser(User user){
		this.users.add(user);
	}
	
	@Override
	public void sendMessage(String msg, User user) {
		for(User u : this.users){
			//message should not be received by the user sending it
			if(u != user){
				u.receive(msg);
			}
		}
	}

}

class UserImpl extends User {

	public UserImpl(ChatMediator med, String name) {
		super(med, name);
	}

	@Override
	public void send(String msg){
		System.out.println(this.name+": Sending Message="+msg);
		mediator.sendMessage(msg, this);
	}
	@Override
	public void receive(String msg) {
		System.out.println(this.name+": Received Message:"+msg);
	}

}