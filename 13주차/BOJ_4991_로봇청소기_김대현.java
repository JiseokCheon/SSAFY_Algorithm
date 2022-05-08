import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class point {
		int r, c;

		public point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int h, w;
	static int[][] map;
	static int[][] distance;
	static ArrayList<point> dust;
	static boolean[] visited;
	static int ans;
	static int[][] dir= {{-1,0},{0,1},{0,-1},{1,0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		while (true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			if (w + h == 0)
				break;

			map = new int[h][w];
			dust = new ArrayList<>();
			int a = 0, b = 0;
			for (int i = 0; i < h; i++) {
				char[] input = br.readLine().toCharArray();
				for (int j = 0; j < w; j++) {
					if (input[j] == 'o') {
						a = i;
						b = j;
						map[i][j] = 1;
					} else if (input[j] == '*') {
						dust.add(new point(i, j));
						map[i][j] = dust.size() + 1;
					} else if (input[j] == 'x') {
						map[i][j] = -1;
					} else {
						map[i][j] = 0;
					}
				}
			}

			distance = new int[dust.size() + 2][dust.size() + 2];

			bfs(1, a, b); //청소기부터 거리 구함

			for (int i = 0; i < dust.size(); i++) {
				bfs(i + 2, dust.get(i).r, dust.get(i).c);
			}

			ans = Integer.MAX_VALUE;

			// 만약 청소기가 갈 수 없는 곳이 있으면 -1
			for (int j = 2; j < distance[0].length; j++) {
				if (distance[1][j] == 0) {
					ans = -1;
				}
			}

			if (ans != -1) {
				// 순열 돌리며 정답 도출
				visited = new boolean[dust.size()];
				p(1, 0, 0);
			}
			sb.append(ans).append('\n');
		}
		System.out.println(sb);
	}

	static void p(int start, int cnt, int sum) {
		if (sum >= ans)
			return;

		if (cnt == dust.size()) {
			ans = Math.min(ans, sum);
			return;
		}

		for (int i = 0; i < dust.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				p(i + 2, cnt + 1, sum + distance[start][i + 2]);
				visited[i]= false;
			}
		}
	}


	static void bfs(int idx, int i, int j) {
		Queue<Integer> q = new LinkedList<>();
		int[][] visit = new int[h][w];

		q.add(i);
		q.add(j);
		visit[i][j] = 1;

		while (!q.isEmpty()) {
			int a = q.poll();
			int b = q.poll();

			int na, nb;
			for (int k = 0; k < 4; k++) {
				na = a + dir[k][0];
				nb = b + dir[k][1];

				if (na < 0 || na >= h || nb < 0 || nb >= w || map[na][nb] == -1 || visit[na][nb] != 0)
					continue;

				if (map[na][nb] > 0) {
					distance[idx][map[na][nb]] = distance[map[na][nb]][idx] = visit[a][b];
				}

				q.add(na);
				q.add(nb);
				visit[na][nb] = visit[a][b] + 1;
			}
		}
	}

}
