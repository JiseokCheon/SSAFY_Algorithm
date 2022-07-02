import java.io.*;
import java.util.*;

public class BOJ_23290_마법사상어와복제 {
	static int[][] four = { {}, { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	static int[][][] fishes;
	static int min_move, max_eat;
	static boolean[][] visit;
	static class Fish {
		int x, y, d;
		public Fish(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[][] eight = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };
		int M = Integer.parseInt(st.nextToken()); // 물고기 수
		int S = Integer.parseInt(st.nextToken()); // 연습 횟수
		fishes = new int[4][4][8];// 물고기 위치와 방향 
		int[][][] smell = new int[4][4][100]; // 냄새 저장

		List<Fish> list = new ArrayList<>(); //물고기들 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			fishes[x][y][d]++;
			list.add(new Fish(x, y, d));
		}

		st = new StringTokenizer(br.readLine());
		int sx = Integer.parseInt(st.nextToken()) - 1;// 상어 위치
		int sy  = Integer.parseInt(st.nextToken()) - 1;

		for (int s = 0; s < S; s++) {
			
			List<Fish> copy = new ArrayList<>(); // 기존에 있던 물고기 복제

			for (int i = 0; i < list.size(); i++) {
				Fish tmp = list.get(i);
				copy.add(tmp); // 물고기 복제
				// 물고기 한칸 이동
				out:for (int k = 0; k < 8; k++) {
					int dir = (tmp.d - k + 8) % 8; //45도 반시계 이동
					int ni = tmp.x + eight[dir][0];
					int nj = tmp.y + eight[dir][1];
					if (ni >= 0 && ni < 4 && nj >= 0 && nj < 4 && !(ni == sx && nj == sy)) { // 상어있는지 확인
						for (int m = s-1; m>=0 && m >= s-2; m--) { // 2번전까지의 물고기 냄새 확인
							if (smell[ni][nj][m] > 0) //냄새가 있으면 방향 전환
								continue out;
						}
						// 물고기 이동
						fishes[tmp.x][tmp.y][tmp.d]--; //기존 자리에서 1빼고
						fishes[ni][nj][dir]++; //새로 이동한 자리에 1 추가
						break;
					}
				}
			}
			min_move = 555;
			max_eat = 0;
			visit = new boolean[4][4];
			//dfs로 상어의 가능한 이동경로 탐색
			findShark(sx, sy, 0, 0,0); //위치, 이동횟수 , 물고기 먹은 수 , 이동경로
			
			// 상어 이동하기
			String dir = Integer.toString(min_move);
			for (int i = 0; i < 3; i++) { //3칸 이동
				int d = dir.charAt(i) - '0';
				sx = sx + four[d][0];
				sy = sy + four[d][1];
				for (int f = 0; f < 8; f++) { 
					if (fishes[sx][sy][f] > 0) { //물고기가 있다면
						fishes[sx][sy][f] = 0; // 물고기 지워주기
						smell[sx][sy][s]++; // 냄새 남기기
					}
				}
			}

			// 처음 복제했던 물고기 넣어주기 fish 배열에 넣기
			list.clear();
			for (int i = 0; i < copy.size(); i++) {
				Fish f =copy.get(i);
				fishes[f.x][f.y][f.d]++;
			}
			copy.clear();
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					for (int d = 0; d < 8; d++) {
						if (fishes[i][j][d] > 0)
							for (int k = 0; k < fishes[i][j][d]; k++) {
								list.add(new Fish(i, j, d)); //
							}
					}
				}
			}
		}
		System.out.println(list.size());
	}

	static void findShark(int i, int j,int cnt, int eat, int move) {
		if(cnt>= 3) {//기저조건
			if(max_eat < eat) {
				max_eat = eat;
				min_move =move;
			}else if(max_eat == eat) {
				min_move = Math.min(move, min_move);
			}
			return;
		}
		for (int k = 1; k <= 4; k++) {//4방 탐색
			int ni = i + four[k][0];
			int nj = j + four[k][1];
			if (ni >= 0 && ni < 4 && nj >= 0 && nj < 4 ) {
				int eating = 0;
				boolean flag = false;
 				if(!visit[ni][nj]) { //이미 방문한 
					visit[ni][nj] = true;
					flag = true;
					for (int f = 0; f < 8; f++) {
						if (fishes[ni][nj][f] > 0) {
							eating += fishes[ni][nj][f]; // 먹은 물고기 수 추가
						}
					}
				}
				findShark(ni,nj, cnt+1, eat+eating , move*10 + k);
				if(flag) visit[ni][nj] = false;
			}
		}				
	}
}
