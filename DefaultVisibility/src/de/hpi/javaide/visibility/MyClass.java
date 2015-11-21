package de.hpi.javaide.visibility;

public class MyClass implements MyInterface {

	// Methode ohne Sichtbarkeits-Modifier => package private
	void packagePrivateMethod() {
		System.out.println("I'm package private");
	}

	// Siehe MyInterface zum Vergleich
	@Override
	public void publicMethod() {
		System.out.println("I'm public");
	}
}
