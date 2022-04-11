package com.ssafy.study.day0408;

import java.util.*;
import java.io.*;

public class BOJ_1620_나는야포켓몬마스터이다솜_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Map<String, Integer> StrInt = new HashMap<String, Integer>();
		Map<Integer, String> IntStr = new HashMap<Integer, String>();
		for (int i = 1; i <= N; i++) {
			String str = in.readLine();
			StrInt.put(str, i);
			IntStr.put(i, str);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			String input = in.readLine();
			if (input.charAt(0) >= 'A' && input.charAt(0) <= 'Z')
				sb.append(StrInt.get(input) + "\n");
			else
				sb.append(IntStr.get(Integer.parseInt(input)) + "\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}

}
