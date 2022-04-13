package com.ssafy.study.day0413;

import java.util.*;
import java.io.*;

public class BOJ_15686_치킨배달_서은지 {
	static int N, M;
	static List<House> houseList;
	static List<Chicken> chickenList;
	static boolean isSelected[];
	static int[][][] prioritySet;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int cIdx = 0, hIdx = 0;
		chickenList = new ArrayList<Chicken>();
		houseList = new ArrayList<House>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 2)
					chickenList.add(new Chicken(i, j, cIdx++));
				else if (num == 1)
					houseList.add(new House(i, j, hIdx++));

			}
		}

		isSelected = new boolean[cIdx];	// 치킨집 조합 확인
		prioritySet = new int[hIdx][cIdx][2];	// house별 치킨집({번호, 거리}) 거리순으로 정렬된 배열
		for (int i = 0; i < hIdx; i++) {
			houseList.get(i).setPriorities(chickenList);
			for (int j = 0; j < cIdx; j++)
				prioritySet[i][j] = houseList.get(i).priorities.poll();
		}

		comb(0, 0);
		System.out.println(min);
	}

	public static void comb(int start, int cnt) {
		if (cnt == M) {
			int sum = 0;
			for (int i = 0; i < houseList.size(); i++) {
				for (int j = 0; j < chickenList.size(); j++) {
					if (isSelected[prioritySet[i][j][0]]) {	// 거리 순서대로 조합에 포함된 치킨집번호인지 확인
						sum += prioritySet[i][j][1];
						break;
					}
				}
			}
			min = Math.min(min, sum);
			return;
		}

		for (int i = start; i < chickenList.size(); i++) {
			isSelected[i] = true;
			comb(i + 1, cnt + 1);
			isSelected[i] = false;
		}
	}

}

class Chicken {
	int row, col, num;

	public Chicken(int row, int col, int num) {
		this.row = row;
		this.col = col;
		this.num = num;
	}

}

class House {
	int row, col, num;
	PriorityQueue<int[]> priorities;

	public House(int row, int col, int num) {
		this.row = row;
		this.col = col;
		this.num = num;
	}

	public void setPriorities(List<Chicken> list) {
		priorities = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];	// 거리순
			}
		});
		for (int i = 0; i < list.size(); i++) {
			int distance = Math.abs(row - list.get(i).row) + Math.abs(col - list.get(i).col);
			priorities.add(new int[] { list.get(i).num, distance });	// {치킨집번호, 거리}
		}
	}

}
