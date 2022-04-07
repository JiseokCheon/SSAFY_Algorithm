package study_mar;

import java.io.*;
import java.util.*;

public class BJ_21608_상어초등학교 {
	static class Seat implements Comparable<Seat> {
		int r, c, like, empty;
		public Seat(int r, int c, int like, int empty) {
			this.r = r;
			this.c = c;
			this.like = like;
			this.empty = empty;
		}
		@Override
		public String toString() {
			return "Seat [r=" + r + ", c=" + c + ", like=" + like + ", empty=" + empty + "]";
		}

		@Override
		public int compareTo(Seat o) {
			if(o.like == this.like) {
				return o.empty-this.empty;
			}else return o.like-this.like;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		int[][] move = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] likes = new int[N * N + 1][4];
		int[] order = new int[N * N + 1];
		for (int i = 1; i <= N * N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			order[i] = n;
			for (int j = 0; j < 4; j++) {
				likes[n][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[][] map = new int[N + 1][N + 1];
		for (int o = 1; o <= N * N; o++) { // 순서대로 자리 배치하기
			int n = order[o];
			PriorityQueue<Seat> pq = new PriorityQueue<>();
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (map[i][j] == 0) {
						int empty=0;
						int like=0;
						for (int d = 0; d < 4; d++) {
							int ni = i + move[d][0];
							int nj = j + move[d][1];
							if (ni > 0 && ni <= N && nj > 0 && nj <= N) {
								if (map[ni][nj] == 0)
									empty++;
								else {
									for (int k = 0; k < 4; k++) {
										if (map[ni][nj] == likes[n][k]) {
											like++;
											break;
										}
									}
								}
							}
						}
						pq.offer(new Seat(i,j,like, empty));
					}
				}
			}
			Seat sel = pq.poll(); // 제일 우선순위인 자리 선택
			map[sel.r][sel.c] = n;
		}
		// 만족도 계산
		long score = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int n = map[i][j];
				int cnt=0;
				for (int d = 0; d < 4; d++) {
					int ni = i + move[d][0];
					int nj = j + move[d][1];
					if (ni > 0 && ni <= N && nj > 0 && nj <= N) {
						for (int k = 0; k < 4; k++) {
							if (map[ni][nj] == likes[n][k]) {
								cnt++;
								break;
							}
						}
					}
				}
				if(cnt==1) score+=1;
				else if(cnt==2) score+=10;
				else if(cnt==3) score+=100;
				else if(cnt==4) score+=1000;
			}
		}
		System.out.println(score);
	}
}

