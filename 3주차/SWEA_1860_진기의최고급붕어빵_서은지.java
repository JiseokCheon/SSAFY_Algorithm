package com.ssafy.study;

import java.util.*;
import java.io.*;

public class SWEA_1860_진기의최고급붕어빵_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int total = 0;
			PriorityQueue<Integer> visitors = new PriorityQueue<Integer>();
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < N; i++)
				visitors.offer(Integer.parseInt(st.nextToken()));
//			int time = 0;
			boolean isPossible = true;

			// 2. 도착한 손님 시간 순서대로 확인
			int given = 0;	// 판매된 붕어빵 수
			while (!visitors.isEmpty()) {
				int visitTime = visitors.poll();	// 손님 도착 시간
				total = (visitTime / M) * K - (++given);	// 현재 붕어빵 수
				isPossible = total >= 0 ? true : false;
				if (!isPossible)
					break;
			}

			// 1. 제일 마지막 손님 도착 시간까지 시간 비교
//			outer:
//			while (!visitors.isEmpty()) {
//				isPossible = true;
//				if (time != 0 && time % M == 0)
//					total += K;
//				while(!visitors.isEmpty() && visitors.peek() == time) {	// 같은 시간에 도착한 손님들
//					total -= 1;
//					visitors.poll();
//					if (total < 0) {
//						isPossible = false;
//						break outer;
//					}
//				}
//				time ++;
//			}
			sb.append("#" + tc + " ").append(isPossible ? "Possible\n" : "Impossible\n");
		}
		System.out.println(sb);
	}
}
