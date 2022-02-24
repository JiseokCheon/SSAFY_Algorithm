package com.ssafy.study;

import java.util.*;
import java.io.*;

public class BOJ_1417_국회의원선거_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int dasom = Integer.parseInt(in.readLine());	// 기호 1번
		
		PriorityQueue<Integer> candidates = new PriorityQueue<Integer>(Collections.reverseOrder());	// 자동으로 역순 정렬
		for (int i = 0; i < N - 1; i++)
			candidates.add(Integer.parseInt(in.readLine()));
		
		int cnt = 0;
		while (!candidates.isEmpty()) {
			int candidate = candidates.poll();	// 제일 앞 후보자 : 우선순위큐에서 제일 큰 수
			if (dasom > candidate)
				break;
			dasom++;
			cnt++;
			candidates.add(candidate - 1);	// 아직 다솜이 득표수보다 크거나 같을 수 있음
		}
		System.out.println(cnt);
	}
}
