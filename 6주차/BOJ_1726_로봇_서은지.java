package com.ssafy.study;

import java.util.*;
import java.io.*;

public class BOJ_1726_로봇_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] delta = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };	// 동서남북
		int[][] arr = new int[M][N];
		boolean[][][] visited = new boolean[M][N][4];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		int[][] setP = new int[2][4];	// 출발점, 도착점(행, 열, 방향, 명령어 수)
		for (int i = 0; i < 2; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < 3; j++)
				setP[i][j] = Integer.parseInt(st.nextToken()) - 1;	// 좌표, 방향 모두 0부터 시작하도록 처리
			setP[i][3] = 0;
		}

		int cnt = 0;	// 출력할 명령어 수
		
		// bfs
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(setP[0]);
		visited[setP[0][0]][setP[0][1]][setP[0][2]] = true;
		while (!queue.isEmpty()) {
			int[] tmp = queue.poll();
			if (tmp[0] == setP[1][0] && tmp[1] == setP[1][1]) {	// 행열좌표가 같다면
				cnt = tmp[3];
				if (tmp[2] != setP[1][2])	// 좌표가 같은데 방향이 다르다면 명령어 수 더해줌(반대 방향이면 방향 인덱스 합이 1이거나 5 그 이외에는 90도 회전)
					cnt += (tmp[2] + setP[1][2] == 1 || tmp[2] + setP[1][2] == 5) ? 2 : 1;
				break;
			}
			
			// 1. 같은 방향에서 갈 수 있는 경우(Go k, k : 1~3)
			for (int i = 1; i <= 3; i++) {
				int dr = tmp[0] + delta[tmp[2]][0] * i;
				int dc = tmp[1] + delta[tmp[2]][1] * i;
				if (dr >= 0 && dc >= 0 && dr < M && dc < N && arr[dr][dc] != 1) {
					if (visited[dr][dc][tmp[2]])
						continue;
					queue.offer(new int[] { dr, dc, tmp[2], tmp[3] + 1 });
					visited[dr][dc][tmp[2]] = true;
				} else	// 1이안되면, 2, 3불가, 2안되면 3도 불가
					break;
			}

			// 2. 다른 방향으로 가는 경우
			for (int i = 0; i < delta.length; i++) {
				if (tmp[2] != i && !visited[tmp[0]][tmp[1]][i]) {
					queue.offer(new int[] {tmp[0], tmp[1], i, tmp[3] + (tmp[2] + i == 1 || tmp[2] + i == 5 ? 2 : 1)});
					visited[tmp[0]][tmp[1]][i] = true;
				}
			}
		}
		System.out.println(cnt);
	}

}
