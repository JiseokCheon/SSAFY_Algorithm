package com.company;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= H; i++) {
            String s = br.readLine();   // 한 문장씩 읽어옴
            int x = -1; // 각 자리에 입력할 값  (c 만나기 전 초기값 = -1)

            // 문장에서 한 글자씩 확인
            for (int j = 0; j < W; j++) {
                // 현재 문자가 c이면 x를 0으로 바꿈
                // x가 -1이 아니면 x를 출력하고 1 증가
                sb.append((x = (s.charAt(j) == 'c' ? 0 : x)) != -1 ? x++ : x).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}

