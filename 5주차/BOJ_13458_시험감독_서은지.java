package com.ssafy.study;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_13458_시험감독_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		String str = in.readLine();
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		long cnt = 0;
		st = new StringTokenizer(str, " ");
		for (int i = 0; i < N; i++) {
			int A = Integer.parseInt(st.nextToken());
			cnt++;
			if (A - B > 0)
				cnt += (A - B) % C == 0 ? (A - B) / C : (A - B) / C + 1;
		}
		System.out.println(cnt);
	}
}
