package BJ_15591_MooTube;

import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int Q=Integer.parseInt(st.nextToken());
		
		List<int[]>[] list=new ArrayList[n+1];
		
		for(int i=1; i<=n; i++) {
			list[i]=new ArrayList<>();
		}
		int p,q,r;
		for(int i=1; i<n; i++) {
			st=new StringTokenizer(br.readLine());
			p=Integer.parseInt(st.nextToken());
			q=Integer.parseInt(st.nextToken());
			r=Integer.parseInt(st.nextToken());
			list[p].add(new int[]{q,r});
			list[q].add(new int[]{p,r});
		}
		StringBuilder sb=new StringBuilder();
		int k,v,now,count;
		for(int i=0; i<Q; i++) {
			st=new StringTokenizer(br.readLine());
			k=Integer.parseInt(st.nextToken());
			v=Integer.parseInt(st.nextToken());
			
			boolean[] visit=new boolean[n+1];
			visit[v]=true;
			Queue<Integer> que=new LinkedList<>();
			que.add(v);
			
			count=0;
			while(!que.isEmpty()) {
				now=que.poll();
				
				for(int[] a : list[now]) {
					if(visit[a[0]]) {
						continue;
					}
					if(a[1] >= k) {
						que.add(a[0]);
						visit[a[0]]=true;
						count++;
					}
				}
			}
			sb.append(count).append("\n");
			
			
		}
		
	
		System.out.print(sb);
		

	}

}
