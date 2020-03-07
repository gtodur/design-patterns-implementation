package com.patterns.design.creational;

public class SingletonDesignPatternTester {

	public static void main(String[] args) {
		SingletonImplementation si1 = SingletonImplementation.getInstance();
		SingletonImplementation si2 = SingletonImplementation.getInstance();
		
		System.out.println("Are both objects same : " + (si1 == si2));
		System.out.println(si1.hashCode());
		System.out.println(si2.hashCode());
		
	}

}

class SingletonImplementation {

	private static volatile SingletonImplementation instance = null;	//volatile instance as it needs to be shared among threads
	 
    // private constructor
    private SingletonImplementation() {
    	
    }
 
    public static SingletonImplementation getInstance() {
        if (instance == null) {//First check to minimise lock acquisition by threads
            synchronized (SingletonImplementation.class) {//Lock aquisition by thread
                // Double check to ensure while this thread was waiting, other threads would have created the instance
                if (instance == null) {
                    instance = new SingletonImplementation();
                }
            }
        }
        return instance;
    }

}
