package com.ssafy.study.day0621;

import java.io.*;

public class BOJ_9461_파도반수열_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		long[] dp = new long[101];
		for (int i = 1; i < 4; i++)
			dp[i] = 1;
		for (int i = 4; i <= 100; i++)
			dp[i] = dp[i - 2] + dp[i - 3];
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(in.readLine());
			sb.append(dp[N] + "\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}

}
