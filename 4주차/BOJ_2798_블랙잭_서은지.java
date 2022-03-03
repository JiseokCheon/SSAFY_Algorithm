package com.ssafy.study;

import java.util.*;
import java.io.*;

public class BOJ_2798_블랙잭_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(in.readLine(), " ");
		int[] cards = new int[N];
		for (int i = 0; i < N; i++)
			cards[i] = Integer.parseInt(st.nextToken());

		int max = 0;

		for (int i = 0; i < N - 2; i++) {
//			if (cards[i] >= M)
//				continue;
			for (int j = i + 1; j < N - 1; j++) {
//				if (cards[i] + cards[j] >= M)
//					continue;
				for (int k = j + 1; k < N; k++) {
					int sum = cards[i] + cards[j] + cards[k];
					if (sum <= M && sum > max)
						max = sum;
				}
			}
		}
		System.out.println(max);
	}
}
