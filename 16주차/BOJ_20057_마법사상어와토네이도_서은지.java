package com.ssafy.study.day0613;

import java.util.*;
import java.io.*;

public class BOJ_20057_마법사상어와토네이도_서은지 {
	static int N, answer, cr, cc, sand;
	static int[][] A;
	static int[][] delta = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
	static int[][][] places = {{{-1, 0}, {1, 0}, {-2, -1}, {2, -1}, {0, -3}, {-1, -1}, {1, -1}, {-1, -2}, {1, -2}},
			{{0, -1}, {0, 1}, {1, -2}, {1, 2}, {3, 0}, {1, -1}, {1, 1}, {2, -1}, {2, 1}}, 
			{{-1, 0}, {1, 0}, {-2, 1}, {2, 1}, {0, 3}, {-1, 1}, {1, 1}, {-1, 2}, {1, 2}},
			{{0, -1}, {0, 1}, {-1, -2}, {-1, 2}, {-3, 0}, {-1, -1}, {-1, 1}, {-2, -1}, {-2, 1}}};
	static double[] ratio = {0.01, 0.01, 0.02, 0.02, 0.05, 0.07, 0.07, 0.1, 0.1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		A = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++)
				A[i][j] = Integer.parseInt(st.nextToken());
		}
		cr = cc = N/2;
		int changed = 0;
		int dir = 0;
		outer:
		while (true) {
			for (int i = 0; i <= changed / 2; i++) {
				if (cr == 0 && cc == 0)
					break outer;
				int yr = cr + delta[dir][0];
				int yc = cc + delta[dir][1];
				int sand = A[yr][yc];
				int next = sand;
				for (int j = 0; j < 9; j++) {
					if (sand == 0)
						break;
					int dr = cr + places[dir][j][0];
					int dc = cc + places[dir][j][1];
					int tmp = (int)(sand * ratio[j]);
					next -= tmp;
					if (dr >= N || dc >= N || dr < 0 || dc < 0) {
						answer += tmp;
						continue;
					}
					A[dr][dc] += tmp;
				}
				cr = yr;
				cc = yc;
				int ar = cr + delta[dir][0];
				int ac = cc + delta[dir][1];
				if (ar >= N || ac >= N || ar < 0 || ac < 0) {
					answer += next;
					continue;
				}
				A[cr + delta[dir][0]][cc + delta[dir][1]] += next;
			}
			dir = (dir + 1) % 4;
			changed++;
		}
		System.out.println(answer);
		
	}

}
