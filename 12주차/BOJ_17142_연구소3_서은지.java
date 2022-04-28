package com.ssafy.study.day0427;

import java.util.*;
import java.io.*;

public class BOJ_17142_연구소3_서은지 {
	static int N, M, min, space;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int[][] map;
	static List<int[]> virus;
	static boolean[] selected;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		min = Integer.MAX_VALUE;

		map = new int[N][N];
		int[][] dp = new int[N][N];
		virus = new ArrayList<int[]>();
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2)
					virus.add(new int[] { i, j });
				else if (map[i][j] == 1)
					dp[i][j] = -1;
				else
					space++;
			}
		}
		selected = new boolean[virus.size()];
		comb(0, 0);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	public static void comb(int current, int cnt) {
		if (cnt == M) {
			bfs();
			return;
		}
		for (int i = current; i < virus.size(); i++) {
			selected[i] = true;
			comb(i + 1, cnt + 1);
			selected[i] = false;
		}

	}

	public static void bfs() {
		Queue<int[]> queue = new LinkedList<int[]>();
		boolean[][] visited = new boolean[N][N];
		for (int i = 0; i < virus.size(); i++) {
			if (selected[i]) {
				queue.offer(virus.get(i));
				visited[virus.get(i)[0]][virus.get(i)[1]] = true;
			}
		}

		int time = 0, cnt = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				int r = queue.peek()[0];
				int c = queue.poll()[1];
				if (map[r][c] == 0)
					cnt++;
				if (cnt == space) {
					min = Math.min(min, time);
					return;
				}
				
				for (int i = 0; i < 4; i++) {
					int dr = r + delta[i][0];
					int dc = c + delta[i][1];
					if (dr >= 0 && dc >= 0 && dr < N && dc < N && !visited[dr][dc] && map[dr][dc] != 1) {
						visited[dr][dc] = true;
						queue.offer(new int[] {dr, dc});
					}
				}
			}
			time++;
		}
	}
}
