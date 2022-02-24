package com.ssafy.study;

import java.util.*;
import java.io.*;

public class BOJ_2840_행운의바퀴_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		char[] wheel = new char[N];
		Arrays.fill(wheel, '?'); // 모두 ? 로 초기화
		int current = 0; // 현재 화살표 위치
		boolean isImpossible = false; // 바퀴 성립 여부
		List<Character> letters = new ArrayList<Character>();	// 등장한 글자 리스트(중복 확인용)
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int S = Integer.parseInt(st.nextToken());
			char letter = st.nextToken().charAt(0);
			current = (current + S) % N;
			if (wheel[current] != '?' && wheel[current] != letter) { // 칸에 있는 글자와 다른 경우
				isImpossible = true;
				break;
			} else if (wheel[current] == '?') {
				for (int j = 0; j < letters.size(); j++) {	// 글자 중복 확인
					if (letters.get(j) == letter) {
						isImpossible = true;
						break;
					}
				}
				if (isImpossible)
					break;
				wheel[current] = letter;
				letters.add(letter);
			}
		}
		if (!isImpossible) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < N; i++)
				sb.append(wheel[(N + current - i) % N]);
			System.out.println(sb);
		} else
			System.out.println("!");
	}
}
