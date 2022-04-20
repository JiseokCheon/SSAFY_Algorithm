import java.io.*;
import java.util.*;

public class BOJ_17144_미세먼지안녕 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[][] move = {{-1,0},{1,0},{0,-1},{0,1}}; //상 하 좌 우
		int[][] cycle = {{3,0,2,1}, {3,1,2,0}}; //반시계방향 ,  시계방향
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int[][] map = new int[R][C];
		int cleanAir = -1; //공기청정기의 행 
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1 && cleanAir== -1) cleanAir = i;
			}
		}
		while(T-->0) {
			//미세먼지 확산
			Queue<int[]> que = new LinkedList<>();
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(map[i][j] > 4) {
						int Arc = map[i][j];
						for (int k = 0; k < 4; k++) {
							int ni = i+move[k][0];
							int nj = j+move[k][1];
							if(ni>=0 && ni<R && nj>=0 && nj<C && map[ni][nj] != -1) {
								map[i][j]-=Arc/5;
								que.add(new int[] {ni,nj, Arc/5});
							}
						}
					}
				}
			}
			//확산된 미세먼지 map에 추가하기 
			while(!que.isEmpty()) {
				map[que.peek()[0]][que.peek()[1]] += que.poll()[2];
			}
			
			//공기청정기 작동
			//0: 위쪽  1: 아래쪽 
			int[][] copy = new int[R][C];
			for (int i = 0; i < R; i++) {
				copy[i] = map[i].clone();
			}
			for (int i = 0; i < 2; i++) {
				int dirIdx = 0;
				int sr = cleanAir+i; //공기 청정이 시작하는 공기 청정기의 행
				int r = sr, c= 1; 
				map[r][c] = 0;
				while(true) {
					int nr = r+move[cycle[i][dirIdx]][0];
					int nc = c+move[cycle[i][dirIdx]][1];
					if(nr < 0 || nr >= R|| nc < 0 || nc >= C) {//범위 벗어나면 dir+1추가
						dirIdx =(dirIdx+1)%4;
						nr = r+move[cycle[i][dirIdx]][0];
						nc = c+move[cycle[i][dirIdx]][1];
					}
					if(nr==sr && nc == 0) break;
					map[nr][nc] = copy[r][c]; 
					r = nr;	c = nc;
				}
			}
		}
		int ans=0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] >0) ans+= map[i][j];
			}
		}
		System.out.println(ans);
	}
}
