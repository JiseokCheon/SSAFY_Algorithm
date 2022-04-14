package com.ssafy.study.day0414;

import java.util.*;
import java.io.*;

public class BOJ_1697_숨바꼭질_서은지 {
	static boolean[] visited;
	static Queue<Integer> queue;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		visited = new boolean[100001];
		queue = new LinkedList<Integer>();
		queue.offer(N);
		visited[N] = true;
		int time = 0;
		outer: while (!queue.isEmpty()) {
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				int cur = queue.poll();
				if (cur == K) {
					System.out.println(time);
					break outer;
				}
				check(2 * cur);
				check(cur + 1);
				check(cur - 1);
			}
			time++;
		}
	}
	
	public static void check(int n) {
		if (n >= 0 && n <= 100000 && !visited[n]) {
			queue.offer(n);
			visited[n] = true;
		}
	}
}
