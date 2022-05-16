package BJ_19238_스타트택시;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map, taxi;
	static boolean[][] wall;
	static int[][] dir = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
	static int answer, n, m;

	public static class p implements Comparable<p> {
		int r, c, k;
		public p(int r, int c, int k) {
			this.r = r;
			this.c = c;
			this.k = k;
		}
		
		@Override
		public int compareTo(p o) {
			if (this.r == o.r) {
				return this.c - o.c;
			} else {
				return this.r - o.r;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		wall = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					wall[i][j] = true;
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		taxi = new int[m + 1][4];
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				taxi[i][j] = Integer.parseInt(st.nextToken()) - 1;
			}
			map[taxi[i][0]][taxi[i][1]] = i;

		}

		if (bfs(r, c, k,0)) {
			System.out.print(answer);
		} else {
			System.out.print(-1);
		}

	}

	private static boolean bfs(int r, int c, int k,int cnt) {
		
		if(cnt==m) {
			answer=k;
			return true;
		}
		Queue<p> q = new LinkedList<p>();
		q.offer(new p(r, c, 0));
		PriorityQueue<p> pq = new PriorityQueue<p>();
		int canK = k;
		boolean[][] visited = new boolean[n][n];
		visited[r][c] = true;
		while (!q.isEmpty()) {
			p p = q.poll();
			int nr = p.r;
			int nc = p.c;
			int nk = p.k;
			if (nk > canK) { // 거리가 더 먼곳은 패스
				continue;
			}
			if (map[nr][nc] > 0) {
				canK = nk;
				pq.offer(new p(nr, nc, nk));
			}
			for (int i = 0; i < 4; i++) {
				int tr = nr + dir[i][0];
				int tc = nc + dir[i][1];
				if (!check(tr, tc) || wall[tr][tc] || visited[tr][tc]) { // 범위 밖이거나
					continue;
				}
				q.offer(new p(tr, tc, nk +1));
				visited[tr][tc] = true;
			}

		}
		if (pq.isEmpty()) {
			return false;
		}
		p p = pq.peek();
		int nr = p.r;
		int nc = p.c;
		int restK =k- p.k;
		int num= map[nr][nc];
		map[nr][nc]=0;
		q.offer(new p(nr,nc,0));
		boolean[][] visit = new boolean[n][n];
		visit[nr][nc] = true;
		canK=restK;
		int nk=0;
		boolean end=false;
		while (!q.isEmpty()) {
			p p2 = q.poll();
			nr = p2.r;
			nc = p2.c;
			nk = p2.k;
			if (nr==taxi[num][2] && nc==taxi[num][3]) {
				
				end=true;
				break;
			}
			if (nk >= canK) { // 거리가 더 먼곳은 패스
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int tr = nr + dir[i][0];
				int tc = nc + dir[i][1];
				
				if (!check(tr, tc) || wall[tr][tc] || visit[tr][tc]) { // 범위 밖이거나
					continue;
				}
				q.offer(new p(tr, tc, nk +1));
				visit[tr][tc] = true;
			}

		}
		if(!end) {
			return false;
		}else {
			return bfs(nr, nc, restK+nk,cnt+1);
			
		}

	}

	private static boolean check(int r, int c) {
		if (r < 0 || r >= n || c < 0 || c >= n) {
			return false;
		}
		return true;
	}

}
