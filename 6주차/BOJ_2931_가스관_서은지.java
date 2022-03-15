package com.ssafy.study;

import java.util.*;
import java.io.*;

public class BOJ_2931_가스관_서은지 {
	static int[] check; // 글자에 따라 파이프가 연결되어있는지 확인해야 할 방향을 담은 배열
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int pr, pc, n;
	static char p;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] arr = new char[R][C];
		List<int[]> pipes = new ArrayList<int[]>(); // 파이프만 저장하는 리스트
		for (int i = 0; i < R; i++) {
			arr[i] = in.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (arr[i][j] != '.' && arr[i][j] != 'M' && arr[i][j] != 'Z') {
					pipes.add(new int[] { i, j });
				}
			}
		}
		n = 0;	// 연결이 끊긴 방향 수
		List<Integer> directions = new ArrayList<Integer>(); // 연결이 끊긴 곳에서 탐색해야 할 방향 리스트
		for (int i = 0; i < pipes.size(); i++) {
			switch (arr[pipes.get(i)[0]][pipes.get(i)[1]]) {
			case '|':
				check = new int[] { 0, 1 };
				break;
			case '-':
				check = new int[] { 2, 3 };
				break;
			case '+':
				check = new int[] { 0, 1, 2, 3 };
				break;
			case '1':
				check = new int[] { 1, 3 };
				break;
			case '2':
				check = new int[] { 0, 3 };
				break;
			case '3':
				check = new int[] { 0, 2 };
				break;
			case '4':
				check = new int[] { 1, 2 };
				break;
			}

			for (int j = 0; j < check.length; j++) {
				int dr = pipes.get(i)[0] + delta[check[j]][0];
				int dc = pipes.get(i)[1] + delta[check[j]][1];
				if (arr[dr][dc] == '.') {	// 나머지 M, Z, 파이프들은 해커가 지운 곳 빼고 모두 연결되어있음
					if (pr == 0 && pc == 0) {
						pr = dr;
						pc = dc;
					}
					n++;
					directions.add(check[j] < 2 ? (check[j] == 0 ? 1 : 0) : (check[j] == 2 ? 3 : 2));
					break;
				}
			}
		}

		// 연결이 끊긴 방향이 네곳이면 무조건 +
		if (n == 4)
			p = '+';
		else {
			// 아니라면 위에서 글자에 따라 체크 방향을 확인한 것과 반대로 리스트에 따라 글자 정보 얻음
			Collections.sort(directions);
			switch (directions.get(0)) {
			case 0:
				if (directions.get(1) == 1)
					p = '|';
				else if (directions.get(1) == 2)
					p = '3';
				else if (directions.get(1) == 3)
					p = '2';
				break;
			case 1:
				if (directions.get(1) == 2)
					p = '4';
				else if (directions.get(1) == 3)
					p = '1';
				break;
			case 2:
				p = '-';
				break;
			}
		}
		System.out.println((pr + 1) + " " + (pc + 1) + " " + p);	// 문제는 1, 1이 시작점이므로 1씩 더해줌

	}

}
