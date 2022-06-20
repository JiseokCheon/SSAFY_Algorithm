package com.ssafy.study.day0617;

import java.util.*;
import java.io.*;

public class BOJ_2160_상어초등학교_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		int N = Integer.parseInt(in.readLine());
		int[][] classroom = new int[N + 1][N + 1];
		int[][] students = new int[N * N + 1][5];
		for (int i = 1; i <= N * N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < 5; j++)
				students[i][j] = Integer.parseInt(st.nextToken());
		}
		int[][] seats = new int[N * N + 1][2];
		boolean[] visited = new boolean[N * N + 1];
		classroom[2][2] = students[1][0];
		seats[students[1][0]] = new int[] { 2, 2 };
		visited[students[1][0]] = true;
		int sum = 0;
		for (int i = 2; i < students.length; i++) {
			List<int[]> tmp = new LinkedList<int[]>();
			for (int j = 1; j < students[0].length; j++) {
				if (visited[students[i][j]]) {
					int r = seats[students[i][j]][0];
					int c = seats[students[i][j]][1];
					for (int k = 0; k < delta.length; k++) {
						int dr = r + delta[k][0];
						int dc = c + delta[k][1];
						if (dr >= 1 && dc >= 1 && dr <= N && dc <= N && classroom[dr][dc] == 0) {
							int cnt = 0, zero = 0;
							for (int a = 0; a < delta.length; a++) {
								int ddr = dr + delta[a][0];
								int ddc = dc + delta[a][1];
								if (ddr >= 1 && ddc >= 1 && ddr <= N && ddc <= N) {
									if (classroom[ddr][ddc] == 0) {
										zero++;
										continue;
									}
									for (int b = 1; b < students[0].length; b++) {
										if (classroom[ddr][ddc] == students[i][b]) {
											cnt++;
											break;
										}
									}
								}
							}
							tmp.add(new int[] { dr, dc, cnt, zero });
						}
					}
				}
			}
			if (tmp.size() == 0) {
				List<int[]> temp = new LinkedList<int[]>();
				outer: for (int j = 1; j <= N; j++) {
					for (int k = 1; k <= N; k++) {
						if (classroom[j][k] == 0) {
							int cnt = 0;
							for (int a = 0; a < delta.length; a++) {
								int dr = j + delta[a][0];
								int dc = k + delta[a][1];
								if (dr >= 1 && dc >= 1 && dr <= N && dc <= N && classroom[dr][dc] == 0) {
									cnt++;
								}
							}
							temp.add(new int[] {j, k, cnt});
							if (cnt == 4)
								break outer;
						}
					}
				}
				Collections.sort(temp, new Comparator<int[]>() {
					@Override
					public int compare(int[] o1, int[] o2) {
						if (o2[2] - o1[2] == 0) {
							if (o1[0] - o2[0] == 0)
								return o1[1] - o2[1];
							return o1[0] - o2[0];
						}
						return o2[2] - o1[2];
					}
				});
				classroom[temp.get(0)[0]][temp.get(0)[1]] = students[i][0];
				seats[students[i][0]] = new int[] { temp.get(0)[0], temp.get(0)[1] };
				visited[students[i][0]] = true;
			} else {
				Collections.sort(tmp, new Comparator<int[]>() {
					@Override
					public int compare(int[] o1, int[] o2) {
						if (o2[2] - o1[2] == 0) {
							if (o2[3] - o1[3] == 0) {
								if (o1[0] - o2[0] == 0)
									return o1[1] - o2[1];
								return o1[0] - o2[0];
							}
							return o2[3] - o1[3];
						}
						return o2[2] - o1[2];
					}
				});
				classroom[tmp.get(0)[0]][tmp.get(0)[1]] = students[i][0];
				seats[students[i][0]][0] = tmp.get(0)[0];
				seats[students[i][0]][1] = tmp.get(0)[1];
				visited[students[i][0]] = true;
			}
		}
		int[][] sortedStds = new int[N * N + 1][4];
		for (int i = 1; i < students.length; i++) {
			for (int j = 0; j < sortedStds[0].length; j++) {
				sortedStds[students[i][0]][j] = students[i][j + 1];
			}
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int cnt = 0;
				for (int k = 0; k < delta.length; k++) {
					int dr = i + delta[k][0];
					int dc = j + delta[k][1];
					if (dr >= 1 && dc >= 1 && dr <= N && dc <= N) {
						for (int a = 0; a < sortedStds[0].length; a++) {
							if (classroom[dr][dc] == sortedStds[classroom[i][j]][a]) {
								cnt++;
								break;
							}
						}
					}
				}
				sum += cnt == 0 ? 0 : Math.pow(10, cnt - 1);
			}
		}
		System.out.println(sum);
	}

}
