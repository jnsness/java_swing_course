package de.hpi.javaide.breakout.screens;

import de.hpi.javaide.breakout.basics.UIObject;
import de.hpi.javaide.breakout.elements.ui.Info;
import de.hpi.javaide.breakout.starter.Game;

public class EndScreen implements Screen {

	private static Screen instance;
	private Game game;
	private UIObject infoBox;

	private EndScreen(Game game) {
		this.game = game;
		init();
	}

	/**
	 * EndScreen implements a "Lazy Instantiation" of the Singleton Design Patterns (Gang of Four) 
	 * This approach is not "Thread safe", but is sufficient for our current needs.
	 * 
	 * Please, be aware that Singletons need to be handled with care.
	 * There are various ways to implement them, all have there pros and cons.
	 * In his book, Effective Java, Joshua Bloch recommends to create Singletons using an enum, 
	 * which is a language concept that we have not discussed here so far.
	 * For those of you who want to go further we suggest to follow this recommendation at some point of time. 
	 * 
	 * @return the EndScreen
	 */
	public static Screen getInstance(Game game) {
		if (instance == null) {
			instance = new EndScreen(game);
		} else {
			instance.init();
		}
		return instance;
	}
	
   /*
    * Currently, we are within the EndScreen.
    * The only action that is required here is to restart the game (switch back to the start screen.)
    * Of course you can add any additional features if you want to.
    */
	@Override
	public void init() {
		game.background(0);
		String info = "Game over!!!\n";
		info += "Press Enter to restart!\n";
		infoBox = new Info(game, info);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void display() {
		System.out.println("Game over: Hit enter to restart.");
		infoBox.display();
	}

	@Override
	public void handleKeyPressed(String key) {
		switch (key) {
		case Screen.KEY_ENTER:
			System.out.println("restart..");
			ScreenManager.setScreen(game, Screen.START);
		}
	}

	@Override
	public void handleMouseDragged() {
		// Interaction with the mouse is not necessary in the EndScreen
	}

	@Override
	public void increaseScore(int i) {
		// There is no counter in the EndScreen
	}
}
