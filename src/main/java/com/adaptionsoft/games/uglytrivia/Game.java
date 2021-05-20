package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.trivia.GameStateException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.adaptionsoft.games.uglytrivia.Questions.*;
import static com.adaptionsoft.games.uglytrivia.Questions.Category.*;

public class Game {
	public static final int MAX_PLAYERS = 6;
	List<Player> players;

	Questions popQuestions;
	Questions sportsQuestions;
	Questions scienceQuestions;
	Questions rockQuestions;

    boolean isGettingOutOfPenaltyBox;
	Player currentPlayer;
	Iterator<Player> playerIterator;

	public Game(){
		popQuestions = new Questions(POP);
		popQuestions.populateQuestions();

		sportsQuestions = new Questions(SPORTS);
		sportsQuestions.populateQuestions();

		scienceQuestions = new Questions(SCIENCE);
		scienceQuestions.populateQuestions();

		rockQuestions = new Questions(ROCK);
		rockQuestions.populateQuestions();

		players = new ArrayList<>();
	}

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public void add(String playerName) throws GameStateException {

		if (howManyPlayers() >= MAX_PLAYERS) {
			throw new GameStateException();
		}

		Player player = new Player(playerName);

		players.add(player);
		playerIterator = players.iterator();
		currentPlayer = playerIterator.next();

		System.out.println(playerName + " was added");
		System.out.println("They are player number " + players.size());
	}

	public int howManyPlayers() {
		return players.size();
	}

	private boolean isRollOdd(int roll) {
		return roll % 2 != 0;
	}

	public void playRound(int roll) throws GameStateException {
		if (!this.isPlayable()) throw new GameStateException();
		
		displayRoll(roll);

		if (currentPlayer.isInPenaltyBox()) {
			isGettingOutOfPenaltyBox = isRollOdd(roll);
			if (isGettingOutOfPenaltyBox) {
				System.out.println(currentPlayer + " is getting out of the penalty box");
				movePlayerAndAskQuestion(roll);
			} else {
				System.out.println(currentPlayer + " is not getting out of the penalty box");
			}
		} else {
			movePlayerAndAskQuestion(roll);
		}
	}

	private void displayRoll(int roll)
	{
		System.out.println(currentPlayer + " is the current player");
		System.out.println("They have rolled a " + roll);
	}

	private void movePlayerAndAskQuestion(int roll) {
		movePlayer(roll);
		askQuestion();
	}

	private void movePlayer(int roll)
	{
		setPlayerPosition(roll);

		System.out.println(currentPlayer
                + "'s new location is "
                + currentPlayer.getPosition());
		System.out.println("The category is " + currentCategory());
	}

	// Who should manage the player position: Game or Player?
	// Game as it knows the rule of the game and may have to
	// manipulate the position before setting it for the current player

	private void setPlayerPosition(int roll) {
		int playerPosition = currentPlayer.getPosition();
		int newPosition = playerPosition + roll;
		if (newPosition > 11) {
			newPosition -= 12;
		}
		currentPlayer.setPosition(newPosition);
	}

	private void askQuestion() {
		if (currentCategory() == POP)
			System.out.println(popQuestions.askQuestion());
		if (currentCategory() == SCIENCE)
			System.out.println(scienceQuestions.askQuestion());
		if (currentCategory() == SPORTS)
			System.out.println(sportsQuestions.askQuestion());
		if (currentCategory() == ROCK)
			System.out.println(rockQuestions.askQuestion());
	}
	
	private Category currentCategory() {
		if (currentPlayer.getPosition() % 4 == 0) return POP;
		if (currentPlayer.getPosition() % 4 == 1) return SCIENCE;
		if (currentPlayer.getPosition() % 4 == 2) return SPORTS;
		return ROCK;
	}

	public boolean wasCorrectlyAnswered() {
		if (currentPlayer.isInPenaltyBox()){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				advanceToNextPlayer();
				currentPlayer.incrementCoins();
				System.out.println(currentPlayer
						+ " now has "
						+ currentPlayer.getCoins()
						+ " Gold Coins.");

				boolean winner = didPlayerWin();

				return winner;
			} else {
				advanceToNextPlayer();
				return true;
			}
			
		} else {
		
			System.out.println("Answer was correct!!!!");
			currentPlayer.incrementCoins();
			System.out.println(currentPlayer
					+ " now has "
					+ currentPlayer.getCoins()
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			advanceToNextPlayer();

			return winner;
		}
	}

	private void advanceToNextPlayer() {
		if (!playerIterator.hasNext()) {
			playerIterator = players.iterator();
		}
		currentPlayer = playerIterator.next();
	}

	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(currentPlayer + " was sent to the penalty box");
		currentPlayer.setInPenaltyBox(true);

		advanceToNextPlayer();
		return true;
	}

	private boolean didPlayerWin() {
		return !(currentPlayer.getCoins() == 6);
	}
}
