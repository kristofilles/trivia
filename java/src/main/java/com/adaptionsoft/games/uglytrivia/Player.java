package com.adaptionsoft.games.uglytrivia;

public class Player {

    private int places;
    private int purses;
    private boolean inPenaltyBox;
    private String name;
    private boolean isGettingOutOfPenaltyBox;
    public Player(String name) {
        this.name = name;
        this.places = 0;
        this.purses = 0;
        this.inPenaltyBox = false;

    }

    public void updateLocation(int roll) {
        places = places + roll;
        if (places > 11) {
            places = places - 12;
        }
        System.out.println(name
                + "'s new location is "
                + places);
    }

    public void givePenalty(){
        System.out.println("Question was incorrectly answered");
        System.out.println(name + " was sent to the penalty box");
        inPenaltyBox = true;

    }

    private boolean didPlayerWin() {
        return purses == 6;
    }

    public void rollDice(int roll) {
        System.out.println(name + " is the current player");
        System.out.println("They have rolled a " + roll);
    }

    public boolean tryGetOutOfPenalty(int roll) {
        if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;
            System.out.println(name + " is getting out of the penalty box");
        } else {
            isGettingOutOfPenaltyBox = false;
            System.out.println(name + " is not getting out of the penalty box");

        }
        return isGettingOutOfPenaltyBox;
    }

    public boolean isGettingOutOfPenaltyBox() {
        return isGettingOutOfPenaltyBox;
    }
}
