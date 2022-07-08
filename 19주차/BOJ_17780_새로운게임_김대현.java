package BJ_17780_새로운게임;

import java.io.*;
import java.util.*;

public class Main {

	static int[][] dir = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
	static int n, k;
	static ArrayList<horse>[][] horses;
	static horse[] h;
	static int[][] map;

	public static class horse {
		int r, c, d, index;
		boolean floor;

		public horse(int r, int c, int d, boolean floor, int index) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.floor = floor;
			this.index = index;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		horses = new ArrayList[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				horses[i][j] = new ArrayList<horse>();
			}
		}
		h = new horse[k];

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			h[i] = new horse(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()) - 1, true, i);
			horses[h[i].r][h[i].c].add(h[i]);
		}

		int count = 0;
		while (count <= 1000) {

			if (check(horses)) {
				break;
			}
			for (int i = 0; i < k; i++) {
				if (h[i].floor) {
					move(i);
				}
			}

			count++;
		}
		if (count > 1000) {
			System.out.print(-1);
		} else {
			System.out.print(count);
		}

	}

	private static void move(int i) {
		horse horse = h[i];

		int r = horse.r;
		int c = horse.c;

		int tempr = r + dir[horse.d][0];
		int tempc = c + dir[horse.d][1];

		if (tempr < 0 || tempr >= n || tempc < 0 || tempc >= n || map[tempr][tempc] == 2) { // 파
			if (h[i].d % 2 == 0) {
				h[i].d += 1;
			} else {
				h[i].d -= 1;
			}
			tempr = r + dir[h[i].d][0];
			tempc = c + dir[h[i].d][1];
			if (tempr < 0 || tempr >= n || tempc < 0 || tempc >= n || map[tempr][tempc] == 2) {

			} else if (map[tempr][tempc] == 0) { // 흰
				// 그대로 옮김
				for (int j = 0; j < horses[r][c].size(); j++) {
					horse temp = h[horses[r][c].get(j).index];
					horses[tempr][tempc].add(new horse(tempr, tempr, temp.d, false, temp.index));
					h[temp.index].r = tempr;
					h[temp.index].c = tempc;
					h[temp.index].floor = false;
				}
				horses[r][c].clear();
				horses[tempr][tempc].get(0).floor = true;
				h[horses[tempr][tempc].get(0).index].floor = true;
			} else if (map[tempr][tempc] == 1) { // 빨
				// 순서 바꿔서 옮김
				for (int j = horses[r][c].size() - 1; j >= 0; j--) {
					horse temp = h[horses[r][c].get(j).index];
					horses[tempr][tempc].add(new horse(tempr, tempr, temp.d, false, temp.index));
					h[temp.index].r = tempr;
					h[temp.index].c = tempc;
					h[temp.index].floor = false;
				}
				horses[r][c].clear();
				horses[tempr][tempc].get(0).floor = true;
				h[horses[tempr][tempc].get(0).index].floor = true;

			}
		} else if (map[tempr][tempc] == 0) { // 흰
			// 그대로 옮김
			for (int j = 0; j < horses[r][c].size(); j++) {
				horse temp = h[horses[r][c].get(j).index];
				horses[tempr][tempc].add(new horse(tempr, tempr, temp.d, false, temp.index));
				h[temp.index].r = tempr;
				h[temp.index].c = tempc;
				h[temp.index].floor = false;
			}
			horses[r][c].clear();
			horses[tempr][tempc].get(0).floor = true;
			h[horses[tempr][tempc].get(0).index].floor = true;
		} else if (map[tempr][tempc] == 1) { // 빨
			// 순서 바꿔서 옮김
			for (int j = horses[r][c].size() - 1; j >= 0; j--) {
				horse temp = h[horses[r][c].get(j).index];
				horses[tempr][tempc].add(new horse(tempr, tempr, temp.d, false, temp.index));
				h[temp.index].r = tempr;
				h[temp.index].c = tempc;
				h[temp.index].floor = false;
			}
			horses[r][c].clear();
			horses[tempr][tempc].get(0).floor = true;
			h[horses[tempr][tempc].get(0).index].floor = true;

		}

	}

	private static boolean check(ArrayList<horse>[][] horses) {

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (horses[i][j].size() >= 4) {
					return true;
				}
			}
		}

		return false;
	}

}
