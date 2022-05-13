package com.ssafy.study.day0512;

import java.io.*;

public class BOJ_5525_IOIOI_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int M = Integer.parseInt(in.readLine());
		String S = in.readLine();
		
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			if (S.charAt(i) == 'O')
				continue;
			int o = 0;
			while (i + 2 < M && S.charAt(i + 1) == 'O' && S.charAt(i + 2) == 'I') {
				o++;
				i += 2;
				if (o == N) {
					o--;
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}

}
