package BJ_16235_나무재테크;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static class tree implements  Comparable<tree>{
		int r,c;
		int y; // 년생
		
		public tree(int r, int c, int y) {
			this.r=r;
			this.c=c;
			this.y=y;
			
		}

		@Override
		public int compareTo(tree o) {
			return this.y-o.y;
		}
	}
	
	static int n,m,k;
	static int[][] dir= {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	public static void main(String[] args) throws IOException {

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken()); // 맵의 크기 n*n
		m=Integer.parseInt(st.nextToken()); // 초기 나무의 갯수
		k=Integer.parseInt(st.nextToken()); // k년 후 나무의 개수
		int[][] map=new int[n][n]; // 양분정보 저장
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				map[i][j]=5;
			}
		}
		int[][] s2d2=new int[n][n]; // 추가 양분 
		
		for(int i=0; i<n; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				s2d2[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		PriorityQueue<tree> pq= new PriorityQueue<tree>();
		int r,c,y;
		for(int i=0; i<m; i++) {
			st=new StringTokenizer(br.readLine());
				r=Integer.parseInt(st.nextToken())-1;
				c=Integer.parseInt(st.nextToken())-1;
				y=Integer.parseInt(st.nextToken());
				pq.offer(new tree(r,c,y));
		}

		PriorityQueue<tree> pq2= new PriorityQueue<tree>();
		int tempr,tempc;
		Queue<tree> q=new LinkedList<tree>();
		Queue<tree> q5=new LinkedList<tree>();
		
		for(int i=0; i<k; i++) {
			if(i%2==0) {
				
				while(!pq.isEmpty()) {
					tree t=pq.poll();
					r=t.r;
					c=t.c;
					y=t.y;
					if(y <= map[r][c]) {
						map[r][c]-=y;
						pq2.offer(new tree(r,c,y+1));
						if((y+1)%5==0) {
							q5.offer(new tree(r,c,1));
						}
					}else {
						q.offer(new tree(r,c,y));
					}
					
				}
				
			}else {
				while(!pq2.isEmpty()) {
					tree t=pq2.poll();
					r=t.r;
					c=t.c;
					y=t.y;
					if(y <= map[r][c]) {
						map[r][c]-=y;
						pq.offer(new tree(r,c,y+1));
						if((y+1)%5==0) {
							q5.offer(new tree(r,c,1));
						}
					}else {
						q.offer(new tree(r,c,y));
					}
				}
			}
			 
			while(!q.isEmpty()) {
				tree t=q.poll();
				map[t.r][t.c]+=t.y/2;
			}
			
			while(!q5.isEmpty()) {
				r=q5.peek().r;
				c=q5.poll().c;
				for(int j=0; j<8; j++) {
					tempr=r+dir[j][0];
					tempc=c+dir[j][1];
					if(tempr <0 || tempr >=n || tempc <0 || tempc >=n ) {
						
						continue;
					}
					
					if(i%2==0) {
						pq2.offer(new tree(tempr,tempc,1));
					}else {
						pq.offer(new tree(tempr,tempc,1));
					}
				}
			}
			
			for(int j=0; j<n; j++) {
				for(int k=0; k<n; k++) {
					map[j][k]+=s2d2[j][k];
				}
			}
			
		}
		
		if(k%2==0) {
			System.out.println(pq.size());
		}else {
			System.out.println(pq2.size());
			
		}
		
	}

}
