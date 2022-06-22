package July;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_21610_마법사상어와비바라기 {
	public static void main(String[] args) throws IOException {
		int[][] move = {{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};
		int[][] side = {{-1,-1},{-1,1},{1,1},{1,-1}};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map =new int[N][N]; //현재 맵 정보
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] move_info = new int[M][2]; //이동 정보 
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			move_info[i][0] = Integer.parseInt(st.nextToken())-1;
			move_info[i][1] = Integer.parseInt(st.nextToken());
		}
		
		//비바라기 시전
		Queue<int[]> que = new LinkedList<int[]>(); //구름들 저장
		//처음 구름 생성
		int[][] first = {{N-2, 0},{N-2,1},{N-1,0},{N-1,1}}; //처음에 생성될 구름의 위치들
		for (int i = 0; i < 4; i++) {
			que.offer(new int[] {first[i][0], first[i][1]});
		}
		
		for (int m = 0; m < M; m++) { //M번 이동
			//이동
			boolean[][] visit = new boolean[N][N];
			int cn = que.size();
			for (int i = 0; i < cn; i++) { //모든 구름 이동 시키기
				int ni = que.peek()[0] + move[move_info[m][0]][0] * move_info[m][1];
				int nj = que.poll()[1] + move[move_info[m][0]][1] * move_info[m][1];
				if(ni<0) ni = (ni+N*50)%N; else if(ni>=N) ni = ni%N; //1행 N행이 연결 되어있으므로
				if(nj<0) nj = (nj+N*50)%N; else if(nj>=N) nj = nj%N; 
				que.offer(new int[] {ni, nj});
				map[ni][nj]++; //구름이 있는 칸에 물 1 증가
			}
			
			int n = que.size();
			//구름 제거 및 대각선 정보 파악해서 물 더해주기
			for (int c = 0; c < n; c++) {
				int i = que.peek()[0], j = que.poll()[1];
				visit[i][j] = true; //구름 사라진 위치 표시 후 제거
				int cnt=0;
				for (int k = 0; k < 4; k++) { //대각선 정보 파악하기
					int ni = i+side[k][0]; 
					int nj = j+side[k][1]; 
					if(ni>=0 && ni<N && nj>=0 && nj<N && map[ni][nj] >0) cnt++;
				}
				map[i][j]+= cnt;
			}
			//구름 생성
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j]>=2 && !visit[i][j]) {
						que.offer(new int[] {i,j});
						map[i][j]-=2;
					}
				}
			}
		}
		int ans = 0;
		for (int i = 0; i < N; i++) { //모든 물의 양 구하기
			for (int j = 0; j < N; j++) {
				ans+=map[i][j]; 
			}
		}
		System.out.println(ans);
	}
}
