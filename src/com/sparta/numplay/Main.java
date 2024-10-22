package com.sparta.numplay;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BaseballGame baseballGame = new BaseballGame();
        Scanner scanner = new Scanner(System.in);
        int input;
        int digits;

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
                case 0 -> {
                    System.out.println("설정하고자 하는 자리수를 입력하세요. (디폴트 값 = 3)");
                    try {
                        digits = scanner.nextInt();
                        baseballGame.setNumberRange(digits);
                    } catch (InputMismatchException | BaseballInputException error) {
                        scanner.nextLine();
                        System.out.println("잘못된 입력입니다.");

                        Optional<String> optionalError = Optional.ofNullable(error.getMessage());//값이 있을수도 있고 없을수도 있는 Optional 생성
                        if (optionalError.isPresent()) { //isPresent() 값이 있는지 체크 -> 있으면 에러내용 출력
                            System.out.println(error.getMessage());
                        }

                        continue;
                    }

                    baseballGame.play();
                }
                case 1 -> {
                    //게임 시작
                    baseballGame.play();
                }
                case 2 -> {
                    //기록 보기
                    BaseballGameDisplay.displayHistory(baseballGame.getGameHistoryMap());
                }
                case 3 -> {
                    //게임 종료
                    baseballGame.clearGameHistory(); //히스토리 클리어
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
