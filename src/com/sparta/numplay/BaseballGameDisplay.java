package com.sparta.numplay;

public class BaseballGameDisplay {

    public static void displayHint(int strike, int ball) {
        if (strike == 0 && ball == 0) {
            System.out.println("Out !");
        } else {
            System.out.println("Strike : " + strike + "  Ball : " + ball);
        }
    }

    public static void displayMenu(int num) {
        System.out.println("1. 게임 시작하기 2. 게임 기록 보기 3. 종료하기");

    }
}
