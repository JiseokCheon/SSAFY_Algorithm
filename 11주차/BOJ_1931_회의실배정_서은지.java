package com.ssafy.study.day0423;

import java.util.*;
import java.io.*;

public class BOJ_1931_회의실배정_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] == o2[1])
					return o1[0] - o2[0];
				return o1[1] - o2[1];
			}
		});
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			pq.offer(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}
		int cnt = 0, cur = 0;
		while (!pq.isEmpty()) {
			if (pq.peek()[0] >= cur) {
				cur = pq.peek()[1];
				cnt++;
			}
			pq.poll();
		}
		System.out.println(cnt);
	}

}
