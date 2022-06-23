import java.io.*;
import java.util.*;

public class Main {
	static int[][] d = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static boolean[][] visited;
	static char[][] arr;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		int count = 0;
		arr = new char[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = s.charAt(j);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					count++;
					visited[i][j] = true;
					dfs(arr[i][j], i, j);
				}
				if (arr[i][j] == 'R')
					arr[i][j] = 'G';
			}
		}
		System.out.print(count + " ");

		visited = new boolean[N][N];
		count = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					count++;
					visited[i][j] = true;
					dfs(arr[i][j], i, j);
				}
			}
		}

		System.out.print(count);
	}

	static void dfs(char c, int x, int y) {

		for (int i = 0; i < 4; i++) {
			int nextX = x + d[i][0];
			int nextY = y + d[i][1];
			if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && !visited[nextX][nextY]
					&& arr[nextX][nextY] == c) {
				visited[nextX][nextY] = true;
				dfs(c, nextX, nextY);
			}
		}
	}
}
