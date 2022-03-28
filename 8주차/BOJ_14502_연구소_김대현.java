package BJ_14502_연구소;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, m, max = 0;
	static int[][] dir = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
	static int aa=0;
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		comb(arr, 0, 0, 0);
		System.out.println(max);

	}

	private static void comb(int[][] arr, int r, int c, int cnt) {
		if (r == n && cnt !=3) {
			return;
		}
		if (cnt == 3) {
			aa++;
			
			int[][] temp = new int[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					temp[i][j] = arr[i][j];
				}
			}
		

			boolean[][] check = new boolean[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (temp[i][j] == 2 && !check[i][j]) {
						check[i][j] = true;
						infection(temp, check, i, j);
					}
				}
			}
			getArea(temp, check);
		} else {
			int tempc = (c + 1) % m;
			int tempr = r;
			
			if ((c + 1) / m == 1) {
				tempr++;
			}

			
			if (arr[r][c] == 0) {
				arr[r][c] = 1;
				comb(arr, tempr, tempc, cnt + 1);
				arr[r][c] = 0;
				comb(arr, tempr, tempc, cnt);
			} else {
				comb(arr, tempr, tempc, cnt);
			}
		}

	}

	private static void infection(int[][] arr, boolean[][] check, int r, int c) {
		int tempr, tempc;
		for (int i = 0; i < 4; i++) {
			tempr = r + dir[i][0];
			tempc = c + dir[i][1];

			if (checks(tempr, tempc) && !check[tempr][tempc] && arr[tempr][tempc] == 0) {
				arr[tempr][tempc] = 2;
				check[tempr][tempc] = true;
				infection(arr, check, tempr, tempc);
			}
		}
	}

	private static boolean checks(int r, int c) {
		if (r < 0 || r >= n || c < 0 || c >= m) {
			return false;
		}
		return true;
	}

	private static void getArea(int[][] arr, boolean[][] check) {
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 0) {
					count++;
				}
			}
		}
		if (max < count) {
			max = count;
		}
	}

}
