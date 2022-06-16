package com.ssafy.study.day0615;

import java.util.*;
import java.io.*;

public class BOJ_20058_마법사상어와파이어스톰_서은지 {
	static int N, Q, sum, maxCnt;
	static int[][] ice;
	static boolean[][] visited;
	static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = (int)Math.pow(2, Integer.parseInt(st.nextToken()));
		Q = Integer.parseInt(st.nextToken());
		ice = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++)
				ice[i][j] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < Q; i++) {
			rotate((int)Math.pow(2, Integer.parseInt(st.nextToken())));
			iceBreak();
		}
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (ice[i][j] != 0 && !visited[i][j]) {
					int tmp = dfs(i, j, 1);
					if (tmp > maxCnt)
						maxCnt = tmp;
				}
			}
		}
		System.out.println(sum + "\n" + maxCnt);
	}
	
	public static void rotate(int L) {
		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i += L) {
			for (int j = 0; j < N; j += L) {
				for (int r = 0; r < L; r++) {
					for (int c = 0; c < L; c++)
						temp[j + c][L - 1 - r + i] = ice[j + r][i + c];
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				ice[i][j] = temp[i][j];
		}
	}
	
	public static void iceBreak() {
		List<int[]> toBreak = new ArrayList<int[]>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (ice[i][j] == 0)
					continue;
				int cnt = 0;
				for (int k = 0; k < 4; k++) {
					int dr = i + delta[k][0];
					int dc = j + delta[k][1];
					if (dr < 0 || dc < 0 || dr >= N || dc >= N || ice[dr][dc] == 0)
						cnt++;
				}
				if (cnt > 1)
					toBreak.add(new int[] {i, j});
			}
		}
		for (int i = 0; i < toBreak.size(); i++)
			ice[toBreak.get(i)[0]][toBreak.get(i)[1]]--;
	}
	
	public static int dfs(int r, int c, int cnt) {
		visited[r][c] = true;
		sum += ice[r][c];
		for (int i = 0; i < 4; i++) {
			int dr = r + delta[i][0];
			int dc = c + delta[i][1];
			if (dr >= 0 && dc >= 0 && dr < N && dc < N && !visited[dr][dc] && ice[dr][dc] != 0)
				cnt = dfs(dr, dc, cnt + 1);
		}
		return cnt;
	}

}
