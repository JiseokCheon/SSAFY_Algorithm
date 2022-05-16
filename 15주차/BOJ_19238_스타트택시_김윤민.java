package may;

import java.io.*;
import java.util.*;

public class BOJ_19238_스타트택시 {
	static int[][] move = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static int[][] map;
	static  PriorityQueue<Person> pq; //가장 가까운 승객
	static  Map<Integer, Person> m; //승객 리스트
	static int N;
	
	static class Person implements Comparable<Person> {
		int  si, sj, di, dj, bdist;
		public Person( int si, int sj, int di, int dj,  int bdist) {
			this.si = si;
			this.sj = sj;
			this.di = di;
			this.dj = dj;
			this.bdist = bdist; // 백준과의 거리
		}
		@Override
		public int compareTo(Person o) { //우선순위 부여
			if (bdist == o.bdist) {
				if (si == o.si) {
					return sj - o.sj;
				} else {
					return si - o.si;
				}
			} else {
				return bdist - o.bdist;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행,열
		int M = Integer.parseInt(st.nextToken()); // 승객 수
		int O = Integer.parseInt(st.nextToken()); // 초기 연료
		map = new int[N][N]; // 지도

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) map[i][j] =-1; //벽을 -1로 변경
			}
		}
		st = new StringTokenizer(br.readLine());
		int bsi = Integer.parseInt(st.nextToken()) - 1; // 백준의 시작 행번호
		int bsj = Integer.parseInt(st.nextToken()) - 1; // 백준의 시작 열 번호

		m = new HashMap<>();
		pq = new PriorityQueue<Person>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int si = Integer.parseInt(st.nextToken()) - 1; // 시작 행번호
			int sj = Integer.parseInt(st.nextToken()) - 1; // 시작 열 번호
			int di = Integer.parseInt(st.nextToken()) - 1; // 도착 행번호
			int dj = Integer.parseInt(st.nextToken()) - 1; // 도착 열 번호
			m.put(i+1, new Person(si, sj, di, dj, 0)); //map에 승객 정보 저장
			map[si][sj] = i+1; //map에 승객의 번호를 표시
		}

		// 승객 선택
		for (int i = 0; i < M; i++) {
			pq.clear();
			findPerson(bsi,bsj); //가장 가까운 승객 찾기
			if(pq.isEmpty()) { //태울수 있는 승객이 없으면 종료
				O=-1; break;
			}
			Person p = pq.poll();  //우선순위가 가장 높은 승객 선택
			map[p.si][p.sj] = 0 ; //map에서 승객 지우기
			if(O < p.bdist) { //승객한테 가는데 연료 확인
				O=-1; break;
			}
			O-=p.bdist;
			int dist = calcMinDist(p.si, p.sj, p.di, p.dj); //출발-도착지 거리 구하기
			if(O < dist) { //연료 확인
				O=-1; break;
			}
			O+=dist; //연료 채우기
			bsi = p.di; 
			bsj = p.dj;
		}
		System.out.println(O);
	}

	static int findPerson(int si, int sj) { //가장 근접한 승객찾기
		int min = Integer.MAX_VALUE;
		boolean[][] visit = new boolean[N][N];
		Queue<int[]> que = new LinkedList<int[]>();
		visit[si][sj] = true;
		que.offer(new int[] { si, sj, 0 });
		while (!que.isEmpty()) {
			int[] t = que.poll();
			if (t[2] > min)	continue;
			if (map[t[0]][t[1]] > 0 && min >= t[2]) { //태울수 있는 가능성이 있는 승객들을 찾아서 pq에 넣기
				min = t[2];
				Person per = m.get(map[t[0]][t[1]]);
				per.bdist = t[2];
				pq.offer(per);
				continue;
			}
			for (int k = 0; k < 4; k++) {
				int ni = t[0] + move[k][0];
				int nj = t[1] + move[k][1];
				if (ni >= 0 && ni < N && nj >= 0 && nj < N && !visit[ni][nj] && map[ni][nj]>= 0) {
					que.offer(new int[] { ni, nj, t[2] + 1 });
					visit[ni][nj] = true;
				}
			}
		}
		return min;
	}
	
	static int calcMinDist(int si, int sj, int di, int dj ) { //bfs로 출발-목적지 사이의 가장 가까운 경로(거리)찾기
		int min = Integer.MAX_VALUE;
		boolean[][] visit = new boolean[N][N];
		Queue<int[]> que = new LinkedList<int[]>();
		visit[si][sj] = true;
		que.offer(new int[] {si,sj, 0});
		while(!que.isEmpty()) {
			int[] t = que.poll();
			if(t[2] > min ) break;
			if(t[0]==di && t[1]==dj && min > t[2]) {
				min = t[2];
				continue;
			}
			for (int k = 0; k < 4; k++) {
				int ni = t[0]+ move[k][0];
				int nj = t[1]+ move[k][1];
				if(ni>=0 && ni<N && nj>=0 && nj<N 
						&& !visit[ni][nj] && map[ni][nj]>= 0 ) {
					que.offer(new int[] {ni,nj, t[2]+1});
					visit[ni][nj]= true;
				}
			}
		}
		return min;
	}
}

