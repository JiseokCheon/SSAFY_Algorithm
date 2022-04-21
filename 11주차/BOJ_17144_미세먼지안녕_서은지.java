package com.ssafy.study.day0420;

import java.util.*;
import java.io.*;

public class BOJ_17144_미세먼지안녕_서은지 {
	static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int R, C, K;
	static int[][] map;
	static int[][] next;
	static int[] machine;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		int idx = 0;
		machine = new int[2];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1)
					machine[idx++] = i;
			}
		}
		
		for (int i = 0; i < K; i++) {
			spread();
			cleanAir();
		}
		System.out.println(getSum());
	}
	
	public static void spread() {
		next = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] >= 5) {
					int cnt = 0;
					for (int d = 0; d < 4; d++) {
						int dr = i + delta[d][0];
						int dc = j + delta[d][1];
						if (dr >= 0 && dc >= 0 && dr < R && dc < C && map[dr][dc] != -1) {
							cnt++;
							next[dr][dc] += map[i][j] / 5;
						}
					}
					next[i][j] += map[i][j] - (map[i][j] / 5) * cnt;
				} else
					next[i][j] += map[i][j];
			}
		}
	}
	
	public static void cleanAir() {
		// 위쪽
		for (int i = machine[0] - 1; i > 0; i--)
			next[i][0] = next[i - 1][0];
		for (int i = 0; i < C - 1; i++)
			next[0][i] = next[0][i + 1];
		for (int i = 0; i < machine[0]; i++)
			next[i][C-1] = next[i+1][C-1];
		for (int i = C-1; i > 1; i--)
			next[machine[0]][i] = next[machine[0]][i-1];
		
		// 아래쪽
		for (int i = machine[1] + 1; i < R-1; i++)
			next[i][0] = next[i + 1][0];
		for (int i = 0; i < C-1; i++)
			next[R-1][i] = next[R-1][i+1];
		for (int i = R-1; i > machine[1]; i--)
			next[i][C-1] = next[i-1][C-1];
		for (int i = C-1; i > 1; i--)
			next[machine[1]][i] = next[machine[1]][i-1];
		
		next[machine[0]][1] = 0;
		next[machine[1]][1] = 0;
		next[machine[0]][0] = -1;
		next[machine[1]][0] = -1;
		
		map = next;
	}
	
	public static int getSum() {
		int sum = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++)
				if (map[i][j] > 0)
					sum += map[i][j];
		}
		return sum;
	}
	
	

}
