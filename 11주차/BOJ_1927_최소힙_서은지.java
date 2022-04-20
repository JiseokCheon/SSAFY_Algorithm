package com.ssafy.study.day0421;

import java.util.*;
import java.io.*;

public class BOJ_1927_최소힙_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		StringBuilder sb = new StringBuilder();
		int x;
		for (int i = 0; i < N; i++) {
			if ((x = Integer.parseInt(in.readLine())) == 0) {
				if (pq.isEmpty())
					sb.append(0+"\n");
				else
					sb.append(pq.poll() + "\n");
			} else
				pq.offer(x);
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}

}
