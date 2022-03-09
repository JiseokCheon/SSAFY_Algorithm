package com.ssafy.study;

import java.util.*;
import java.io.*;

public class BOJ_12100_2048Easy_서은지 {

	static int N, max;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		int[][] board = new int[N][N];
		List<int[]> blocks = new LinkedList<int[]>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] != 0) {
					blocks.add(new int[] { i, j, board[i][j] });
					max = Math.max(max, board[i][j]);
				}
			}
		}
		game(0, blocks);
		System.out.println(max);
	}

	public static void game(int t, List<int[]> blocks) {
		// 기저조건 : 5번째거나 남은 블록이 1개일 때
		if (t == 5 || blocks.size() == 1) {
			// 남은 블록들의 값과 최댓값 비교
			for (int i = 0; i < blocks.size(); i++) {
				max = Math.max(max, blocks.get(i)[2]);
			}
			return;
		}
		// 상하좌우 재귀 탐색
		for (int i = 0; i < 4; i++) {
			switch (i) {
			case 0:
				game(t+1, up(blocks));
				break;
			case 1:
				game(t+1, down(blocks));
				break;
			case 2:
				game(t+1, left(blocks));
				break;
			case 3:
				game(t+1, right(blocks));
				break;
			}
		}
	}

	// 상
	public static List<int[]> up(List<int[]> blocks) {
		// 리스트를 열(오름차순)->행(오름차순)으로 정렬
		Collections.sort(blocks, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				int c = o1[1] - o2[1];
				if (c == 0)
					return o1[0] - o2[0];
				return c;
			}
		});
		// 행, 열 시작 인덱스
		int rIdx = 0, cIdx = 0;
		List<int[]> next = new LinkedList<int[]>();
		for (int i = 0; i < blocks.size(); i++) {
			// 다음 검색할 리스트와 열 인덱스가 다르다면 행, 열 인덱스 초기화
			if (cIdx != blocks.get(i)[1]) {
				cIdx = blocks.get(i)[1];
				rIdx = 0;
			}
			// 마지막 인덱스라면 다음에 비교할 인덱스 없으므로 바로 next리스트에 추가 후 break
			if (i == blocks.size() - 1) {
				next.add(new int[] { rIdx, cIdx, blocks.get(i)[2] });
				break;
			}
			// 열이 같고, 값도 같으면 next 리스트에 행인덱스 + 1, 2배 값으로 저장, 다음 인덱스는 합쳐졌으므로 i+1
			if (cIdx == blocks.get(i + 1)[1] && blocks.get(i)[2] == blocks.get(i + 1)[2]) {
				next.add(new int[] { rIdx++, cIdx, 2 * blocks.get(i)[2] });
				i++;
			}
			// 아니라면 next 리스트에 행인덱스 + 1만 변경해서 추가
			else {
				next.add(new int[] { rIdx++, cIdx, blocks.get(i)[2] });
			}
		}
		return next;
	}

	// 하
	public static List<int[]> down(List<int[]> blocks) {
		Collections.sort(blocks, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				int c = o1[1] - o2[1];
				if (c == 0)
					return o2[0] - o1[0];
				return c;
			}
		});
		List<int[]> next = new LinkedList<int[]>();
		int rIdx = N - 1;
		int cIdx = 0;
		for (int i = 0; i < blocks.size(); i++) {
			if (cIdx != blocks.get(i)[1]) {
				cIdx = blocks.get(i)[1];
				rIdx = N - 1;
			}
			if (i == blocks.size() - 1) {
				next.add(new int[] { rIdx, cIdx, blocks.get(i)[2] });
				break;
			}
			if (cIdx == blocks.get(i + 1)[1] && blocks.get(i)[2] == blocks.get(i + 1)[2]) {
				next.add(new int[] { rIdx--, cIdx, 2 * blocks.get(i)[2] });
				i++;
			} else {
				next.add(new int[] { rIdx--, cIdx, blocks.get(i)[2] });
			}
		}
		return next;
	}

	// 좌
	public static List<int[]> left(List<int[]> blocks) {
		Collections.sort(blocks, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				int r = o1[0] - o2[0];
				if (r == 0)
					return o1[1] - o2[1];
				return r;
			}
		});
		List<int[]> next = new LinkedList<int[]>();
		int rIdx = 0;
		int cIdx = 0;
		for (int i = 0; i < blocks.size(); i++) {
			if (rIdx != blocks.get(i)[0]) {
				rIdx = blocks.get(i)[0];
				cIdx = 0;
			}
			if (i == blocks.size() - 1) {
				next.add(new int[] { rIdx, cIdx, blocks.get(i)[2] });
				break;
			}
			if (rIdx == blocks.get(i + 1)[0] && blocks.get(i)[2] == blocks.get(i + 1)[2]) {
				next.add(new int[] { rIdx, cIdx++, 2 * blocks.get(i)[2] });
				i++;
			} else {
				next.add(new int[] { rIdx, cIdx++, blocks.get(i)[2] });
			}
		}
		return next;
	}

	// 우
	public static List<int[]> right(List<int[]> blocks) {
		Collections.sort(blocks, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				int r = o1[0] - o2[0];
				if (r == 0)
					return o2[1] - o1[1];
				return r;
			}
		});
		List<int[]> next = new LinkedList<int[]>();
		int rIdx = 0;
		int cIdx = N - 1;
		for (int i = 0; i < blocks.size(); i++) {
			if (rIdx != blocks.get(i)[0]) {
				rIdx = blocks.get(i)[0];
				cIdx = N - 1;
			}
			if (i == blocks.size() - 1) {
				next.add(new int[] { rIdx, cIdx, blocks.get(i)[2] });
				break;
			}
			if (rIdx == blocks.get(i + 1)[0] && blocks.get(i)[2] == blocks.get(i + 1)[2]) {
				next.add(new int[] { rIdx, cIdx--, 2 * blocks.get(i)[2] });
				i++;
			} else {
				next.add(new int[] { rIdx, cIdx--, blocks.get(i)[2] });
			}
		}
		return next;
	}
}
