
import java.io.*;

public class BOJ_11047_동전0 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int K = Integer.parseInt(str[1]);
		int[] moneys = new int[N];
		for (int i = 0; i < N; i++) {
			moneys[i] = Integer.parseInt(br.readLine());
		}
		int ans =0; //동전 개수
		for (int i = N-1; i >=0;) { //마지막 부터 시작
			if(K/moneys[i] != 0) { //만약 몫이 있으면
				ans += K/moneys[i]; //동전 개수 추가
				K-= (K/moneys[i]) * moneys[i]; //몫*금액 만큼 K에서 빼기
			}else i--; //없으면 더 작은 단위로 
			if(K==0) break; 
		}
		System.out.println(ans);
	}
}
