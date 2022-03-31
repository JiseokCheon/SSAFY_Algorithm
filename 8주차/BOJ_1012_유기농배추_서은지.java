package com.ssafy.study;

import java.io.*;
import java.util.*;

public class BOJ_1012_유기농배추_서은지 {
	static int N, M, K;
	static int[][] map;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			int[][] k = new int[K][2];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				k[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
				map[k[i][0]][k[i][1]] = 1;
			}
			int cnt = 0;
			for (int i = 0; i < K; i++) {
				if (map[k[i][0]][k[i][1]] == 1) {
					cnt++;
					dfs(k[i][0], k[i][1]);
				}
			}
			sb.append(cnt + "\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}

	public static void dfs(int r, int c) {
		map[r][c] = 0;
		for (int i = 0; i < delta.length; i++) {
			int dr = r + delta[i][0];
			int dc = c + delta[i][1];

			if (dr >= 0 && dc >= 0 && dr < N && dc < M && map[dr][dc] == 1)
				dfs(dr, dc);
		}
	}

}
