package com.ssafy.study.day0504;

import java.util.*;
import java.io.*;

public class BOJ_17822_원판돌리기_서은지 {
	static int N, M, T, total, cnt;
	static int[][] circle;
	static HashSet<String> set;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		circle = new int[N][M];
		cnt = N * M;
		total = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				circle[i][j] = Integer.parseInt(st.nextToken());
				total += circle[i][j];
			}
		}
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			rotate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) == 0 ? 1 : M - 1,
					Integer.parseInt(st.nextToken()));
			adjCheck();
		}
		System.out.println(total);
	}

	public static void rotate(int x, int d, int k) {
		int cur = x;
		while (cur <= N) {
			int[] tmp = new int[M];
			for (int i = 0; i < M; i++) {
				tmp[(i + d * k) % M] = circle[cur - 1][i];
			}
			circle[cur - 1] = tmp;
			cur += x;
		}
	}

	public static void adjCheck() {
		set = new HashSet<String>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M - 1; j++) {
				if (circle[i][j] != 0 && circle[i][j] == circle[i][j + 1]) {
					set.add(Integer.toString(i) + " " + Integer.toString(j));
					set.add(Integer.toString(i) + " " + Integer.toString(j + 1));
				}
			}
			if (circle[i][M - 1] != 0 && circle[i][M - 1] == circle[i][0]) {
				set.add(Integer.toString(i) + " " + Integer.toString(M - 1));
				set.add(Integer.toString(i) + " " + Integer.toString(0));
			}
		}

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N - 1; j++) {
				if (circle[j][i] != 0 && circle[j][i] == circle[j + 1][i]) {
					set.add(Integer.toString(j) + " " + Integer.toString(i));
					set.add(Integer.toString(j + 1) + " " + Integer.toString(i));
				}
			}
		}
		
		if (set.size() == 0) {
			float avg = (float) total / cnt;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (circle[i][j] == 0)
						continue;
					if (circle[i][j] > avg) {
						circle[i][j] -= 1;
						total--;
					} else if (circle[i][j] < avg) {
						circle[i][j] += 1;
						total++;
					}
				}
			}
			return;
		}

		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			StringTokenizer st = new StringTokenizer(it.next(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			total -= circle[r][c];
			cnt--;
			circle[r][c] = 0;
		}
	}
}
