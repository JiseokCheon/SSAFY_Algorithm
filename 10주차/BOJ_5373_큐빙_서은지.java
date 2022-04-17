package com.ssafy.study.day0415;

import java.util.*;
import java.io.*;

public class BOJ_5373_큐빙_서은지 {
	static int[][][] cube; // 돌릴 큐브
	static int[][][] line = { { { 0, 0 }, { 0, 1 }, { 0, 2 } }, { { 0, 2 }, { 1, 2 }, { 2, 2 } },
			{ { 2, 0 }, { 2, 1 }, { 2, 2 } }, { { 0, 0 }, { 1, 0 }, { 2, 0 } }, { { 0, 2 }, { 0, 1 }, { 0, 0 } },
			{ { 2, 2 }, { 1, 2 }, { 0, 2 } }, { { 2, 2 }, { 2, 1 }, { 2, 0 } }, { { 2, 0 }, { 1, 0 }, { 0, 0 } } };
	static int[][][] connection; // [현재 면][북동남서(시계)][연결 된 면 번호, 라인 인덱스]
	static char[] color = { 'w', 'y', 'r', 'o', 'g', 'b' }; // 각 번호 색

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			connection = new int[6][4][2];
			cube = new int[6][3][3];
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++)
					Arrays.fill(cube[i][j], i);
			}
			connection[0] = new int[][] { { 3, 4 }, { 5, 4 }, { 2, 4 }, { 4, 4 } };
			connection[1] = new int[][] { { 2, 2 }, { 5, 2 }, { 3, 2 }, { 4, 2 } };
			connection[2] = new int[][] { { 0, 2 }, { 5, 3 }, { 1, 4 }, { 4, 5 } };
			connection[3] = new int[][] { { 0, 4 }, { 4, 3 }, { 1, 2 }, { 5, 5 } };
			connection[4] = new int[][] { { 0, 3 }, { 2, 3 }, { 1, 3 }, { 3, 5 } };
			connection[5] = new int[][] { { 0, 5 }, { 3, 3 }, { 1, 5 }, { 2, 5 } };
			int n = Integer.parseInt(in.readLine());
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int i = 0; i < n; i++) {
				String str = st.nextToken();
				char tmp = str.charAt(0);
				int idx = 0;
				int d = str.charAt(1) == '+' ? 3 : 1;
				switch (tmp) {
				case 'U':
					idx = 0;
					break;
				case 'D':
					idx = 1;
					break;
				case 'F':
					idx = 2;
					break;
				case 'B':
					idx = 3;
					break;
				case 'L':
					idx = 4;
					break;
				case 'R':
					idx = 5;
					break;
				}
				cubing(idx, d);
			}
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++)
					sb.append(color[cube[0][i][j]]);
				sb.append("\n");
			}
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}

	public static void cubing(int idx, int d) {
		// 연결된 라인 돌리기
		int[] tmp = new int[3];
		for (int i = 0; i < 3; i++)
			tmp[i] = cube[connection[idx][0][0]][line[connection[idx][0][1]][i][0]][line[connection[idx][0][1]][i][1]];

		if (d == 1) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					cube[connection[idx][i][0]][line[connection[idx][i][1]][j][0]][line[connection[idx][i][1]][j][1]] = cube[connection[idx][(i
							+ d)
							% 4][0]][line[connection[idx][(i + d) % 4][1]][j][0]][line[connection[idx][(i + d)
									% 4][1]][j][1]];
				}
			}

			for (int i = 0; i < 3; i++)
				cube[connection[idx][3][0]][line[connection[idx][3][1]][i][0]][line[connection[idx][3][1]][i][1]] = tmp[i];
		} else {
			for (int i = 4; i > 1; i--) {
				for (int j = 0; j < 3; j++)
					cube[connection[idx][i % 4][0]][line[connection[idx][i % 4][1]][j][0]][line[connection[idx][i
							% 4][1]][j][1]] = cube[connection[idx][(i + d)
									% 4][0]][line[connection[idx][(i + d) % 4][1]][j][0]][line[connection[idx][(i + d)
											% 4][1]][j][1]];
			}

			for (int i = 0; i < 3; i++)
				cube[connection[idx][1][0]][line[connection[idx][1][1]][i][0]][line[connection[idx][1][1]][i][1]] = tmp[i];
		}

		// 면 돌리기
		int[] temp = { cube[idx][0][0], cube[idx][0][1], cube[idx][0][2], cube[idx][1][2], cube[idx][2][2],
				cube[idx][2][1], cube[idx][2][0], cube[idx][1][0] };
		int turn = d == 1 ? 2 : 6;
		for (int i = 0; i < 3; i++) {
			cube[idx][0][i] = temp[(turn + i) % 8];
			cube[idx][i][2] = temp[(turn + 2 + i) % 8];
			cube[idx][2][2 - i] = temp[(turn + 4 + i) % 8];
			cube[idx][2 - i][0] = temp[(turn + 6 + i) % 8];
		}

	}

}
