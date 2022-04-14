import java.io.*;
import java.util.*;

public class BOJ_1697_숨바꼭질 {
	public static void main(String[] args) throws IOException {
		int[] move = {-1,1};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]); //수빈 위치
		int K = Integer.parseInt(str[1]); //동생 위치
		int min = 100000;
		Queue<int[]> que = new LinkedList<>();
		boolean[] visit = new boolean[100001];
		que.offer(new int[] {N,0});
		visit[N]= true;
		while(!que.isEmpty()) { //bfs시작
			int[] t = que.poll();
			if(t[0]==K) { //동생위치와 같으면 시간 확인
				min = Math.min(min, t[1]);
				continue;
			}
			if(t[1]>min) continue; //현재시간이 현재저장된 최소시간보다 더 크면 continue
			for (int i = 0; i < 2; i++) { // -1, +1 인 경우
				int n = t[0]+move[i];
				if(n<=100000 && n >= 0 && !visit[n]) {
					visit[n] = true;
					que.offer(new int[] {n, t[1]+1});
				}
			}
			if(t[0]*2 <= 100000 && !visit[t[0]*2 ]) { //*2 순간이동 하는 경우
				visit[t[0]*2 ] = true;
				que.offer(new int[] {t[0]*2 , t[1]+1});
			}
		}
		System.out.println(min);
	}
}
