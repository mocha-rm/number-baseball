package com.sparta.numplay;

import java.util.HashMap;


public class BaseballGameDisplay {
    //콘솔 출력만 하는 클래스
    public static void displayMenu() {
        System.out.println("1. 게임 시작하기 2. 게임 기록 보기 3. 종료하기");
    }

    public static void displayHint(int strike, int ball) {
        if (strike == 0 && ball == 0) {
            System.out.println("Out !");
        } else {
            System.out.println("Strike : " + strike + "  Ball : " + ball);
        }
    }

    public static void displayHistory(HashMap<Integer, Integer> historyMap) {

    }
}
