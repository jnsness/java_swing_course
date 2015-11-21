package de.hpi.javaide.breakout.basics;

import de.hpi.javaide.breakout.Displayable;
import de.hpi.javaide.breakout.starter.Game;

public abstract class UIObject implements Displayable {

	protected Game game;

	public UIObject(Game game) {
		this.game = game;
	}

	public abstract void update(String input);
}
