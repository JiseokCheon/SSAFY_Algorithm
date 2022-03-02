package com.ssafy.study;

import java.util.*;
import java.io.*;

public class BOJ_2567_색종이2_서은지 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[][] arr = new int[102][102];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 10; k++) {
					arr[x + j][y + k] = 1;
				}
			}
		}
		
		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				if ((i == 0 || i == 100) && arr[i][j] == 1)
					cnt++;
				if ((j == 0 || j == 100) && arr[i][j] == 1)
					cnt++;
				if (arr[i][j] != arr[i+1][j])
					cnt++;
				if (arr[i][j] != arr[i][j+1])
					cnt++;
			}
		}
		System.out.println(cnt);
	}
}
