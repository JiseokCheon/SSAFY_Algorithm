package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[K + 1];  // 무게당 최대 물품 가치

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            for (int j = K; j >= w; j--) {      // 해당 물건 넣을 경우 최대 가치합 찾음
                dp[j] = Math.max(dp[j - w] + v, dp[j]);
            }
        }

        System.out.println(dp[K]);
    }
}




