package com.company;

public class Main {

    public static void main(String[] args) {
        GameOfLife game = new GameOfLife(20);
        game.show();
        //game.populateRandom(1000);
        game.placePentaDecathlon();
        game.show();

        for (int i = 0; i < 20; i++){
            game.execCycle();
            game.show();
        }
    }
}

