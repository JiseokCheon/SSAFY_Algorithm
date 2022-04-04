package April;

import java.io.*;

public class BJ_14891_톱니바퀴 {
	static boolean[] ch;
	static int[][] cycle;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		cycle = new int[4][8];
		for (int i = 0; i < 4; i++) {
			String info = br.readLine();
			for (int j = 0; j < 8; j++) {
				cycle[i][j] = info.charAt(j) - '0';
			}
		}
		// 회전하기
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			ch = new boolean[4]; // 회전 여부를 체크
			String[] s = br.readLine().split(" ");
			int n = Integer.parseInt(s[0]); //바퀴 번호
			int dir = Integer.parseInt(s[1]); //회전 방향
			rotate(n - 1, dir); //회전 시작
		}
		//회전 후 상태로 점수 계산
		int score = 0; 
		if(cycle[0][0]==1) score+= 1;
		if(cycle[1][0]==1) score+= 2;
		if(cycle[2][0]==1) score+= 4;
		if(cycle[3][0]==1) score+= 8;
		System.out.println(score);
	}

	private static void rotate(int n, int dir) { // 회전 시키기
		if(ch[n]) return; //이미 회전 바퀴면 return
		int[] temp = new int[8];
		ch[n]= true;
		for (int i = 0; i < 8; i++) { //방향에 따라서 새로운 배열에 저장
			temp[(i + dir + 8) % 8] = cycle[n][i];
		}

		//양방향 톱니확인 후 회전
		if(n+1<4 && !ch[n+1]) { //오른쪽 톱니바퀴확인
			if(cycle[n][2] != cycle[n+1][6]) rotate(n+1,-dir); //극이 다른 경우 반대방향으로 회전
		}
		if(n-1 >=0 && !ch[n-1]) { //왼쪽 톱니바퀴확인
			if(cycle[n][6] != cycle[n-1][2]) rotate(n-1,-dir);
		}

		for (int i = 0; i < 8; i++) { //회전 후 상태를 저장한 임시배열을 복사하기
			cycle[n][i] = temp[i];
		}
	}
}
