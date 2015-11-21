package de.hpi.javaide.breakout.elements;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.text.Position;

import de.hpi.javaide.breakout.basics.Elliptic;
import de.hpi.javaide.breakout.basics.Vector;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;

public class Ball extends Elliptic {
	
	public Ball(Game game, Point point, Dimension dimension) {
		super(game, point,
				dimension);
		setColor(150, 150, 150);
		System.out.println("sdjklgbsdfjklghbsjklghsjklgbklsdjbgs" + getX() + getY() + getWidth() + getHeight());
		
	}

	@Override
	public void display() {
		game.ellipse(getX(), getY(), getWidth(), getHeight());
		
	}

	public void move() {
		// TODO Auto-generated method stub
		
	}
}