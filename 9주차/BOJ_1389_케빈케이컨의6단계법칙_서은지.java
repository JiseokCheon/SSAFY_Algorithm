package com.ssafy.study.day0405;

import java.util.*;
import java.io.*;

public class BOJ_1389_케빈케이컨의6단계법칙_서은지 {

	// bfs
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] adjMatrix = new int[N][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			adjMatrix[a][b] = 1;
			adjMatrix[b][a] = 1;
		}
		
		int min = Integer.MAX_VALUE;
		int idx = 0;
		for (int i = 0; i < N; i++) {
			boolean[] visited = new boolean[N];
			Queue<Integer> queue = new LinkedList<Integer>();
			visited[i] = true;
			queue.offer(i);
			int cnt = 0;
			int dist = 0;
			while (!queue.isEmpty()) {
				int size = queue.size();
				for (int s = 0; s < size; s++) {
					int node = queue.poll();
					for (int j = 0; j < N; j++) {
						if (adjMatrix[node][j] == 1 && !visited[j]) {
							queue.offer(j);
							visited[j] = true;
							dist += cnt + 1;
						}
					}
				}
				cnt++;
			}
			if (dist < min) {
				min = dist;
				idx = i;
			}
		}
		System.out.println(idx + 1);
	}

	// 플로이드 와샬
	public static void main1(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] adjMatrix = new int[N][N];
		for (int i = 0; i < N; i++)
			Arrays.fill(adjMatrix[i], 30000);
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			adjMatrix[a][b] = 1;
			adjMatrix[b][a] = 1;
		}

		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if (i == k)
					continue;
				for (int j = 0; j < N; j++) {
					if (i == j || k == j)
						continue;
					if (adjMatrix[i][j] > adjMatrix[i][k] + adjMatrix[k][j])
						adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
				}
			}
		}
		int min = Integer.MAX_VALUE;
		int idx = 0;
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j < N; j++) {
				if (i == j)
					continue;
				sum += adjMatrix[i][j];
			}
			System.out.println(i + " " + sum);
			if (sum < min) {
				min = sum;
				idx = i;
			}
		}
		System.out.println(idx + 1);
	}

}
