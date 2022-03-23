package BJ_3190_뱀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] arr;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int n, l;

	public static class point {
		int r;
		int c;

		public point(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		arr = new int[n][n];

		for (int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			arr[r][c] = 1;
		}

		l = Integer.parseInt(br.readLine());
		int[] larr = new int[l];
		char[] lcarr = new char[l];
		for (int i = 0; i < l; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			larr[i] = Integer.parseInt(st.nextToken());
			lcarr[i] = st.nextToken().charAt(0);
		}
		Queue<point> q = new LinkedList<>();
		int answer = eat(0, 0, 0, larr, lcarr, 0, 0, q);

		System.out.println(answer);

	}

	private static int eat(int r, int c, int time, int[] larr, char[] lcarr, int cnt, int d, Queue<point> q) {
		q.offer(new point(r, c));
		arr[r][c] = 2; // 뱀 = 2 , 사과=1 없으면 = 0
		if (cnt < l && time == larr[cnt]) {
			if (lcarr[cnt] == 'D') {
				d = (d + 1) % 4;
			} else if (lcarr[cnt] == 'L') {
				d = (d + 3) % 4;
			}
			cnt++;
		}

		int tempr = r + dir[d][0];
		int tempc = c + dir[d][1];
		if (tempr < 0 || tempr >= n || tempc < 0 || tempc >= n || arr[tempr][tempc] == 2) {
			return time + 1;
		}

		if (arr[tempr][tempc] == 1) {
			arr[tempr][tempc] = 0;
			return eat(tempr, tempc, time + 1, larr, lcarr, cnt, d, q);
		} else {
			arr[q.peek().r][q.poll().c] = 0;
			return eat(tempr, tempc, time + 1, larr, lcarr, cnt, d, q);
		}

	}

}
