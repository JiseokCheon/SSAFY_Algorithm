package april;

import java.io.*;
import java.util.*;

public class BOJ_16235_나무재테크 {
	static class Tree {
		int r, c, age;
		public Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[][] move = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][N]; // 양분의 양
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], 5);
		}
		int[][] add = new int[N][N]; // 추가할 양분의 양
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				add[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		PriorityQueue<Tree> trees = new PriorityQueue<>(new Comparator<Tree>() {
			@Override
			public int compare(Tree o1, Tree o2) {
				return o1.age-o2.age;
			}
		});
		for (int i = 0; i < M; i++) { // 심을 나무
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			trees.add(new Tree(x-1, y-1, z));
		}
		Queue<Tree> newTree = new LinkedList<Tree>(); //봄여름가을겨울이 지난 후 남아있는 새로운 나무들
		Queue<Tree> dead = new LinkedList<Tree>();  //죽은 나무
		Queue<Tree> bun = new LinkedList<Tree>(); //번식 가능한 나무
		
		for (int k = 0; k < K; k++) { // K년까지 반복
			// 봄
			while(!trees.isEmpty()) {
				Tree tree = trees.poll();
				if (tree.age <= map[tree.r][tree.c]) { // 나이만큼 양분이 있으면 나이 1증가 후 pq에 넣기
					map[tree.r][tree.c] -= tree.age;
					newTree.add(new Tree(tree.r, tree.c, tree.age + 1)); //양분 먹고 나이 1증가한 뒤 현재 나무 리스트에 추가
					if ((tree.age+1) % 5 == 0) bun.add(new Tree(tree.r, tree.c, tree.age + 1)); //번식 가능한 나무들 추가
				} else { // 죽은 나무 저장
					dead.offer(new Tree(tree.r, tree.c, tree.age));
				}
			}
			
			// 여름 : 죽은 나무 양분으로
			while(!dead.isEmpty()) {
				Tree dt = dead.poll(); 
				map[dt.r][dt.c]+= dt.age/2;
			}

			// 가을 : 나무 번식
			while(!bun.isEmpty()) {
				Tree bt = bun.poll();
				for (int j = 0; j < 8; j++) {
					int nr = bt.r + move[j][0];
					int nc = bt.c + move[j][1];
					if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
						newTree.add(new Tree(nr, nc, 1));
					}
				}
			}
			
			// 겨울 : 땅에 양분 추가
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] += add[i][j];
				}
			}
			while(!newTree.isEmpty()) {
				trees.add(newTree.poll());
			}
		}
		System.out.println(trees.size());
	}
}
