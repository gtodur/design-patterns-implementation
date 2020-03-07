package com.patterns.design.behavioral;

//In Template pattern, an abstract class exposes defined way(s)/template(s) to execute its methods. 
//Its subclasses can override the method implementation as per need but the invocation is to be in the same way as defined by an abstract class
//widely accepted behavioral design pattern to enforce some sort of algorithm (fixed set of steps) in the context of programming
//It defines the sequential steps to execute a multi-step algorithm and optionally can provide a default implementation as well (based on requirements).
//template method pattern is the solution which can be used to enforce execution of sequence of steps (algorithm) in an orderly manner.
//Applicable when - 
	//When we have pre-defined steps to achieve some algorithm.
	//When we want to avoid duplicating code, moving the common implementation and steps in the base class.
//

public class TemplateMethodDesignPatternTester {

	public static void main(String[] args) {
		HouseTemplate houseType = new WoodenHouse();
		
		//using template method
		houseType.buildHouse();
		System.out.println("************");
		
		houseType = new GlassHouse();
		
		houseType.buildHouse();
	}

}

abstract class HouseTemplate {

	//template method, final so subclasses can't override
	public final void buildHouse(){
		buildFoundation();
		buildPillars();
		buildWalls();
		buildWindows();
		System.out.println("House is built.");
	}

	//default implementation
	private void buildWindows() {
		System.out.println("Building Glass Windows");
	}

	//methods to be implemented by subclasses
	public abstract void buildWalls();
	public abstract void buildPillars();

	private void buildFoundation() {
		System.out.println("Building foundation with cement,iron rods and sand");
	}
}

class GlassHouse extends HouseTemplate {

	@Override
	public void buildWalls() {
		System.out.println("Building Glass Walls");
	}

	@Override
	public void buildPillars() {
		System.out.println("Building Pillars with glass coating");
	}

}

class WoodenHouse extends HouseTemplate {

	@Override
	public void buildWalls() {
		System.out.println("Building Wooden Walls");
	}

	@Override
	public void buildPillars() {
		System.out.println("Building Pillars with Wood coating");
	}

}