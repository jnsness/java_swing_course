package de.hpi.javaide.breakout.basics;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Shape;

import de.hpi.javaide.breakout.Collidable;
import de.hpi.javaide.breakout.Colorable;
import de.hpi.javaide.breakout.Displayable;
import de.hpi.javaide.breakout.starter.Game;

public abstract class CollisionObject implements Collidable, Displayable, Colorable {

	protected Shape geometry;
	protected Game game;
	protected Dimension dimension;
	protected Point position;
	private Color color;

	public CollisionObject(Game game, Point position, Dimension dimension) {
		this.game = game;
		this.position = position;
		this.dimension = dimension;
		this.color = new Color(255, 255, 255);
	}

	@Override
	public int getWidth() {
		return dimension.width;
	}

	@Override
	public int getHeight() {
		return dimension.height;
	}

	@Override
	public int getX() {
		return position.x;
	}

	@Override
	public int getY() {
		return position.y;
	}

	@Override
	public int getR() {
		return color.getR();
	}

	@Override
	public int getG() {
		return color.getG();
	}

	@Override
	public int getB() {
		return color.getB();
	}

	@Override
	public void setColor(int r, int g, int b) {
		this.color = new Color(r, g, b);
	}

	@Override
	public Shape getGeometry() {
		return this.geometry;
	}

	@Override
	public void update(Point position, Dimension dimension) {
		this.position = position;
		this.dimension = dimension;
	}
}
