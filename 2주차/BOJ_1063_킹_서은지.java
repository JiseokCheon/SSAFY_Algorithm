package com.ssafy.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1063_킹_서은지 {
	static int kR, kC, sR, sC;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		String king = st.nextToken();
		String stone = st.nextToken();
		int N = Integer.parseInt(st.nextToken());

		// 좌표 (0, 0) ~ (8, 8)
		kR = king.charAt(1) - '1';
		kC = king.charAt(0) - 'A';
		sR = stone.charAt(1) - '1';
		sC = stone.charAt(0) - 'A';

		for (int i = 0; i < N; i++) {
			int dr = 0, dc = 0;	// 행, 열 이동할 방향
			String move = in.readLine();
			if (move.contains("B")) {
				dr = -1;	// 하
			} else if (move.contains("T")) {
				dr = 1;		// 상
			}
			if (move.contains("L")) {
				dc = -1;	// 좌
			} else if (move.contains("R")) {
				dc = 1;		// 우
			}

			if ((kR + dr) >= 0 && (kC + dc) >= 0 && (kR + dr) < 8 && (kC + dc) < 8) {	// 킹이 체스판을 벗어나지 않으면
				if ((kR + dr) == sR && (kC + dc) == sC) {	// 만약 킹 다음 위치에 돌이 있다면
					if ((sR + dr) >= 0 && (sC + dc) >= 0 && (sR + dr) < 8 && (sC + dc) < 8) {	// 돌이 체스판을 벗어나지 않으면
						sR += dr;
						sC += dc;
						kR += dr;
						kC += dc;
					}
				} else {	// 킹 다음 위치에 돌이 없다면
					kR += dr;
					kC += dc;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append((char) (kC + 'A') + "" + (char) (kR + '1') + "\n");
		sb.append((char) (sC + 'A') + "" + (char) (sR + '1'));
		System.out.println(sb);
	}
}
