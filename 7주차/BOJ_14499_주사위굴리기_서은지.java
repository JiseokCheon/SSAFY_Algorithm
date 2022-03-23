package com.ssafy.study;

import java.util.*;
import java.io.*;

public class BOJ_14499_주사위굴리기_서은지 {
	static int[] idx = {3, 4, 5};	// 문제에 있는 전개도 기준(idx[2]가 바닥면)
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[][] delta = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };	// 문제 순서대로(동, 서, 북, 남)
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int[] dice = new int[6];	// 주사위 각 인덱스에 적힌 값
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < K; i++) {
			int d = Integer.parseInt(st.nextToken()) - 1;
			int dr = r + delta[d][0];
			int dc = c + delta[d][1];
			if (dr < 0 || dc < 0 || dr > N-1 || dc > M-1)
				continue;
			
			switch(d) {
			case 0:
				swap(0, 2);
				break;
			case 1:
				swap(2, 0);
				break;
			case 2:
				swap(2, 1);
				break;
			case 3:
				swap(1, 2);
				break;
			}
			if (map[dr][dc] == 0) {	// 0이면 주사위 바닥면 값 맵에 복사
				map[dr][dc] = dice[idx[2]];
			} else {	// 아니면 맵의 값을 주사위 바닥면에 복사한 후 0으로 설정
				dice[idx[2]] = map[dr][dc];
				map[dr][dc] = 0;
			}
			r = dr;
			c = dc;
			sb.append(dice[5-idx[2]] + "\n");	// 윗면 출력
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	public static void swap(int a, int b) {
		int tmp = idx[a];
		idx[a] = idx[b];
		idx[b] = 5-tmp;
	}

}
