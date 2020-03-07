package com.patterns.design.structural;

import java.util.HashMap;

//enables use sharing of objects to support large numbers of fine-grained objects efficiently
//A flyweight is a shared object that can be used in multiple contexts simultaneously. 
//The flyweight acts as an independent object in each context.
//we can use it when we need a large number of similar objects that are unique in terms of only a few parameters and most of the stuffs are common in general
//also used when need to control the memory consumption by large number of objects – by creating fewer objects and sharing them across.
//A flyweight objects essentially has two kind of attributes – intrinsic and extrinsic.
	//An intrinsic state attribute is stored/shared in the flyweight object, and it is independent of flyweight’s context. As the best practice, we should make intrinsic states immutable.
	//An extrinsic state varies with flyweight’s context, which is why they cannot be shared. Client objects maintain the extrinsic state, and they need to pass this to a flyweight object during object creation.

public class FlyweightDesignPatternTester {

	public static void main(String[] args) {
		Pen yellowThinPen1 = PenFactory.getThickPen("YELLOW"); // created new pen
		yellowThinPen1.draw("Hello World !!");

		Pen yellowThinPen2 = PenFactory.getThickPen("YELLOW"); // pen is shared
		yellowThinPen2.draw("Hello World !!");

		Pen blueThinPen = PenFactory.getThickPen("BLUE"); // created new pen
		blueThinPen.draw("Hello World !!");

		System.out.println(yellowThinPen1.hashCode());
		System.out.println(yellowThinPen2.hashCode());

		System.out.println(blueThinPen.hashCode());
	}
}

interface Pen {
	public void setColor(String color);

	public void draw(String content);
}

enum BrushSize {
	THIN, MEDIUM, THICK
}

class ThickPen implements Pen {

	final BrushSize brushSize = BrushSize.THICK; // intrinsic state - shareable
	private String color = null; // extrinsic state - supplied by client

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public void draw(String content) {
		System.out.println("Drawing THICK content in color : " + color);
	}
}

class ThinPen implements Pen {

	final BrushSize brushSize = BrushSize.THIN;
	private String color = null;

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public void draw(String content) {
		System.out.println("Drawing THIN content in color : " + color);
	}
}

class MediumPen implements Pen {

	final BrushSize brushSize = BrushSize.MEDIUM;
	private String color = null;

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public void draw(String content) {
		System.out.println("Drawing MEDIUM content in color : " + color);
	}
}

class PenFactory {
	private static final HashMap<String, Pen> pensMap = new HashMap<>();

	public static Pen getThickPen(String color) {
		String key = color + "-THICK";

		Pen pen = pensMap.get(key);

		if (pen != null) {
			return pen;
		} else {
			pen = new ThickPen();
			pen.setColor(color);
			pensMap.put(key, pen);
		}

		return pen;
	}

	public static Pen getThinPen(String color) {
		String key = color + "-THIN";

		Pen pen = pensMap.get(key);

		if (pen != null) {
			return pen;
		} else {
			pen = new ThinPen();
			pen.setColor(color);
			pensMap.put(key, pen);
		}

		return pen;
	}

	public static Pen getMediumPen(String color) {
		String key = color + "-MEDIUM";

		Pen pen = pensMap.get(key);

		if (pen != null) {
			return pen;
		} else {
			pen = new MediumPen();
			pen.setColor(color);
			pensMap.put(key, pen);
		}

		return pen;
	}
}