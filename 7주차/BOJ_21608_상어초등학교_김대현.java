package BJ_21608_상어초등학교;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[][] arr = new int[n][n];
		int[][] stu = new int[n * n][7];
		StringTokenizer st;
		for (int i = 0; i < n * n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				stu[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int r = 0, c = 0;
		int maxL, maxS;
		int space, like;
		int tempr, tempc;
		int[][] dir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
		for (int sn = 0; sn < n * n; sn++) { // 학생 순서대로 돌면서

			maxL = 0;
			maxS = -1;
			for (int i = 0; i < n; i++) { // 배열 전체를 확인
				for (int j = 0; j < n; j++) {
					like = 0;
					space = 0;
					if (arr[i][j] != 0) {
						continue;
					}
					for (int k = 0; k < 4; k++) { // 4방 탐색
						tempr = i + dir[k][0];
						tempc = j + dir[k][1];
						if (tempr >= 0 && tempr < n && tempc >= 0 && tempc < n) {
							if (arr[tempr][tempc] == 0) {
								space++; // 가능한 공간이면 ++
							}
							for (int l = 1; l < 5; l++) { // 좋아하는 학생 체크
								if (arr[tempr][tempc] == stu[sn][l]) { // sn번째 학생의 좋아하는 학생들

									like++;
									break;
								}
							}
						}
					}

					if (like > maxL) {
						r = i;
						c = j;
						maxL = like;
						maxS = space;
					}

					if (like == maxL) {
						if (space > maxS) {
							r = i;
							c = j;
							maxL = like;
							maxS = space;
						}
					}

				}
			}

			arr[r][c] = stu[sn][0];
			stu[sn][5] = r;
			stu[sn][6] = c;

		}
		int count;
		int sum = 0;

		for (int i = 0; i < n * n; i++) {
			r = stu[i][5];
			c = stu[i][6];
			count = 0;
			for (int j = 0; j < 4; j++) {
				tempr = r + dir[j][0];
				tempc = c + dir[j][1];
				if (tempr >= 0 && tempr < n && tempc >= 0 && tempc < n) {
					for (int l = 1; l < 5; l++) { // 좋아하는 학생 체크
						if (arr[tempr][tempc] == stu[i][l]) { // i번째 학생의 좋아하는 학생들
							count++;
							break;
						}
					}
				}
			}
			if (count == 4) {
				sum += 1000;
			} else if (count == 3) {
				sum += 100;
			} else if (count == 2) {
				sum += 10;
			} else if (count == 1) {
				sum++;
			}
		}
		System.out.print(sum);

	}

}
