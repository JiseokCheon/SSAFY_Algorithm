package July;

import java.io.*;
import java.util.*;

public class BOJ_20058_마법사상어와파이어스톰_김윤민 {
	static int[][] move = {{0,1},{0,-1},{1,0},{-1,0}};
	static int[][] map;
	static int sum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int n = (int) Math.pow(2, N); //전제 길이
		map =new int[n][n];
		int[] list_l = new int[Q]; // L의 리스트
		
		sum=0; //전체 얼음 개수
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				sum+= map[i][j];
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			list_l[i] =  Integer.parseInt(st.nextToken());
		}
		
		//Q만큼 실행
		for (int i = 0; i < Q; i++) {
			rotate(list_l[i]); //회전 시키기
			clearIce(); //조건에 따라 얼음 제거
			
		}
		
		//남아있는 얼음의 양
		System.out.println(sum);
		//가장 큰 덩어리의 칸 수
		System.out.println(kan());
		
	}


	static void rotate(int l) { //l길이의 구역을 90도 회전 
		int[][] copy = new int[map.length][map.length];
		for (int i = 0; i < map.length; i++) {
			copy[i]  = Arrays.copyOf(map[i], map.length);
		}
		
		int nl = (int) Math.pow(2, l); //부분격자의 길이
		for (int i = 0, j=0; j < map.length; ) {
			//시작점을 기준으로 시계방향으로 90도 회전
			for (int p = 0; p < nl; p++) {
				 for (int q = 0; q < nl; q++) {
					map[i+q][j+nl-p-1]= copy[i+p][j+q];
				}
			}
			i+= nl; //격자 길이만큼 시작점 행 인덱스에 추가
			if(i>map.length-1) { //범위 벗어나면 열에 격자길이만큼 추가 후 행의 다시 0으로
				i=0; 
				j+= nl;
			}	
		}
	}
	static void clearIce() { //
		int[][] copy = new int[map.length][map.length];
		for (int i = 0; i < map.length; i++) {
			copy[i]  = Arrays.copyOf(map[i], map.length);
		}
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if(map[i][j] > 0) { //현재 위치의 얼음이 0보다 큰 경우에만
					int cnt=0;
					for (int k = 0; k < 4; k++) { //인접한 구역에 얼음이 있는지 확인
						int ni = i+move[k][0];
						int nj = j+move[k][1];
						if(ni>=0 && ni<map.length && nj>=0 && nj<map.length && copy[ni][nj]>0) {
							cnt++;
						}
					}
					if(cnt < 3) { //얼음이 있는 구역이 3개미만인 경우 얼음 제거
						map[i][j]--;
						sum--; //전체 얼음 개수도 -1
					}
				}
			}
		}
	}

	private static int kan() { //큰 덩어리의 칸 개수 구하기
		int ans = 0;
		int n = map.length;
		boolean[][]visit = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(!visit[i][j] && map[i][j]>0) { //아직 방문안하고, 얼음이 있는 경우
					int cnt=0;
					Queue<int[]> que = new LinkedList<int[]>();
					que.add(new int[] {i, j});
					visit[i][j] = true;
					while(!que.isEmpty()) { //bfs로 얼음의 덩어리가 차지하는 칸의 개수 구하기
						int[] temp = que.poll(); 
						cnt++;
						for (int k = 0; k < 4; k++) {
							int ni= temp[0]+move[k][0];
							int nj= temp[1]+move[k][1];
							if(ni>=0 && ni<map.length && nj>=0 && nj<map.length && map[ni][nj]>0 && !visit[ni][nj]) {
								que.offer(new int[] {ni,nj});
								visit[ni][nj] = true;
							}
						}
					}
					ans = Math.max(ans, cnt); //가장 큰 칸의 개수로 업데이트
				}
			}
		}
		return ans;
	}
}
