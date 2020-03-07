package com.patterns.design.creational;

public class AbstractFactoryDesignPatternTester {

	public static void main(String[] args) {
		System.out.println(CarFactory.buildCar(CarType.SMALL));
	    System.out.println(CarFactory.buildCar(CarType.SEDAN));
	    System.out.println(CarFactory.buildCar(CarType.LUXURY));
	}

}

abstract class Car {
	 
	  public Car(CarType model, Location location){
	    this.model = model;
	    this.location = location;
	  }
	 
	  protected abstract void construct();
	 
	  private CarType model = null;
	  private Location location = null;
	 
	  //getters and setters
	 
	  @Override
	  public String toString() {
	    return "Model- "+model + " built in "+location;
	  }
	}

enum Location {
	  DEFAULT, USA, ASIA
	}

class LuxuryCar extends Car
{
  public LuxuryCar(Location location)
  {
    super(CarType.LUXURY, location);
    construct();
  }
 
  @Override
  protected void construct() {
    System.out.println("Building luxury car");
    //add accessories
  }
}

class SmallCar extends Car
{
  public SmallCar(Location location)
  {
    super(CarType.SMALL, location);
    construct();
  }
 
  @Override
  protected void construct() {
    System.out.println("Building small car");
    //add accessories
  }
}

class SedanCar extends Car
{
  public SedanCar(Location location)
  {
    super(CarType.SEDAN, location);
    construct();
  }
 
  @Override
  protected void construct() {
    System.out.println("Building sedan car");
    //add accessories
  }
}

class AsiaCarFactory
{
  public static Car buildCar(CarType model)
  {
    Car car = null;
    switch (model)
    {
      case SMALL:
      car = new SmallCar(Location.ASIA);
      break;
 
      case SEDAN:
      car = new SedanCar(Location.ASIA);
      break;
 
      case LUXURY:
      car = new LuxuryCar(Location.ASIA);
      break;
 
      default:
      //throw some exception
      break;
    }
    return car;
  }
}

class DefaultCarFactory
{
  public static Car buildCar(CarType model)
  {
    Car car = null;
    switch (model)
    {
      case SMALL:
      car = new SmallCar(Location.DEFAULT);
      break;
 
      case SEDAN:
      car = new SedanCar(Location.DEFAULT);
      break;
 
      case LUXURY:
      car = new LuxuryCar(Location.DEFAULT);
      break;
 
      default:
      //throw some exception
      break;
    }
    return car;
  }
}

class USACarFactory
{
  public static Car buildCar(CarType model)
  {
    Car car = null;
    switch (model)
    {
      case SMALL:
      car = new SmallCar(Location.USA);
      break;
 
      case SEDAN:
      car = new SedanCar(Location.USA);
      break;
 
      case LUXURY:
      car = new LuxuryCar(Location.USA);
      break;
 
      default:
      //throw some exception
      break;
    }
  return car;
  }
}

class CarFactory
{
  private CarFactory() {
    //Prevent instantiation
  }
 
  public static Car buildCar(CarType type)
  {
    Car car = null;
    Location location = Location.ASIA; //Read location property somewhere from configuration
    //Use location specific car factory
    switch(location)
    {
      case USA:
        car = USACarFactory.buildCar(type);
        break;
      case ASIA:
        car = AsiaCarFactory.buildCar(type);
        break;
      default:
        car = DefaultCarFactory.buildCar(type);
    }
  return car;
  }
}