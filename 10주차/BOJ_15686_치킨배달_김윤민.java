package april;

import java.io.*;
import java.util.*;

public class BJ_15686_치킨배달 {
	static int[][] move = {{0,1},{0,-1},{1,0},{-1,0}};
	static int N, M, K, min;
	static int[][] map;
	static int[] result;
	static ArrayList<int[]> homes;
	static ArrayList<int[]> bhcs;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map =new int[N+1][N+1];
		homes = new ArrayList<>();
		bhcs = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
				if(map[i][j]==1) homes.add(new int[] {i,j}); //집인 경우 집 list 추가
				else if(map[i][j]==2) bhcs.add(new int[] {i,j}); //치킨집인 경우 치킨집list 추가
			}
		}
		K = bhcs.size(); //치킨집 개수
		min = Integer.MAX_VALUE; //도시의 치킨 거리 최솟값
		result = new int[K];
		//M개 뽑는 조합
		combi(0,0);
		System.out.println(min);
	}
	private static void combi(int cnt, int start) {
		if(cnt==M) {
			//각 집마다 치킨 거리 구하기
			int [] cd = new int[homes.size()]; //각 집의 치킨 거리 저장할 배열
			for (int i = 0; i < homes.size(); i++) { 
				int dist = N*N;
				int[] h = homes.get(i);
				for (int j = 0; j < M; j++) { //뽑은 치킨 집만큼 반복
					int[] b = bhcs.get(result[j]); //고른 치킨집 하나씩 거리 비교해서 최소 거리 저장하기
					dist = Math.min(dist, Math.abs(h[0]-b[0])+Math.abs(h[1]-b[1]));
				}
				cd[i] = dist;
			}
			
			int sum =0; //도시의 치킨 거리 
			for (int i = 0; i < homes.size(); i++) {
				sum+= cd[i];
			}
			min = Math.min(min, sum);
			return;
		}
		
		for (int i = start; i < K; i++) {
			result[cnt] = i;
			combi(cnt+1, i+1);
		}
	}
}
