package com.ssafy.study.day0623;

import java.util.*;
import java.io.*;

public class BOJ_10026_적록색맹_서은지 {
	static int N;
	static char[][] picture;
	static boolean[][] noWeakness, hasWeakness;
	static List<Character> colors;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int cnt = 0, wCnt = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		picture = new char[N][N];
		for (int i = 0; i < N; i++)
			picture[i] = in.readLine().toCharArray();

		noWeakness = new boolean[N][N];
		hasWeakness = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!noWeakness[i][j]) {
					dfs(i, j);
					cnt++;
				}
				if (!hasWeakness[i][j]) {
					dfs_weakness(i, j);
					wCnt++;
				}
			}
		}
		System.out.println(cnt + " " + wCnt);

	}

	public static void dfs(int r, int c) {
		noWeakness[r][c] = true;

		for (int i = 0; i < delta.length; i++) {
			int dr = r + delta[i][0];
			int dc = c + delta[i][1];

			if (dr >= 0 && dc >= 0 && dr < N && dc < N && !noWeakness[dr][dc] && picture[dr][dc] == picture[r][c])
				dfs(dr, dc);
		}

	}
	
	public static void dfs_weakness(int r, int c) {
		hasWeakness[r][c] = true;
		
		for (int i = 0; i < delta.length; i++) {
			int dr = r + delta[i][0];
			int dc = c + delta[i][1];

			if (dr >= 0 && dc >= 0 && dr < N && dc < N && !hasWeakness[dr][dc] && (picture[dr][dc] == picture[r][c] || (picture[dr][dc] == 'G' && picture[r][c] == 'R') || (picture[dr][dc] == 'R' && picture[r][c] == 'G')))
				dfs_weakness(dr, dc);
		}
	}


}
