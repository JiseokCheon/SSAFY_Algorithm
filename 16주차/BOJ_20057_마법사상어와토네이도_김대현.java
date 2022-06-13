package BJ_20057_마법사상어와토네이도;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static int[][] dir = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };// 왼쪽, 아래, 오른쪽, 위
	static int[][] map;
	static int[][][] shark = {
			{ { 0, -3 }, { -1, -2 }, { 1, -2 }, { -2, -1 }, { 2, -1 }, { -1, -1 }, { 1, -1 }, { -1, 0 }, { 1, 0 } },
			{ { 3, 0 }, { 2, 1 }, { 2, -1 }, { 1, 2 }, { 1, -2 }, { 1, 1 }, { 1, -1 }, { 0, 1 }, { 0, -1 } },
			{ { 0, 3 }, { 1, 2 }, { -1, 2 }, { 2, 1 }, { -2, 1 }, { 1, 1 }, { -1, 1 }, { 1, 0 }, { -1, 0 } },
			{ { -3, 0 }, { -2, -1 }, { -2, 1 }, { -1, -2 }, { -1, 2 }, { -1, -1 }, { -1, 1 }, { 0, -1 }, { 0, 1 } } };
	static double[] sharkp = { 0.05, 0.1, 0.1, 0.02, 0.02, 0.07, 0.07, 0.01, 0.01 };
	static int answer, n;
	static int nr, nc, temp, sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		answer = 0;
		map = new int[n][n];
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		tornado(n / 2, n / 2, 0, 0, 1);// x,y좌표 , 현재 방향, 현재 방향으로 몇회,방향의 길이
		System.out.print(answer);

	}

	private static void tornado(int x, int y, int d, int dn, int dl) {
		if (x == 0 && y == 0) {
			return;
		} else {
			sum = 0;
			temp = map[x + dir[d][0]][y + dir[d][1]];
			map[x + dir[d][0]][y + dir[d][1]] = 0;
			for (int i = 0; i < 9; i++) {
				sum += (int) (temp * sharkp[i]);
				nr = x + shark[d][i][0];
				nc = y + shark[d][i][1];
				if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
					answer += (int) (temp * sharkp[i]);
				} else {
					map[nr][nc] += (int) (temp * sharkp[i]);
				}
			}
			nr = x + dir[d][0] * 2;
			nc = y + dir[d][1] * 2;
			if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
				answer += temp - sum;
			} else {
				map[nr][nc] += temp - sum;
			}

			nr = x + dir[d][0];
			nc = y + dir[d][1];
			dn++;
			if (dn == dl) {
				d++;
				dn = 0;
				if (d % 2 == 0) {
					dl++;
				}
			}
			tornado(nr, nc, d % 4, dn, dl);// x,y좌표 , 현재 방향, 현재 방향으로 몇회,방향의 길이

		}

	}

}
