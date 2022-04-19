package com.ssafy.study.day0419;

import java.util.*;
import java.io.*;

public class BOJ_1780_종이의개수_서은지 {
	static int[] cnt;
	static int[][] arr;
	static int[][] small;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		cnt = new int[3];
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		while (true) {
			if (N == 1) {
				if (arr[0][0] != 9)
					cnt[arr[0][0] + 1]++;
				break;
			}
			small = new int[N / 3][N / 3];
			for (int i = N - 3; i >= 0; i -= 3) {
				for (int j = N - 3; j >= 0; j -= 3)
					check(i, j);
			}
			arr = small;
			N /= 3;
		}
		System.out.println(cnt[0] + "\n" + cnt[1] + "\n" + cnt[2]);
	}

	public static void check(int r, int c) {
		int start = arr[r][c];
		int cnt0 = 0, cnt1 = 0, cnt2 = 0;
		for (int i = r; i < r + 3; i++) {
			for (int j = c; j < c + 3; j++) {
				if (arr[i][j] == -1)
					cnt0++;
				else if (arr[i][j] == 0)
					cnt1++;
				else if (arr[i][j] == 1)
					cnt2++;
			}
		}
		if (cnt0 == 9 || cnt1 == 9 || cnt2 == 9) {
			small[r / 3][c / 3] = start;
		} else {
			small[r / 3][c / 3] = 9;
			cnt[0] += cnt0;
			cnt[1] += cnt1;
			cnt[2] += cnt2;
		}
	}

}
