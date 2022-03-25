package com.ssafy.study;

import java.util.*;
import java.io.*;

public class BOJ_14500_테트로미노_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		int max = 0;

		// 시간 더 걸림
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j <= M-2; j++) {
//				sum = arr[i][j] + arr[i][j+1];
//				if (i < N-1) {
//					max = Math.max(max, sum + arr[i+1][j] + arr[i+1][j+1]);	// 네모는 맨 윗줄에서도 가능, 어디에서 봐도 같은 모양 -> 가로/세로 기준 한 번만 탐색
//					if (i != 0) {
//						max = Math.max(max, sum + arr[i-1][j] + arr[i+1][j+1]);
//						max = Math.max(max, sum + arr[i-1][j+1] + arr[i+1][j]);
//					}
//				}
//				if (j >= M-2)
//					continue;
//				sum += arr[i][j+2];
//				if (i == 0 || i == N-1) {
//					for (int k = 0; k < 3; k++)
//						max = Math.max(max, sum + (i == 0 ? arr[i+1][j+k] : arr[i-1][j+k]));
//				} else {
//					for (int k = 0; k < 3; k++) {
//						max = Math.max(max, sum + arr[i-1][j+k]);
//						max = Math.max(max, sum + arr[i+1][j+k]);
//					}
//				}
//				if (j >= M-3)
//					continue;
//				max = Math.max(max, sum + arr[i][j+3]);
//			}
//		}
//
//		for (int j = 0; j < M; j++) {
//			for (int i = 0; i <= N-2; i++) {
//				sum = arr[i][j] + arr[i+1][j];
//				if (j != 0 && j < M-1) {
//					max = Math.max(max, sum + arr[i][j-1] + arr[i+1][j+1]);
//					max = Math.max(max, sum + arr[i+1][j-1] + arr[i][j+1]);
//				}
//				if (i >= N-2)
//					continue;
//				sum += arr[i+2][j];
//				if (j == 0 || j == M-1) {
//					for (int k = 0; k < 3; k++)
//						max = Math.max(max, sum + (j == 0 ? arr[i+k][j+1] : arr[i+k][j-1]));
//				} else {
//					for (int k = 0; k < 3; k++) {
//						max = Math.max(max, sum + arr[i+k][j-1]);
//						max = Math.max(max, sum + arr[i+k][j+1]);
//					}
//				}
//				if (i >= N-3)
//					continue;
//				max = Math.max(max, sum + arr[i+3][j]);
//			}
//		}



		 // 4일때(막대)
		 for (int i = 0; i < N; i++) {
		 	for (int j = 0; j <= M-4; j++)
		 		max = Math.max(max, arr[i][j] + arr[i][j+1] + arr[i][j+2] + arr[i][j+3]);
		 }
		 for (int j = 0; j < M; j++) {
		 	for (int i = 0; i <= N-4; i++)
		 		max = Math.max(max, arr[i][j] + arr[i+1][j] + arr[i+2][j] + arr[i+3][j]);
		 }
		
		 // 3일때(ㅗ, ㄱ)
		 for (int i = 0; i < N; i++) {
		 	for (int j = 0; j <= M-3; j++) {
		 		int sum = arr[i][j] + arr[i][j+1] + arr[i][j+2];
		 		if (i == 0 || i == N-1) {	// 맨 위나 아래는 만들어지는 모양만 만들어볾
		 			for (int k = 0; k < 3; k++)
		 				max = Math.max(max, sum + (i == 0 ? arr[i+1][j+k] : arr[i-1][j+k]));
		 			continue;
		 		}
		 		for (int k = 0; k < 3; k++) {
		 			max = Math.max(max, sum + arr[i-1][j+k]);
		 			max = Math.max(max, sum + arr[i+1][j+k]);
		 		}
		 	}
		 }
		 for (int j = 0; j < M; j++) {
		 	for (int i = 0; i <= N-3; i++) {
		 		int sum = arr[i][j] + arr[i+1][j] + arr[i+2][j];
		 		if (j == 0 || j == M-1) {
		 			for (int k = 0; k < 3; k++)
		 				max = Math.max(max, sum + (j == 0 ? arr[i+k][j+1] : arr[i+k][j-1]));
		 			continue;
		 		}
		 		for (int k = 0; k < 3; k++) {
		 			max = Math.max(max, sum + arr[i+k][j-1]);
		 			max = Math.max(max, sum + arr[i+k][j+1]);
		 		}
		 	}
		 }
		
		 // 2일때(나머지)
		 for (int i = 0; i < N-1; i++) {
		 	for (int j = 0; j <= M-2; j++) {
		 		int sum = arr[i][j] + arr[i][j+1];
		 		max = Math.max(max, sum + arr[i+1][j] + arr[i+1][j+1]);	// 네모는 맨 윗줄에서도 가능, 어디에서 봐도 같은 모양 -> 가로/세로 중 한 번만 탐색
		 		if (i == 0) continue;
		 		max = Math.max(max, sum + arr[i-1][j] + arr[i+1][j+1]);
		 		max = Math.max(max, sum + arr[i-1][j+1] + arr[i+1][j]);
		 	}
		 }
		 for (int j = 1; j < M-1; j++) {
		 	for (int i = 0; i <= N-2; i++) {
		 		int sum = arr[i][j] + arr[i+1][j];
		 		max = Math.max(max, sum + arr[i][j-1] + arr[i+1][j+1]);
		 		max = Math.max(max, sum + arr[i+1][j-1] + arr[i][j+1]);
		 	}
		 }
		System.out.println(max);
	}

}
