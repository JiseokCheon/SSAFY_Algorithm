package april;

import java.io.*;
import java.util.*;

public class BOJ_1780_종이의개수 {
	static int N;
	static int[] cnt;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cnt = new int[3];
		slice(0,0, N);
		StringBuilder sb = new StringBuilder();
		for (int cnt: cnt) {
			sb.append(cnt+"\n");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb);
	}
	private static void slice(int i, int j, int n) {
		int first = map[i][j];
		for (int p = i; p < i+n; p++) {
			for (int q = j; q < j+n; q++) {
				if(first != map[p][q]) {
					int nn = n/3;
					for (int k = 0; k <3 ; k++) {
						for (int z = 0; z < 3; z++) {
							slice(i+nn*k, j+nn*z, nn); 
						}
					}
					return;
				}
			}
		}
		
		cnt[first+1]++;
	}
}
