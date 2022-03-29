package com.ssafy.study;

import java.util.*;
import java.io.*;

public class BOJ_1003_피보나치함수_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(in.readLine());
			if (N == 0) {
				sb.append(1 + " " + 0 + "\n");
			} else if (N == 1) {
				sb.append(0 + " " + 1 + "\n");
			} else {
				int[][] dp = new int[N + 1][2];
				dp[0][0] = 1;
				dp[1][1] = 1;
				for (int i = 2; i <= N; i++) {
					dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
					dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
				}
				sb.append(dp[N][0] + " " + dp[N][1]+"\n");
			}
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}

}
