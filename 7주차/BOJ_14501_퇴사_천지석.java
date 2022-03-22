package com.company;

import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N + 1];
        int[] P = new int[N + 1];
        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken()) - 1;
            P[i] = Integer.parseInt(st.nextToken());
        }
        int max = 0;    // 최대 이익
        for (int i = 1; i < N + 1; i++) {
            if (T[i] + i < N + 1) {    // 상담이 가능하면
                dp[T[i] + i] = Math.max(dp[T[i] + i], max + P[i]);
            }
            max = Math.max(max, dp[i]); // 현재 날짜에서 가능한 최대이익
        }

        System.out.println(max);
    }
}