package com.ssafy.study.day0620;

import java.util.*;
import java.io.*;

public class BOJ_21609_상어중학교_서은지 {
	static int N, M, sum, rbMax;
	static int[][] board;
	static boolean[][] visited;
	static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Queue<int[]> max, tmp;
	static List<int[]> rainbow;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		rainbow = new ArrayList<int[]>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 0)
					rainbow.add(new int[] {i, j});
			}
		}
		while (true) {
			visited = new boolean[N][N];
			max = new LinkedList<int[]>();
			rbMax = 0;
			for (int i = 0; i < rainbow.size(); i++)
				visited[rainbow.get(i)[0]][rainbow.get(i)[1]] = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == -1 || board[i][j] == -2 || board[i][j] == 0 || visited[i][j])
						continue;
					tmp = new LinkedList<int[]>();
					visited[i][j] = true;
					tmp.offer(new int[] {i, j});
					dfs(i, j, board[i][j], 0);
				}
			}
//			System.out.println(max.size());
			if (max.size() < 2)
				break;
			sum += (int)Math.pow(max.size(), 2);
			while (!max.isEmpty()) {
				board[max.peek()[0]][max.poll()[1]] = -2;
			}
			gravity();
			turn();
			gravity();
		}
		System.out.println(sum);
	}
	
	public static void dfs(int r, int c, int color, int rbCnt) {
		for (int d = 0; d < 4; d++) {
			int dr = r + delta[d][0];
			int dc = c + delta[d][1];
			if (dr < 0 || dc < 0 || dr >= N || dc >= N || (board[dr][dc] != color && board[dr][dc] != 0) || visited[dr][dc])
				continue;
			visited[dr][dc] = true;
			tmp.offer(new int[] {dr, dc});
			if (board[dr][dc] == 0)
				dfs(dr, dc, color, rbCnt + 1);
			else
				dfs(dr, dc, color, rbCnt);
		}
		if (tmp.size() > max.size()) {
			max.clear();
			max.addAll(tmp);
			rbMax = rbCnt;
		} else if (tmp.size() == max.size()) {
			if (rbCnt >= rbMax) {
				max.clear();
				max.addAll(tmp);
				rbMax = rbCnt;
			}
		}
	}
	
	public static void gravity() {
		for (int i = 0; i < N; i++) {
			int cnt = 0;
			for (int j = N - 1; j >= 0; j--) {
				if (board[j][i] == -1)
					cnt = 0;
				else if (board[j][i] == -2)
					cnt++;
				else if (cnt != 0) {
					board[j + cnt][i] = board[j][i];
					board[j][i] = -2;
				}
			}
		}
	}
	
	public static void turn() {
		int[][] tmp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				tmp[N - 1 - j][i] = board[i][j]; 
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				board[i][j] = tmp[i][j];
		}
	}

}
