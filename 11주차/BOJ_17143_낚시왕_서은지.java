package com.ssafy.study.day0422;

import java.util.*;
import java.io.*;

class Shark {
	int idx, r, c, s, d, z;

	public Shark(int idx, int r, int c, int s, int d, int z) {
		this.idx = idx;
		this.r = r;
		this.c = c;
		this.s = s;
		this.d = d;
		this.z = z;
	}
}

public class BOJ_17143_낚시왕_서은지 {
	static int R, C, M;
	static Shark[][] map;
	static Shark[] sharks;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sharks = new Shark[M];
		map = new Shark[R][C];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			sharks[i] = new Shark(i, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()));
			sharks[i].s %= sharks[i].d < 2 ? (R - 1) * 2 : (C - 1) * 2;
			map[sharks[i].r][sharks[i].c] = sharks[i];
		}

		int sum = 0;
		for (int i = 0; i < C; i++) {
			for (int j = 0; j < R; j++) {
				if (map[j][i] != null) {
					sum += map[j][i].z;
					sharks[map[j][i].idx] = null;
					break;
				}
			}
			if (i == C-1) break;
			sharkMove();
		}
		System.out.println(sum);
	}

	public static void sharkMove() {
		Shark tmp[][] = new Shark[R][C];
		for (int i = 0; i < M; i++) {
			if (sharks[i] != null) {
				// 316ms
				int p = sharks[i].d < 2 ? sharks[i].r : sharks[i].c;
				boolean isRow = sharks[i].d < 2 ? true : false;
				int heading = (sharks[i].d == 0 || sharks[i].d == 3) ? 0 : sharks[i].d == 1 ? R - 1 : C - 1;
				int opposite = (sharks[i].d == 1 || sharks[i].d == 2) ? 0 : sharks[i].d == 0 ? R - 1 : C - 1;
				int total = sharks[i].s;
				if (total <= Math.abs(p - heading)) {
					if (isRow)
						sharks[i].r += total * delta[sharks[i].d][0];
					else
						sharks[i].c += total * delta[sharks[i].d][1];
				} else if (total - Math.abs(p - heading) <= Math.abs(opposite - heading)) {
					total -= Math.abs(p - heading);
					sharks[i].d += (sharks[i].d == 0 || sharks[i].d == 2) ? 1 : -1;
					if (isRow)
						sharks[i].r = heading + total * delta[sharks[i].d][0];
					else
						sharks[i].c = heading + total * delta[sharks[i].d][1];
				} else {
					if (isRow)
						sharks[i].r += ((R - 1) * 2 - total) * (-delta[sharks[i].d][0]);
					else
						sharks[i].c += ((C - 1) * 2 - total) * (-delta[sharks[i].d][1]);

				}

				// 632ms
//				for (int j = 0; j < sharks[i].s; j++) {
//					int dr = sharks[i].r + delta[sharks[i].d][0];
//					int dc = sharks[i].c + delta[sharks[i].d][1];
//					if (dr < 0 || dc < 0 || dr >= R || dc >= C)
//						sharks[i].d += (sharks[i].d == 0 || sharks[i].d == 2) ? 1 : -1;
//					sharks[i].r += delta[sharks[i].d][0];
//					sharks[i].c += delta[sharks[i].d][1];
//				}

				if (tmp[sharks[i].r][sharks[i].c] != null) {
					if (tmp[sharks[i].r][sharks[i].c].z < sharks[i].z) {
						sharks[tmp[sharks[i].r][sharks[i].c].idx] = null;
						tmp[sharks[i].r][sharks[i].c] = sharks[i];
					} else {
						sharks[i] = null;
					}

				} else
					tmp[sharks[i].r][sharks[i].c] = sharks[i];
			}
		}
		map = tmp;
	}

}
