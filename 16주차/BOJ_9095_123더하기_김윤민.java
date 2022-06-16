
import java.io.*;

public class BOJ_9095_123더하기_김윤민 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(br.readLine());
			int[] dp = new int[12];
			dp[1] =1;
			dp[2] =2;
			dp[3] =4;
			for (int i = 4; i <= n ; i++) {
				dp[i] = dp[i-1]+dp[i-2]+dp[i-3];       
			}
			sb.append(dp[n]+"\n");
		}
		System.out.println(sb);
	}
}
