package com.ssafy.bj;

import java.io.*;
import java.util.*;

// bfs 탐색 - 모든 방향 (각 구슬의 위치와 이동 횟수 체크)
// 빨간구슬, 파란구슬의 위치가 동일한 경우 더 많이 이동한 구슬을 덜 이동
public class BOJ_13460_구슬탈출2_김혜진 {
	static String src = "3 10\r\n" + 
			"##########\r\n" + 
			"#.O....RB#\r\n" + 
			"##########";

	static int N, M;
	static char[][] map;
	static boolean[][][][] visited; // 구슬의 방문 여부 체킹
	static int result = -1; // count>10 이면 result=-1
	static Pos r_pos, b_pos;
	// 구슬의 위치 이동(상하좌우 4방)
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		// 빨간구슬, 파란구슬의 위치를 각각 담아야하므로 4차원 배열
		visited = new boolean[N][M][N][M];

		/*
		 * 5 5 ##### #..B# #.#.# #RO.# #####
		 */
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'R')
					r_pos = new Pos(i, j, 0); // 빨간구슬의 현재 위치
				else if (map[i][j] == 'B')
					b_pos = new Pos(i, j, 0); // 파란 구슬의 현재 위치
			}
		}
		
		bfs(r_pos, b_pos);
		System.out.println(result);
	}
	
	public static void bfs(Pos r, Pos b) {
		Queue<Pos> rq = new LinkedList<>();
		Queue<Pos> bq = new LinkedList<>();
		
		rq.offer(r);
		bq.offer(b);
		
		// 빨간 구슬과 파란 구슬의 위치에 대한 방문 체킹 
		visited[r.x][r.y][b.x][b.y] = true;
		
		while(!rq.isEmpty() && !bq.isEmpty()) {
			Pos nr = rq.poll();
			Pos nb = bq.poll();
			
			// 빨간 구슬의 이동 횟수가 10번을 넘어가면 -1 출력하고 반복문 종료
			if(nr.count > 10) {
				result = -1;
				return;
			}
			
			// 파란 구슬이 빠져나간 경우는 다음 고려 x 
			if(map[nb.x][nb.y]=='O') continue;
			
			// 빨간 구슬이 빠져나오면 결과에 이동횟수 저장 후 반복문 종료
			if(map[nr.x][nr.y]=='O') {
				result = nr.count;
				return;
			}
			// 구슬위치 이동(4방)
			move(nr, nb, rq, bq);
		}
	}
	
	public static void move(Pos nr, Pos nb, Queue<Pos> rq, Queue<Pos> bq) {
		for(int d=0; d<4; d++) {
			// 두 구슬의 위치 고려하지 않고 우선 이동
			// 파란구슬이 도달하는 지점으로 이동
			int bx = nb.x;
			int by = nb.y;
			// 구멍이나 장애물을 만날 때까지 계속 이동 
			while(true) {
				bx += dx[d];
				by += dy[d];
				// 구멍을 통해 빠져나간 경우
				if(map[bx][by] == 'O') break;
				// 장애물을 만났을 경우 - 반대로 이동
				if(map[bx][by] == '#') {
					bx -= dx[d];
					by -= dy[d];
					break;
				}
			}
			
			// 빨간 구슬
			int rx = nr.x;
			int ry = nr.y;
			// 구멍이나 장애물을 만날 때까지 계속 이동
			while(true) {
				rx += dx[d];
				ry += dy[d];
				// 구멍을 만날 경우
				if(map[rx][ry]=='O') break;
				// 장애물을 만날 경우
				if(map[rx][ry]=='#') {
					rx -= dx[d];
					ry -= dy[d];
					break;
				}
			}
			// 두 구슬의 위치 고려하여 이동
			// 구멍이 아닌데 두 구슬의 위치가 동일한 경우
			// 이동거리가 더 긴 쪽이 덜 이동하게 
			if(bx==rx && by==ry && map[rx][ry]!='O') {
				int rDis = Math.abs(nr.x - rx) + Math.abs(nr.y-ry);
				int bDis = Math.abs(nb.x - bx) + Math.abs(nb.y-by);
				
				// 빨간 구슬 이 더 이동한 경우
				if(rDis > bDis) {
					// 빨간 구슬이 덜 이동하게 
					rx -= dx[d];
					ry -= dy[d];
				}else {
					// 파란 구슬 덜 이동
					bx -= dx[d];
					by -= dy[d];
				}
			}
			
			// 두 구슬이 기울여서 도착하는 지점 방문처리 한 후 큐에 추가 
			if(!visited[rx][ry][bx][by]) {
				visited[rx][ry][bx][by] = true;
				rq.offer(new Pos(rx,ry,nr.count+1));
				bq.offer(new Pos(bx,by,nb.count+1));
			}
		}
	}

}

//구슬의 위치, 이동횟수 담는 클래스
	class Pos {
		int x;
		int y;
		int count;

		public Pos(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}
