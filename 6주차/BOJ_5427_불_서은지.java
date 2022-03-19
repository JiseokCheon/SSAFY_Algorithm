package com.ssafy.study;

import java.util.*;
import java.io.*;

public class BOJ_5427_불_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			char[][] map = new char[h][w];
			boolean[][] visited = new boolean[h][w];
			Queue<int[]> queue = new LinkedList<int[]>();	// 상근이가 다음으로 이동할 곳
			Queue<int[]> fire = new LinkedList<int[]>();	// 다음으로 불이 번질 곳
			for (int i = 0; i < h; i++) {
				map[i] = in.readLine().toCharArray();
				for (int j = 0; j < w; j++) {
					if (map[i][j] == '@') {
						queue.offer(new int[] { i, j, 0 });
						visited[i][j] = true;
					} else if (map[i][j] == '*')
						fire.add(new int[] { i, j });
				}
			}

			int min = Integer.MAX_VALUE;
			// bfs: 불이 붙으려는 칸에도 이동할 수 없으므로 불 먼저 map에 정보저장하고 상근이 이동
			outer:
			while (!queue.isEmpty()) {
				int size = fire.size();	// 현재 카운트에서 움직여야 할 불의 수
				for (int i = size; i > 0; i--) {
					int fr = fire.peek()[0];
					int fc = fire.peek()[1];
					fire.poll();
					for (int j = 0; j < delta.length; j++) {
						int dfr = fr + delta[j][0];
						int dfc = fc + delta[j][1];
						if (dfr >= 0 && dfc >= 0 && dfr < h && dfc < w && map[dfr][dfc] != '#' && map[dfr][dfc] != '*') {
							map[dfr][dfc] = '*';
							fire.add(new int[] { dfr, dfc });
						}
					}
				}

				size = queue.size();	// 현재 카운트에서 상근이가 움직여봐야하는 수
				for (int i = size; i > 0; i--) {
					int r = queue.peek()[0];
					int c = queue.peek()[1];
					int cnt = queue.peek()[2];
					queue.poll();
					if (r == 0 || c == 0 || r == h - 1 || c == w - 1) {	// 경계라면 다음 이동에 무조건 탈출하므로  +1하고 전체 반복문 탈출
						min = cnt + 1;
						break outer;
					}
					for (int j = 0; j < delta.length; j++) {
						int dr = r + delta[j][0];
						int dc = c + delta[j][1];
						if (dr >= 0 && dc >= 0 && dr < h && dc < w && !visited[dr][dc] && map[dr][dc] == '.') {
							queue.offer(new int[] { dr, dc, cnt + 1 });
							visited[dr][dc] = true;
						}
					}
				}
			}
			System.out.println(min == Integer.MAX_VALUE ? "IMPOSSIBLE" : min);
		}
	}

}
