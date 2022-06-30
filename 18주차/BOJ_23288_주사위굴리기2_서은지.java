package com.ssafy.study.day0627;

import java.util.*;
import java.io.*;

public class BOJ_23288_주사위굴리기2_서은지 {
	static int N, M, K, answer;
	static int[][] map, score;
	static int[] dice;
	static int[] idx = {3, 4, 5};
	static boolean[][] visited;
	static int[][] delta = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static Queue<int[]> queue;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		score = new int[N][M];
		dice = new int[6];
		for (int i = 0; i < 6; i++)
			dice[i] = i;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		visited = new boolean[N][M];
		queue = new LinkedList<int[]>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				if (!visited[i][j]) {
					queue.clear();
					queue.offer(new int[] {i, j});
					visited[i][j] = true;
					dfs(i, j);
					int x = queue.size() * map[i][j];
					while (!queue.isEmpty())
						score[queue.peek()[0]][queue.poll()[1]] = x;
				}
		}
		
		int dir = 0;
		int r = 0, c = 0;
		for (int i = 0; i < K; i++) {
			int dr = r + delta[dir][0];
			int dc = c + delta[dir][1];
			if (dr < 0 || dc < 0 || dr >= N || dc >= M) {
				dir = dir < 2 ? dir + 2 : dir - 2;
				dr = r + delta[dir][0];
				dc = c + delta[dir][1];
			}
			r = dr;
			c = dc;
			moveDice(dir);
			if (dice[idx[2]] + 1 > map[r][c])
				dir = (dir + 1) % 4;
			else if (dice[idx[2]] + 1 < map[r][c])
				dir = (dir + 3) % 4;
			answer += score[r][c];
		}
		
		System.out.println(answer);
	}
	
	public static void dfs(int r, int c) {
		for (int i = 0; i < 4; i++) {
			int dr = r + delta[i][0];
			int dc = c + delta[i][1];
			if (dr >= 0 && dc >= 0 && dr < N && dc < M && map[r][c] == map[dr][dc] && !visited[dr][dc]) {
				visited[dr][dc] = true;
				queue.offer(new int[] {dr, dc});
				dfs(dr, dc);
			}
		}
	}
	
	public static void moveDice(int dir) {
		switch(dir) {
		case 0:
			swap(0, 2);
			break;
		case 1:
			swap(2, 1);
			break;
		case 2:
			swap(2, 0);
			break;
		case 3:
			swap(1, 2);
			break;
		}
	}
	
	public static void swap(int a, int b) {
		int tmp = idx[a];
		idx[a] = idx[b];
		idx[b] = 5 - tmp;
	}

}
