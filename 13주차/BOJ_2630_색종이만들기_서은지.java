package com.ssafy.study.day0505;

import java.util.*;
import java.io.*;

public class BOJ_2630_색종이만들기_서은지 {
	static int N, white, blue;
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		count(0, 0, N);
		System.out.println(white + "\n" + blue);
	}
	
	public static void count(int r, int c, int n) {
		for (int i = r; i < r + n; i++) {
			for (int j = c; j < c + n; j++)
				if (arr[i][j] != arr[r][c]) {
					count(r, c, n/2);
					count(r + n/2, c, n/2);
					count(r, c + n/2, n/2);
					count(r + n/2, c + n/2, n/2);
					return;
				}
		}
		if (arr[r][c] == 1)
			blue++;
		else
			white++;
	}
	
	

}
