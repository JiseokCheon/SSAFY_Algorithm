package com.ssafy.day0708;

import java.util.*;
import java.io.*;

class Piece {
	int r, c, d, cur;

	public Piece(int r, int c, int d) {
		this.r = r;
		this.c = c;
		this.d = d;
	}
}

class Point {
	int color;
	List<Integer> list;

	public Point(int color, LinkedList<Integer> list) {
		this.color = color;
		this.list = list;
	}
}

public class BOJ_17780_새로운게임_서은지 {
	static int N, K;
	static Point[][] map;
	static int[][] delta = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
	static Piece[] pieces;
	static boolean isFinished;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new Point[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++)
				map[i][j] = new Point(Integer.parseInt(st.nextToken()), new LinkedList<Integer>());
		}
		pieces = new Piece[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			pieces[i] = new Piece(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()) - 1);
			map[pieces[i].r][pieces[i].c].list.add(i);
		}
		int turn = 1;
		while (turn <= 1000) {
			for (int i = 0; i < K; i++)
				move(i);
			if (isFinished)
				break;
			turn++;
		}
		System.out.println(turn > 1000 ? -1 : turn);
	}

	public static void move(int idx) {
		if (isFinished)
			return;
		int dr = pieces[idx].r + delta[pieces[idx].d][0];
		int dc = pieces[idx].c + delta[pieces[idx].d][1];
		if (dr < 0 || dc < 0 || dr >= N || dc >= N || map[dr][dc].color == 2) {
			pieces[idx].d += pieces[idx].d % 2 == 1 ? -1 : 1;
			dr = pieces[idx].r + delta[pieces[idx].d][0];
			dc = pieces[idx].c + delta[pieces[idx].d][1];
			if (dr < 0 || dc < 0 || dr >= N || dc >= N || map[dr][dc].color == 2)
				return;
			else
				move(idx);
		} else {
			int cur = map[dr][dc].list.size();
			int gap = 0;
			List<Integer> tmp = new ArrayList<Integer>(map[pieces[idx].r][pieces[idx].c].list.subList(pieces[idx].cur, map[pieces[idx].r][pieces[idx].c].list.size()));
			if (map[dr][dc].color == 1)
				Collections.reverse(tmp);
			map[dr][dc].list.addAll(tmp);
			map[pieces[idx].r][pieces[idx].c].list.removeAll(tmp);
			for(int i = 0; i < tmp.size(); i++) {
				pieces[tmp.get(i)].r = dr;
				pieces[tmp.get(i)].c = dc;
				pieces[tmp.get(i)].cur = cur + gap++;
			}
		}
		if (map[dr][dc].list.size() >= 4)
			isFinished = true;
	}

}
