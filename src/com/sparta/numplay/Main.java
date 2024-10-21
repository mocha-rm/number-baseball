package com.sparta.numplay;

public class Main {
    public static void main(String[] args) {
        BaseballGame baseballGame = new BaseballGame();
        int countOfSolve = baseballGame.play();
        System.out.println(countOfSolve + "번만에 클리어 했습니다.");
    }
}
