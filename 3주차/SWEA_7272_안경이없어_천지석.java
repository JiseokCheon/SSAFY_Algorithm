package com.company;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        // 알파벳 구멍 갯수
        int[] arr = {1, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0};

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            String s1 = st.nextToken();
            String s2 = st.nextToken();
            boolean check = true;   // 문자열 판별

            if (s1.length() == s2.length()) {
                for (int i = 0; i < s1.length(); i++) {
                    // 문자열 구멍 갯수 비교
                    if (arr[s1.charAt(i) - 'A'] != arr[s2.charAt(i) - 'A']) {
                        check = false;
                        break;
                    }
                }
            } else { // 길이 다르면 두 문자열 다름
                check = false;
            }

            System.out.println("#" + tc + " " + (check ? "SAME" : "DIFF"));
        }
    }
}