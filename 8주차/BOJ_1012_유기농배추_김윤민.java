package study_mar;

import java.io.*;
import java.util.*;

public class BJ_1012_유기농배추 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		int[][] move = {{-1,0},{0,1},{1,0},{0,-1}}; //북, 동 , 남 , 서
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String[] str = br.readLine().split(" "); 
			int N = Integer.parseInt(str[1]); //가로 세로 배추개수 저장
			int M = Integer.parseInt(str[0]);
			int K = Integer.parseInt(str[2]);
			int[][] map = new int[N][M];
			for (int i = 0; i < K; i++) { //배추 위치 저장
				str = br.readLine().split(" ");
				int r = Integer.parseInt(str[1]);
				int c = Integer.parseInt(str[0]);
				map[r][c] = 1;
			}
			int cnt =0;
			boolean [][] visit = new boolean[N][M]; //방문 체크
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j]==1 && !visit[i][j]) { //배추가 있고, 아직 방문하지 않은 배추이면
						cnt++;  //벌레수 추가
						Queue<int[]> que = new LinkedList<>();
						que.add(new int[] {i,j}); //첫 시작 위치
						visit[i][j] = true;
						while(!que.isEmpty()) { //bfs시작
							int[] t = que.poll();
							for (int l = 0; l < 4; l++) { //4방 탐색
								int ni = t[0] + move[l][0];
								int nj = t[1] + move[l][1];
								if(ni>=0 && ni<N && nj>=0 && nj<M 
										&& map[ni][nj]==1 &&!visit[ni][nj]) { //배추이면서 아직 방문하지 않았으면 
									que.add(new int[] {ni,nj}); //큐에 추가
									visit[ni][nj]=true; //방문 배열 true로 변경
								}
							}
						}
					}
				}
			}
			System.out.println(cnt);
		}
	}
}
