package BJ_11401_이항계수3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		/*
		 * 페르마의 소정리
		 *  nCk 	= n! / (k! (n-k)!) % p
		 * 			= n! * (k! (n-k)!)^(-1)%p -> a=n!,b=k!*(n-k)! 
		 * 			= a * b^(-1) %p 
		 * 			= a * b^(-1) *1 %p 
		 * 			= a * b^(-1) *(b^(p-1)%p) % p
		 * 			= a * (b^(p-2) %p ) %p
		 * 			= ((a%p) (b^(p-2) %p) %p
		 */
		long p = 1000000007L;
		long fac[] = new long[n + 1];
		fac[0] = 1;
		fac[1] = 1;

		for (int i = 2; i <= n; i++) {
			fac[i] = (fac[i - 1] * i) % p;
		}

		long top = fac[n];
		long bottom = (fac[k] * fac[n - k]) % p;
//		System.out.println(top+" "+bottom);
		long e = p - 2; // 지수
		long bb = 1; // 밑

		while (e > 0) {
			if (e % 2 == 1) {
				bb = (bb * bottom) % p;
			}
			bottom = (bottom * bottom) % p;
			e = e / 2;
		}

		System.out.println( ( (top % p) * (bb % p)) % p);

	}

}
