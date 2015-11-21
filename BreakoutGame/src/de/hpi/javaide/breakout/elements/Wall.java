package de.hpi.javaide.breakout.elements;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import processing.core.PApplet;
import de.hpi.javaide.breakout.Displayable;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;

public class Wall implements Displayable, Iterable<Brick> {

	private ArrayList<Brick> wall = new ArrayList<Brick>();

	public Wall(Game game, int i, int j) {
		for (int k = 0; k < i; k++) {

			wall.add(new Brick(game, new Point(50, 50), new Dimension(10,10)));
			System.out.println("test"+ i);
		}
		System.out.println(wall.toString());
	}

	@Override
	public Iterator<Brick> iterator() {
		return wall.iterator();
	}

	/**
	 * Build the wall by putting the single bricks into their position Hint: You
	 * might want to use one or two for-loops
	 * 
	 * @param game
	 * @param columns
	 * @param rows
	 */
	private void buildWall(Game game, int columns, int rows) {
		
	}

	@Override
	public void display() {
	}
}
