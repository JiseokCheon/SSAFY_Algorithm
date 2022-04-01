package com.ssafy.study.day0401;

import java.util.*;
import java.io.*;

public class BJ_14890_경사로_서은지 {
	static int N, L, roads;
	static int[][] map, map2;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		map2 = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int tmp = map[i][j] = Integer.parseInt(st.nextToken());
				map2[j][i] = tmp;
			}
		}

		for (int i = 0; i < N; i++) {
			checkRoad(i, map);
			checkRoad(i, map2);
		}
		System.out.println(roads);
	}

	public static void checkRoad(int idx, int[][] map) {
		int cnt = 1;	// 같은 숫자가 나오고 있는 수
		int current = -1;	// 최근 경사로 끝 지점
		for (int i = 0; i < N - 1; i++) {
			if (map[idx][i] == map[idx][i + 1]) {	// 높이가 같으면
				cnt++;
				continue;
			}
			if (map[idx][i] + 1 == map[idx][i + 1]) {	// 높이가 1 더 높으면
				if (cnt >= L && i + 1 - L > current) {	// 현재까지 같은 수가 L개 이상인지, 최근 경사 끝지점보다 큰 지 확인
					cnt = 1;
					current = i + 1 - L;
				} else
					return;
			} else if (map[idx][i] - 1 == map[idx][i + 1]) {	// 높이가 1 더 낮으면
				if (i + L < N ) {	// 경사로의 길이까지 더했을때 범위 내이면
					for (int j = 1; j <= L; j++) {	// 모두 같은 높이인지 확인
						if (map[idx][i + j] != map[idx][i + 1])
							return;
					}
					if (i + 1 + L < N && map[idx][i + 1 + L] > map[idx][i + 1])	// 그 길의 끝지점이 아닐때, 더 큰 값이 오면 경사만들기 불가능
						return;
					cnt = 1;
					current = i + L;
					i += L-1;	// 경사로 끝지점만큼 인덱스 스킵(끝에서도 더 작은 경사로라면 만들 수 있음)
				} else
					return;
			} else
				return;
		}
		roads++;
	}

}
