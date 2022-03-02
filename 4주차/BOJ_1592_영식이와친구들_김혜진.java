package com.ssafy.im;

import java.io.*;
import java.util.*;

public class BOJ_1592_영식이와친구들_김혜진 {
	static String src = "5 3 2";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // N명의 사람
		int M = Integer.parseInt(st.nextToken()); // M번 받으면 게임 끝
		int L = Integer.parseInt(st.nextToken()); // 공을 받은 횟수 : 홀수(시계방향으로 L번째) 짝수(반시계방향으로 L번째)
		
		int[] arr = new int[N+1];
		
		int cnt = 0; // 공을 던지는 횟수
		
		arr[1] = 1; // 첫번째 사람은 처음에 무조건 공을 받음
		int i = 1; // i는 1부터 시작
		// 게임이 끝날 때까지 반복
		while(true) {
			// 한명이라도 M번 공을 받게되면 게임 종료
			if(arr[i]==M) break;
			
			// 공을 받은 횟수가 짝수라면 반시계방향으로 L번째 있는 사람에게 공 전달(기준 왼쪽사람에게)
			if(arr[i]%2==0) i -= L;
			else i += L; // 홀수면 시계방향으로 L번째(기준 오른쪽사람)
			
			if(i>N) i %= N; // N보다 커지면 인덱스에 N으로 나눈 나머지가 들어가야됨
			if(i<1) i += N; // 1보다 작아지면 인덱스 +N
			
			arr[i]++; // 공 받는 인덱스 +1
			cnt++; // 던지는 횟수도 +1
		}
		
		System.out.println(cnt);
		
	}

}
