import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static class point implements Comparable<point> {
		int r;
		int c;

		public point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(point o) {
			if (this.c == o.c) {
				return o.r - this.r;
			}
			return this.c - o.c;
		}

	}

	static char[][] map;
	static int r, c;
	static int[][] dir = { { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 } };// 아래,왼,오,위

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		for (int i = 0; i < r; i++) {
			String str = br.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int tempr, tempc;
		PriorityQueue<point> pq = new PriorityQueue<point>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {

			if (i % 2 == 0) {
				for (int j = 0; j < c; j++) {
					if (map[r - arr[i]][j] == 'x') {
						map[r - arr[i]][j] = '.';
						if (check(r - arr[i], j)) {
							for (int k = 0; k < 4; k++) {
								tempr = r - arr[i] + dir[k][0];
								tempc = j + dir[k][1];
								if (tempr < 0 || tempr >= r || tempc < 0 || tempc >= c)
									continue;
								if (map[tempr][tempc] == 'x') {
									if (!bottom(tempr, tempc)) {
										addPQ(tempr, tempc, pq);
										down(pq);
										break;
									}
								}
							}
						}
						break;
					}
				}

			} else {
				for (int j = c - 1; j >= 0; j--) {
					if (map[r - arr[i]][j] == 'x') {
						map[r - arr[i]][j] = '.';
						if (check(r - arr[i], j)) {
							for (int k = 0; k < 4; k++) {
								tempr = r - arr[i] + dir[k][0];
								tempc = j + dir[k][1];
								if (tempr < 0 || tempr >= r || tempc < 0 || tempc >= c)
									continue;
								if (map[tempr][tempc] == 'x') {
									if (!bottom(tempr, tempc)) {
										addPQ(tempr, tempc, pq);
										down(pq);
										break;
									}
								}
							}
						}
						break;
					}
				}
			}

		}
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				sb.append(map[i][j]);
			}
			if (i != r - 1) {
				sb.append("\n");

			}
		}
		System.out.print(sb);

	}

	private static void down(PriorityQueue<point> pq) {
		int term = 0;
		PriorityQueue<point> pq2 = new PriorityQueue<point>();
		int min = r;
		boolean[][] check=new boolean[r][c];
		while (!pq.isEmpty()) {
			point p = pq.poll();
			pq2.offer(p);
			check[p.r][p.c]=true;

			term = r - 1 - p.r;
			for (int i = 2; p.r + i < r; i++) {
				if (map[p.r + i][p.c] == 'x' && !check[p.r+i][p.c]) {
					term = i - 1;
					break;
				}
			}
			if (term < min) {

				min = term;
			}

		}
		while (!pq2.isEmpty()) {
			point p = pq2.poll();
			map[p.r][p.c] = '.';
			map[p.r + min][p.c] = 'x';
		}

	}

	private static void addPQ(int tempr, int tempc, PriorityQueue<point> pq) {
		pq.clear();
		Queue<point> q = new LinkedList<point>();
		pq.offer(new point(tempr, tempc));
		q.offer(new point(tempr, tempc));
		boolean[][] check = new boolean[r][c];

		while (!q.isEmpty()) {
			point p = q.poll();

			for (int i = 0; i < 4; i++) {
				tempr = p.r + dir[i][0];
				tempc = p.c + dir[i][1];
				if (tempr < 0 || tempr >= r || tempc < 0 || tempc >= c) {
					continue;
				}

				if (map[tempr][tempc] == 'x' && !check[tempr][tempc]) {
					check[tempr][tempc] = true;
					q.offer(new point(tempr, tempc));
					pq.offer(new point(tempr, tempc));
				}
			}

		}

	}

	private static boolean bottom(int tempr, int tempc) {
		Queue<point> q = new LinkedList<point>();
		q.offer(new point(tempr, tempc));
		boolean[][] check = new boolean[r][c];
		check[tempr][tempc] = true;
		while (!q.isEmpty()) {
			point p = q.poll();
			if (p.r == r - 1) {
				return true;
			}
			for (int i = 0; i < 4; i++) {
				tempr = p.r + dir[i][0];
				tempc = p.c + dir[i][1];
				if (tempr < 0 || tempr >= r || tempc < 0 || tempc >= c) {
					continue;
				}

				if (map[tempr][tempc] == 'x' && !check[tempr][tempc]) {
					check[tempr][tempc] = true;
					q.offer(new point(tempr, tempc));
				}
			}

		}
		return false;

	}

	private static boolean check(int i, int j) {
		int cnt = 0;
		if (j - 1 >= 0 && map[i][j - 1] == 'x') {
			cnt++;
		}
		if (j + 1 < c && map[i][j + 1] == 'x') {
			cnt++;
		}

		if (i - 1 >= 0 && map[i - 1][j] == 'x') {
			cnt++;
		}

		if (cnt > 0) {
			return true;
		} else {
			return false;

		}
	}

}
