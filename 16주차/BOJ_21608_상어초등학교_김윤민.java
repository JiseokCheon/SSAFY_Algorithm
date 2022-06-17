package July;

import java.io.*;
import java.util.*;

public class BOJ_21608_상어초등학교_김윤민 {
	static int[][] move = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	static class Kan implements Comparable<Kan> {
		int i, j, like, empty;

		public Kan(int i, int j, int like, int empty) {
			this.i = i;
			this.j = j;
			this.like = like;
			this.empty = empty;
		}

		@Override
		public int compareTo(Kan o) { //우선순위 설정
			if (o.like != this.like) {
				return o.like - this.like;
			} else if (o.empty != this.empty) {
				return o.empty - this.empty;
			} else if (o.i != this.i) {
				return this.i - o.i;
			} else
				return this.j - o.j;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		int[][] like = new int[N * N + 1][4]; // 번호와 좋아하는 학생 번호 저장
		boolean[][] visit = new boolean[N][N];
		
		for (int i = 0; i < N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 4; j++) { // 좋아하는 학생 번호 저장
				like[num][j] = Integer.parseInt(st.nextToken());
			}
			
			PriorityQueue<Kan> pq = new PriorityQueue<Kan>();
			for (int p = 0; p < N; p++) { //모든 칸을 돌면서 좋아하는 학생수, 빈칸 확인해주기
				for (int q = 0; q < N; q++) {
					if(!visit[p][q]) { //방문안한 자리만 pq에 넣기
						pq.offer(new Kan(p, q, likes(p, q, map, like[num]), emptys(p, q, map)));
					}
				}
			}
			Kan k = pq.poll(); //가장 첫번째값
			map[k.i][k.j] = num; //자리 지정
			visit[k.i][k.j] = true;
		}

		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				switch(likes(i,j,map,like[map[i][j]])) {
				case 4 : ans+=1000;break;
				case 3 : ans+=100;break;
				case 2 : ans+=10;break;
				case 1 : ans+=1;break;
				}
			}
		}
		System.out.println(ans);
	}

	static int likes(int i, int j, int[][] map, int[] like) { //주변에 좋아하는 학생수 구하는 함수
		int ans = 0;
		for (int k = 0; k < 4; k++) {
			int ni = i + move[k][0];
			int nj = j + move[k][1];
			if (ni >= 0 && ni < map.length && nj >= 0 && nj < map.length) {
				for (int z = 0; z < like.length; z++) {
					if (map[ni][nj] == like[z]) {
						ans++;
					}
				}
			}
		}
		
		return ans;
	}

	static int emptys(int i, int j, int[][] map) { //주변에 빈칸 구하는 함수 
		int ans = 0;
		for (int k = 0; k < 4; k++) {
			int ni = i + move[k][0];
			int nj = j + move[k][1];
			if (ni >= 0 && ni < map.length && nj >= 0 && nj < map.length && map[ni][nj] == 0) {
				ans++;
			}
		}
		return ans;
	}
}
