package com.ssafy.study;

import java.util.*;
import java.io.*;

public class BOJ_14502_연구소_서은지 {
	static int N, M, min, vCnt, zero;
	static int[][] map;
	static boolean[] visited;
	static boolean[][] vVisited;
	static List<int[]> virus, area;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		virus = new ArrayList<int[]>();
		area = new ArrayList<int[]>();
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				if ((map[i][j] = Integer.parseInt(st.nextToken())) == 2)
					virus.add(new int[] { i, j });
				else if (map[i][j] == 0)
					area.add(new int[] { i, j });
			}
		}
		visited = new boolean[area.size()];
		min = Integer.MAX_VALUE;
		comb(0, 0);
		System.out.println(area.size() - 3 - min);	// 빈 칸 - 벽 3개 - 바이러스 수
	}

	// 벽 3개 선택하는 조합
	public static void comb(int current, int cnt) {
		if (cnt == 3) {
			// 바이러스 퍼뜨려서 퍼진 바이러스 수 계산
			// 1. dfs (220ms)
			vCnt = 0;
			vVisited = new boolean[N][M];
			for (int i = 0; i < virus.size(); i++)
				dfs(virus.get(i)[0], virus.get(i)[1]);
			min = Math.min(min, vCnt);
			// 2. bfs (340ms)
//			bfs();
			return;
		}

		for (int i = current; i < area.size(); i++) {
			visited[i] = true;
			map[area.get(i)[0]][area.get(i)[1]] = 1;
			comb(i + 1, cnt + 1);
			visited[i] = false;
			map[area.get(i)[0]][area.get(i)[1]] = 0;
		}
	}

	public static void dfs(int r, int c) {
		for (int i = 0; i < delta.length; i++) {
			int dr = r + delta[i][0];
			int dc = c + delta[i][1];
			if (dr >= 0 && dc >= 0 && dr < N && dc < M && !vVisited[dr][dc] && map[dr][dc] == 0) {
				if (++vCnt > min)
					return;
				vVisited[dr][dc] = true;
				dfs(dr, dc);
			}
		}
	}
	
	public static void bfs() {
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> queue = new LinkedList<int[]>();
		int cnt = 0;
		for (int i = 0; i < virus.size(); i++)
			queue.offer(new int[] {virus.get(i)[0], virus.get(i)[1]});	
		while(!queue.isEmpty()) {
			int r = queue.peek()[0];
			int c = queue.poll()[1];
			for (int i = 0; i < delta.length; i++) {
				int dr = r + delta[i][0];
				int dc = c + delta[i][1];
				if (dr >= 0 && dc >= 0 && dr < N && dc < M && !visited[dr][dc] && map[dr][dc] == 0) {
					queue.offer(new int[] {dr, dc});
					visited[dr][dc] = true;
					if (++cnt > min)
						return;
				}
			}
		}
		min = Math.min(min, cnt);
	}

}
