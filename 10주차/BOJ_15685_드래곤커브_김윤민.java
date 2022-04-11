package April;

import java.io.*;
import java.util.*;

public class BOJ_15685_드래곤커브_김윤민 {
	public static void main(String[] args) throws IOException {
		int [][] move = {{0,1},{-1,0},{0,-1},{1,0}};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		boolean [][] map = new boolean[101][101];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); //x좌표
			int y = Integer.parseInt(st.nextToken()); //y좌표
			int d = Integer.parseInt(st.nextToken()); //방향
			int g = Integer.parseInt(st.nextToken()); //세대수
			
			//세대수에 따라서 드래곤 커브 구하기
			ArrayList<Integer> list = new ArrayList<Integer>(); //드래곤 커브 누적
			map[y][x] = true;
			map[y+move[d][0]][x+move[d][1]] = true; //드래곤 커브 포함된 꼭짓점 true로
			list.add(d);
			y = y+move[d][0]; 
			x = x+move[d][1]; 
			for (int j = 0; j < g; j++) {
				for (int k = list.size()-1; k >= 0; k--) { //누적된 드래곤 커브 만큼
					int dir = (list.get(k)+1)%4; //90도 회전
					list.add(dir);
					y = y+move[dir][0]; 
					x = x+move[dir][1]; 
					map[y][x] =true; //드래곤 커브 꼭짓점 표시
				}
			}
		}
		//사각형 개수 구하기
		int cnt=0; 
		for (int i = 0; i <= 100; i++) {
			for (int j = 0; j <= 100; j++) {
				if(i+1<=100 && j+1 <= 100 && 
						map[i][j] && map[i+1][j]&& map[i][j+1]&&map[i+1][j+1])cnt++;
			}
		}
		System.out.println(cnt);
	}
}
