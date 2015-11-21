package de.hpi.javaide.breakout.elements;

import java.awt.Dimension;
import java.awt.Point;

import processing.core.PApplet;
import de.hpi.javaide.breakout.basics.Rectangular;
import de.hpi.javaide.breakout.starter.Game;

public class Brick extends Rectangular {

	public Brick(Game game, Point position, Dimension dimension) {
		super(game, position, dimension);
		setColor(150, 150, 150);
		System.out.println("Erfolg!!!!!");

	}

	@Override
	public void display() {
		// TODO Auto-generated method stub

	}

}
