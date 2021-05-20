package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.trivia.GameStateException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


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
		popQuestions = new Questions("Pop");
		popQuestions.populateQuestions();

		sportsQuestions = new Questions("Sports");
		sportsQuestions.populateQuestions();

		scienceQuestions = new Questions("Science");
		scienceQuestions.populateQuestions();

		rockQuestions = new Questions("Rock");
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
		if (currentCategory() == "Pop")
			System.out.println(popQuestions.askQuestion());
		if (currentCategory() == "Science")
			System.out.println(scienceQuestions.askQuestion());
		if (currentCategory() == "Sports")
			System.out.println(sportsQuestions.askQuestion());
		if (currentCategory() == "Rock")
			System.out.println(rockQuestions.askQuestion());
	}
	
	
	private String currentCategory() {
		if (currentPlayer.getPosition() == 0) return "Pop";
		if (currentPlayer.getPosition() == 4) return "Pop";
		if (currentPlayer.getPosition() == 8) return "Pop";
		if (currentPlayer.getPosition() == 1) return "Science";
		if (currentPlayer.getPosition() == 5) return "Science";
		if (currentPlayer.getPosition() == 9) return "Science";
		if (currentPlayer.getPosition() == 2) return "Sports";
		if (currentPlayer.getPosition() == MAX_PLAYERS) return "Sports";
		if (currentPlayer.getPosition() == 10) return "Sports";
		return "Rock";
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
