package may;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7569_토마토 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[][] move = {{0,0,1},{0,0,-1},{1,0,0},{-1,0,0},{0,1,0},{0,-1,0}};
		int M = Integer.parseInt(st.nextToken()); //열
		int N = Integer.parseInt(st.nextToken()); //행
		int H = Integer.parseInt(st.nextToken()); //높이
		int[][][] map = new int[N][M][H];
		int cnt = 0; //익지 않은 토마토 개수
		Queue<int[]> que = new LinkedList<int[]>();
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j][h] =  Integer.parseInt(st.nextToken()); 
					if(map[i][j][h]==1)	que.add(new int[] {i,j,h}); //익은 토마토 que에 넣기
					else if(map[i][j][h]== 0)cnt++; //안익은 토마토 개수 세기
				}
			}
		}
		int day = 0;
		out:while(!que.isEmpty() && cnt>0) { //안익은 토마토개수가 0보다 큰경우에만
			day++; //일수 추가
			int size = que.size();
			for (int i = 0; i < size; i++) { //일을 구별하기 위해서 size를 미리 구하기
				int[] t = que.poll();
				for (int k = 0; k < 6; k++) { //6방향 탐색
					int ni = t[0]+move[k][0];
					int nj = t[1]+move[k][1];
					int nh = t[2]+move[k][2];
					if(ni>=0 && ni<N && nj>=0 && nj<M && nh>=0 && nh<H
							&& map[ni][nj][nh]==0) {
						que.offer(new int[] {ni,nj,nh});
						map[ni][nj][nh] = 1; //익은 토마토로 변경
						cnt--; //안익은 토마토 개수 1 감소
						if(cnt==0) break out; //만약 안익은 토마토가 없으면 종료
					}
				}
				
			}
		}
		if(cnt>0) day = -1; //안익은 토마토가 남아있다면 -1출력
		System.out.println(day);
	}
}
