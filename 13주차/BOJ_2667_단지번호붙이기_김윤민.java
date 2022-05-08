import java.io.*;
import java.util.*;

public class BOJ_2667_단지번호붙이기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		int[][] move = {{1,0},{-1,0},{0,1},{0,-1}};
		
		int[][]map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j)-'0';
				if(map[i][j] != 0) map[i][j]=-1;
			}
		}
		int cnt=1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]==-1) { 
					//집을 만나면 bfs시작
					Queue<int[]> que = new LinkedList<>();
					que.add(new int[] {i,j, cnt});
					while(!que.isEmpty()) {
						int[] temp = que.poll();
						int p = temp[0];
						int q = temp[1];
						if(map[p][q] != -1) continue; 
						int c = temp[2];
						map[p][q] = c; //단지 정해주기
						for (int k = 0; k < 4; k++) {
							int np= p+move[k][0];
							int nq= q+move[k][1];
							if(np>=0 && np <N && nq>=0 &&nq<N && map[np][nq]==-1) {
								que.add(new int[] {np, nq, c});
							}
						}
					}
					cnt++;
				}
			}
		}
		cnt= cnt-1;
		int[] cnts = new int[cnt];
		for (int[] r : map) {
			for (int j : r) {
				if(j != 0) {
					cnts[j-1]++;
				}
			}
		}
		Arrays.sort(cnts);
		sb.append(cnt+"\n");
		for (int i = 0; i < cnt; i++) {
			sb.append(cnts[i]+"\n");
		}
		System.out.println(sb);
	}

}
