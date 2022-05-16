package com.ssafy.study.day0517;

import java.util.*;
import java.io.*;

public class BOJ_7569_토마토_서은지 {
	static int[][][] tomatoes;
	static int N, M, H, unripe;
	static int[][] delta = { { 0, 0, -1 }, { 0, 0, 1 }, { 0, -1, 0 }, { 0, 1, 0 }, { -1, 0, 0 }, { 1, 0, 0 } };

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		tomatoes = new int[H][N][M];
		Queue<int[]> queue = new LinkedList<int[]>();
		boolean[][][] visited = new boolean[H][N][M];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(in.readLine());
				for (int k = 0; k < M; k++) {
					tomatoes[i][j][k] = Integer.parseInt(st.nextToken());
					if (tomatoes[i][j][k] == 0)
						unripe++;
					else if (tomatoes[i][j][k] == 1) {
						queue.offer(new int[] { i, j, k });
						visited[i][j][k] = true;
					}
				}
			}
		}

		if (unripe == 0)
			System.out.println(0);
		else {
			int time = 0;
			int ans = -1;
			outer: while (!queue.isEmpty()) {
				int size = queue.size();
				for (int s = 0; s < size; s++) {
					int[] tomato = queue.poll();
					for (int i = 0; i < 6; i++) {
						int dh = tomato[0] + delta[i][0];
						int dr = tomato[1] + delta[i][1];
						int dc = tomato[2] + delta[i][2];

						if (dr >= 0 && dc >= 0 && dh >= 0 && dr < N && dc < M && dh < H && !visited[dh][dr][dc]
								&& tomatoes[dh][dr][dc] != -1) {
							if (--unripe == 0) {
								ans = time + 1;
								break outer;
							}
							queue.offer(new int[] { dh, dr, dc });
							visited[dh][dr][dc] = true;
						}
					}
				}
				time++;
			}
			System.out.println(ans);
		}

	}

}
