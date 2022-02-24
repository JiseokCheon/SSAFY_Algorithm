package com.ssafy.study;

import java.util.*;
import java.io.*;

public class BJ_1091_카드섞기_서은지 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());
		int P[] = new int[N];
		int S[] = new int[N];
		int tmp[] = new int[N];
		int cnt = 0;

		StringTokenizer stP = new StringTokenizer(in.readLine(), " ");
		StringTokenizer stS = new StringTokenizer(in.readLine(), " ");
		boolean flag = true;
		for (int i = 0; i < N; i++) {
			P[i] = Integer.parseInt(stP.nextToken());
			S[i] = Integer.parseInt(stS.nextToken());
			tmp[i] = i % 3;
			if (P[i] != tmp[i])
				flag = false;
		}
		
		if (flag) {	// 이미 P 순서대로 주어졌다면 0 반환 후 메인함수 종료
			System.out.println(cnt);
			return;
		}
		
		int temp[] = new int[N];
		boolean isImpossible;	// 사이클이 한 바퀴 돌았는데도 while문 탈출 못하면 P 순서 불가능이라 판단
		while (!flag) {	// P와 같은 순서면 탈출
			flag = true;
			isImpossible = true;
			cnt++;
			for (int i = 0; i < N; i++) {
				temp[i] = tmp[S[i]];
			}
			for (int i = 0; i < N; i++) {
				tmp[i] = temp[i];
				if (temp[i] != P[i])
					flag = false;
				if (temp[i] != i%3)
					isImpossible = false;
			}
			if (isImpossible) {	// 불가능하면 cnt -1 출력
				cnt = -1;
				break;
			}
		}
		System.out.println(cnt);
	}
}
