package com.ssafy.study.day0509;

import java.util.*;
import java.io.*;

public class BOJ_20061_모노미노도미노2_서은지 {
	static int N, score;
	static boolean[][] blue, green;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		blue = new boolean[6][4];
		green = new boolean[6][4];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			dropBlock(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			checkScore(blue);
			checkScore(green);
			check01(blue);
			check01(green);
		}
		int cnt = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if (green[i][j])
					cnt++;
				if (blue[i][j])
					cnt++;
			}
		}
		System.out.println(score + "\n" + cnt);
	}

	public static void dropBlock(int t, int x, int y) {
		switch (t) {
		case 1:
			int r = 5, c = y;
			for (int i = 0; i < 6; i++) {
				if (green[i][c])
					break;
				r = i;
			}
			green[r][c] = true;
			r = 5;
			c = x;
			for (int i = 0; i < 6; i++) {
				if (blue[i][c])
					break;
				r = i;
			}
			blue[r][c] = true;
			break;
		case 2:
			r = 5;
			c = y;
			for (int i = 0; i < 6; i++) {
				if (green[i][c] || green[i][c + 1])
					break;
				r = i;
			}
			green[r][c] = true;
			green[r][c + 1] = true;
			r = 4;
			c = x;
			for (int i = 0; i < 5; i++) {
				if (blue[i][c] || blue[i + 1][c])
					break;
				r = i;
			}
			blue[r][c] = true;
			blue[r + 1][c] = true;
			break;
		case 3:
			r = 4;
			c = y;
			for (int i = 0; i < 5; i++) {
				if (green[i][c] || green[i+1][c])
					break;
				r = i;
			}
			green[r][c] = true;
			green[r+1][c] = true;
			r = 5;
			c = x;
			for (int i = 0; i < 6; i++) {
				if (blue[i][c] || blue[i][c+1])
					break;
				r = i;
			}
			blue[r][c] = true;
			blue[r][c+1] = true;
			break;
		}
	}

	public static void checkScore(boolean[][] map) {
		for (int i = 2; i < 6; i++) {
			int cnt = 0;
			for (int j = 0; j < 4; j++) {
				if (map[i][j])
					cnt++;
			}
			if (cnt == 4) {
				score++;
				Arrays.fill(map[i], false);
				for (int j = i - 1; j >= 0; j--) {
					for (int k = 0; k < 4; k++) {
						if (map[j][k]) {
							map[j+1][k] = true;
							map[j][k] = false;
						}
					}
				}
			}
		}
	}

	public static void check01(boolean[][] map) {
		for (int i = 0; i < 4; i++) {
			if (map[0][i]) {
				Arrays.fill(map[5], false);
				Arrays.fill(map[4], false);
				for (int j = 3; j >= 0; j--) {
					for (int k = 0; k < 4; k++) {
						if (map[j][k]) {
							map[j + 2][k] = true;
							map[j][k] = false;
						}
					}
				}
				return;
			}
		}
		for (int i = 0; i < 4; i++) {
			if (map[1][i]) {
				Arrays.fill(map[5], false);
				for (int j = 4; j >= 0; j--) {
					for (int k = 0; k < 4; k++) {
						if (map[j][k]) {
							map[j + 1][k] = true;
							map[j][k] = false;
						}
					}
				}
				return;
			}
		}
	}

}
