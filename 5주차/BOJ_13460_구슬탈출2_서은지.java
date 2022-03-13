package com.ssafy.study;

import java.util.*;
import java.io.*;

// 빨간구슬 좌표, 파란구슬 좌표, 이동횟수 저장 클래스
class Marbles {
	int rr, rc, br, bc, cnt;
	
	public Marbles(int rr, int rc, int br, int bc, int cnt) {
		this.rr = rr;
		this.rc = rc;
		this.br = br;
		this.bc = bc;
		this.cnt = cnt;
	}
}
public class BOJ_13460_구슬탈출2_서은지 {
	static int N, M, min = Integer.MAX_VALUE;
	static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][][][] visited;	// 빨간구슬 좌표, 파란구슬 좌표
	static char[][] board;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		visited = new boolean[N][M][N][M];	// 방문여부 체크(빨간구슬 좌표, 파란구슬 좌표)
		int rr = 0, rc = 0, br = 0, bc = 0;
		
		for (int i = 0; i < N; i++) {
			board[i] = in.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 'R') {
					rr = i;
					rc = j;
					board[i][j] = '.';	// 어차피 배열로 이동 확인할거니까 .으로
				} else if (board[i][j] == 'B') {
					br = i;
					bc = j;
					board[i][j] = '.';
				}
			}
		}
		
		Marbles marbles = new Marbles(rr, rc, br, bc, 0);
		
		// bfs 탐색
		Queue<Marbles> queue = new LinkedList<Marbles>();
		queue.offer(marbles);
		visited[marbles.rr][marbles.rc][marbles.br][marbles.bc] = true;
		outer:
		while (!queue.isEmpty()) {
			// 현재 구슬 정보 담는 tmp
			Marbles tmp = queue.poll();
			// 현재 구슬이 10 이상(+1을 반환하)이면 더이상 불가능
			if (tmp.cnt >= 10)
				break;
			// 4방 탐색
			for (int i = 0; i < delta.length; i++) {
				int drr = tmp.rr;
				int drc = tmp.rc;
				int dbr = tmp.br;
				int dbc = tmp.bc;
				
				boolean rflag = false, bflag = false;	// 구슬이 구멍에 들어갔는지 확인
				// 빨간구슬이 벽에 닿거나 구멍에 들어갈 때까지 이동
				while (board[drr + delta[i][0]][drc + delta[i][1]] != '#') {
					drr += delta[i][0];
					drc += delta[i][1];
					if (board[drr][drc] == 'O') {
						rflag = true;
						break;
					}
				}
				// 파란구슬도 똑같이 이동
				while (board[dbr + delta[i][0]][dbc + delta[i][1]] != '#') {
					dbr += delta[i][0];
					dbc += delta[i][1];
					if (board[dbr][dbc] == 'O') {
						bflag = true;
						break;
					}
				}
				
				// 파란구슬이 들어갔으면 현재 탐색 중단
				if (bflag)
					continue;
				// 빨간구슬만 들어갔으면 최소 여부 판단해서 min값 조정, 전체 탐색 중단
				if (rflag) {
					min = Math.min(min, tmp.cnt+1);
					break outer;
				}
				// 두 구슬이 같은 좌표에 있다면
				if (drr == dbr && drc == dbc) {
					// 각자 이동 수 구했을 때 더 작은 값이 먼저 도착
					int dr = Math.abs(drr - tmp.rr) + Math.abs(drc - tmp.rc);
					int db = Math.abs(dbr - tmp.br) + Math.abs(dbc - tmp.bc);
					
					// 파란 구슬이 먼저 도착한경우, 빨간 구슬의 움직임 하나 감소
					if (dr > db) {
						drr -= delta[i][0];
						drc -= delta[i][1];
					} else {	// 반대 경우
						dbr -= delta[i][0];
						dbc -= delta[i][1];
					}
				}
				
				// 방문체크
				if (!visited[drr][drc][dbr][dbc]) {
					visited[drr][drc][dbr][dbc] = true;
					queue.offer(new Marbles(drr, drc, dbr, dbc, tmp.cnt+1));
				}
			}
		}
		// min값이 변하지 않았다면 이동 불가 -> -1 출력, 아니라면 min 출력
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

}
