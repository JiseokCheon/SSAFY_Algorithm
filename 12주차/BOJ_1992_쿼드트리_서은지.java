package com.ssafy.study.day0426;

import java.io.*;

public class BOJ_1992_쿼드트리_서은지 {
	static int[][] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());
		arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			String str = in.readLine();
			for (int j = 0; j < N; j++)
				arr[i][j] = str.charAt(j) - '0';
		}
		divide(N, 0, 0);
		System.out.println(sb);
	}

	public static void divide(int N, int startR, int startC) {
		if (check(N, startR, startC)) {
			sb.append(arr[startR][startC]);
			return;
		}

		sb.append("(");
		divide(N / 2, startR, startC);
		divide(N / 2, startR, startC + N / 2);
		divide(N / 2, startR + N / 2, startC);
		divide(N / 2, startR + N / 2, startC + N / 2);
		sb.append(")");

	}

	public static boolean check(int N, int startR, int startC) {
		int tmp = arr[startR][startC];
		for (int i = startR; i < startR + N; i++) {
			for (int j = startC; j < startC + N; j++) {
				if (arr[i][j] != tmp)
					return false;
			}
		}
		return true;
	}

}
