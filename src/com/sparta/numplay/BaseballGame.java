package com.sparta.numplay;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BaseballGame {

    public BaseballGame() {
        createRandomNumber();
    }

    int numberArrange = 3; //난이도 선택 3 = 3자릿수, 4 = 4자릿수, 5 = 5자릿수
    String answerNumber = ""; // 정답 숫자
    int gameCount = 0;

    //랜덤한 숫자 생성 (0, 동일한 숫자 사용 불가
    HashSet<Integer> randomSet = new HashSet<>();

    private void createRandomNumber() {

        while (randomSet.size() != numberArrange) {

            int rand = (int) (Math.random() * 9) + 1;
            randomSet.add(rand);
        }

        answerNumber = randomSet.stream().map(String::valueOf).collect(Collectors.joining());
        System.out.println(answerNumber);//테스트용
    }


    //숫자를 입력하기
    //서로 다른 3자리 수를 입력할 수 있다.
    //동일한 숫자는 사용될 수 없다. 즉, 숫자는 중복되지 않아야 한다.
    //숫자만 입력 가능하며, 문자는 작성할 수 없다.
    //0은 입력받을 수 없다.
    public int play() {

        Scanner sc = new Scanner(System.in);
        String input = "";
        while (true) {
            System.out.println("숫자를 입력하세요.");
            try {
                input = String.valueOf(sc.nextInt());
            } catch (InputMismatchException e) {
                System.out.println("숫자가 아닙니다.");
                sc.nextLine();
                continue;
            }

            try {
                if (validateInput(input)) { //올바른 입력값을 받았는지 검증
                    gameCount++; // 게임 진행횟수 증가
                }
            } catch (BaseballInputException e) {
                System.out.println(e.getMessage());
                continue;
            }

            if (input.equals(answerNumber)) {
                // 정답여부 확인, 만약 정답이면 탈출
                System.out.println("정답 !");
                break;
            } else {
                // 스트라이크 갯수 계산
                // 볼 갯수 계산
                //힌트 출력
                BaseballGameDisplay.displayHint(countStrike(input), countBall(input));
            }

        }

        return gameCount;
    }

    private boolean validateInput(String input) throws BaseballInputException {
        //자릿수를 초과할 시
        if (input.length() > numberArrange) {
            throw new BaseballInputException("숫자가 범위를 초과하였습니다\n" + numberArrange + "자리수 숫자를 입력해주세요.");
        }

        //0을 입력했을 시
        if (input.contains("0")) {
            throw new BaseballInputException("게임에서 0은 사용되지 않습니다.");
        }

        //숫자가 중복되었을 시
        HashSet<Character> uniqueChars = new HashSet<>();
        for (char c : input.toCharArray()) {

           if (!uniqueChars.add(c)) { //요소를 셋에 추가할 수 없으면 , 즉 중복된 요소이면 예외 발생 -> add() 는 요소를 저장할 뿐만 아니라 boolean 타입을 반환한다.
               throw new BaseballInputException("같은 숫자가 중복되었습니다.\n중복되지 않는 숫자를 입력해주세요.");
           }
        }

        return true;
    }

    private int countStrike(String input) {
        //다 입력하면 정답과 비교해서 스트라이크의 갯수 리턴해주기
        //스트라이크 -> 숫자일치, 위치일치
        return 1;
    }

    private int countBall(String input) {
        //다 입력하면 정답과 비교해서 볼의 갯수 리턴해주기
        //볼 -> 숫자일치, 위치 불일치
        return 1;
    }

    public void setDifficulty(int difficulty) {
        this.numberArrange = difficulty;
    }
}
