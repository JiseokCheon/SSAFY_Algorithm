package com.ssafy.study.day0424;

import java.util.*;
import java.io.*;

class Move {
	int r, c, cnt, d;

	public Move(int r, int c, int cnt, int d) {
		this.r = r;
		this.c = c;
		this.cnt = cnt;
		this.d = d;
	}
}

public class BOJ_6087_레이저통신_서은지 {
	static int W, H;
	static char[][] map;
	static int[][] C, dijk;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		dijk = new int[H][W];
		C = new int[2][2];
		int idx = 0;
		for (int i = 0; i < H; i++) {
			Arrays.fill(dijk[i], Integer.MAX_VALUE);
			map[i] = in.readLine().toCharArray();
			for (int j = 0; j < W; j++) {
				if (map[i][j] == 'C')
					C[idx++] = new int[] { i, j };
			}
		}
		Queue<Move> queue = new LinkedList<Move>();
		for (int i = 0; i < 4; i++) {
			int dr = C[0][0] + delta[i][0];
			int dc = C[0][1] + delta[i][1];
			if (dr >= 0 && dc >= 0 && dr < H && dc < W)
				queue.offer(new Move(C[0][0], C[0][1], 0, i));
		}

		while (!queue.isEmpty()) {
			Move move = queue.poll();
			if (move.r == C[1][0] && move.c == C[1][1])
				continue;
			for (int i = 0; i < 4; i++) {
				int dr = move.r + delta[i][0];
				int dc = move.c + delta[i][1];
				if (dr >= 0 && dc >= 0 && dr < H && dc < W && map[dr][dc] != '*') {
					if (i + move.d == 1 || i + move.d == 5)
						continue;
					int tmp = i == move.d ? move.cnt : move.cnt + 1;
					if (dijk[dr][dc] >= tmp) {
						dijk[dr][dc] = tmp;
						queue.offer(new Move(dr, dc, tmp, i));
					}
				}
			}
		}
		System.out.println(dijk[C[1][0]][C[1][1]]);
	}

}
