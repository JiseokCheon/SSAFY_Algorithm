package com.ssafy.study.day0628;

import java.util.*;
import java.io.*;

public class BOJ_11279_최대힙_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(in.readLine());
			if (x == 0)
				sb.append((pq.isEmpty() ? 0 : pq.poll()) + "\n");
			else
				pq.offer(x);
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}
