package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.trivia.GameStateException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Game {
	class Player {
		String name;
		int position;

		public Player(String name) {
			this.name = name;
			this.position = 0;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	public static final int MAX_PLAYERS = 6;
	List<Player> players = new ArrayList<>();
    int[] playerPositions = new int[MAX_PLAYERS];
    int[] playerCoins = new int[MAX_PLAYERS];
    boolean[] inPenaltyBox  = new boolean[MAX_PLAYERS];
    
    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    
    public  Game(){
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast("Rock Question " + i);
    	}
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
		playerPositions[howManyPlayers()-1] = 0;
		playerCoins[howManyPlayers()-1] = 0;
		inPenaltyBox[howManyPlayers()-1] = false;

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
		
		if (inPenaltyBox[currentPlayer]) {
			isGettingOutOfPenaltyBox = isRollOdd(roll);
			if (isGettingOutOfPenaltyBox) {
				System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
				movePlayerAndAskQuestion(roll);
			} else {
				System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
			}
		} else {
			movePlayerAndAskQuestion(roll);
		}
	}

	private void displayRoll(int roll)
	{
		System.out.println(players.get(currentPlayer) + " is the current player");
		System.out.println("They have rolled a " + roll);
	}

	private void movePlayerAndAskQuestion(int roll) {
		movePlayer(roll);
		askQuestion();
	}

	private void movePlayer(int roll)
	{
		setPlayerPosition(roll);

		System.out.println(players.get(currentPlayer)
                + "'s new location is "
                + getPlayerPosition());
		System.out.println("The category is " + currentCategory());
	}
	//TODO: Next step - manage positions from Player, instead of relying on arrays
	private void setPlayerPosition(int roll) {
		int playerPosition = getPlayerPosition();
		int newPosition = playerPosition + roll;
		if (newPosition > 11) {
			newPosition -= 12;
		}
		playerPositions[currentPlayer] = newPosition;
	}

	private int getPlayerPosition() {
//		return players.get(currentPlayer).position;
		return playerPositions[currentPlayer];
	}

	private void askQuestion() {
		if (currentCategory() == "Pop")
			System.out.println(popQuestions.removeFirst());
		if (currentCategory() == "Science")
			System.out.println(scienceQuestions.removeFirst());
		if (currentCategory() == "Sports")
			System.out.println(sportsQuestions.removeFirst());
		if (currentCategory() == "Rock")
			System.out.println(rockQuestions.removeFirst());
	}
	
	
	private String currentCategory() {
		if (getPlayerPosition() == 0) return "Pop";
		if (getPlayerPosition() == 4) return "Pop";
		if (getPlayerPosition() == 8) return "Pop";
		if (getPlayerPosition() == 1) return "Science";
		if (getPlayerPosition() == 5) return "Science";
		if (getPlayerPosition() == 9) return "Science";
		if (getPlayerPosition() == 2) return "Sports";
		if (getPlayerPosition() == MAX_PLAYERS) return "Sports";
		if (getPlayerPosition() == 10) return "Sports";
		return "Rock";
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				advanceToNextPlayer();
				playerCoins[currentPlayer]++;
				System.out.println(players.get(currentPlayer)
						+ " now has "
						+ playerCoins[currentPlayer]
						+ " Gold Coins.");

				boolean winner = didPlayerWin();

				return winner;
			} else {
				advanceToNextPlayer();
				return true;
			}
			
			
			
		} else {
		
			System.out.println("Answer was correct!!!!");
			playerCoins[currentPlayer]++;
			System.out.println(players.get(currentPlayer)
					+ " now has "
					+ playerCoins[currentPlayer]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			advanceToNextPlayer();

			return winner;
		}
	}

	private void advanceToNextPlayer() {
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
	}

	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;

		advanceToNextPlayer();
		return true;
	}


	private boolean didPlayerWin() {
		return !(playerCoins[currentPlayer] == 6);
	}
}
