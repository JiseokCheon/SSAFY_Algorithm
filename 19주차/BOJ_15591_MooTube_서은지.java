package com.ssafy.day0707;

import java.util.*;
import java.io.*;

public class BOJ_15591_MooTube_서은지 {
	static int N, Q, cnt;
	static ArrayList<int[]>[] adj;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++)
			adj[i] = new ArrayList<int[]>();
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken()), to = Integer.parseInt(st.nextToken()), value = Integer.parseInt(st.nextToken());
			adj[from].add(new int[] {to, value});
			adj[to].add(new int[] {from, value});
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			cnt = 0;
			st = new StringTokenizer(in.readLine());
			bfs(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			sb.append(cnt + "\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	public static void bfs(int k, int v) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(v);
		boolean[] visited = new boolean[N + 1];
		visited[v] = true;
		
		while (!queue.isEmpty()) {
			int val = queue.poll();
			for (int j = 0; j < adj[val].size(); j++) {
				if (!visited[adj[val].get(j)[0]] && adj[val].get(j)[1] >= k) {
					cnt++;
					queue.offer(adj[val].get(j)[0]);
					visited[adj[val].get(j)[0]] = true;
				}
			}
		}
	}

}
