package com.ssafy.study.day0622;

import java.util.*;
import java.io.*;

public class BOJ_21610_마법사상어와비바라기 {
	static int[][] delta = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
	static int[][] cross = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
	static int N, M;
	static int[][] map;
	static Queue<int[]> queue;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		queue = new LinkedList<int[]>();
		queue.offer(new int[] {N - 1, 0});
		queue.offer(new int[] {N - 1, 1});
		queue.offer(new int[] {N - 2, 0});
		queue.offer(new int[] {N - 2, 1});
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			move(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
		}
		System.out.println(getSum());
	}
	
	public static void move(int d, int s) {
		for (int i = 0; i < queue.size(); i++) {
			int r = (queue.peek()[0] + N * s + delta[d][0] * s ) % N;
			int c = (queue.poll()[1] + N * s + delta[d][1] * s) % N;
			map[r][c]++;
			queue.offer(new int[] {r, c});
		}
		boolean[][] wasCloud = new boolean[N][N];
		while (!queue.isEmpty()) {
			for (int j = 0; j < 4; j++) {
				int dr = queue.peek()[0] + cross[j][0];
				int dc = queue.peek()[1] + cross[j][1];
				if (dr >= 0 && dc >= 0 && dr < N && dc < N && map[dr][dc] != 0)
					map[queue.peek()[0]][queue.peek()[1]]++;
			}
			wasCloud[queue.peek()[0]][queue.poll()[1]] = true;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] >= 2 && !wasCloud[i][j]) {
					queue.offer(new int[] {i, j});
					map[i][j] -= 2;
				}
			}
		}
	}
	
	public static int getSum() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				sum += map[i][j];
		}
		return sum;
	}

}
