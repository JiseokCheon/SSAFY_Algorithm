package BJ_19237_어른상어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static class smell {
		int num;
		int time;

		public smell(int num, int time) {
			this.num = num;
			this.time = time;
		}
	}

	public static class shark {
		int num;
		int r, c;
		int d;

		public shark(int num, int r, int c, int d) {
			this.num = num;
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		smell[][] smells = new smell[n][n];
		shark[] sharks = new shark[m + 1];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 0) {
					smells[i][j] = new smell(0, 0);
				} else {
					smells[i][j] = new smell(temp, k);
					sharks[temp] = new shark(temp, i, j, 0);
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= m; i++) {
			sharks[i].d = Integer.parseInt(st.nextToken()) - 1;
		}
		int[][][] sharkDir = new int[m + 1][4][4];

		for (int i = 1; i <= m; i++) {
			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int d = 0; d < 4; d++) {
					sharkDir[i][j][d] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
		}
		boolean[] sharkAlive = new boolean[m + 1];
		Arrays.fill(sharkAlive, true);
		int count = 0;
		int time = 0;
		boolean timecheck = false;
		while (time <= 1000) {
			// 체크하기
			if (count == m - 1) {
				timecheck = true;
				break;
			}

			time++;
			// 상어 이동 (좌표만 찾아놓기)
			for (int i = 1; i <= m; i++) {
				if (!sharkAlive[i]) {
					continue;
				}
				int r = sharks[i].r;
				int c = sharks[i].c;
				int d = sharks[i].d;
				// 냄새 없는 곳 찾기
				boolean check = false;
				for (int j = 0; j < 4; j++) {
					int nr = r + dir[sharkDir[i][d][j]][0];
					int nc = c + dir[sharkDir[i][d][j]][1];
					if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
						continue;
					}
					if (smells[nr][nc].num == 0) {
						sharks[i].r = nr;
						sharks[i].c = nc;
						sharks[i].d = sharkDir[i][d][j];
						check = true;
						break;
					}
				}
				if (!check) {
					for (int j = 0; j < 4; j++) {
						int nr = r + dir[sharkDir[i][d][j]][0];
						int nc = c + dir[sharkDir[i][d][j]][1];
						if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
							continue;
						}
						if (smells[nr][nc].num == sharks[i].num) {
							sharks[i].r = nr;
							sharks[i].c = nc;
							sharks[i].d = sharkDir[i][d][j];
							break;
						}
					}

				}

			}

			// 냄새 -1씩

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (smells[i][j].time > 0) {
						smells[i][j].time--;
						if (smells[i][j].time == 0) {
							smells[i][j].num = 0;
						}
					}
				}
			}

			// 지우기+냄새 남기기
			for (int i = 1; i <= m; i++) {
				if (!sharkAlive[i]) {
					continue;
				}
				int r = sharks[i].r;
				int c = sharks[i].c;
				if (smells[r][c].num != 0 && smells[r][c].num < sharks[i].num) {
					sharkAlive[i] = false;
					count++;
					continue;
				}
				smells[r][c].num = i;
				smells[r][c].time = k;

			}

		}

		if (timecheck) {
			System.out.print(time);
		} else {
			System.out.print(-1);
		}
	}

}
