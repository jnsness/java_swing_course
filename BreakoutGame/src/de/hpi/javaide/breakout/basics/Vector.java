package de.hpi.javaide.breakout.basics;

import processing.core.PVector;

public class Vector {

	private PVector vector;

	public Vector(float x, float y) {
		vector = new PVector(x, y);
	}

	public float getX() {
		return vector.x;
	}

	public float getY() {
		return vector.y;
	}

	public void setX(float x) {
		vector.x = x;
	}

	public void setY(float y) {
		vector.y = y;
	}

	public void mult(float n) {
		vector.mult(n);
	}

	public void normalize() {
		vector.normalize();
	}

}
