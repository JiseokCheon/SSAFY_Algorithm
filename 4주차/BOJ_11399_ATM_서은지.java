package com.ssafy.study;

import java.util.*;
import java.io.*;

public class BOJ_11399_ATM_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
		
		// 1. sub로 다음 배열 요소에 더해야 하는 값 쌓아가기
//		int total = 0, sub = 0;
//		for (int i = 0; i < N; i++) {
//			total += arr[i] + sub;
//			sub += arr[i];
//		}
		
		// 2. 첫번째 배열요소는 N번, 마지막 배열 요소는 1번 더해짐
		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum += arr[i] * (N-i);
		}
		
		// 3. 이중포문
//		int sum = 0;
//		for (int i = N-1; i >= 0; i--) {
//			for (int j = i; j >= 0; j--)
//				sum += arr[j];
//		}
		
//		System.out.println(total);
		System.out.println(sum);
	}
}
