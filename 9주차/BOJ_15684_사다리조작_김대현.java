package BJ_15684_사다리조작;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int M;
	static int H;
	static boolean[][] check;
	static int answer = -1;

	static void solve() {
		for (int i = 0; i <= 3; i++) {
			if (dfs(i)) {
				answer = i;
				break;
			}
		}
	}

	static boolean dfs(int depth) {
		if (depth == 0) {
			return check();
		}
		for (int i = 1; i <= H; i++) {
			for (int j = 1; j <= N; j++) {
				if (!check[i][j] && !check[i][j - 1] && !check[i][j == N ? j : j + 1]) {
					check[i][j] = true;
					if (dfs(depth - 1))
						return true;
					check[i][j] = false;
				}
			}
		}
		return false;
	}

	static boolean check() {
		for (int i = 1; i <= N; i++) {
			int current_x = i;
			for (int j = 1; j <= H; j++) {
				if (check[j][current_x - 1]) {
					current_x--;
				} else if (current_x < N && check[j][current_x]) {
					current_x++;
				}
			}
			if (current_x != i)
				return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		check = new boolean[H + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			check[x][y] = true;
		}

		solve();

		System.out.println(answer);
	}
}
