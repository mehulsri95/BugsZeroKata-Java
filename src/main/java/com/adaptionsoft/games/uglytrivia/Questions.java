package com.adaptionsoft.games.uglytrivia;


import java.util.LinkedList;

public class Questions {
    String category;
    LinkedList<String> questions;

    public Questions(String category) {
        this.category = category;
        this.questions = new LinkedList<>();
    }

    public void populateQuestions() {
        for (int i=0; i<50; i++) {
            this.questions.add(this.category + " Question " + i);
        }
    }

    public String askQuestion() {
        return this.questions.removeFirst();
    }
}