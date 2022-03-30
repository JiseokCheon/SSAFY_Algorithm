
import java.io.*;
import java.util.StringTokenizer;

public class BJ_14503_로봇청소기 {
	static int[][] move = {{-1,0},{0,1},{1,0},{0,-1}}; //북, 동 , 남 , 서
	static boolean[][] visit;
	static int[][] map;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			 st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) visit[i][j] = true;
			}
		}
		int cnt = 0;
find :	while(true) {
			visit[r][c] = true; //1번 청소하기
			cnt++;
			while(true) { //후진한 뒤에도 4방향 모두 청소되었는지 확인해야하므로 while문으로
				if(check(r,c)) { //4방향 모두 청소된 경우
					int t = (d+2)%4; //후진 방향
					int i = r+move[t][0];
					int j = c+move[t][1];
					if(i>=0 && i<N && j >= 0 && j < M && map[i][j]==0){ //후진 가능 
						r = i; //c.후진 가능한 경우 현재 위치 변경
						c = j;
					}else { //후진 불가능
						break find; 
					}
				}else { 
					break;
				}
			}
			//왼쪽 방향부터 차례대로 인접칸 확인 - 2번
			while(true) {
				int t = (d-1+4)%4; //왼쪽 방향
				if(!visit[r+move[t][0]][c+move[t][1]]) { //a.청소하지 않는 공간 존재
					d = t; //방향 회전
					r = r+move[t][0]; //한칸 전진
					c = c+move[t][1];
					break; //1번으로
				}else { //청소 공간 없다면
					d = t; //회전 후 2번으로
				}
			}
		}
		System.out.println(cnt);
	}
	private static boolean check(int r, int c) { //4방향 중에서 청소할 곳이 없는 경우 true
		for (int k = 0; k < 4 ; k++) {
			int i = r+ move[k][0];
			int j = c+ move[k][1];
			if(i>=0 && i<N && j >= 0 && j<M && !visit[i][j]) {
				return false;
			}
		}
		return true;
	}
}

