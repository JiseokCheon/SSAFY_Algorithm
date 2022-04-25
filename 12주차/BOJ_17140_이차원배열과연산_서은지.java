package com.ssafy.study.day0425;

import java.util.*;
import java.io.*;

public class BOJ_17140_이차원배열과연산_서은지 {
	static int r, c, k, R = 3, C = 3;
	static int[][] A;
	static int[] num;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;
		k = Integer.parseInt(st.nextToken());
		A = new int[100][100];
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < 3; j++)
				A[i][j] = Integer.parseInt(st.nextToken());
		}
		int time = 0;
		while (time <= 100) {
			if (A[r][c] == k)
				break;
			if (++time > 100)
				break;
			if (R >= C)
				sortArr_R();
			else
				sortArr_C();
		}
		System.out.println(time > 100 ? -1 : time);

	}

	public static void sortArr_R() {
		for (int i = 0; i < R; i++) {
			num = new int[101];
			HashSet<Integer> set = new HashSet<Integer>();
			for (int j = 0; j < C; j++) {
				if (A[i][j] == 0)
					continue;
				if (num[A[i][j]]++ == 0)
					set.add(A[i][j]);
				A[i][j] = 0;
			}
			PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if (o1[1] == o2[1])
						return o1[0] - o2[0];
					return o1[1] - o2[1];
				}
			});
			Iterator<Integer> it = set.iterator();
			while (it.hasNext()) {
				int number = it.next();
				pq.offer(new int[] { number, num[number] });
			}
			int tmpC = pq.size() * 2 > 100 ? 100 : pq.size() * 2;
			C = Math.max(C, tmpC);
			for (int j = 0; j < tmpC / 2; j++) {
				A[i][2 * j] = pq.peek()[0];
				A[i][2 * j + 1] = pq.poll()[1];
			}
		}

	}

	public static void sortArr_C() {
		for (int i = 0; i < C; i++) {
			num = new int[101];
			HashSet<Integer> set = new HashSet<Integer>();
			for (int j = 0; j < R; j++) {
				if (A[j][i] == 0)
					continue;
				if (num[A[j][i]]++ == 0)
					set.add(A[j][i]);
				A[j][i] = 0;
			}
			PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if (o1[1] == o2[1])
						return o1[0] - o2[0];
					return o1[1] - o2[1];
				}
			});
			Iterator<Integer> it = set.iterator();
			while (it.hasNext()) {
				int number = it.next();
				pq.offer(new int[] { number, num[number] });
			}
			int tmpR = pq.size() * 2 > 100 ? 100 : pq.size() * 2;
			R = Math.max(R, tmpR);
			for (int j = 0; j < tmpR / 2; j++) {
				A[2 * j][i] = pq.peek()[0];
				A[2 * j + 1][i] = pq.poll()[1];
			}
		}
	}

}
