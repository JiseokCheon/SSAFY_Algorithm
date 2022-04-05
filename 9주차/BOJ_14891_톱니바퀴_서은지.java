package com.ssafy.study.day0404;

import java.util.*;
import java.io.*;

public class BOJ_14891_톱니바퀴_서은지 {
	static int[][] gear;
	static int[] index;	// 12시 방향 인덱스

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		gear = new int[4][8];
		for (int i = 0; i < 4; i++) {
			String str = in.readLine();
			for (int j = 0; j < 8; j++)
				gear[i][j] = str.charAt(j) - '0';
		}

		index = new int[4];
		int K = Integer.parseInt(in.readLine());
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			rotate(Integer.parseInt(st.nextToken()) - 1, -Integer.parseInt(st.nextToken()));
		}

		int sum = 0;
		for (int i = 0; i < 4; i++) {
			if (gear[i][index[i]] == 1)
				sum += Math.pow(2, i);
		}
		System.out.println(sum);
	}

	public static void rotate(int num, int d) {
		// 왼쪽
		if (num - 1 >= 0 && gear[num][(index[num] + 6) % 8] != gear[num - 1][(index[num - 1] + 2) % 8])
			rotate_left(num - 1, -d);
		// 오른쪽
		if (num + 1 < 4 && gear[num][(index[num] + 2) % 8] != gear[num + 1][(index[num + 1] + 6) % 8])
			rotate_right(num + 1, -d);
		// 회전
		index[num] = (index[num] + d + 8) % 8;
	}

	public static void rotate_left(int num, int d) {
		if (num - 1 >= 0 && gear[num][(index[num] + 6) % 8] != gear[num - 1][(index[num - 1] + 2) % 8])
			rotate_left(num - 1, -d);
		index[num] = (index[num] + d + 8) % 8;
	}

	public static void rotate_right(int num, int d) {
		if (num + 1 < 4 && gear[num][(index[num] + 2) % 8] != gear[num + 1][(index[num + 1] + 6) % 8])
			rotate_right(num + 1, -d);
		index[num] = (index[num] + d + 8) % 8;
	}

}
