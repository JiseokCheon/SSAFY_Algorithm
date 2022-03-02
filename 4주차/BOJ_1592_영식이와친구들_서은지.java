package com.ssafy.study;

import java.util.*;
import java.io.*;

public class BOJ_1592_영식이와친구들_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		int cnt = 0, idx = 0;
		while (true) {
			arr[idx]++;
			if (arr[idx] == M)
				break;
			cnt++;
			idx = arr[idx] % 2 == 0 ? (idx + N - L % N) % N : (idx + L) % N;
		}
		System.out.println(cnt);
	}
}
