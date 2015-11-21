package de.hpi.javaide.breakout.elements;

import java.awt.Dimension;
import java.awt.Point;

import de.hpi.javaide.breakout.basics.Rectangular;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;
import processing.core.PApplet;

public class Paddle extends Rectangular {
	public Paddle(Game game) {
		super(game, GameConstants.STARTPOSITION,
				new Dimension(100, 20));
		setColor(150, 150, 150);

	}

	@Override
	public void display() {
//		game.rectMode(PApplet.CENTER);
//		game.noStroke();
//		game.fill(getR(), getG(), getB());
		game.rect(getX(), getY(), getWidth(), getHeight());

	}

	public void move() {
		update(new Point(game.mouseX, getY()), new Dimension(getWidth(),
				getHeight()));
	}
}
