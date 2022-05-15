package may;

import java.io.*;
import java.util.*;

public class BOJ_6064_카잉달력 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			int gcd = gcd(M,N);  
			int maxDay= M*N/gcd; //최소공배수 구하기 (마지막 날)
			int cnt = -1; //몇번째 날인지 저장할 변수 
			
			if(y==M) y=0; // M까지 표기 가능하므로 나머지를 구하기 위해서 M이면 0으로 바꿔주기
			for (int i = x; i <= maxDay; i+=N) { //N으로 나눴을때 나머지가 x인 날짜들만 확인하기
				if(i%M == y) { // M으로 나눈 나머지가 y이면 찾으려는 해이므로 저장하고 break;
					cnt = i; 
					break;
				}
			}
			sb.append(cnt+"\n");
		}
		System.out.println(sb);
	}

	private static int gcd(int x, int y) { //유클리드호제법
		if(y ==0 ) return x;
		return gcd(y, x%y);
	}
}
