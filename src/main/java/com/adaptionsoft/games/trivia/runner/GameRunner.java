
package com.adaptionsoft.games.trivia.runner;
import java.util.Random;

import com.adaptionsoft.games.trivia.GameStateException;
import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {

	private static boolean notAWinner;

	public static void main(String[] args) {
		Random rand = new Random();
		playGame(rand);
	}

	public static void playGame(Random rand) {
		Game aGame = new Game();

		try {
			aGame.add("Chet");
			aGame.add("Pat");
			aGame.add("Sue");
		} catch (GameStateException e) {
			e.printStackTrace();
		}

		do {
			try {
				aGame.playRound(rand.nextInt(5) + 1);
			} catch (GameStateException e) {
				e.printStackTrace();
			}

			if (rand.nextInt(9) == 7) {
				notAWinner = aGame.wrongAnswer();
			} else {
				notAWinner = aGame.wasCorrectlyAnswered();
			}
		} while (notAWinner);
	}
}
