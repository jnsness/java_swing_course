package de.hpi.javaide.breakout.screens;

import de.hpi.javaide.breakout.basics.UIObject;
import de.hpi.javaide.breakout.elements.ui.Info;
import de.hpi.javaide.breakout.starter.Game;

public class StartScreen implements Screen {

	private static Screen instance;
	private Game game;
	private UIObject infoBox;
	
	private StartScreen(Game game){
		this.game = game;
		init();
	}

	/**
	 * StartScreen implements a "Lazy Instantiation" of the Singleton Design Patterns (Gang of Four) 
	 * This approach is not "Thread safe", but is sufficient for our current needs.
	 * 
	 * Please, be aware that Singletons need to be handled with care.
	 * There are various ways to implement them, all have there pros and cons.
	 * In his book, Effective Java, Joshua Bloch recommends to create Singletons using an enum, 
	 * which is a language concept that we have not discussed here so far.
	 * For those of you who want to go further we suggest to follow this recommendation at some point of time. 
	 *
	 * @return the StartScreen
	 */
	public static Screen getInstance(Game game){
		if(instance == null){
	    	instance = new StartScreen(game);
	    } else {
	    	instance.init();
	    }
	    return instance;
	}
	
	/*
	 * The user should be able to start the game here (by switching to the GameScreen.)
	 * 
	 * (non-Javadoc)
	 * @see de.hpi.javaide.breakout.screens.Screen#handleKeyPressed(java.lang.String)
	 */
	@Override
	public void init() {
		game.noLoop();
		game.background(0);
		String info = "Press Enter to start!\n";
		info += "Press Enter to launch the balls\n";
		infoBox = new Info(game, info);
		infoBox.display();
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void display() {
		System.out.println("Hit enter to start");
		infoBox.display();
	}

	@Override
	public void handleKeyPressed(String key) {
		switch (key) {
		case Screen.KEY_ENTER: 
			System.out.println("starting..");
			ScreenManager.setScreen(game, Screen.GAME);
			break;
		default: break;
		}
	}

	@Override
	public void handleMouseDragged() {
		// Im StartScreen ist keine Interaktion mit der Maus notwendig.
	}
	
	@Override
	public void increaseScore(int i) {
		// Im StartScreen gibt es keinen Counter.
	}
}
