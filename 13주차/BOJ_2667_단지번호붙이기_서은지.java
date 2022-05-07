package com.ssafy.study.day0507;

import java.util.*;
import java.io.*;

public class BOJ_2667_단지번호붙이기_서은지 {
	static PriorityQueue<Integer> pq;
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String str = in.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
				if (map[i][j] == 0)
					visited[i][j] = true;
			}
		}
		pq = new PriorityQueue<Integer>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					visited[i][j] = true;
					pq.offer(dfs(i, j));
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(pq.size() + "\n");
		while (!pq.isEmpty())
			sb.append(pq.poll() + "\n");
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	public static int dfs(int r, int c) {
		int cnt = 1;
		for (int i = 0; i < 4; i++) {
			int dr = r + delta[i][0];
			int dc = c + delta[i][1];
			if (dr >= 0 && dc >= 0 && dr < N && dc < N && !visited[dr][dc]) {
				visited[dr][dc] = true;
				cnt += dfs(dr, dc);
			}
		}
		return cnt;
	}

}
