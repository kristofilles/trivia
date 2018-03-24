package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Game {
	private ArrayList<String> players = new ArrayList<String>();
    private int[] places = new int[6];
    private int[] purses  = new int[6];
    private boolean[] inPenaltyBox  = new boolean[6];

	private ArrayList<Player> players2 = new ArrayList<Player>();


    private LinkedList<String> popQuestions = new LinkedList<String>();
    private LinkedList<String> scienceQuestions = new LinkedList<String>();
    private LinkedList<String> sportsQuestions = new LinkedList<String>();
    private LinkedList<String> rockQuestions = new LinkedList<String>();
    
    private int currentPlayer = 0;
    private Player currentPlayer2;
    private boolean isGettingOutOfPenaltyBox;
    
    public  Game(){
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i));
    	}
    }

	private String createRockQuestion(int index){
		return "Rock Question " + index;
	}

	public boolean add(String playerName) {
		players2.add(new Player(playerName));
		currentPlayer2 = players2.get(0);

	    players.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
	    
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + players.size());
		return true;
	}
	
	private int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		currentPlayer2.rollDice(roll);

		if (!inPenaltyBox[currentPlayer]) {

			updateLocation(roll);
			selectCurrentCategory();
			askQuestion(places[currentPlayer]);
			return;
		}

		if(currentPlayer2.tryGetOutOfPenalty(roll)) {
			updateLocation(roll);
			selectCurrentCategory();
			askQuestion(places[currentPlayer]);
		}

	}

	public boolean tryGetOutOfPenalty(int roll) {
		if (roll % 2 != 0) {
			isGettingOutOfPenaltyBox = true;
			System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
		} else {
			isGettingOutOfPenaltyBox = false;
			System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");

		}
		return isGettingOutOfPenaltyBox;
	}

	private void selectCurrentCategory() {
		System.out.println("The category is " + currentCategory(places[currentPlayer]));
	}

	private void updateLocation(int roll) {
		places[currentPlayer] = places[currentPlayer] + roll;
		if (places[currentPlayer] > 11) {
			places[currentPlayer] = places[currentPlayer] - 12;
		}
		System.out.println(players.get(currentPlayer)
				+ "'s new location is "
				+ places[currentPlayer]);
	}



	private void askQuestion(int position) {
		if ("Pop".equals(currentCategory(position)))
			System.out.println(popQuestions.removeFirst());

		if ("Science".equals(currentCategory(position)))
			System.out.println(scienceQuestions.removeFirst());

		if ("Sports".equals(currentCategory(position)))
			System.out.println(sportsQuestions.removeFirst());

		if ("Rock".equals(currentCategory(position)))
			System.out.println(rockQuestions.removeFirst());		
	}



	
	private String currentCategory(int position) {
		if (position == 0) return "Pop";
		if (position == 4) return "Pop";
		if (position == 8) return "Pop";
		if (position == 1) return "Science";
		if (position == 5) return "Science";
		if (position == 9) return "Science";
		if (position == 2) return "Sports";
		if (position == 6) return "Sports";
		if (position == 10) return "Sports";
		return "Rock";
	}



	private void correctAnswer() {
		System.out.println("Answer was correct!!!!");
		purses[currentPlayer]++;
		System.out.println(players.get(currentPlayer)
				+ " now has "
				+ purses[currentPlayer]
				+ " Gold Coins.");
	}

	public void givePenalty(){
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;

	}

	public void nextPlayer() {

		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;

		currentPlayer2 = players2.get(currentPlayer);
	}


	private boolean didPlayerWin() {
		return purses[currentPlayer] == 6;
	}

	public boolean playRound(Random rand) {
		if (isWrongAnswer(rand)) {
			givePenalty();
			return false;
		}

		if (!inPenaltyBox[currentPlayer]){

			correctAnswer();

			return didPlayerWin();
		}

		if (!currentPlayer2.isGettingOutOfPenaltyBox()) {

			return false;
		}
		correctAnswer();

		return didPlayerWin();

	}

	private boolean isWrongAnswer(Random rand) {
		return rand.nextInt(9)== 7;
	}
}
