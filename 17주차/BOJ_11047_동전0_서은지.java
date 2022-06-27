package com.ssafy.study.day0625;

import java.util.*;
import java.io.*;

public class BOJ_11047_동전0_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		int cnt = 0;
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(in.readLine());
		for (int i = N - 1; i >= 0; i--) {
			if (K / arr[i] > 0) {
				cnt += K / arr[i];
				K %= arr[i];
				if (K == 0)
					break;
			}
		}
		System.out.println(cnt);
	}

}
