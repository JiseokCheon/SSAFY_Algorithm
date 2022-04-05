package com.ssafy.study.day0402;

import java.util.*;
import java.io.*;

public class BOJ_1107_리모컨_서은지 {
	static int N, M, ans;
	static String strN;
	static boolean[] broken;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		strN = in.readLine();
		N = Integer.parseInt(strN);
		M = Integer.parseInt(in.readLine());
		// 하나도 고장 안 났을 때(입력 없음)
		if (M == 0)
			ans = Math.min(strN.length(), Math.abs(N - 100));
		else {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			broken = new boolean[10];
			for (int i = 0; i < M; i++)
				broken[Integer.parseInt(st.nextToken())] = true;
			ans = Integer.MAX_VALUE;
			// 채널이 100일 때
			if (N == 100)
				ans = 0;
			// 모두 고장났을 때
			else if (M == 10)
				ans = Math.abs(N - 100);
			// 하나빼고 다 고장났을 때
			else if (M == 9) {
				int idx = 0;
				for (int i = 0; i < 10; i++) {
					if (!broken[i]) {
						idx = i;
						break;
					}
				}
				String same = "", less = "", more = "";
				for (int i = 0; i < strN.length() + 1; i++) {
					more += String.valueOf(idx);
					if (i >= strN.length())
						continue;
					same += String.valueOf(idx);
					if (i >= strN.length() - 1)
						continue;
					less += String.valueOf(idx);
				}
				if (less == "")
					ans = Math.min(Math.abs(N - 100),
							Math.min(same.length() + Math.abs(N - Integer.parseInt(same)), more.length() + Math.abs(N - Integer.parseInt(more))));
				else
					ans = Math.min(Math.abs(N - 100), Math.min(same.length() + Math.abs(N - Integer.parseInt(same)),
							Math.min(less.length() + Math.abs(N - Integer.parseInt(less)), more.length() + Math.abs(N - Integer.parseInt(more)))));
			}
			// 그 외
			else {
				// 100에서 증감버튼으로 이동하는 경우
				ans = Math.abs(N - 100);
				// 한 자릿 수 작은 값, 큰 값
				int min = Integer.MAX_VALUE, max = -1, zero = Integer.MAX_VALUE;
				if (!broken[0])
					zero = 0;
				for (int i = 1; i < 10; i++) {
					if (!broken[i]) {
						if (i < min)
							min = i;
						if (i > max)
							max = i;
					}
				}
				if (strN.length() - 1 > 0) {
					String num = "";
					for (int i = 0; i < strN.length() - 1; i++)
						num += String.valueOf(max);
					ans = Math.min(ans, strN.length() - 1 + N - Integer.parseInt(num));
				}
				String num = "";
				if (zero == 0) {
					num += min;
					for (int i = 0; i < strN.length(); i++)
						num += String.valueOf(0);
				} else {
					for (int i = 0; i < strN.length() + 1; i++)
						num += String.valueOf(min);
				}
				ans = Math.min(ans, strN.length() + 1 + Integer.parseInt(num) - N);
				// 같은 자릿수만큼의 값중에서 차가 제일 적은 것
				dfs("");
			}
		}
		System.out.println(ans);
	}

	public static void dfs(String str) {
		if (str.length() == strN.length()) {
			ans = Math.min(ans, str.length() + Math.abs(Integer.parseInt(str) - N));
			return;
		}
		for (int i = 0; i < 10; i++) {
			if (!broken[i])
				dfs(str + String.valueOf(i));
		}
	}

}
