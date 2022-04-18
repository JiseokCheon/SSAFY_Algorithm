package com.ssafy.study.day0418;

import java.util.*;
import java.io.*;

public class BOJ_16235_나무재테크_서은지 {
	static int N, M, K;
	static int[][] map, A;
	static int[][] delta = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
	static List<int[]> parent, child;
	static Queue<int[]> dead;
//	static List<int[]> trees;
	static PriorityQueue<int[]> trees;
	static PriorityQueue<int[]> left;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		A = new int[N][N];
//		trees = new LinkedList<int[]>();
		trees = new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			};
		});
		left = new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			};
		});
		dead = new LinkedList<int[]>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = 5;
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
//			trees.add(new int[] {Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())});
			trees.offer(new int[] {Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())});
		}
//		Collections.sort(trees, new Comparator<int[]>() {
//			@Override
//			public int compare(int[] o1, int[] o2) {
//				return o1[2] - o2[2];
//			}
//		});
		
		for (int i = 0; i < K; i++) {
			spring();
			summer();
			fall();
			winter();
		}
		System.out.println(trees.size());
	}
	
	public static void spring() {
		parent = new LinkedList<int[]>();
//		for (int i = 0; i < trees.size(); i++) {
//			if (map[trees.get(i)[0]][trees.get(i)[1]] >= trees.get(i)[2]) {
//				map[trees.get(i)[0]][trees.get(i)[1]] -= trees.get(i)[2];
//				if (++trees.get(i)[2] % 5 == 0)
//					parent.add(trees.get(i));
//			} else {
//				dead.offer(trees.get(i));
//				trees.remove(i);
//				i--;
//			}
//		}
		while(!trees.isEmpty()) {
			int[] tmp = trees.poll();
			if (map[tmp[0]][tmp[1]] >= tmp[2]) {
				map[tmp[0]][tmp[1]] -= tmp[2];
				left.offer(new int[] {tmp[0], tmp[1], tmp[2] + 1});
				if ((tmp[2] + 1) % 5 == 0)
					parent.add(new int[] {tmp[0], tmp[1], tmp[2] + 1});
			} else {
				dead.offer(tmp);
			}
		}
	}
	
	public static void summer() {
		while (!dead.isEmpty())
			map[dead.peek()[0]][dead.peek()[1]] += dead.poll()[2] / 2; 
	}
	
	public static void fall() {
		child = new LinkedList<int[]>();
		for (int[] p : parent) {
			for (int i = 0; i < 8; i++) {
				int dr = p[0] + delta[i][0];
				int dc = p[1] + delta[i][1];
				if (dr >= 0 && dc >= 0 && dr < N && dc < N)
					child.add(new int[] {dr, dc, 1});
			}
		}
//		trees.addAll(0, child);
		trees.addAll(child);
	}
	
	public static void winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				map[i][j] += A[i][j];
		}
		while (!left.isEmpty())
			trees.offer(left.poll());
	}

}
