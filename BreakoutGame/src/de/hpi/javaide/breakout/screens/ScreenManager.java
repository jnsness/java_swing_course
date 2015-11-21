package de.hpi.javaide.breakout.screens;

import de.hpi.javaide.breakout.starter.Game;

public class ScreenManager {

	private static Screen currentScreen;

	/**
	 * The private constructor prohibits the instantiation of the class.
	 */
	private ScreenManager() {
	}

	public static void setScreen(Game game, String type) {
		switch (type) {
		case Screen.START:
			currentScreen = StartScreen.getInstance(game);
			break;
		case Screen.GAME:
			currentScreen = GameScreen.getInstance(game);
			break;
		case Screen.END:
			currentScreen = EndScreen.getInstance(game);
			break;
		}
	}

	public static Screen getCurrentScreen() {
		return currentScreen;
	}

}
