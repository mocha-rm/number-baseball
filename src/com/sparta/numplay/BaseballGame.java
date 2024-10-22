package com.sparta.numplay;

import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BaseballGame {
    //생성자에서 정답숫자 생성
    public BaseballGame() {
        createRandomNumber();
    }

    //TODO: 난이도 설정 로직 구현
    private int numberArrange = 3; //난이도 선택 3 = 3자릿수, 4 = 4자릿수, 5 = 5자릿수

    private String answerNumber = ""; // 정답 숫자

    private int gameCount = 0; // 게임을 몇번 했는지 담는 변수
    private int tryCount = 0;// 문제를 몇번 시도했는지 담는 변수
    private HashMap<Integer, Integer> gameHistory = new HashMap<>();


    private int strikeCount = 0;
    private int ballCount = 0;

    //랜덤한 숫자 생성 (0, 동일한 숫자 사용 불가
    private HashSet<Integer> randomSet = new HashSet<>();

    private void createRandomNumber() {

        while (randomSet.size() != numberArrange) {

            int rand = (int) (Math.random() * 9) + 1;
            randomSet.add(rand);
        }

        answerNumber = "456";//randomSet.stream().map(String::valueOf).collect(Collectors.joining());
        System.out.println(answerNumber);//TODO : 테스트 후에 제거 하기
    }

    public void play() {

        Scanner sc = new Scanner(System.in);
        String input = "";
        System.out.println("숫자를 입력하세요.");

        while (true) {
            try {
                input = String.valueOf(sc.nextInt());
            } catch (InputMismatchException e) {
                System.out.println("숫자가 아닙니다.");
                sc.nextLine();
                continue;
            }

            try {
                if (validateInput(input)) { //올바른 입력값을 받았는지 검증
                    tryCount++; // 게임 진행횟수 증가
                }
            } catch (BaseballInputException e) {
                System.out.println(e.getMessage());
                continue;
            }

            if (input.equals(answerNumber)) {
                // 정답여부 확인, 만약 정답이면 반복문 탈출 = 종료
                gameCount++;
                System.out.println("정답 !");
                break;
            } else {
                //힌트 출력
                grading(input);
                BaseballGameDisplay.displayHint(strikeCount, ballCount);
                clear();
            }
        }
    }

    private boolean validateInput(String input) throws BaseballInputException {
        //자릿수를 초과할 시
        if (input.length() > numberArrange || input.length() < numberArrange) {
            throw new BaseballInputException(numberArrange + "자리수 숫자를 입력해주세요.");
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


    private void grading(String input) {

        String[] answerLetters = answerNumber.split(""); // 정답 숫자를 자릿수 별로 나눠서 배열에 담기
        String[] inputLetters = input.split(""); // input 을 자릿수 별로 나눠서 배열에 담기

        for (int i = 0; i < inputLetters.length; i++) {
            if (inputLetters[i].equals(answerLetters[i])) {
                strikeCount++;
            } else {
                ballCheck(inputLetters[i]);
            }
        }
    }

    private void ballCheck(String inputLetter) {
        //숫자는 맞지만 위치는 맞지 않을 경우
        if (answerNumber.contains(inputLetter)) { //정답에 유저가 입력한 값이 존재하는지 확인
            ballCount++;
        }
    }

    private void clear() {
        strikeCount = 0;
        ballCount = 0;
    }

    public void setDifficulty(int difficulty) {
        this.numberArrange = difficulty;
    }

    public HashMap<Integer, Integer> getGameHistoryMap() {
        return gameHistory;
    }

    public void clearGameHistory() {
        gameHistory.clear();
    }
}
