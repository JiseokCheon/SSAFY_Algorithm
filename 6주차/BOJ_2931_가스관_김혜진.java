package com.ssafy.bj;

import java.io.*;
import java.util.*;

public class BOJ_2931_가스관_김혜진 {
	static String src = "3 7\r\n" + 
			".......\r\n" + 
			".M-.-Z.\r\n" + 
			".......";
	
	static int r, c;
	static char[][] map;
	static int[] dr = {-1,0,1,0}; // 상,우,하,좌
	static int[] dc = {0,1,0,-1};
//	static char[] block = {'|','-','+','1','2','3','4'}; // 블록의 모양
	static boolean[][][] pipe; // 방향, 행, 열
	static int[] dir = new int[4];
	static int startr, startc, n;
	static boolean flag = false;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		
		// 파이프 받으면 4방향 boolean배열 이용하여 연결 가능한 곳 - true
		// DFS탐색 - '.' 는 해커가 훔쳐간 자리 >> 4방 탐색 : 파이프 모양 예측
		// M,Z(출발,도착위치) >> 4방탐색 : if문으로 걸러주기
		// 전제 : 가스의 흐름은 항상 유일, 항상 답 존재
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new char[r][c];
		pipe = new boolean[4][r][c]; // 4방향에 대해 연결 가능한지 체크
		
		for(int i=0; i<r; i++) {
			String str = br.readLine();
			for(int j=0; j<c; j++) {
				map[i][j] = str.charAt(j);
				// 출발위치
				if(map[i][j] == 'M') {
					startr = i;
					startc = j;
				}
				// 블록이 존재한다면
				if(map[i][j] != '.') {
					// 파이프 방향대로 true로 상태 바꿔줌 - 파이프의 위치 표시
					check(true, i,j);
				}
			}
		}
		// dfs 탐색 - M의 위치부터 시작
		dfs(startr,startc);
		
		// 출력 : 지워진 블록의 행과 열의 위치, 어떤 블록이었는지
		System.out.println(sb);
		System.out.println("hi");
	}
	// 파이프블록의 모양이 이동할 수 있는 곳 true 체크
	public static void check(boolean flag, int i, int j) {
		switch(map[i][j]){
			case '|': // 상, 하
				pipe[0][i][j] = flag;
				pipe[2][i][j] = flag;
				break;
			case '-': // 좌, 우
				pipe[1][i][j] = flag;
				pipe[3][i][j] = flag;
				break;
			case '+': // 상,하,좌,우
				pipe[0][i][j] = flag;
				pipe[1][i][j] = flag;
				pipe[2][i][j] = flag;
				pipe[3][i][j] = flag;
				break;
			case '1': // 우,하
				pipe[1][i][j] = flag;
				pipe[2][i][j] = flag;
				break;
			case '2': // 상,우
				pipe[0][i][j] = flag;
				pipe[1][i][j] = flag;
				break;
			case '3': // 상,좌
				pipe[0][i][j] = flag;
				pipe[3][i][j] = flag;
				break;
			case '4': // 하,좌
				pipe[2][i][j] = flag;
				pipe[3][i][j] = flag;
				break;
			case 'M': 
				pipe[0][i][j] = flag;
				pipe[1][i][j] = flag;
				pipe[2][i][j] = flag;
				pipe[3][i][j] = flag;
				break;
			case 'Z':
				pipe[0][i][j] = flag;
				pipe[1][i][j] = flag;
				pipe[2][i][j] = flag;
				pipe[3][i][j] = flag;
				break;
		}
	}
	// dfs - M의 위치에서부터 4방탐색
	public static void dfs(int r, int c) {
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(nr < 0 || nr >= r || nc < 0 || nc >= c) continue;
			
			// 다음칸 '.'(비어있는), 현재 칸의 연결하는 파이프가 있는 경우 - 해커가 훔쳐간 곳
			if(map[nr][nc]=='.' && pipe[i][r][c] && map[r][c] != 'M' && map[r][c] != 'Z') {
				
				// 4방 탐색 - 이어질 수 있는 파이프 유추
				for(int d=0; d<4; d++) {
					int pr = nr + dr[d];
					int pc = nc + dc[d];
					
					if(pr < 0 || pr >= r || pc < 0 || pc >= c) continue;
					
					// M과 Z 인 경우 고려 x
					if(map[pr][pc] == 'M' || map[pr][pc] == 'Z') continue;
					
					// 해당 방향의 파이프 +1해주어 파이프의 모양 유추
					if(pipe[(d+2)%4][pr][pc]) dir[d]++;
				}
				
				findPipe(nr,nc);
			}
			else if(map[nr][nc] == '.') continue; // 다음 위치가 비어있다면 고려x
			
			// 파이프가 이어질 수 있을 때 (현재칸과 다음칸의 방향이 일치하고 파이프가 존재할 때)
			if(pipe[i][r][c] && pipe[(i+2)%4][nr][nc]) {
				// 파이프 사용할 수 없음 - false
				pipe[i][r][c] = false;
				pipe[(i+2)%4][nr][nc] = false;
				dfs(nr,nc);
				// 파이프 사용할 수 있음 - true
				pipe[i][r][c] = true;
				pipe[(i+2)%4][nr][nc] = true;
			}
		}
	}
	
	public static void findPipe(int row, int col) {
		char ch = '.';
		if(dir[0] == 1 && dir[1] == 1 && dir[2] == 1 && dir[3] == 1) ch = '+';
		else if(dir[0] == 1 && dir[2] == 1) ch = '|';
		else if(dir[1] == 1 && dir[3] == 1) ch = '-';
		else if(dir[1] == 1 && dir[2] == 1) ch = '1';
		else if(dir[0] == 1 && dir[1] == 1) ch = '2';
		else if(dir[0] == 1 && dir[3] == 1) ch = '3';
		else if(dir[2] == 1 && dir[3] == 1) ch = '4';
		
		sb.append(row+1).append(" ").append(col+1).append(" ").append(ch);
		//System.out.println((r+1) + " " + (c+1) + " " + ch); 
		System.exit(0);

	}
}
