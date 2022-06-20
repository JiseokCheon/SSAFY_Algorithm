package com.ssafy.study.day0618;

import java.util.*;
import java.io.*;

public class BOJ_9375_패션왕신해빈 {
	static int n, answer;
	static HashMap<String, Integer> map;
	static String[] items;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(in.readLine());
			map = new HashMap<>();
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				st.nextToken();
				String key = st.nextToken();
				if (map.containsKey(key))
					map.replace(key, map.get(key) + 1);
				else
					map.put(key, 1);
			}
			answer = 1;
			for (String key : map.keySet()) {
				answer *= map.get(key) + 1;
			}
			sb.append(--answer + "\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);

	}
}
