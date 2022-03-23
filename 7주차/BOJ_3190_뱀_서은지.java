package com.ssafy.study;

import java.util.*;
import java.io.*;

public class BOJ_3190_뱀_서은지 {
	static int N, time, hr, hc, d;
	static int[][] delta = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } }; // 위쪽방향부터 반시계(방향 계산)
	static int[][] board;
	static Queue<int[]> tail;
	

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		board = new int[N][N];
		int K = Integer.parseInt(in.readLine());
		StringTokenizer st;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			board[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1; // 인덱스 0부터, 사과는 1
		}

		int L = Integer.parseInt(in.readLine());
		int[][] moves = new int[L][2];
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			moves[i][0] = Integer.parseInt(st.nextToken());
			moves[i][1] = st.nextToken().equals("D") ? 3 : 1; // D면 오른쪽, L이면 왼쪽
		}

		time = 0; d = 3; hr = 0; hc = 0; // (0, 0), 오른쪽 방향부터 시작
		int interval = moves[0][0]; // 처음 방향 전환 전까지
		board[0][0] = 2; // 뱀이 있는 곳 2
		tail = new LinkedList<int[]>(); // 뱀이 움직일때마다 좌표 큐에 저장하고 꼬리 줄일때마다 하나씩 poll
		tail.offer(new int[] { 0, 0 });
		for (int i = 0; i < moves.length; i++) {
			if(!moving(interval)) break;
			d = (d + moves[i][1]) % 4; // 방향 전환
			if (i == moves.length - 1) { // 마지막 방향 전환 후에는 멈출때까지 같은 방향으로 진행
				moving(10000);
				break;
			}
			interval = moves[i + 1][0] - moves[i][0];
		}
		System.out.println(time);
	}
	
	public static boolean moving(int interval) {
		for (int i = 0; i < interval; i++) {
			time++;
			int dr = hr + delta[d][0];
			int dc = hc + delta[d][1];
			if (dr < 0 || dc < 0 || dr > N - 1 || dc > N - 1 || board[dr][dc] == 2)
				return false;

			if (board[dr][dc] == 0) {
				board[tail.peek()[0]][tail.peek()[1]] = 0;
				tail.poll();
			}
			board[hr = dr][hc = dc] = 2;
			tail.offer(new int[] { dr, dc });
		}
		return true;
	}

}
