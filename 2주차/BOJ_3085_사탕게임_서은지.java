package com.ssafy.study;

import java.io.*;

public class BOJ_3085_사탕게임_서은지 {
	static char[][] arr;
	static int N, max;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		arr = new char[N][N];
		for (int i = 0; i < N; i++) {
			arr[i] = in.readLine().toCharArray();
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N-1; j++) {
				count(i, j);	// 인접 칸 교환 전 해당 행, 열의 사탕 수 확인
				if (j == N-2)	// j 인덱스가 N-2까지 이므로 N-1행까지 확인
					count(i, j+1);
				if (arr[i][j] != arr[i][j+1]) {
					swapC(i, j);
					count(i, j);
					count(i, j+1);
					if (j == N-2)
						count(i, j+1);
					swapC(i, j);
				}
				if (i == N-1)	// i가 N-1이면 비교할 N행 없으므로 과정 생략
					continue;
				else if (arr[i][j] != arr[i+1][j]) {
					swapR(i, j);
					count(i, j);
					count(i+1, j);
					swapR(i, j);
				}
			}
		}
		System.out.println(max);

	}
	
	// 해당 좌표의 행, 열 사탕 수 카운트
	public static void count(int i, int j) {
		int rCnt = 1, cCnt = 1;
		char r = arr[i][0];
		char c = arr[0][j];
		for (int k = 1; k < N; k++) {	// 해당 행/열의 0 인덱스부터 같으면 카운트 1 추가, 다르면 max와 비교후 초기화
			if (r == arr[i][k])
				rCnt++;
			else if (r != arr[i][k]) {
				max = Math.max(max, rCnt);
				rCnt = 1;
				r = arr[i][k];
			}
			
			if (c == arr[k][j])
				cCnt++;
			else if (c != arr[k][j]) {
				max = Math.max(max, cCnt);
				cCnt = 1;
				c = arr[k][j];
			}
		}
		max = Math.max(max, rCnt > cCnt ? rCnt : cCnt);
	}
	
	// 인접 열 교환
	public static void swapC(int i, int j) {
		char tmp = arr[i][j];
		arr[i][j] = arr[i][j+1];
		arr[i][j+1] = tmp;
	}
	
	// 인접 행 교환
	public static void swapR(int i, int j) {
		char tmp = arr[i][j];
		arr[i][j] = arr[i+1][j];
		arr[i+1][j] = tmp;
	}

}
