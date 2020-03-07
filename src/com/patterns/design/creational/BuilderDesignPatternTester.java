package com.patterns.design.creational;

//this pattern is used to create objects made from bunch of other objects
//this building of object is independent of main object
//hide creation from client so that client and builder are not dependent
//the builder knows the specifics and no one else
//disadvantage - requires separate builder class for each object which needs to be custom built

public class BuilderDesignPatternTester {

	public static void main(String[] args) {
		User user1 = new UserBuilder("Lokesh", "Gupta")
			    .age(30)
			    .phone("1234567")
			    .address("Fake address 1234")
			    .build();
			 
			    System.out.println(user1);
			 
			    User user2 = new UserBuilder("Jack", "Reacher")
			    .age(40)
			    .phone("5655")
			    //no address
			    .build();
			 
			    System.out.println(user2);
			 
			    User user3 = new UserBuilder("Super", "Man")
			    //No age
			    //No phone
			    //no address
			    .build();
			 
			    System.out.println(user3);
	}

}

class User
{
    //All final attributes
    private String firstName; // required
    private String lastName; // required
    private int age; // optional
    private String phone; // optional
    private String address; // optional
 
    public User(String firstName, String lastName, int age, String phone, String address) {
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.age = age;
    	this.phone = phone;
    	this.address = address;
        
    }
 
    //All getter, and NO setter to provde immutability
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getAge() {
        return age;
    }
    public String getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }
 
    @Override
    public String toString() {
        return "User: "+this.firstName+", "+this.lastName+", "+this.age+", "+this.phone+", "+this.address;
    }
} 
     
class UserBuilder
    {
		//same fields
        private final String firstName;
        private final String lastName;
        private int age;
        private String phone;
        private String address;
 
        //constructor with only required fields first
        public UserBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
        
        //call builder method and set only required attributes and return the builder object
        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }
        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }
        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }
        //Call this after building fields which returns finally constructed User object
        public User build() {
            User user =  new User(firstName, lastName, age, phone, address);
            validateUserObject(user);
            return user;
        }
        private void validateUserObject(User user) {
            //Do some basic validations to check
            //if user object does not break any assumption of system
        }
    }
