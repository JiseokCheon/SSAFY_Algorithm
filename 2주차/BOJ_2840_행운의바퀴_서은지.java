package com.ssafy.study;

import java.util.*;
import java.io.*;

public class BOJ_2840_행운의바퀴_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		char[] wheel = new char[N];
		int current = 0;
		boolean isImpossible = false;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int S = Integer.parseInt(st.nextToken());
			char letter = st.nextToken().charAt(0);
			current = current < S ? (N + current) - S : current - S;
			if (wheel[current] != '\u0000' && wheel[current] != letter) {
				isImpossible = true;
				break;
			} else if (wheel[current] == '\u0000')
				wheel[current] = letter;
		}
		if (!isImpossible) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < N; i++)
				sb.append(wheel[(N + current + i) % N] == '\u0000' ? '?' : wheel[(N + current + i) % N]);
			System.out.println(sb);
		} else {
			System.out.println("!");
		}

	}

}
