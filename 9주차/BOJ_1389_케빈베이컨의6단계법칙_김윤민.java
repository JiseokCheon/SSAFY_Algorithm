package April;

import java.io.*;
import java.util.Arrays;

public class BOJ_1389_케빈베이컨의6단계법칙 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int[][] adjM = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(adjM[i], 999999);
		}
		for (int i = 0; i < M; i++) {
			String[] s = br.readLine().split(" ");
			int a = Integer.parseInt(s[0])-1;
			int b = Integer.parseInt(s[1])-1;
			adjM[a][b] = 1;
			adjM[b][a] = 1;
		}
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if(k==i) continue;
				for (int j = 0; j < N; j++) {
					if(k==j || i==j) continue;
					if(adjM[i][j] > adjM[i][k]+adjM[k][j])
						adjM[i][j] = adjM[i][k]+adjM[k][j];
				}
			}
		}
		int min = 999999;
		int idx = -1;
		for (int i = 0; i < N; i++) {
			int sum=0;
			for (int j = 0; j < N; j++) {
				if(i==j) continue;
				sum+= adjM[i][j];
			}
			if(min > sum) {
				min = sum;
				idx = i;
			}
		}
		System.out.println(idx+1);
	}
}
