package July;

import java.io.*;

public class BOJ_9461_파도반수열 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb =new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		long[] dp = new long[101]; 
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 1;
		for (int i = 4; i <=100; i++) {
			dp[i] = dp[i-2] +dp[i-3];
		}
		for (int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(dp[n]+"\n");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb);
	}
}
