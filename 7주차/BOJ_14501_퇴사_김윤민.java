package D0322;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_14501_퇴사 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] t = new int[N+1];
		int[] p = new int[N+1];
		int[] dp = new int[N+2]; //N일 일 때 제일 많이 얻을 수 있는 수익
		for (int i = 1; i <= N; i++) {
			String[] str = br.readLine().split(" ");
			t[i] = Integer.parseInt(str[0]);
			p[i] = Integer.parseInt(str[1]);
		}
		int max = 0;
		for (int i = 1; i <= N; i++) {
			max = dp[i] = Math.max(max, dp[i]); //i일 전까지 가장 많은 수익과 i일의 dp값중에 더 큰 값을 dp[i]에 저장
			if(i+t[i] > N+1) continue; //상담끝나는 일이 범위 벗어나면 continue
			dp[i+t[i]] = Math.max(dp[i+t[i]], dp[i]+p[i]);  //상담 끝나는일 다음날에 i일까지 최대 값과 상담 금액을 더한 것과 기존에 있는 값중에 더 큰값을 넣기
		}
		System.out.println(Math.max(max, dp[N+1])); //N+1번째를 확인해야하므로 둘 중 비교해서 더 큰값 출력
	}
}
