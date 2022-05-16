package com.ssafy.study.day0514;

import java.util.*;
import java.io.*;

public class BOJ_6064_카잉달력_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		outer: for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int M = Integer.parseInt(st.nextToken()), N = Integer.parseInt(st.nextToken()),
					x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
			int lcm = lcm(M, N);
			int n = 0;
			
			while(n * M < lcm) {
				if ((n * M + x - y) % N == 0) {
					sb.append(n * M + x + "\n");
					continue outer;
				}
				n++;
			}
			sb.append(-1 + "\n");
		}
		System.out.println(sb);
	}

	// 최대공약수
	public static int gcd(int a, int b) {
		while (b != 0) {
			int r = a % b;
			a = b;
			b = r;
		}
		return a;
	}

	// 최소공배수
	public static int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}

}
