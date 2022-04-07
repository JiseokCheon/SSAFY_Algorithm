package com.ssafy.algo0331;

import java.util.*;
import java.io.*;

public class BJ_1463_1로만들기_서은지 {
	static int min;

	// dfs(112), bfs(288)
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		min = Integer.MAX_VALUE;
		dfs(N, 0);
		System.out.println(min);

		min = Integer.MAX_VALUE;
		bfs(new int[] { N, 0 });
		System.out.println(min);
	}

	public static void dfs(int current, int sum) {
		if (current == 1) {
			min = Math.min(min, sum + 0);
			return;
		}

		if (sum >= min)
			return;

		if (current % 3 == 0)
			dfs(current / 3, sum + 1);
		if (current % 2 == 0)
			dfs(current / 2, sum + 1);
		dfs(current - 1, sum + 1);
	}

	public static void bfs(int[] arr) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(arr);

		while (!queue.isEmpty()) {
			int n = queue.peek()[0];
			int sum = queue.poll()[1];

			if (sum >= min)
				continue;
			
			if (n == 1) {
				min = Math.min(min, sum + 0);
				break;
			}

			if (n % 3 == 0)
				queue.offer(new int[] { n / 3, sum + 1 });
			if (n % 2 == 0)
				queue.offer(new int[] { n / 2, sum + 1 });
			queue.offer(new int[] { n - 1, sum + 1 });
		}

	}

	// dp(120)
	static int N;
	static int[] dp;

	public static void main1(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		dp = new int[N + 1];
		if (N == 1)
			System.out.println(0);
		else {
			dp[1] = 0;
			for (int i = 2; i <= N; i++)
				dp[i] = f(i);
			System.out.println(dp[N]);
		}

	}

	public static int f(int n) {
		if (n % 6 == 0)
			return Math.min(Math.min(dp[n / 3], dp[n / 2]), dp[n - 1]) + 1;
		else if (n % 3 == 0)
			return Math.min(dp[n / 3], dp[n - 1]) + 1;
		else if (n % 2 == 0)
			return Math.min(dp[n / 2], dp[n - 1]) + 1;
		return dp[n - 1] + 1;
	}

}
