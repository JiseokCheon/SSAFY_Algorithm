package com.ssafy.im;

import java.io.*;
import java.util.*;

public class BOJ_2567_색종이2_김혜진 {
//	static String src = "4\r\n" + 
//			"3 7\r\n" + 
//			"5 2\r\n" + 
//			"15 7\r\n" + 
//			"13 14";
	static int[] dr = {-1,1,0,0}; // 상하좌우
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		// 색종이가 벽에 붙은 경우의 둘레도 계산해야하므로 범위 101까지 검사
		int[][] map = new int[102][102];

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			// 사각형이 있는 곳을 1로 표시 
			for(int r=x+1; r<=x+10; r++) {
				for(int c=y+1; c<=y+10; c++) {
					map[r][c] = 1; 
				}
			}
		}
		
		int len = 0; 
		
		for(int r=0; r<102; r++) {
			for(int c=0; c<=102; c++) {
				// 4방탐색 - 1로 표시된 곳을 기준으로 상,하,좌,우가 0인곳의 길이를 더해주면 둘레
				for(int d=0; d<4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(nr < 1 || nr > 100 || nc < 1 || nc > 100) continue;
					
					if(map[r][c]==0 && map[nr][nc]==1) len++;
					
					
				}
			}
		}
		
		
		System.out.println(len);
	}

}
