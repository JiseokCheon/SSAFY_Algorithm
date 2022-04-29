import java.io.*;
import java.util.*;

public class BOJ_17142_연구소3 {
	static int[][] move = {{1,0},{-1,0},{0,1},{0,-1}};
	static int N, M, VN, ans;
	static boolean flag;
	static int[][] map;
	static ArrayList<int[]> virus;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		virus = new ArrayList<>();
		ans = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) virus.add(new int[] {i,j});
			}
		}
		VN = virus.size();
		combi(0,0, new int[M]);
		if(!flag) ans = -1;
		System.out.println(ans);
	}

	private static void combi(int cnt, int start, int[] result) {
		if(cnt==M) {
			//바이러스 확산 시간 구하기
			Queue<int[]> que = new LinkedList<>();
			int[][] time = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(time[i], -1); //초기값은 -1로 
			}
			for (int i = 0; i < M; i++) {
				int[] v = virus.get(result[i]);
				que.add(new int[] {v[0], v[1], 0});
				time[v[0]][v[1]] = 0;
			}
			//바이러스 확산하기
			int mtime = 0;
			while(!que.isEmpty()) {
				if(allSpread(time)) break;
				int[] v = que.poll();
				if(ans < v[2]) continue;
				
				for (int k = 0; k < 4; k++) {
					int ni = v[0]+move[k][0];
					int nj = v[1]+move[k][1];
					if(ni>=0 && ni <N && nj>=0 && nj<N && 
							map[ni][nj] != 1 && time[ni][nj] == -1) { //아직 퍼지지 않은 곳들만
						if(mtime < v[2]+1) mtime= v[2]+1;
						time[ni][nj] = v[2]+1;
						que.offer(new int[] {ni,nj, v[2]+1});
					}

				}
 			}
			
			if(!allSpread(time)) return;
			flag = true;
			ans = Math.min(ans, mtime);
			return;
		}
		for (int i = start; i < VN; i++) {
			result[cnt] = i;
			combi(cnt+1, i+1, result);
		}
	}

	private static boolean allSpread(int[][] time) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] ==0 && time[i][j]==-1) {
					return false;
				}
			}
		}
		return true;
	}
}
