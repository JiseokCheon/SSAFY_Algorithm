import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_10026_적록색약_김윤민 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[][] map = new char[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		StringBuilder sb = new StringBuilder();
		// 적록색약 아닌 경우
		sb.append(bfs(map, 1)+ " ");
		// 적록색약인 경우
		sb.append(bfs(map, 0));
		System.out.println(sb);
	}

	private static int bfs(char[][] map, int mode) {
		int[][] move = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
		int ans = 0;
		int n = map.length;
		boolean[][] visit = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visit[i][j]) {
					ans++;
					char color = map[i][j];
					Queue<int[]> que = new LinkedList<int[]>();
					visit[i][j] = true;
					que.offer(new int[] { i, j });
					while (!que.isEmpty()) {
						int p = que.peek()[0];
						int q = que.poll()[1];
						for (int k = 0; k < 4; k++) {
							int ni = p + move[k][0];
							int nj = q + move[k][1];
							if (ni >= 0 && ni < n & nj >= 0 && nj < n && !visit[ni][nj]) {
								if (mode == 0) { //적록색약인 경우
									if (color == 'B' && map[ni][nj] != 'B')	continue;
									else if (color != 'B' && map[ni][nj] == 'B')continue;
								} else if (mode == 1 && color != map[ni][nj])continue;
								que.offer(new int[] { ni, nj });
								visit[ni][nj] = true;
							}
						}
					}
				}
			}
		}
		return ans;
	}

}
