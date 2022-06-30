package com.ssafy.study.day0630;

import java.util.*;
import java.io.*;

public class BOJ_11286_절댓값힙_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				int r = Math.abs(o1) - Math.abs(o2);
				return r == 0 ? o1 - o2 : r;
			}
		});
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
