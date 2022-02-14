package com.ssafy.im;

import java.util.*;
import java.io.*;

public class BOJ_3060_욕심쟁이돼지 {
	static String src = "2\r\n" + 
			"21\r\n" + 
			"1 2 3 4 5 6\r\n" + 
			"21\r\n" + 
			"1 2 3 4 5 7";
	
	static int N;
	static int[] pigs = new int[7];
	static int days;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine()); // 사료의 개수
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int sum = 0;
			
			for(int i=1; i<=6; i++) {
				pigs[i] = Integer.parseInt(st.nextToken());
				sum += pigs[i];
			}
			
			days = 1;
			// 하루가 지날 때마다 돼지들이 먹는 사료 양 4배씩 증가
			while(N-sum>=0) {
				sum *= 4;
				days++;
			}
			System.out.println(days);
		}
	}

}
