package de.hpi.javaide.breakout.screens;

import de.hpi.javaide.breakout.basics.UIObject;
import de.hpi.javaide.breakout.elements.Ball;
import de.hpi.javaide.breakout.elements.BallDepot;
import de.hpi.javaide.breakout.elements.CollisionLogic;
import de.hpi.javaide.breakout.elements.Paddle;
import de.hpi.javaide.breakout.elements.Wall;
import de.hpi.javaide.breakout.elements.ui.Score;
import de.hpi.javaide.breakout.elements.ui.Timer;
import de.hpi.javaide.breakout.starter.Game;

public class GameScreen implements Screen {

	private static Screen instance;

	private BallDepot ballDepot;
	private Ball currentBall;

	private Paddle paddle;
	private Wall wall;

	private UIObject score;
	private UIObject timer;

	private Game game;

	private GameScreen(Game game) {
		this.game = game;
		init();
	}

	/**
	 * GameScreen implements a "Lazy Instantiation" of the Singleton Design Patterns (Gang of Four) 
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
			instance = new GameScreen(game);
		} else {
			instance.init();
		}
		return instance;
	}

	/*
	 * Hint for the following error messages: 
	 * rather consider to create the necessary constructors than to remove the arguments.
	 * 
	 * (non-Javadoc)
	 * @see de.hpi.javaide.breakout.Initializable#init()
	 */
	@Override
	public void init() {
		ballDepot = new BallDepot(game);
		paddle = new Paddle(game);
		wall = new Wall(game, 6, 7);
		score = new Score(game);
		timer = new Timer(game);
		game.loop();
	}

	@Override
	public void update() {
		if (currentBall != null) {
			currentBall.move();
			CollisionLogic.checkCollision(game, currentBall, paddle, wall);
		}
		timer.update(null);
	}

	@Override
	public void display() {
		ballDepot.display();
		if (currentBall != null) {
			currentBall.display();
		} else {
			// there is no more Ball in the game and the depot is empty.
			if (ballDepot.isEmpty()) {
				ScreenManager.setScreen(game, Screen.END);
			}
		}
		paddle.display();
		wall.display();
		score.display();
		timer.display();
	}

	@Override
	public void handleKeyPressed(String key) {
		switch (key) {
		case Screen.KEY_ENTER:
			currentBall = ballDepot.dispense();
			break;
		case Screen.KEY_SPACE:
		default:
			break;
		}
	}

	@Override
	public void handleMouseDragged() {
		paddle.move();
	}

	@Override
	public void increaseScore(int amount) {
		// cheap trick to convert an int to a String
		// (Hint: the update() Method expects an input argument of type String)
		score.update(amount + "");
	}
}
