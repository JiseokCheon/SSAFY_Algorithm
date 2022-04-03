package BJ_3197_백조의호수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int r, c, now = 0, next = 1;
	static char arr[][];
	static boolean[][] lvisit, iceMelt;
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 0 } };
	static Queue<Point>[] wq, lq;
	static Point L[] = new Point[2]; // 백조 위치 저장

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken()); // 행 크기
		c = Integer.parseInt(st.nextToken()); // 열 크기
		arr = new char[r][c];
		// 물과 빙판 위치를 저장하는 Queue
		wq = new LinkedList[2];
		wq[0] = new LinkedList<>();
		wq[1] = new LinkedList<>();
		// 백조가 현재와 다음 날 탐색할 위치를 저장하는 Queue
		lq = new LinkedList[2];
		lq[0] = new LinkedList<>();
		lq[1] = new LinkedList<>();

		int cnt = 0;

		for (int i = 0; i < r; i++) {
			arr[i] = br.readLine().toCharArray();
			for (int j = 0; j < c; j++) {
				// 두 마리의 백조 위치 저장
				if (arr[i][j] == 'L') {
					L[cnt++] = new Point(i, j);
					arr[i][j] = '.';
				}
				// 물의 위치를 저장
				if (arr[i][j] == '.') {
					wq[0].add(new Point(i, j));
				}
			}
		}

		System.out.println(process());
	}

	private static int process() {

		int time = 0;
		lvisit = new boolean[r][c];
		iceMelt = new boolean[r][c];
		// 0번 백조가 1번 백조를 향해
		lq[0].add(L[0]);
		lvisit[L[0].r][L[0].c] = true;

		while (true) {
			// 두 마리의 백조가 만날 수 있는지 확인
			if (check())
				return time;
			// 빙하가 녹는다
			melt();
			// index switch
			now = (now + 1) % 2;
			next = (next + 1) % 2;

			time++;
		}
	}

	private static boolean check() {
		// 현재 백조가 갈 수 있는 구간을 모두 탐색
		while (!lq[now].isEmpty()) {
			Point p = lq[now].poll();

			for (int i = 0; i < 4; i++) {
				int tempr = p.r + dir[i][0];
				int tempc = p.c + dir[i][1];

				if (tempr < 0 || tempc < 0 || tempr >= r || tempc >= c || lvisit[tempr][tempc])
					continue;
				// 상대 백조를 만났을 경우 return
				if (tempr == L[1].r && tempc == L[1].c)
					return true;

				lvisit[tempr][tempc] = true;
				// 빙하일 경우 다음 날 탐색을 위해 Queue에 추가
				if (arr[tempr][tempc] == 'X') {
					lq[next].add(new Point(tempr, tempc));
				} else {
					lq[now].add(new Point(tempr, tempc));
				}
			}
		}

		return false;
	}

	private static void melt() {
		// 물이 있는 구간을 모두 탐색
		while (!wq[now].isEmpty()) {

			Point p = wq[now].poll();

			for (int i = 0; i < 4; i++) {
				int tempr = p.r + dir[i][0];
				int tempc = p.c + dir[i][1];
				// 범위를 초과하거나 이미 방문했을 경우
				if (tempr < 0 || tempc < 0 || tempr >= r || tempc >= c || lvisit[tempr][tempc] || iceMelt[tempr][tempc])
					continue;
				iceMelt[tempr][tempc] = true;
				// 빙산일 경우
				if (arr[tempr][tempc] == 'X') {
					// 물 공간과 접촉한 모든 빙판 공간이 녹는다.
					arr[tempr][tempc] = '.';
					wq[next].add(new Point(tempr, tempc));
				}
			}
		}
	}

}
