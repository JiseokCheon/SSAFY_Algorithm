package com.ssafy.study.day0411;

import java.util.*;
import java.io.*;

public class BOJ_15685_드래곤커브_서은지 {
	static int N;
	static int[][] matrix;
	static int[][] delta = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		matrix = new int[101][101];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			List<Integer> directions = new ArrayList<Integer>();	// 지금까지 방향 정보
			directions.add(d);
			matrix[y][x] = 1;
			matrix[y += delta[d][0]][x += delta[d][1]] = 1;	//	0세대
			for (int j = 1; j <= g; j++) {	// 1세대 ~ g세대
				int size = directions.size();
				for (int s = size - 1; s >= 0; s--) {	// 역순으로 새로운 방향 정보 추가
					d = (directions.get(s) + 1) % 4;
					matrix[y += delta[d][0]][x += delta[d][1]] = 1;
					directions.add(d);
				}
			}
		}

		// 정사각형 수 계산
		int cnt = 0;
		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				if (matrix[i][j] == 1 && i + 1 < 101 && j + 1 < 101 && matrix[i+1][j] == 1 && matrix[i][j+1] == 1 && matrix[i+1][j+1] == 1)
					cnt++;
			}
		}
		System.out.println(cnt);
	}
}
