package BJ_14500_테트로미노;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int max = 0;
	static int[][] arr;
	static int n, m;
	static int[][] dir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	static boolean[][] checks;

	public static class point {
		int r;
		int c;
		int cnt;
		int sum;

		public point(int r, int c, int cnt, int sum) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.sum = sum;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		checks = new boolean[n][m];
		boolean[][] check;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				checks[i][j] = true;
				findmax(i, j, arr[i][j], 1);
				checks[i][j] = false;

				findmax2(i, j);
			}
		}
		System.out.println(max);

	}

	private static void findmax2(int r, int c) {// 凸 모양....
		int temp = arr[r][c];
		int tempr, tempc;
		int count=0;
		int min=1001;
		for (int i = 0; i < 4; i++) {
			tempr = r + dir[i][0];
			tempc = c + dir[i][1];
			if (tempr >= 0 && tempr < n && tempc >= 0 && tempc < m) {
				temp += arr[tempr][tempc];
				count++;
				if(min>arr[tempr][tempc]) {
					min=arr[tempr][tempc];
				}
			}
		}
		if(count==4) {
			temp=temp-min;
		}
		if(max<temp) {
			max=temp;
		}

	}

	private static void findmax(int r, int c, int sum, int cnt) { // 나머지
		int tempr, tempc;

		if (cnt == 4) {
			if (max < sum) {
				max = sum;
			}
		} else {
			for (int i = 0; i < 4; i++) {
				tempr = r + dir[i][0];
				tempc = c + dir[i][1];
				if (tempr >= 0 && tempr < n && tempc >= 0 && tempc < m && !checks[tempr][tempc]) {
					checks[tempr][tempc] = true;
					findmax(tempr, tempc, sum + arr[tempr][tempc], cnt + 1);
					checks[tempr][tempc] = false;
				}
			}

		}

	}

}
