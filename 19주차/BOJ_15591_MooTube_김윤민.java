package July;

import java.io.*;
import java.util.*;

public class BOJ_15591_MooTube {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		ArrayList<int[]>[] list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<int[]>();
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken()) - 1;
			int q = Integer.parseInt(st.nextToken()) - 1;
			int usado = Integer.parseInt(st.nextToken());
			list[p].add(new int[] { q, usado });
			list[q].add(new int[] { p, usado });
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken()) - 1;
			Queue<Integer> que = new LinkedList<Integer>();
			boolean[] visit = new boolean[N];
			que.add(v); visit[v] = true;
			int cnt = 0;
			while(!que.isEmpty()) {
				int n = que.poll();
				for (int j = 0; j < list[n].size(); j++) {
					int[] t = list[n].get(j);
					if(!visit[t[0]] && t[1] >= k) {
						que.add(t[0]);
						visit[t[0]] = true;
						cnt++;
					}
				}
			}
			sb.append(cnt).append('\n');
		}
		System.out.println(sb);
	}
}
