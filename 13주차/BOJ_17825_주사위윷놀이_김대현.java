import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int max = 0;

	static int[][] map = { { 0, 2, 4, 6, 8}, { 10, 12, 14, 16, 18}, { 20, 22, 24, 26, 28},
			{ 30, 32, 34, 36, 38}, { 10, 13, 16, 19}, { 20, 22, 24}, { 30, 28, 27, 26},
			{ 25, 30, 35 } ,{40}};
	static int[] mapsize = { 5, 5, 5, 5, 4, 3, 4, 3,0 };
	static int[] turn;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		turn = new int[10];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 10; i++) {
			turn[i] = Integer.parseInt(st.nextToken());
		}
		boolean[] can = new boolean[4];
		Arrays.fill(can, true);
		int[][] horse = new int[4][2];
		horse[0][1] = turn[0];
		if (turn[0] == 5) {
			horse[0][0] = 4;
			horse[0][1] = 0;
			letsgo(horse, map[horse[0][0]][horse[0][1]], 1, can);// 1번(r,c) ... 4번(r,c), sum, turn

		} else {
			horse[0][1]=turn[0];
			letsgo(horse, map[horse[0][0]][horse[0][1]], 1, can);// 1번(r,c) ... 4번(r,c), sum, turn
		}
		

		System.out.println(max);

	}

	private static void letsgo(int[][] horse, int sum, int now, boolean[] can) {
		if (now == 10) {
			if (sum > max) {
				max = sum;
			}
		} else {
			for (int i = 0; i < 4; i++) {
				if (can[i]) {
					int r = horse[i][0];
					int c = horse[i][1];
					int tempr = horse[i][0];
					int tempc = horse[i][1] + turn[now];
					if (tempc > mapsize[tempr]) { // 넘어가는경우
						if (tempr <= 2) {
							tempc -= mapsize[tempr];
							tempr += 1;
						} else if (tempr == 4 || tempr == 5 || tempr == 6) {
							tempc -= mapsize[tempr];
							tempr = 7;

							if (tempc == mapsize[tempr]) {
								tempr = 8;
								tempc = 0;
							}else if (tempc > mapsize[tempr]) {
								tempr = 9;
								tempc = 0;
							}
						} else if (tempr == 3 || tempr == 7 || tempr==8) {
							tempr = 9;
							tempc = 0;
						}
					} else if (tempc == mapsize[tempr]) { // 파란색인경우
						if (tempr <= 2) {
							tempc = 0;
							tempr += 4;
						} else if (tempr == 4 || tempr == 5 || tempr == 6) {
							tempr = 7;
							tempc = 0;
						} else if (tempr ==3 || tempr == 7) {
							tempr= 8;
							tempc= 0;
						}
					}
					horse[i][0] = tempr;
					horse[i][1] = tempc;
					if (tempr == 9) { // 도착지 넘어가는경우
						can[i] = false;
						letsgo(horse, sum, now + 1, can);
						can[i] = true;

					} else {
						// 이미 말이 있는지 체크
						boolean same = false;
						for (int h = 0; h < 4; h++) {
							if (h == i) {
								continue;
							}
							if (horse[i][0] == horse[h][0] && horse[i][1] == horse[h][1]) {
								same = true;
							}
						}
						if (!same) {
							letsgo(horse, sum + map[horse[i][0]][horse[i][1]], now + 1, can);

						}

					}
					horse[i][0] = r;
					horse[i][1] = c;
				}
			}
		}

	}

}
