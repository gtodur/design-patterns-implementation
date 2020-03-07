package com.patterns.design.creational;

//use this pattern to return one of many classes that share a common parent class
//encapsulate object creation
//class is chosen at run time
//promotes loose coupling between classes - just specify type and factory will create it, obj creation in centralized place
//most suitable where there is some complex object creation steps are involved

public class FactoryDesignPatternTester {

	public static void main(String[] args) {
		System.out.println(F_CarFactory.buildCar(CarType.SMALL));
        System.out.println(F_CarFactory.buildCar(CarType.SEDAN));
        System.out.println(F_CarFactory.buildCar(CarType.LUXURY));
	}

}

enum CarType {
    SMALL, SEDAN, LUXURY
}

abstract class F_Car {
	 
    public F_Car(CarType model) {
        this.model = model;
        arrangeParts();
    }
 
    private void arrangeParts() {
        // Do one time processing here
    }
 
    // Do subclass level processing in this method
    protected abstract void construct();
 
    private CarType model = null;
 
    public CarType getModel() {
        return model;
    }
 
    public void setModel(CarType model) {
        this.model = model;
    }
}

class F_LuxuryCar extends F_Car {
	 
    F_LuxuryCar() {
        super(CarType.LUXURY);
        construct();
    }
 
    @Override
    protected void construct() {
        System.out.println("Building luxury car");
        // add accessories
    }
}

class F_SmallCar extends F_Car {
	 
    F_SmallCar() {
        super(CarType.SMALL);
        construct();
    }
 
    @Override
    protected void construct() {
        System.out.println("Building small car");
        // add accessories
    }
}

class F_SedanCar extends F_Car {
	 
    F_SedanCar() {
        super(CarType.SEDAN);
        construct();
    }
 
    @Override
    protected void construct() {
        System.out.println("Building sedan car");
        // add accessories
    }
}

class F_CarFactory {
    public static F_Car buildCar(CarType model) {
        F_Car car = null;
        switch (model) {
        case SMALL:
            car = new F_SmallCar();
            break;
 
        case SEDAN:
            car = new F_SedanCar();
            break;
 
        case LUXURY:
            car = new F_LuxuryCar();
            break;
 
        default:
            // throw some exception
            break;
        }
        return car;
    }
}


