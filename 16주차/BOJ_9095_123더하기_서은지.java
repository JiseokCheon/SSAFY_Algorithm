package com.ssafy.study.day0616;

import java.io.*;

public class BOJ_9095_123더하기_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] dp = new int[12];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for (int i = 4; i < dp.length; i++)
			dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
		
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++)
			sb.append(dp[Integer.parseInt(in.readLine())] + "\n");
		
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}

}
