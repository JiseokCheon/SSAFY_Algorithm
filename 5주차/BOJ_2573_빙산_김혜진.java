package com.ssafy.bj;

import java.io.*;
import java.util.*;

public class BOJ_2573_빙산_김혜진 {
	static String src = "5 7\r\n" + 
			"0 0 0 0 0 0 0\r\n" + 
			"0 2 4 5 3 0 0\r\n" + 
			"0 3 0 2 5 2 0\r\n" + 
			"0 7 6 2 4 0 0\r\n" + 
			"0 0 0 0 0 0 0";
	// 빙산 주변 4방 탐색
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int R,C, year;
	static int[][] arr; // 빙산 정보 저장
	static int[][] sea; // 바다 정보 저장
	static boolean[][] visited; // 방문 정보 체킹 - 원래 있던 빙산 확인해서 바다로 판단 x
	
	// DFS탐색
	// 1. 4방 탐색 - 바다의 개수만큼 빙산 높이 녹음
	// 2. 빙산의 덩어리 개수 탐색 - 2덩이 이상 탐색 종료 후 걸린 년 수 출력 (아니면 year+1한 후 다시 1번부터 탐색)
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		sea = new int[R][C];
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		year = 0;
		int cnt;
		while(true) {
			boolean check = false; // 빙산이 남아있는지 확인
			visited = new boolean[R][C];
			cnt = 0;
			
			year++; // 년 수 1년 증가 후 빙산의높이-바다의 개수
			
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					if(arr[i][j]>0) {
						sea[i][j] = getCnt(i,j); // 빙산 주위 탐색 결과(바다의 개수)를 바다 배열에 저장
					}
				}
			}
			
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					if(arr[i][j]>0) {
						check = true; // 빙산이 현재 존재
						// 빙산의 높이-바다의 개수가 0보다 크다면 빼주고 아니면 0 저장
						if(arr[i][j] - sea[i][j] > 0) arr[i][j] -= sea[i][j];
						else arr[i][j] = 0;
					}
				}
			}
			// check = false 라면 빙산이 다 녹아서 없어졌으므로 while문 빠져나옴
			if(!check) break;
			
			// dfs 탐색으로 빙산이 두 덩이 이상으로 나눠졌는지 확인
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					if(!visited[i][j] && arr[i][j]>0) {
						cnt++; // dfs 탐색 결과 - cnt(빙산의 개수)
						if(cnt>=2) {
							sb.append(year); // 빙산의 개수가 2개가 넘어가면 year 결과 담기
							//System.out.println(year);
							break;
						}
						dfs(i,j); // 넘어가지 않았다면 dfs 탐색 반복
					}
				}
			}
		}
		
		sb.setLength(1);
		System.out.println(sb);
		
	}
	
	// 빙산 주변 바다의 개수 반환 함수
	static int getCnt(int r, int c) {
		int cnt = 0;
		// 4방 탐색하며 현재 빙산의 주변에 바다가 몇 개 있는지 
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			// 0 이면 바다 - 개수 ++
			if(arr[nr][nc]==0) cnt++;
		}
		return cnt; // 바다의 개수 return
	}
	
	static void dfs(int r, int c) {
		visited[r][c] = true; // 방문 여부 체킹
		
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			// 방문한 적이 없고 빙산의 높이가 0보다 클 때까지 계속 탐색
			if(!visited[nr][nc] && arr[nr][nc] > 0) {
				dfs(nr,nc);
			}
		}
	}

}
