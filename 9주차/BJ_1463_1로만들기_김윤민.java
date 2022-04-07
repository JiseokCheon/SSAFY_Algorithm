import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1]; //1로 만들기 위한 횟수의 최소값 저장
		
		for (int i = 1; i <= N; i++) {
			dp[i] = i-1;
		}
		
		for (int i = 2; i <= N; i++) {
			if(i%3==0) {
				dp[i] = Math.min(dp[i/3]+1, dp[i]); 
			}
			if(i%2==0) {
				dp[i] = Math.min(dp[i/2]+1, dp[i]); 
			}
			dp[i] =Math.min(dp[i-1]+1, dp[i]); 
		}
		System.out.println(dp[N]);
	}
}
