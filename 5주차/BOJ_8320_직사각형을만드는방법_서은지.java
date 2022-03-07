package com.ssafy.study;

import java.io.*;

public class BOJ_8320_직사각형을만드는방법_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int cnt = N;
		int n = 2;
		while (N / n > n - 1) {
			cnt += N / n - (n - 1);
			n++;
		}
		System.out.println(cnt);
	}
}
