import java.io.*;
import java.util.*;

public class BOJ_16954_움직이는미로탈출 {
	public static void main(String[] args) throws IOException {
		int[][] move = {{1,0},{1,-1},{-1,0},{-1,-1},{0,1},{1,1},{0,-1},{-1,1},{0,0}};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] map = new char[8][8];
		for (int i = 0; i < 8; i++) {
			map[i] = br.readLine().toCharArray();
		}
		//시작 위치는 7,0 도착 위치는 0,7 
		int si = 7, sj = 0;
		boolean flag = false;
		Queue<int[]> que = new LinkedList<int[]>();
		que.add(new int[] {si,sj});
		boolean[][][] visit = new boolean[8][8][9];
		while(!que.isEmpty()) {
			int s = que.size();
			for (int p = 0; p < s; p++) {
				int[] t = que.poll();
				if(t[0]==0 && t[1]==7) {
					flag = true;
					break;
				}
				if(map[t[0]][t[1]]=='#') continue; //벽이 있다면 캐릭터 이동 불가
				for (int k = 0; k < 9; k++) {
					int ni = t[0]+move[k][0];
					int nj = t[1]+move[k][1];
					if(ni>=0 && ni<8 && nj>=0 && nj<8 
							&& !visit[ni][nj][k] && map[ni][nj]=='.') {
						visit[ni][nj][k] = true;
						que.add(new int[] {ni,nj});
					}
				}
			}
			//벽 이동
			for (int i = 6; i >=0; i--) {
				for (int j = 0; j < 8; j++) {
					map[i+1][j] = map[i][j];
				}
			}
			for (int j = 0; j < 8; j++) {
				map[0][j] = '.';
			}
		}
		if(flag) System.out.println(1);
		else System.out.println(0);
	}
}
