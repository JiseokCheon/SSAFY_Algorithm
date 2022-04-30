package com.ssafy.study.day0430;

import java.io.*;

public class BOJ_2579_계단오르기_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(in.readLine());
		int[] dp = new int[N];
		dp[0] = arr[0];
		if (N > 1) {
			dp[1] = arr[0] + arr[1];
			if (N > 2) {
				dp[2] = Math.max(arr[0] + arr[2], arr[1] + arr[2]);
				for (int i = 3; i < N; i++)
					dp[i] = Math.max(dp[i - 2] + arr[i], dp[i - 3] + arr[i - 1] + arr[i]);
			}
		}
		System.out.println(dp[N - 1]);
	}
}
