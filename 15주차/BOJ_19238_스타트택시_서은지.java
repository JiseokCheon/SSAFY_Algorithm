package com.ssafy.study.day0516;

import java.util.*;
import java.io.*;

public class BOJ_19238_스타트택시_서은지 {
	static int N, M, energy, r, c;
	static int[][] map;
	static int[] dist;
	static int[][] start, end;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		energy = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		start = new int[M][2];
		end = new int[M][2];
		dist = new int[M];
		st = new StringTokenizer(in.readLine(), " ");
		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			start[i] = new int[] { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1 };
			end[i] = new int[] { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1 };
			dist[i] = getDist(start[i], end[i]);
		}
		visited = new boolean[M];
		for (int i = 0; i < M; i++) {
			int[] next = nextDestination(new int[] { r, c });
			if (next[0] == -1 || energy < (next[1] + dist[next[0]])) {
				energy = -1;
				break;
			}
			energy -= next[1] + dist[next[0]];
			energy += 2 * dist[next[0]];
			r = end[next[0]][0];
			c = end[next[0]][1];
			visited[next[0]] = true;
		}
		System.out.println(energy);
	}

	public static int[] nextDestination(int[] before) {
		int min = Integer.MAX_VALUE;
		int idx = -1;
		for (int i = 0; i < M; i++) {
			if (!visited[i]) {
				int dist = getDist(before, start[i]);
				if (dist != -1) {
					if (dist == min) {
						if (start[idx][0] == start[i][0])
							idx = start[idx][1] > start[i][1] ? i : idx;
						idx = start[idx][0] > start[i][0] ? i : idx;
					} else if (dist < min) {
						min = dist;
						idx = i;
					}
				}
			}
		}
		return new int[] { idx, min };
	}

	public static int getDist(int[] start, int[] end) {
		// 바로 내리는 경우
		if (start[0] == end[0] && start[1] == end[1])
			return 0;
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> queue = new LinkedList<int[]>();
		visited[start[0]][start[1]] = true;
		queue.offer(start);
		int dist = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				int r = queue.peek()[0];
				int c = queue.poll()[1];

				for (int i = 0; i < 4; i++) {
					int dr = r + delta[i][0];
					int dc = c + delta[i][1];

					if (dr >= 0 && dc >= 0 && dr < N && dc < N && !visited[dr][dc] && map[dr][dc] != 1) {
						if (dr == end[0] && dc == end[1])
							return dist + 1;
						visited[dr][dc] = true;
						queue.offer(new int[] { dr, dc });
					}
				}
			}
			dist++;
		}
		return -1;
	}

}
