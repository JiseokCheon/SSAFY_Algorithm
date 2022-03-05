package com.ssafy.bj;

import java.util.*;
import java.io.*;

public class BOJ_11399_ATM_김혜진 {

	static String src = "5\r\n" + 
			"3 1 4 3 2";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		
		int N = Integer.parseInt(br.readLine()); // 대기 사람의 수
		int[] min = new int[N+1]; // 1번부터 N번까지 해야하니까 N+1 
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			min[i] = Integer.parseInt(st.nextToken()); // 1번부터 걸리는 시간
		}
		
		Arrays.sort(min); // 최소 시간을 구해야하므로 오름차순 정렬
		
		int sum=0;
		// 1번부터 N번까지 누적해서 합해줘야함
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=i; j++) {
				sum += min[j];
			}
		}
		
		System.out.println(sum);
		
	}

}
