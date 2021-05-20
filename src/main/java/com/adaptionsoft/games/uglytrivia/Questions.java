package com.adaptionsoft.games.uglytrivia;


import java.util.LinkedList;

public class Questions {

    enum Category {
        POP("Pop"),
        SCIENCE("Science"),
        ROCK("Rock"),
        SPORTS("Sports");

        public final String category;

        @Override
        public String toString() {
            return category;
        }

        Category(String category) {
            this.category = category;
        }
    }

    Category category;
    LinkedList<String> questions;

    public Questions(Category category) {
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