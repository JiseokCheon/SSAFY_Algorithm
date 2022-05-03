package com.ssafy.study.day0503;

import java.util.*;
import java.io.*;

public class BOJ_2606_바이러스_서은지 {
	static int N, K, num;
	static int[][] connected;
	static boolean[] infected;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		K = Integer.parseInt(in.readLine());
		connected = new int[N][N];
		infected = new boolean[N];
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken()) - 1, to = Integer.parseInt(st.nextToken()) - 1;
			connected[from][to] = 1;
			connected[to][from] = 1;
		}
		dfs(0);
		System.out.println(num - 1);
	}
	
	public static void dfs (int idx) {
		if (infected[idx])
			return;
		infected[idx] = true;
		num++;
		for (int i = 0; i < N; i++) {
			if (connected[idx][i] == 1 && !infected[i])
				dfs(i);
		}
	}

}
