import java.util.*;
import java.io.*;

public class Main {

	static int[][] map;
	static int n, k;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		int count = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			map[0][i] = Integer.parseInt(st.nextToken());
		}
		while (true) {
			if (check()) {
				break;
			}
			minadd();
			heap();
			gap();
			line();
			levitation();
			levitation2();
			gap();
			line();
			count++;
		}

		System.out.print(count);

	}

	private static void levitation2() {
		int[][] temp = new int[n][n];
		for (int i = 0; i < n; i++) {
			if (i < n / 4) {
				temp[0][i] = map[0][n / 4 + i];
			} else if (i < n / 2) {
				temp[1][i - n / 4] = map[1][i];
			} else if (i < 3 * n / 4) {
				temp[3][i - n / 2] = map[0][3 * n / 4 - 1 - i];
			} else {
				temp[2][i - 3 * n / 4] = map[1][n - 1 - i];
			}
		}

		copy(temp);

	}

	private static void levitation() {
		int[][] temp = new int[n][n];
		for (int i = 0; i < n; i++) {
			if (i < n / 2) {
				temp[0][i] = map[0][n / 2 + i];
			} else {
				temp[1][i - n / 2] = map[0][n - i - 1];
			}
		}
		copy(temp);

	}

	private static void line() {
		int[][] temp = new int[n][n];
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			if (map[0][i] == 0) {
				break;
			}
			for (int j = 0; j < n; j++) {
				if (map[j][i] == 0) {
					break;
				}
				temp[0][cnt++] = map[j][i];
			}

		}

		copy(temp);

	}

	private static void gap() {
		int[][] temp = new int[n][n];
		int tempr, tempc, gab;
		for (int i = 0; i < n; i++) {
			if (map[i][0] == 0) {
				break;
			}
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 0) {
					break;
				}
				int num = map[i][j];
				int sum = 0;
				for (int d = 0; d < 4; d++) {
					tempr = i + dir[d][0];
					tempc = j + dir[d][1];
					if (tempr < 0 || tempr >= n || tempc < 0 || tempc >= n || map[tempr][tempc] == 0) {
						continue;
					}
					if (map[tempr][tempc] < map[i][j]) {
						gab = map[i][j] - map[tempr][tempc];
						temp[tempr][tempc] += gab / 5;
						num -= gab / 5;
					}
				}
				temp[i][j] += num;
			}
		}
		copy(temp);

	}

	private static void copy(int[][] arr) {

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = arr[i][j];
			}
		}
	}

	private static void heap() {
		boolean check = false;
		map[1][0] = map[0][0];
		for (int i = 0; i < n - 1; i++) {
			map[0][i] = map[0][i + 1];
		}
		map[0][n - 1] = 0;
		int rl = 2;
		int cl = 1;
		int[][] temp;
		while (n - cl * rl >= rl) {
			temp = new int[n][n];
			for (int i = 0; i < n - cl; i++) {
				temp[0][i] = map[0][i + cl];
			}

			for (int i = 0; i < cl; i++) {
				for (int j = 0; j < rl; j++) {
					temp[i + 1][j] = map[j][cl - 1 - i]; // 1 0 - > 0 1, 2 0 -> 0 0
				}
			}
			copy(temp);
			int t = rl;
			rl = cl + 1;
			cl = t;

		}

	}

	private static void print(int[][] arr) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.printf("%3d", arr[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void minadd() {
		Queue<Integer> q = new LinkedList<Integer>();

		int min = Integer.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			if (map[0][i] < min) {
				min = map[0][i];
				q.clear();
				q.offer(i);
			} else if (map[0][i] == min) {
				q.offer(i);
			}
		}

		while (!q.isEmpty()) {
			map[0][q.poll()]++;
		}

	}

	private static boolean check() {
		int max = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			if (map[0][i] > max) {
				max = map[0][i];
			}
			if (map[0][i] < min) {
				min = map[0][i];
			}
		}

		if (max - min <= k) {
			return true;
		}
		return false;

	}

}
