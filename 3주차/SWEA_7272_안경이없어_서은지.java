package com.ssafy.study;

import java.util.*;
import java.io.*;

public class SWEA_7272_안경이없어_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		int[] alphabet = new int[26];
		for (int i = 0; i < 26; i++) {
			switch((char)(i+'A')) {
			case 'A':
			case 'D':
			case 'O':
			case 'P':
			case 'Q':
			case 'R':
				alphabet[i] = 1;
				break;
			case 'B':
				alphabet[i] = 2;
				break;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		outer:
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			String str1 = st.nextToken(), str2 = st.nextToken();
			sb.append("#" + tc + " ");
			if (str1.length() != str2.length()) {
				sb.append("DIFF\n");
				continue outer;
			}
			
			for (int i = 0; i < str1.length(); i++) {
				if (alphabet[str1.charAt(i) - 'A'] != alphabet[str2.charAt(i) - 'A']) {
					sb.append("DIFF\n");
					continue outer;
				}
			}
			sb.append("SAME\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);

	}
}
