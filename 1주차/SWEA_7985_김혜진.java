package com.ssafy.im;

import java.io.*;
import java.util.*;

public class D3_7985_RootedBinaryTree재구성 {
	static String src = "2\r\n" + "3\r\n" + "3 2 7 5 6 1 4\r\n" + "2\r\n" + "2 1 3";
	static int[] tree, answer;
	static int K, N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			K = Integer.parseInt(br.readLine()); // 높이
			N = (int) Math.pow(2, K) - 1;// 노드의개수 2의k제곱-1

			tree = new int[N]; // 노드의 개수가 N개인 배열 생성(중위순회)
			answer = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < tree.length; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
			}

			inOrder(0, N - 1, 1);

			System.out.print("#" + tc + " ");
			int cnt = 0;
			
			for(int i=0; i<K; i++) {
				for(int j=0; j<Math.pow(2, i); j++) {
					System.out.print(answer[cnt++] + " ");
				}
				System.out.println();
			}
			
		}
	}

	public static void inOrder(int s, int e, int num) {
		// 기저조건
		if (s < 0 || s > N || e < 0 || e > N || num > N)
			return;

		int root = (s + e) / 2;
		answer[num - 1] = tree[root]; // answer[0] 은 항상 루트노드
		// 자식노드 없음 -> 리프노드 (리턴)
		if (s == e)
			return;
		inOrder(s, root - 1, num * 2); // 왼쪽자식
		inOrder(root + 1, e, num * 2 + 1); // 오른쪽자식

	}

}
