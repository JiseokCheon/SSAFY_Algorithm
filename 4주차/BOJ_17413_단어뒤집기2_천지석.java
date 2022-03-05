package com.company;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");  // 공백으로 나눠서 단어 구분
        boolean check = false;      // 괄호 열렸는지 확인
        StringBuilder sb = new StringBuilder();     // 출력할 문자열
        StringBuilder temp = new StringBuilder();   // 뒤집을때 사용할 단어

        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length(); j++) {
                char c = s[i].charAt(j);
                // 열린 괄호 만나면
                if (c == '<') {
                    sb.append(temp.reverse());  // 이전까지 만난 단어 뒤집어서 저장
                    sb.append("<");         // 괄호 저장
                    temp.setLength(0);      // 저장된 단어 초기화
                    check = true;           // 괄호 열림
                }
                // 닫힌 괄호 만나면
                else if (c == '>') {
                    check = false;      // 괄호 닫힘
                    sb.append(">");     // 괄호 저장
                }
                // 괄호 내부
                else if (check) {
                    sb.append(c);   // 내부 문자 저장
                }
                // 괄호 내부가 아닌 상태일 경우
                else {
                    temp.append(c); // 뒤집을 단어에 저장
                }
            }
            // 단어 뒤집어서 저장
            sb.append(temp.reverse()).append(i < s.length - 1 ? " " : "");
            temp.setLength(0);  // 단어 초기화
        }
        System.out.println(sb);

    }
}
