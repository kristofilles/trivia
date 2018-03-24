
package com.adaptionsoft.games.trivia.runner;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {

	public static void main(String[] args) {
		GameRunner runner = new GameRunner();
		runner.runGame(new Random());
		
	}

	public void runGame(Random rand)
	{
		Game aGame = new Game();

		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");


		boolean winner;

		do {

			aGame.roll(rand.nextInt(5) + 1);

			winner = aGame.playRound(rand);
			aGame.nextPlayer();

		} while (!winner);
	}



}
