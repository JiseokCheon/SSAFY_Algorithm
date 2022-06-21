package July;

import java.io.*;
import java.util.*;


public class BOJ_21609_상어중학교 {
	static int[][] map ;
	static class Group{
		int cnt, rb_cnt, color; //블록 수, 무지개블록 수
		List<int[]> list; //블록 리스트
		public Group(int cnt, int rb_cnt, int color, List<int[]> list) {
			this.cnt = cnt;
			this.rb_cnt = rb_cnt;
			this.color = color;
			this.list = list;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[][] move = {{0,1},{0,-1},{1,0},{-1,0}};

		int N = Integer.parseInt(st.nextToken()); //한변의 크기
		int M = Integer.parseInt(st.nextToken()); //색상 개수
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] =  Integer.parseInt(st.nextToken());
			}
		}
		int ans = 0;
		while(true) {
			PriorityQueue<Group> pq = new PriorityQueue<>(new Comparator<Group>() { //조건에 맞춰서 우선순위 정하기
				@Override
				public int compare(Group o1, Group o2) {
					if(o1.cnt != o2.cnt) {
						return o2.cnt-o1.cnt;	
					}else if(o1.rb_cnt != o2.rb_cnt) {
						return o2.rb_cnt - o1.rb_cnt;
					}else if(o1.list.get(0)[0] != o2.list.get(0)[0]) {
						return o2.list.get(0)[0] - o1.list.get(0)[0];
					}else return o2.list.get(0)[1] - o1.list.get(0)[1];
				}
			});
			boolean[][] visit = new boolean[N][N];
			
			//블록 그룹 찾기 
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] > 0 && !visit[i][j]) {
						int cnt=0, rb_cnt=0, color=0; //그룹의 블록수, 무지개블록 수 , 블록의 색
						List<int[]> l = new ArrayList<int[]>(); //블록 리스트
						
						Queue<int[]> q = new LinkedList<int[]>(); //그룹을 찾는 bfs에 사용
						q.add(new int[] {i,j,map[i][j]}); //기준 블록
						visit[i][j] = true;
						color = map[i][j]; //블록 색 저장
						while(!q.isEmpty()) {
							int[] t = q.poll();
							cnt++; //블록 개수 추가
							if(t[2]==0) rb_cnt++; //무지개 블록 개수 추가
							l.add(new int[] {t[0], t[1]});
							
							for (int k = 0; k < 4; k++) { //4방 탐색
								int ni = t[0]+move[k][0];
								int nj = t[1]+move[k][1];
								if(ni>=0 && ni<N && nj>=0 && nj<N 
										&& !visit[ni][nj] 
												&&  (map[ni][nj] == color || map[ni][nj] == 0 )) { //무지개 블록 또는 블록의 색이 일치한 경우
									q.add(new int[] {ni,nj, map[ni][nj]}); 
									visit[ni][nj] = true;
								}
							}
						}
						if(cnt >= 2  && cnt-rb_cnt >=1) { //블록이 2개 이상이고 일반 블록이 1개 이상인 경우에만 pq에 넣기
							pq.add(new Group(cnt, rb_cnt,color, l));
						}
						for (int k = 0; k < l.size(); k++) { //무지개 블록은 다른 그룹이 될 수도 있으므로 visit배열을 false로 변경해주기
							if(map[l.get(k)[0]][l.get(k)[1]] ==0) {
								visit[l.get(k)[0]][l.get(k)[1]] = false;
							}
						}
					}
				}
			}
			if(pq.isEmpty()) break; //찾은 그룹이 하나도 없는 경우 while문 종료
			Group target = pq.poll();
			for (int i = 0; i < target.list.size(); i++) { //블록 그룹 제거하기
				map[target.list.get(i)[0]][target.list.get(i)[1]] = -100; //제거한 블록은 -100으로
			}
			ans+= (int)Math.pow(target.list.size(), 2); //점수 더하기
			gravity(); //중력 작용
			rotate(); //회전
			gravity(); // 중력
		}
		System.out.println(ans);
	}
	private static void rotate() { //반시계 90도 회전
		int n = map.length;
		int[][] copy = new int[n][n];
		for (int i = 0; i < n; i++) {
			copy[i] = Arrays.copyOf(map[i], n);
		}
		for (int i = 0; i <n; i++) {
			for (int j = 0; j < n; j++) {
				map[n-j-1][i] = copy[i][j];
			}
		}
	}
	private static void gravity() { //중력 작용
		int n=map.length;
		for (int i = n-2; i >=0;  i--) {
			for (int j = 0; j < n; j++) {
				if(map[i][j] >= 0) { //검은 블록이 아닌 블록만 중력 작용
					for (int p = i+1; p <= n ; p++) { //현재 위치의 행+1부터 n까지 행을 바꾸기
						if(p==n ||map[p][j] == -1 || map[p][j] >= 0 ) { //만약 행이 n이거나, 검은 블록이거나, 다른 블록이 있다면 
							map[p-1][j] = map[i][j]; //행-1 자리에 블록 옮겨주고
							if(p-1 != i) { //옮긴 자리가 현재 위치와 다른 경우에만 -100으로 변경해주기
								map[i][j] = -100;
							}
							break;
						}
					}
				}
				
			}
		}
	}
}
