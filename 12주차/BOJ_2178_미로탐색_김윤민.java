import java.io.*;
import java.util.*;

public class BOJ_2178_미로탐색_김윤민 {
	static int[][] move = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static char[][] map;
	static int[][] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		map = new char[N + 1][M + 1];
		dist = new int[N + 1][M + 1];
		for (int i = 1; i <=N ; i++) {
			Arrays.fill(dist[i], N * M);
		}
		for (int i = 1; i <= N; i++) {
			String s1 = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j + 1] = s1.charAt(j);
			}
		}
		// 1,1에서 출발해서 N,M으로 도착
		// bfs , 4방탐색을 하면서 1,1으로부터 걸리는 거리를 dist배열에 넣기, 만약 넣을 거리가 현재 배열에 존재하는 거리보다 더 크다면
		// 넣지 않기
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] { 1, 1 });
		dist[1][1] = 1;

		while (!que.isEmpty()) {
			int[] temp = que.poll();
			int i = temp[0];
			int j = temp[1];
			if (i == N && j == M) { // 기저조건
				continue;
			}
			// 4방탐색
			for (int k = 0; k < 4; k++) {
				int ni = i + move[k][0];
				int nj = j + move[k][1];
				if (ni > 0 && ni <= N && nj > 0 && nj <= M && map[ni][nj] == '1') { // 범위와 갈 수 있는 길인지 확인
					if (dist[ni][nj] > dist[i][j] + 1) { // dist[ni][nj]에 있는 기존 값보다 dist[i][j]+1가 더 작으면 que에 넣기, dist 갱신
						que.offer(new int[] { ni, nj });
						dist[ni][nj] = dist[i][j] + 1;
					}
				}
			}
		}
		System.out.println(dist[N][M]);

	}
}
