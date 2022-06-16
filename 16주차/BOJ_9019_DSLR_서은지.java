package com.ssafy.study.day0614;

import java.util.*;
import java.io.*;

class Register {
	int num;
	String command;

	public Register(int num, String command) {
		this.num = num;
		this.command = command;
	}
}

public class BOJ_9019_DSLR_서은지 {
	static StringBuilder sb;
	static String[] dslr = {"D", "S", "L", "R"};

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			bfs(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}

	public static void bfs(int A, int B) {
		Queue<Register> queue = new LinkedList<Register>();
		queue.offer(new Register(A, ""));
		boolean[] visited = new boolean[10000];
		visited[A] = true;
		while (!queue.isEmpty()) {
			int num = queue.peek().num;
			String command = queue.poll().command;
			if (num == B) {
				sb.append(command + "\n");
				break;
			}
//			// D
//			int tmp = (2 * num) % 10000;
//			if (!visited[tmp]) {
//				queue.offer(new Register(tmp, command + "D"));
//				visited[tmp] = true;
//			}
//			// S
//			tmp = num == 0 ? 9999 : num - 1;
//			if (!visited[tmp]) {
//				queue.offer(new Register(tmp, command + "S"));
//				visited[tmp] = true;
//			}
//			
//			// L
//			tmp = (num % 1000) * 10 + num / 1000;
//			if (!visited[tmp]) {
//				queue.offer(new Register(tmp, command + "L"));
//				visited[tmp] = true;
//			}
//			
//			// R
//			tmp = (num % 10) * 1000 + num / 10;
//			if (!visited[tmp]) {
//				queue.offer(new Register(tmp, command + "R"));
//				visited[tmp] = true;
//			}
			int[] arr = {(2 * num) % 10000, num == 0 ? 9999 : num - 1, (num % 1000) * 10 + num / 1000, (num % 10) * 1000 + num / 10};
			for (int i = 0; i < 4; i++) {
				if (!visited[arr[i]]) {
					queue.offer(new Register(arr[i], command + dslr[i]));
					visited[arr[i]] = true;
				}
			}
//			String a = String.valueOf(num);
//			int len = a.length();
//			for (int i = 0; i < 4 - len; i++)
//				a = "0" + a;
//			a = String.valueOf(a.charAt(a.length() - 1)) + a + String.valueOf(a.charAt(0));
//			// L
//			tmp = Integer.parseInt(a.substring(2, a.length()));
//			if (!visited[tmp]) {
//				queue.offer(new Register(tmp, command + "L"));
//				visited[tmp] = true;
//			}
//			// R
//			tmp = Integer.parseInt(a.substring(0, a.length() - 2));
//			if (!visited[tmp]) {
//				queue.offer(new Register(tmp, command + "R"));
//				visited[tmp] = true;
//			}
		}

	}

}
