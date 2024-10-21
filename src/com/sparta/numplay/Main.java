package com.sparta.numplay;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        int difficulty = 3; //난이도 선택 3 = 3자릿수, 4 = 4자릿수, 5 = 5자릿수
        String answerNumber = ""; // 정답 숫자

        //랜덤한 숫자 생성 (0, 동일한 숫자 사용 불가
        HashSet<Integer> randomSet = new HashSet<>();

        while (randomSet.size() != difficulty) {

            int rand = (int)(Math.random() * 9) + 1;
            randomSet.add(rand);
        }

        answerNumber = randomSet.stream().map(String::valueOf).collect(Collectors.joining());
    }
}
