package april;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_5373_큐빙 {
	static int[][] lines = {{0,1,2},{2,5,8},{0,3,6},{6,7,8},{2,1,0},{8,5,2}};
	static int[][] m90 = {{0,1,2},{2,5,8},{8,7,6},{6,3,0}};
	static int[][][] sideInfo = {{{5,3},{3,4},{2,4},{1,4}},    //0번 면의 인접 면 정보
									{{0,2},{2,2},{4,2},{5,2}},
									{{0,3},{3,2},{4,4},{1,5}},
									{{0,5},{5,5},{4,5},{2,5}},
									{{2,3},{3,3},{5,4},{1,3}},
									{{4,3},{3,5},{0,4},{1,2}},};
	static char[] color = {'w','g','r','b','y','o'};
	static char[][] dice;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			dice = new char[6][9];
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 9; j++) {
					dice[i][j] = color[i];
				}
			}
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				String str = st.nextToken();
				char m = str.charAt(0); //회전할 주사위 면
				char dir = str.charAt(1); //회전 방향
				turn(m, dir);
			}
		
			for (int i = 0; i < 9; i++) {
				sb.append(dice[0][i]);
				if((i+1)%3==0)sb.append("\n");
			}
			
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb);
	}
	private static void turn(char m, char dir) {
		int idx = 0;
		if(m=='U') { idx = 0;
		}else if(m=='D') {idx = 4;
		}else if(m=='F') {idx = 2;
		}else if(m=='B') {idx = 5;
		}else if(m=='L') {idx = 1;
		}else if(m=='R') {idx = 3;
		}
		int d = 0;
		if(dir=='+') d= 1;
		else d= -1;
		//선택한 면 회전 해주기
		char [] t = dice[idx].clone();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				dice[idx][m90[(i+d+4)%4][j]] = t[m90[i][j]];
			}
		}
		
		int[][]	sides = sideInfo[idx]; //{{0,2},{2,3},{4,3},{5,3}}
		char[][] temp = new char[4][3];
		for (int i = 0; i < 4; i++) {
			int mm = sides[i][0]; //이동할 면의 인덱스
			int lineIdx = sides[i][1];
			int[] line = lines[lineIdx]; //이동할 라인
			for (int j = 0; j < 3; j++) {
				temp[(i+d+4)%4][j] = dice[mm][line[j]]; 
			}
		}
		//temp를dice에 적용
		for (int i = 0; i < 4; i++) {
			int mm = sides[i][0]; //이동할 면의 인덱스
			int lineIdx = sides[i][1];
			int[] line = lines[lineIdx]; //이동할 라인
			for (int j = 0; j < 3; j++) {
				dice[mm][line[j]] = temp[i][j];
			}
		}
		
	}
}
