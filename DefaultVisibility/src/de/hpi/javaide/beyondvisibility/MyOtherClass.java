package de.hpi.javaide.beyondvisibility;

import de.hpi.javaide.visibility.MyClass;

public class MyOtherClass {


	public static void main(String[] args) {
		MyClass mc = new MyClass();
		//mc.packagePrivateMethod();
		mc.publicMethod();
	}

}
