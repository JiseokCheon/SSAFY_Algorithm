package com.ssafy.study.day0412;

import java.util.*;
import java.io.*;

public class BOJ_1676_팩토리얼0의개수_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		if (N < 5)
			System.out.println(0);
		else {
			int cnt = 0;
			while(N >= 5) {
				cnt += N / 5;
				N /= 5;
			}
			System.out.println(cnt);
		}
	}
	
	// 처음 접근
//	public static void main1(String[] args) throws Exception {
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		int N = Integer.parseInt(in.readLine());
//		if (N == 0)
//			System.out.println(0);
//		else {
//			int cnt = 0;
//			while (N > 0) {
//				if (N % 5 == 0) {
//					int n = N;
//					while (true) {
//						if (n % 5 == 0)
//							cnt++;
//						else
//							break;
//						n /= 5;
//					}
//				}
//				N--;
//			}
//			System.out.println(cnt);
//		}
//	}

}
