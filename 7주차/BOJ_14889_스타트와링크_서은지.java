package com.ssafy.study;

import java.util.*;
import java.io.*;

public class BOJ_14889_스타트와링크_서은지 {
	static int N, min;
	static int[][] arr;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		arr = new int[N][N];
		min = Integer.MAX_VALUE;
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		comb(0, 0);
		System.out.println(min);
	}

	// 두 개의 팀을 만드는 조합
	public static void comb(int cnt, int current) {
		if (cnt == N / 2) {
			int sT = 0, lT = 0;
			// 각 팀의 합을 구하는 조합
			for (int i = 0; i < N-1; i++) {
				for (int j = i+1; j < N; j++) {
					if (visited[i] && visited[j])
						sT += arr[i][j] + arr[j][i];
					else if (!visited[i] && !visited[j])
						lT += arr[i][j] + arr[j][i];
				}
			}
			min = Math.min(min, Math.abs(sT - lT));
			return;
		}

		for (int i = current; i < N; i++) {
			visited[i] = true;
			comb(cnt + 1, i + 1);
			visited[i] = false;
		}
	}

}
