package com.ssafy.study.day0428;

import java.util.*;
import java.io.*;

public class BOJ_2178_미로탐색_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = in.readLine();
			for (int j = 0; j < M; j++)
				arr[i][j] = str.charAt(j) - '0';
		}
		
		Queue<int[]> queue = new LinkedList<int[]>();
		boolean[][] visited = new boolean[N][M];
		queue.offer(new int[] {0, 0, 1});
		visited[0][0] = true;
		
		while (!queue.isEmpty()) {
			int r = queue.peek()[0];
			int c = queue.peek()[1];
			int cnt = queue.poll()[2];
			
			if (r == N-1 && c == M-1) {
				System.out.println(cnt);
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				int dr = r + delta[i][0];
				int dc = c + delta[i][1];
				if (dr >= 0 && dc >= 0 && dr < N && dc < M && arr[dr][dc] == 1 && !visited[dr][dc]) {
					visited[dr][dc] = true;
					queue.offer(new int[] {dr, dc, cnt + 1});
				}
			}
		}

	}

}
