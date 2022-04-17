package BJ_2749_피보나치수3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int mod= 1000000;
	static int[] dp;
	// 파사노주기 인터넷 참고..
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		long n=Long.parseLong(br.readLine());
		int p = 15*mod / 10; // 파사노주기 공식..
		dp=new int[p+1];
		
		int answer=fibo((int)(n%p),p);
		System.out.print(answer);

	}
	
	
	public static int fibo(int n, int p) {
		dp[1]=1;
		dp[2]=1;
		for(int i=3; i<=n; i++) {
			dp[i]=(dp[i-2]+dp[i-1])%mod;
		}
		
		
		return dp[n] % mod;
	}

}
