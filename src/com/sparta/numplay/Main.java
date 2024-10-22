package com.sparta.numplay;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BaseballGame baseballGame = new BaseballGame();
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        while (input != 3) {

        }
        int countOfSolve = baseballGame.play();
        //baseballGame.getHistory();

    }
}
