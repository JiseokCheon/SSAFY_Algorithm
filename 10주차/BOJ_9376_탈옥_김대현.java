package BJ_9376_탈옥;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

	private static class point implements Comparable<point> {
		int r, c, cnt;

		private point(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(point o) {
			return this.cnt - o.cnt;
		}
	}

	private static int h, w;
	private static char[][] map;
	private static int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	private static int[][] door, prisoner;
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < t; tc++) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			map = new char[h + 2][w + 2];
			door = new int[h + 2][w + 2];
			prisoner = new int[2][2];
			int cnt = 0;

			for (int i = 1; i <= h; i++) {
				String str = br.readLine();
				for (int j = 1; j <= w; j++) {
					map[i][j] = str.charAt(j - 1);
					if (map[i][j] == '$') {
						prisoner[cnt][0] = i;
						prisoner[cnt][1] = j;
						cnt++;
					}
				}
			}

			for (int i = 0; i <= w + 1; i++) {
				map[0][i] = '.';
				map[h + 1][i] = '.';
			}
			for (int i = 0; i <= h + 1; i++) {
				map[i][0] = '.';
				map[i][w + 1] = '.';
			}

			if(check()) {
				sb.append("0").append("\n");
			}else {
				bfs(0,0);
				bfs(prisoner[0][0],prisoner[0][1]);
				bfs(prisoner[1][0],prisoner[1][1]);

				sb.append(getSum()).append("\n");
			}

		}
		System.out.print(sb);
	}
	
	private static boolean check(){
        Queue<point> q = new LinkedList<>();
        visited = new boolean[h+2][w+2];
        q.add(new point(0,0,0));
        visited[0][0] = true;
        int cnt = 0;
        int tempr,tempc;

        while(!q.isEmpty()) {
            point n = q.poll();

            for (int i = 0; i < 4; ++i) {
                tempr = n.r + dir[i][0];
                tempc = n.c + dir[i][1];

                if (tempr < 0 || tempr >= h + 2 || tempc < 0 || tempc >= w + 2) continue;
                if (visited[tempr][tempc]) continue;
                if (map[tempr][tempc] == '*' || map[tempr][tempc] == '#') continue;

                visited[tempr][tempc] = true;
                if (map[tempr][tempc] == '$') cnt++;
                q.add(new point(tempr, tempc, 0));
            }
        }
        if(cnt == 2) return true;
        else return false;

    }

	private static int getSum() {
		int min = Integer.MAX_VALUE;

		for (int i = 1; i < h + 1; ++i) {
			for (int j = 1; j < w + 1; ++j) {
				if (map[i][j] == '#') {
					min = Math.min(min, door[i][j]-2);
				}
			}
		}
		return min ;
	}

	private static void bfs(int r, int c) {
		PriorityQueue<point> q = new PriorityQueue<>();
		visited = new boolean[h + 2][w + 2];
		q.add(new point(r, c, 0));
		visited[r][c] = true;
		
		int tempr;
		int tempc;
		while (!q.isEmpty()) {
			point n = q.poll();

			for (int j = 0; j < 4; ++j) {
				tempr = n.r + dir[j][0];
				tempc = n.c + dir[j][1];

				if (tempr < 0 || tempr >= h + 2 || tempc < 0 || tempc >= w + 2)
					continue;
				if (visited[tempr][tempc] || map[tempr][tempc] == '*')
					continue;

				visited[tempr][tempc] = true;

				if (map[tempr][tempc] == '#') {
					door[tempr][tempc] += n.cnt + 1;
					q.add(new point(tempr, tempc, n.cnt + 1));
				} else {
					door[tempr][tempc] += n.cnt ;
					q.add(new point(tempr, tempc, n.cnt));
				}

			}
		}
	}
}
