package com.company;

import java.io.*;
import java.util.*;

public class Main {
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        dp = new int[41][2]; // n 번째의 0 출력한 횟수와 1 출력한 횟수

        for (int i = 0; i <= 40; i++) {
            dp[i][0] = -1;
            dp[i][1] = -1;
        }

        for (int i = 0; i < T; i++) {
            int x = Integer.parseInt(br.readLine());
            fibonacci(x);
            System.out.println(dp[x][0] + " " + dp[x][1]);
        }
    }

    static void fibonacci(int n) {
        if (dp[n][0] != -1 && dp[n][1] != -1) { // 전에 탐색해서 값을 가지고 있으면 반환
            return;
        }

        if (n == 0) {
            dp[0][0] = 1;
            dp[0][1] = 0;
        } else if (n == 1) {
            dp[1][1] = 1;
            dp[1][0] = 0;
        } else {
            fibonacci(n - 1);
            fibonacci(n - 2);
            dp[n][0] = dp[n - 1][0] + dp[n - 2][0];     // 피보나치 돌려서 값 할당
            dp[n][1] = dp[n - 1][1] + dp[n - 2][1];
        }
    }
}