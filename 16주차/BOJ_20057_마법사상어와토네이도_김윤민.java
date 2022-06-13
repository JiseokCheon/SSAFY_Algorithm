package July;

import java.io.*;
import java.util.*;

public class BOJ_20057_마법사상어와토네이도_김윤민 {
	static int[][] move = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } }; // 상좌하우
	static int[] percent = { 1, 1, 2, 7, 7, 2, 10, 10, 5 };
	static int[][] sand = { { 0, -1 }, { 0, 1 }, { 1, -2 }, { 1, -1 }, { 1, 1 }, { 1, 2 }, { 2, -1 }, { 2, 1 },	{ 3, 0 } };
	static int N, start, sum;
	static int[][] map;
	static boolean[][] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		int cur_i = N / 2, cur_j = N / 2; //시작 위치
		sum = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visit = new boolean[N][N];
		int dir = 1;
		while (true) {
			visit[cur_i][cur_j] = true;
			if (cur_i == 0 && cur_j == 0) { // 0,0 이면 종료
				break;
			}
			int y_i = cur_i + move[dir][0], y_j = cur_j + move[dir][1]; //y위치
			int a_i = cur_i + 2 * move[dir][0], a_j = cur_j + 2 * move[dir][1]; //a위치

			int y = map[y_i][y_j];
			for (int k = 0; k < 9; k++) { //모래가 흔터지는 구역 만큼
				int sand_i = sand[k][0];
				int sand_j = sand[k][1];

				if (dir == 0) { //방향에 따라서 위치 조정해주기
					sand_i = -sand_i; //행 반전
				} else if (dir == 3) { //행,열 교환
					sand_i = sand[k][1];
					sand_j = sand[k][0];
				} else if (dir == 1) { //행,열 교환, 열 반전
					sand_i = sand[k][1];
					sand_j = sand[k][0];
					sand_j = -sand_j;
				}
				int ni = cur_i + sand_i; //모래가 이동할 위치
				int nj = cur_j + sand_j;
				int subsand = (int) (y * 0.01 * percent[k]); //이동할 모래의 양
				map[y_i][y_j] -= subsand; // 이동한 모래의 합
				if (ni >= 0 && ni < N && nj >= 0 && nj < N) { // 격자 범위 안에 있는 경우
					map[ni][nj] += subsand;
				} else { // 격자에서 벗어나는 경우 더해주기
					sum += subsand;
				}
			}
			if (a_i >= 0 && a_i < N && a_j >= 0 && a_j < N) { //a구간이 범위 안에 있으면
				map[a_i][a_j] += map[y_i][y_j]; // 남은 모래 이동
			} else { //없으면 sum에 더해주기
				sum += map[y_i][y_j];
			}
			map[y_i][y_j] = 0; 
			// 방향 정하기
			// 왼쪽으로 갈 수 있다면 왼쪽으로 회전. 아니면 직진
			int si = y_i + move[(dir + 1) % 4][0];
			int sj = y_j + move[(dir + 1) % 4][1];
			if (si >= 0 && si < N && sj >= 0 && sj < N && !visit[si][sj]) {
				dir = (dir + 1) % 4;
			}
			cur_i = y_i;
			cur_j = y_j;

		}
		System.out.println(sum);
	}
}
