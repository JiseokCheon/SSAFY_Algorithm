package com.ssafy.study;

import java.util.*;
import java.io.*;

public class BOJ_2573_빙산_서은지 {
	static int N, M, year;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int[][] arr;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		List<int[]> iceberg = new ArrayList<int[]>();	// 빙산의 좌표와 값을 저장하는 리스트
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] != 0) {
					iceberg.add(new int[] { i, j, arr[i][j] });
				}
			}
		}

		List<int[]> next = new ArrayList<int[]>();	// 다음 빙산 정보를 저장할 리스트
		next.addAll(iceberg);	// iceberg 리스트 복제

		outer: while (true) {
			if (year > 0) {	//year이 0일 때는 이미 0이 아닌 빙산으로만 이루어져 있음
				// 배열 변경, 빙산의 높이가 0이면 리스트에서 삭제
				for (int i = 0; i < next.size(); i++) {
					arr[next.get(i)[0]][next.get(i)[1]] = next.get(i)[2];
					if (next.get(i)[2] == 0) {
						next.remove(i--);
					}
				}
				// 더 이상 녹을 빙산이 없으면 0 출력
				if (next.size() == 0) {
					System.out.println(0);
					break outer;
				}
				// 빙산 정보 업데이트
				iceberg.clear();
				iceberg.addAll(next);
			}

			visited = new boolean[N][M];

			// 빙산리스트의 크기와 dfs를 통해 구한 이어진 빙산의 수와 다르면 빙산이 분리된 것 -> year 출력
			if (iceberg.size() != dfs(iceberg.get(0)[0], iceberg.get(0)[1])) {
				System.out.println(year);
				break outer;
			}
			
			for (int i = 0; i < iceberg.size(); i++) {
				int cnt = 0;	// 4방탐색 시 높이가 0인 칸의 수
				for (int j = 0; j < delta.length; j++) {
					int dr = iceberg.get(i)[0] + delta[j][0];
					int dc = iceberg.get(i)[1] + delta[j][1];
					if (dr >= 0 && dr < N && dc >= 0 && dc < M && arr[dr][dc] == 0)
						cnt++;
				}
				// 4방탐색 후 높이가 0인 칸 수가 현재 위치의 빙산 높이보다 크거나 같으면 다음 빙산정보 0으로 저장
				if (cnt >= arr[iceberg.get(i)[0]][iceberg.get(i)[1]])
					next.get(i)[2] = 0;
				// 아니라면 0인 칸의 수만큼 감소
				else
					next.get(i)[2] -= cnt;
			}
			year++;
		}
	}

	// 이어진 빙산의 수를 계산하기 위한 dfs 함수
	static int dfs(int r, int c) {
		visited[r][c] = true;
		int cnt = 1;
		for (int i = 0; i < delta.length; i++) {
			int dr = r + delta[i][0];
			int dc = c + delta[i][1];
			if (dr >= 0 && dr < N && dc >= 0 && dc < M && arr[dr][dc] > 0 && !visited[dr][dc])
				cnt += dfs(dr, dc);
		}
		return cnt;
	}
}
