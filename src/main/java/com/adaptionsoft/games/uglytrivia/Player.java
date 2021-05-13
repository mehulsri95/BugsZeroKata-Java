package com.adaptionsoft.games.uglytrivia;

class Player {
    String name;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private int position;

    public int getCoins() {
        return coins;
    }

    public void incrementCoins() {
        this.coins += 1;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    private int coins;
    private boolean inPenaltyBox;

    public Player(String name) {
        this.name = name;
        this.position = 0;
        this.coins = 0;
        this.inPenaltyBox = false;
    }

    @Override
    public String toString() {
        return name;
    }
}
