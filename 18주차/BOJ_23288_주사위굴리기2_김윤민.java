package June;

import java.io.*;
import java.util.*;

public class BOJ_23288_주사위굴리기2 {
	static int[][] move = {{0,1},{1,0},{0,-1},{-1,0}};
	static int[][] map;
	static int N,M; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		map =new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[] dice = {2,1,5,6,4,3}; //주사위 인덱스
		int dir = 0, i=0, j=0; //방향과 현재 위치
		int ans = 0; //점수 합
		while(K-->0) { //K번 이동
			int ni  = i+ move[dir][0];
			int nj  = j+ move[dir][1];
			if(ni<0 || ni>=N || nj<0 || nj>=M) { //범위 벗어나면 반대 방향으로
				dir = (dir+2)%4;
			}
			moveDice(dice, dir);  //주사위 이동
			i = i+ move[dir][0]; //위치 변경
			j = j+ move[dir][1];
			
			int A =dice[3]; //주사위 아랫면 
			//점수 획득
			int B = map[i][j]; //map에서 현재 위치의 값 
			int C = bfs(i, j, B); //B와 같고 연속이동 가능한 칸 수
			ans+= B*C;
			
			//방향 회전
			if(A > B) dir = (dir+1)%4;
			else if(A<B) dir = (dir-1 + 4)%4;
		}
		System.out.println(ans);
		
	}

	 private static int bfs(int i, int j, int b) {
		int ans=1;
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] visit =new boolean[N][M];
		visit[i][j] = true;
		q.offer(new int[] {i,j, ans});
		while(!q.isEmpty()) {
			int[] t = q.poll();
			for (int k = 0; k < 4; k++) {
				int ni  = t[0]+ move[k][0];
				int nj  = t[1]+ move[k][1];
				if(ni>=0 && ni<N && nj>=0 && nj<M
						&& map[ni][nj] == b && !visit[ni][nj] ) { //B값과 같은 경우만  q에 넣기
					q.offer(new int[] {ni,nj, t[2]+1});
					visit[ni][nj] = true;
					ans++; //칸수 추가
				}
			}
		}
		return ans;
	}

	static void moveDice(int[] dice, int dir) { //주사위 이동
		 int t= dice[1];
		 if(dir==0) { //오른쪽
			 dice[1] = dice[4];
			 dice[4] = dice[3];
			 dice[3] = dice[5];
			 dice[5] = t;
		 }else if(dir==1) { // 아래
			 dice[1] = dice[0];
			 dice[0] = dice[3];
			 dice[3] = dice[2];
			 dice[2] = t;
		 }else if(dir==2) { //왼쪽
			 dice[1] = dice[5];
			 dice[5] = dice[3];
			 dice[3] = dice[4];
			 dice[4] = t;
		 }else {//위
			 dice[1] = dice[2];
			 dice[2] = dice[3];
			 dice[3] = dice[0];
			 dice[0] = t;
		 }
	}
}
