package com.ssafy.study.day0416;

import java.util.*;
import java.io.*;

public class BOJ_1764_듣보잡_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashSet<String> set = new HashSet<String>();
		for (int i = 0; i < N; i++)
			set.add(in.readLine());
		List<String> list = new ArrayList<String>();
		for (int j = 0; j < M; j++) {
			String str = in.readLine();
			if (set.contains(str))
				list.add(str);
		}
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		sb.append(list.size() + "\n");
		for (int i = 0; i < list.size(); i++)
			sb.append(list.get(i) + "\n");
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
		
		// 시간초과
//		List<String>[] words = new List[26];
//		List<String> list = new ArrayList<String>();
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < N; i++) {
//			String str = in.readLine();
//			if (words[str.charAt(0) - 'a'] == null)
//				words[str.charAt(0) - 'a'] = new ArrayList<String>();
//			words[str.charAt(0) - 'a'].add(str);
//		}
//
//		for (int i = 0; i < M; i++) {
//			String str = in.readLine();
//			if (words[str.charAt(0) - 'a'] != null) {
//				for (int j = 0; j < words[str.charAt(0) - 'a'].size(); j++) {
//					if (words[str.charAt(0) - 'a'].get(j).equals(str))
//						list.add(str);
//				}
//			}
//		}
//		Collections.sort(list);
//		sb.append(list.size() + "\n");
//		for (int i = 0; i < list.size(); i++)
//			sb.append(list.get(i) + "\n");
//		sb.setLength(sb.length() - 1);
//		System.out.println(sb);
	}

}
