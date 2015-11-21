package de.hpi.javaide.breakout;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Shape;

public interface Collidable extends Measureable {
	void update(Point position, Dimension dimension);

	Shape getGeometry();
}