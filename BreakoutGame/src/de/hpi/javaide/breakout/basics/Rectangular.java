package de.hpi.javaide.breakout.basics;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import de.hpi.javaide.breakout.starter.Game;

public abstract class Rectangular extends CollisionObject {

	public Rectangular(Game game, Point position, Dimension dimension) {
		super(game, position, dimension);
		geometry = new Rectangle2D.Float(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void update(Point position, Dimension dimension) {
		super.update(position, dimension);
		((Rectangle2D) this.getGeometry()).setFrame(getX(), getY(), getWidth(), getHeight());
	}
}
