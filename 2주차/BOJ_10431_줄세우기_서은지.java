package com.ssafy.study;

import java.util.*;
import java.io.*;

public class BOJ_10431_줄세우기_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int P = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= P; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int T = Integer.parseInt(st.nextToken());
			int cnt = 0;
			List<Integer> students = new LinkedList<Integer>();
			for (int i = 0; i < 20; i++) {
				int student = Integer.parseInt(st.nextToken());
				int idx = i;
				for (int j = 0; j < students.size(); j++) {
					if (student < students.get(j)) {
						idx = j;	// 들어갈 위치
						cnt += i - idx;	// 뒤로 물러난 사람 수
						break;
					}
				}
				students.add(idx, student);	// 해당 위치에 추가
			}
			sb.append(T + " " + cnt + "\n");
		}
		System.out.println(sb);
	}

}

