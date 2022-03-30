package com.ssafy.study;

import java.util.*;
import java.io.*;

public class BOJ_14503_로봇청소기_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(in.readLine(), " ");
		int r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken()),
				d = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		int cnt = 0;
		boolean flag = true;	// 현재 지역이 청소할 수 있는 지역인지
		while (true) {
			if (flag) {	// 현재 지역 청소
				map[r][c] = 2;
				cnt++;
				flag = false;
			}
			for (int i = 3; i >= 0; i--) {	// 왼쪽방향부터 4방탐색
				int dr = r + delta[(d + i) % 4][0];
				int dc = c + delta[(d + i) % 4][1];
				if (dr >= 0 && dc >= 0 && dr < N && dc < M && map[dr][dc] == 0) {	// 범위 내이고, 청소할 수 있는 곳이면 방향을 바꾸고, 플래그 설정, 그쪽 방향으로 이동
					r = dr;
					c = dc;
					d = (d + i) % 4;
					flag = true;
					break;
				}
			}
			if (!flag) {	// 4방 모두 청소 불가능하면 후진 가능한지(벽이 아닌지) 확인 후 벽이 아니면 후진, 벽이면 이동 불가
				int dr = r + delta[(d + 2) % 4][0];
				int dc = c + delta[(d + 2) % 4][1];
				if (dr >= 0 && dc >= 0 && dr < N && dc < M && map[dr][dc] != 1) {
					r = dr;
					c = dc;
				} else
					break;
			}
		}
		System.out.println(cnt);
	}

}
