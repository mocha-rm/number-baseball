package com.sparta.numplay;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BaseballGame baseballGame = new BaseballGame();
        Scanner scanner = new Scanner(System.in);
        int input = 0;

        while (true) {
            BaseballGameDisplay.displayMenu();

            try {
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("올바른 숫자를 입력해주세요!");
                continue;
            }

            switch (input) {
                case 1 -> {
                    //게임 시작
                    baseballGame.play();
                }
                case 2 -> {
                    //기록 보기
                    BaseballGameDisplay.displayHistory(baseballGame.getGameHistoryMap());
                }
                case 3 -> {
                    //히스토리 클리어
                    baseballGame.clearGameHistory();
                    System.out.println("게임을 종료합니다.");
                    return;
                }
                default -> {
                    System.out.println("올바른 숫자를 입력해주세요!");
                }
            }
        }
    }
}
