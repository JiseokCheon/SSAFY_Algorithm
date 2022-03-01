package com.ssafy.study;

import java.util.*;
import java.io.*;

public class BOJ_2804_크로스워드만들기_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		String A = st.nextToken(), B = st.nextToken();
		int r = 0, c = 0;
		int N = A.length(), M = B.length();
		char[][] arr = new char[M][N];
		outer:
		for (int i = 0; i < N; i++) {	// 동시에 포함되어 있는 글자가 여러개인 경우 A에서 제일 먼저 등장하는 글자를 선택
			for (int j = 0; j < M; j++) {
				if (A.charAt(i) == B.charAt(j)) {
					r = j;
					c = i;
					break outer;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (i == r) {	// 행 번호가 r과 같으면 바로 A 단어 추가
					sb.append(A);
					break;
				}
				if (j == c)
					sb.append(B.charAt(i));	// 현재 행에서 열 번호가 c와 같으면 B의 해당 열 번호 글자 추가
				else
					sb.append('.');
			}
			sb.append("\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}
