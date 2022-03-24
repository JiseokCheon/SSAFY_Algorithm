package study_mar;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_14500_테트로미노 {
	static int[][] move = {{-1,0},{1,0},{0,1},{0,-1}};
	static int[][] map;
	static int N, M , max;
	
	static int toInt(String str) {
		return Integer.parseInt(str);
	}
	
	static boolean check(int i, int j) {
		if(i>=0 && i<N && j>=0 && j<M ) return true; //범위 안에 있으면 true
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = toInt(st.nextToken());
		M = toInt(st.nextToken());
		max = Integer.MIN_VALUE;
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = toInt(st.nextToken());
			}
		}
		//dfs
		boolean[][] visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visit[i][j]= true;
				dfs(i,j,0, 0, visit); //dfs
				visit[i][j]= false; // 방문 해제
				other(i,j,0); //ㅗ 모양 확인
			}
		}
		System.out.println(max);
	}
	private static void dfs(int i, int j, int cnt, int sum, boolean[][] visit) { //현재 위치 ,개수, 현재까지 더한 값
		if(cnt==4) {
			max = Math.max(max, sum);
			return;
		}
		sum += map[i][j];
		for (int k = 0; k < 4; k++) {
			int ni = i+move[k][0];
			int nj = j+move[k][1];
			if(check(ni,nj) && !visit[ni][nj]) {
				visit[ni][nj] = true;
				dfs(ni, nj, cnt+1, sum, visit);
				visit[ni][nj] = false;
			}
		}
	}
	private static void other(int i, int j, int k) {
		int sum = 0;
		for (int dir = 0; dir < 4; dir++) { // 가운데를 제외한 상하좌우를 반복문 돌면서 제외하고 더해주기
			sum  = map[i][j]; //정가운데 값은 항상 더하기
			for (int l = 0; l < 4; l++) {
				if(dir == l )continue; //순서와 현재 방향이 같으면 넘어가기
				int ni = i+move[l][0];
				int nj = j+move[l][1];
				if(check(ni,nj)) { 
					sum+= map[ni][nj];
				}else break ; //하나라도 범위에 벗어나면 다음 반복문으로
			}
			max = Math.max(max, sum);
		}
	}
}
