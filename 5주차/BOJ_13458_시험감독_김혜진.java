package com.ssafy.bj;

import java.io.*;
import java.util.*;

// 1. 총 감독관은 무조건 1명 있어야함 - 총감독관이 감시할 수 있는 학생의 수(B) 만큼 빼줌
// 2. 남은 학생 수가 0 이하라면 부감독관 필요 x
// 3. 부감독관 남아있으면 남은학생%C (나머지연산)
// 4. 나머지 연산 결과 0이라면 : 부감독관의 수 : (남은학생/C) / 아니라면 (남은학생/C)+1
public class BOJ_13458_시험감독_김혜진 {
//	static String src = "5\r\n" + 
//			"10 9 10 9 10\r\n" + 
//			"7 2";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		br = new BufferedReader(new StringReader(src));
		
		int N;
		int B,C;
		int[] std;
		
		N = Integer.parseInt(br.readLine());
		std = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			std[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		// 시험장 개수와 학생의 명수가 최대 1,000,000 이므로 int 가 아닌 long으로 선언 해줘야함
		long result=0;
		for(int i=0; i<N; i++) {
			// 총감독관은 무조건 1명 있어야함 (B명 감독)
			// num : 남은 학생들의 수
			int num = std[i]-B;
			result++;
			// 남은 학생수가 0 이하라면 부감독관 필요 x
			if(num<=0) continue;
			// 부감독관 명수 구하기
			// 나머지가 딱 0이라면
			if(num%C == 0) result += num/C; // 몫 만큼 부감독관의 수 더해줌
			else result += (num/C +1);
		}
		
		System.out.println(result);
	}

}
