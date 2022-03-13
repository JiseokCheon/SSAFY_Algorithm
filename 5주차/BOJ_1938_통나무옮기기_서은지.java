package com.ssafy.study;

import java.util.*;
import java.io.*;

class Center {
	int r, c, d, cnt;

	public Center(int r, int c, int d, int cnt) {
		this.r = r;
		this.c = c;
		this.d = d; // 0이면 가로, 1이면 세로
		this.cnt = cnt;
	}
}

public class BOJ_1938_통나무옮기기_서은지 {
	static int N;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };
	static char[][] arr;
	static boolean[][][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		arr = new char[N][N];
		visited = new boolean[N][N][2];	// 방문 체크 배열(가로, 세로에 따라 방문여부 다르게 체크)
		int num = 0;
		int r = 0, c = 0, d = 0;	// 중심의 좌표 및 방향
		for (int i = 0; i < N; i++) {
			arr[i] = in.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 'B') {
					if (num == 1) {
						r = i;
						c = j;
						d = (j > 0 && arr[i][j - 1] == 'B') ? 0 : 1;
					}
					num++;
				}
			}
		}
		bfs(r, c, d, 0);
	}

	// 통나무가 E와 일치하는 최소 너비 구하기
	public static void bfs(int r, int c, int d, int cnt) {
		Queue<Center> queue = new LinkedList<>();	// 다음 중심의 이동을 담은 큐
		queue.offer(new Center(r, c, d, cnt));
		visited[r][c][d] = true;
		while (!queue.isEmpty()) {
			int qr = queue.peek().r;
			int qc = queue.peek().c;
			int qd = queue.peek().d;
			int qcnt = queue.peek().cnt;
			queue.poll();
			
			// 가로방향 & 모두 E와 일치하거나 세로방향 & 모두 E와 일치하면 너비 출력후 함수 종료
			if ((qd == 0 && qc - 1 >= 0 && qc + 1 < N && arr[qr][qc - 1] == 'E' && arr[qr][qc] == 'E'
					&& arr[qr][qc + 1] == 'E')
					|| (qd == 1 && qr - 1 >= 0 && qr + 1 < N && arr[qr - 1][qc] == 'E' && arr[qr][qc] == 'E'
							&& arr[qr + 1][qc] == 'E')) {
				System.out.println(qcnt);
				return;
			}

			// 회전 확인
			boolean isPossible = true;
			int nd = qd == 0 ? 1 : 0;	// 방향 전환
			if (!visited[qr][qc][nd]) {	// 방문하지않았을 때, 중심 기준 8방탐색해서 범위 밖이거나 1이면 회전 불가
				for (int i = 0; i < 8; i++) {
					int dr = qr + delta[i][0];
					int dc = qc + delta[i][1];
					if (dr < 0 || dc < 0 || dr >= N || dc >= N || arr[dr][dc] == '1') {
						isPossible = false;
						break;
					}
				}
			}

			// 방문하지않았고, 회전이 가능하다면 다음 방향 방문처리하고 큐에 삽입
			if (isPossible && !visited[qr][qc][nd]) {
				visited[qr][qc][nd] = true;
				queue.offer(new Center(qr, qc, nd, qcnt + 1));
			}

			// 상하좌우 이동 가능 여부 판단
			for (int i = 0; i < 4; i++) {
				int dr = qr + delta[i][0];
				int dc = qc + delta[i][1];
				// 중심이 이동할 좌표가 범위 밖이거나, 이미 방문했거나, 1이라면 이동 불가 -> continue
				if (dr < 0 || dc < 0 || dr >= N || dc >= N || arr[dr][dc] == '1' || visited[dr][dc][qd])
					continue;

				// 가로/세로방향이고, 통나무가 모두 범위 이내이면서 1이 아니라면 이동 가능하므로 방문처리하고 큐에 삽입
				if (qd == 0 && dc - 1 >= 0 && dc + 1 < N && arr[dr][dc - 1] != '1' && arr[dr][dc + 1] != '1') {
					visited[dr][dc][qd] = true;
					queue.offer(new Center(dr, dc, qd, qcnt + 1));
				} else if (qd == 1 && dr - 1 >= 0 && dr + 1 < N && arr[dr - 1][dc] != '1' && arr[dr + 1][dc] != '1') {
					visited[dr][dc][qd] = true;
					queue.offer(new Center(dr, dc, qd, qcnt + 1));
				}
			}
		}
		// 큐가 모두 비었는데도 함수 종료가 안됐다면 E로 갈 수 없음 -> 0출력
		System.out.println(0);
	}

}
