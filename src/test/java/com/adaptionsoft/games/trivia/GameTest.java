package com.adaptionsoft.games.trivia;

import static org.junit.Assert.*;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import com.adaptionsoft.games.uglytrivia.Game;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.io.*;
import java.util.Random;
import java.util.stream.IntStream;

public class GameTest {

	@Test(expected = GameStateException.class)
	public void gameCannotStartWithNoPlayers() throws GameStateException
	{
		Game game = new Game();
		game.playRound(1);
	}

	@Test(expected = GameStateException.class)
	public void gameCannotStartWithOnlyOnePlayer() throws GameStateException
	{
		Game game = new Game();
		game.add("B");
		assertEquals("Game should have only 1 player.", 1, game.howManyPlayers());
		game.playRound(1);
	}

	@Test
	public void gameCanStartWithAtLeastTwoPlayers() throws GameStateException
	{
		Game game = new Game();
		game.add("Joey");
		game.add("Monica");

		game.playRound(1);
	}

	@Test
	public void itsLockedDown() throws Exception {

        Random randomizer = new Random(123455);
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultStream));

        IntStream.range(1,15).forEach(i -> GameRunner.playGame(randomizer));

        Approvals.verify(resultStream.toString());

	}

	@Test(expected = GameStateException.class)
	public void gameCanHaveAtmost6Players() throws GameStateException
	{
		Game game = new Game();
		game.add("Joey");
		game.add("Chandler");
		game.add("Phoebe");
		game.add("Ross");
		game.add("Monica");
		game.add("Rachel");
		game.add("Gunther");

		game.playRound(1);
	}
}
