package com.ssafy.study.day0624;

import java.util.*;
import java.io.*;

public class BOJ_21611_마법사상어와블리자드_서은지 {
	static int N, M, answer;
	static int[][] map;
	static int[] line;
	static int[][] delta = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		line = new int[N * N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int cr = N / 2, cc = N / 2;
		int changed = 0;
		int dir = 0;
		int num = 0;
		outer:
		while (true) {
			for (int i = 0; i <= changed / 2; i++) {
				line[num++] = map[cr][cc];
				if (cr == 0 && cc == 0)
					break outer;
				cr += delta[dir][0];
				cc += delta[dir][1];
			}
			dir = (dir + 1) % 4;
			changed++;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			blizard(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			while(bomb())
				move();
			change();
		}

		System.out.println(answer);
	}
	
	public static void move() {
		int idx = 1;
		for (int i = 1; i < N * N; i++) {
			if (line[i] == 0)
				continue;
			line[idx++] = line[i];
		}
		
		for (int i = idx; i < N * N; i++)
			line[i] = 0;
	}
	
	public static void blizard(int d, int s) {
		int start = 0;
		switch(d) {
		case 1:
			start = 7;
			break;
		case 2:
			start = 3;
			break;
		case 3:
			start = 1;
			break;
		case 4:
			start = 5;
			break;
		}
		
		int diff = 0;
		int now = 0;
		for (int i = 0; i < s; i++) {
			now += diff + start;
			line[now] = 0;
			diff += 8;
		}
		move();
	}
	
	public static boolean bomb() {
		boolean flag = false;
		int cnt = 1;
		for (int i = 1; i < line.length; i++) {
			if (line[i] != line[i - 1] || i == line.length - 1) {
				if (cnt >= 4) {
					flag = true;
					answer += line[i - 1] * cnt;
					for (int j = 1; j <= cnt; j++)
						line[i - j] = 0;
				}
				cnt = 1;
			} else {
				cnt++;
			}
			if (line[i] == 0)
				break;
		}
		return flag;
	}
	
	public static void change() {
		int[] tmp = new int[N*N];
		int cnt = 1;
		int idx = 1;
		for (int i = 2; i < line.length; i++) {
			if (line[i] != line[i - 1] || i == line.length - 1) {
				tmp[idx++] = cnt;
				tmp[idx++] = line[i - 1];
				cnt = 1;
			} else {
				cnt++;
			}
			if (line[i] == 0 || idx >= N * N)
				break;
		}
		
		for (int i = 1; i < N*N; i++)
			line[i] = tmp[i];
	}
	
	public static void print() {
		for (int k = 0; k < line.length; k++)
			System.out.print(line[k] + " ");
		System.out.println();
	}

}
