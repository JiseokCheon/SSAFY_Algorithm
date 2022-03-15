package com.ssafy.study;

import java.util.*;
import java.io.*;

public class BOJ_1937_욕심쟁이판다_서은지 {
	static int N, max;
	static int[][] forest, dp;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		forest = new int[N][N];
		dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				forest[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		max = 0;
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = Math.max(dfs(i, j), max);	// 현재 위치에서 최대로 움직일 수 있는 칸 수와 max 비교
			}
		}
		System.out.println(max);
	}

	public static int dfs(int r, int c) {
		// 이미 최대 이동 수를 구한 칸이면 그 값을 반환
		if (dp[r][c] != 0)
			return dp[r][c];
		
		int cnt = 1;	// 현재 위치에서 최대로 움직 일 수  있는 칸 수(1: 처음 위치에서 대나무를 다 먹을때)
		for (int i = 0; i < delta.length; i++) {
			int dr = r + delta[i][0];
			int dc = c + delta[i][1];
			
			// 다음 이동이 범위내에 있고, 현재 위치보다 대나무가 더 많을 때
			if (dr >= 0 && dc >= 0 && dr < N && dc < N && forest[dr][dc] > forest[r][c])
				cnt = Math.max(dfs(dr, dc) + 1, cnt);
		}
		dp[r][c] = cnt;
		return cnt;
	}
}
