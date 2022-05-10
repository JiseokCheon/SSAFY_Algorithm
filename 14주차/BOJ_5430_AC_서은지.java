package com.ssafy.study.day0510;

import java.util.*;
import java.io.*;

public class BOJ_5430_AC_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			String p = in.readLine();
			int L = Integer.parseInt(in.readLine());
			String arr = in.readLine();
			StringTokenizer st = new StringTokenizer(arr, "[,]");
			Deque<Integer> dq = new LinkedList<Integer>();
			for (int i = 0; i < L; i++)
				dq.offer(Integer.parseInt(st.nextToken()));
			boolean isError = false;
			boolean isFirst = true;
			for (int i = 0; i < p.length(); i++) {
				if (p.charAt(i) == 'R')
					isFirst = !isFirst;
				else if (p.charAt(i) == 'D') {
					if (dq.size() == 0) {
						isError = true;
						break;
					}
					if (isFirst)
						dq.pollFirst();
					else
						dq.pollLast();
				}
			}
			if (isError)
				sb.append("error\n");
			else {
				if (dq.size() == 0)
					sb.append("[]\n");
				else {
					sb.append("[");
					if (isFirst) {
						while (!dq.isEmpty())
							sb.append(dq.pollFirst() + ",");
					} else {
						while (!dq.isEmpty())
							sb.append(dq.pollLast() + ",");
					}
					sb.setLength(sb.length() - 1);
					sb.append("]\n");
				}
			}
		}
		System.out.println(sb);
	}

}
