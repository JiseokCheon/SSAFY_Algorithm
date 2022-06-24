package BJ_21611_마법사상어와블리자드;

import java.util.*;
import java.io.*;

public class Main {
	static int n, m;
	static int[][] dir = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };
	static int[] marble;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int d;
		int[][] shoot = new int[m][2];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			switch (d) { // 4방을 왼,아,오,위 순서로 바꿔서 저장
			case 1:
				shoot[i][0] = 3;
				break;
			case 2:
				shoot[i][0] = 1;
				break;
			case 3:
				shoot[i][0] = 0;
				break;
			case 4:
				shoot[i][0] = 2;
				break;
			}

			shoot[i][1] = Integer.parseInt(st.nextToken());
		}

		marble = new int[n * n - 1]; // 1차원 배열로 나타낸 구슬
		int r = n / 2; // 중간부분부터 시작
		int c = n / 2;
		d = 0;
		int dl = 1;
		int dn = 0;
		for (int i = 0; i < n * n - 1; i++) {
			r += dir[d][0];
			c += dir[d][1];
			marble[i] = map[r][c];
			dn++;
			if (dn == dl) {
				dn = 0;
				d = (d + 1) % 4;
				if (d % 2 == 0) {
					dl++;
				}
			}
		}

		int[][] broken = new int[4][n / 2]; // 깨지는 구슬의 index를 저장
		int temp = 0;
		int term = 1;
		for (int i = 0; i < 2 * (n - 1); i++) {
			if (i % 4 == 3 || i % 4 == 0) {
				term++;
			}
			broken[i % 4][i / 4] = temp;
			temp += term;

		}

		answer = 0;
		for (int i = 0; i < m; i++) {
			int k = shoot[i][1];
			for (int j = 0; j < k; j++) { // 구슬 깨기
				marble[broken[shoot[i][0]][j]] = 0;
			}
			back();
			while (remove()) {
				back();
			}
			group();
		}

		System.out.println(answer);

	}

	private static void group() {
		int[] temp = new int[n * n - 1];
		int num = marble[0];
		int count = 1;
		int index = 0;
		for (int i = 1; i < n * n - 1; i++) {

			if (marble[i] == num) {
				count++;
			} else {
				temp[index * 2] = count;
				temp[index * 2 + 1] = num;
				index++;
				num = marble[i];
				count = 1;
			}

			if (marble[i] == 0 || index * 2 == n * n - 1) {
				break;
			}
		}
		for (int i = 0; i < n * n - 1; i++) {
			marble[i] = temp[i];
		}

	}

	private static boolean remove() {
		boolean check = false;
		int now = marble[0];
		int count = 1;
		for (int i = 1; i < n * n - 1; i++) {
			if (marble[i] == now) {
				count++;
			} else {
				if (count >= 4) {
					check = true;
					answer += count * now;
					for (int k = 1; k <= count; k++) {
						marble[i - k] = 0;
					}
				}
				count = 1;
				now = marble[i];

			}

		}

		return check;

	}
	
	private static void back() {
		int last=0;
		for(int i=0; i<n*n-1; i++) {
			if(marble[i]==0) continue;
			marble[last++]=marble[i];
		}
		for(int i=last; i<n*n-1;i++) {
			marble[i]=0;
		}
	}
	


}
