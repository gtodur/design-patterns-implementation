package com.patterns.design.behavioral;

//a state allows an object to alter its behavior when its internal state changes. The object will appear to change its class
//there shall be a separate concrete class per possible state of an object
//Each concrete state object will have logic to accept or reject a state transition request based on it’s present state and context information passed to it as method arguments
//State pattern allows the objects to behave differently based on the current state, and we can define state-specific behaviors within different classes.
//The benefits of using State pattern to implement polymorphic behavior is clearly visible. 
//The chances of error are less and it’s very easy to add more states for additional behavior.
//State Pattern is very similar to Strategy Pattern

public class StateDesignPatternTester {

	public static void main(String[] args) {
		DeliveryContext ctx = new DeliveryContext(null, "Test123");
		DeliveryContext ctx1 = new DeliveryContext(null, "Test1234");
        
        ctx.update();
        ctx.update();
        
        ctx1.update();
        ctx1.update();
        
        ctx.update();
        ctx.update();
        ctx.update();
	}

}

interface PackageState
{
    public void updateState(DeliveryContext ctx);
}

class Acknowledged implements PackageState
{
    //Singleton
    private static Acknowledged instance = new Acknowledged();
 
    private Acknowledged() {}
 
    public static Acknowledged instance() {
        return instance;
    }
     
    //Business logic and state transition
    @Override
    public void updateState(DeliveryContext ctx)
    {
        System.out.println("Package is acknowledged !!");
        ctx.setCurrentState(Shipped.instance());
    }
}

class Shipped implements PackageState
{
    //Singleton
    private static Shipped instance = new Shipped();
 
    private Shipped() {}
 
    public static Shipped instance() {
        return instance;
    }
     
    //Business logic and state transition
    @Override
    public void updateState(DeliveryContext ctx)
    {
        System.out.println("Package is shipped !!");
        ctx.setCurrentState(InTransition.instance());
    }
}

class InTransition implements PackageState
{
    //Singleton
    private static InTransition instance = new InTransition();
 
    private InTransition() {}
 
    public static InTransition instance() {
        return instance;
    }
     
    //Business logic and state transition
    @Override
    public void updateState(DeliveryContext ctx)
    {
        System.out.println("Package is in transition !!");
        ctx.setCurrentState(OutForDelivery.instance());
    }
}

class OutForDelivery implements PackageState
{
    //Singleton
    private static OutForDelivery instance = new OutForDelivery();
 
    private OutForDelivery() {}
 
    public static OutForDelivery instance() {
        return instance;
    }
     
    //Business logic and state transition
    @Override
    public void updateState(DeliveryContext ctx)
    {
        System.out.println("Package is out of delivery !!");
        ctx.setCurrentState(Delivered.instance());
    }
}

class Delivered implements PackageState
{
    //Singleton
    private static Delivered instance = new Delivered();
 
    private Delivered() {}
 
    public static Delivered instance() {
        return instance;
    }
     
    //Business logic
    @Override
    public void updateState(DeliveryContext ctx)
    {
        System.out.println("Package is delivered!!");
    }
}

class DeliveryContext {
     
    private PackageState currentState;
    private String packageId;
     
    public DeliveryContext(PackageState currentState, String packageId)
    {
        super();
        this.currentState = currentState;
        this.packageId = packageId;
         
        if(currentState == null) {
            this.currentState = Acknowledged.instance();
        }
    }
 
    public PackageState getCurrentState() {
        return currentState;
    }
 
    public void setCurrentState(PackageState currentState) {
        this.currentState = currentState;
    }
     
    public String getPackageId() {
        return packageId;
    }
 
    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }
 
    public void update() {
        currentState.updateState(this);
    }
}
