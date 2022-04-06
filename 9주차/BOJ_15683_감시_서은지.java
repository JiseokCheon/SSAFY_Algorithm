package com.ssafy.study.day0406;

import java.util.*;
import java.io.*;

public class BOJ_15683_감시_서은지 {

	static int N, M;
	static int[][] arr;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상, 하, 좌, 우
	// cctvSet 구조 : 인덱스(cctv번호 - 1) >> 회전 >> 이동할 방향(delta 인덱스)
	static int[][][] cctvSet = { { { 0 }, { 1 }, { 2 }, { 3 } }, // cctv 1
			{ { 0, 1 }, { 2, 3 } }, // cctv2
			{ { 0, 3 }, { 3, 1 }, { 1, 2 }, { 2, 0 } }, // cctv3
			{ { 0, 1, 2 }, { 0, 1, 3 }, { 0, 2, 3 }, { 1, 2, 3 } }, // cctv4
			{ { 0, 1, 2, 3 } } }; // cctv5
	static List<CCTV> cctv = new ArrayList<CCTV>(); // 배열에 있는 cctv 정보 저장할 리스트
	static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] != 0 && arr[i][j] != 6)
					cctv.add(new CCTV(arr[i][j], i, j));
			}
		}

		min = Integer.MAX_VALUE;
		permutation(arr, 0);
		System.out.println(min);

	}

	public static void permutation(int[][] arr, int cnt) {
		if (cnt == cctv.size()) {
			int zeroCnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					zeroCnt += arr[i][j] == 0 ? 1 : 0;
				}
			}
			min = Math.min(min, zeroCnt);
			return;
		}

		CCTV tmp = cctv.get(cnt); // 현재 cctv
		int num = tmp.num - 1; // 인덱스는 cctv번호 - 1
		for (int i = 0; i < cctvSet[num].length; i++) { // cctv 하나의 회전 수 만큼
			int[][] tmpArr = setArr(arr); // tmpArr : 조작할 배열
			for (int j = 0; j < cctvSet[num][i].length; j++) { // 하나의 회전당 방향 수만큼
				int r = tmp.r;
				int c = tmp.c;
				int dir = cctvSet[num][i][j]; // 다음 이동할 방향

				while (true) {
					int dr = r + delta[dir][0];
					int dc = c + delta[dir][1];

					if (dr < 0 || dc < 0 || dr >= N || dc >= M || tmpArr[dr][dc] == 6) // 배열 범위 벗어나거나 벽이면 중단
						break;
					tmpArr[dr][dc] = -1;
					r = dr;
					c = dc;
				}
			}
			permutation(tmpArr, cnt + 1);
		}

	}

	public static int[][] setArr(int[][] arr) {
		int[][] tmpArr = new int[N][M];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				tmpArr[i][j] = arr[i][j];
		return tmpArr;
	}

}

class CCTV {
	int num;
	int r;
	int c;

	public CCTV(int num, int r, int c) {
		this.num = num;
		this.r = r;
		this.c = c;
	}
}
