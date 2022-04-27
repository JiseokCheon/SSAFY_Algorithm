package BJ_17142_연구소3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N,M,count;
	static int[][] v,map;
	static boolean find=false;
	static int answer=Integer.MAX_VALUE;
	static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}};
	static boolean[][] virus;
	
	
	public static class point{
		int r,c;
		
		public point(int r, int c) {
			this.r=r;
			this.c=c;
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new int[N][N];
		count=0;
		virus=new boolean[N][N];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					virus[i][j]=true;
					count++;
				}
			}
		}
		v=new int[count][2];
		count=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==2) {
					v[count][0]=i;
					v[count++][1]=j;
				}
			}
		}
		boolean[] check=new boolean[count];
		getcom(0,0,check);
		if(find) {
			System.out.print(answer);
		}else {
			System.out.print("-1");
		}
	}

	private static void getcom(int now, int cnt,boolean[] check) {
		
		if(cnt==M) {
			test(check);
		}else if(now < count){
				check[now]=true;
				getcom(now+1,cnt+1,check);
				check[now]=false;
				getcom(now+1,cnt,check);
		}
		
	}

	private static void test(boolean[] check) {
		
		int[][] temp=new int[N][N];
		for(int i=0; i<N;i++) {
			for(int j=0; j<N;j++) {
				temp[i][j]=map[i][j];
			}
		}
		
		Queue<point> q=new LinkedList<point>();
		boolean[][] checks=new boolean[N][N];
		for(int i=0; i<count;i++) {
			if(check[i]) {
				q.offer(new point(v[i][0],v[i][1]));
				temp[v[i][0]][v[i][1]]=0;
				checks[v[i][0]][v[i][1]]=true;
			}
		}
		int tempr,tempc;
		while(!q.isEmpty()) {
			point p=q.poll();
			
			for(int i=0; i<4; i++) {
				tempr=p.r+dir[i][0];
				tempc=p.c+dir[i][1];
				if(tempr <0 || tempc < 0 || tempr >=N || tempc >=N) {
					continue;
				}
				
				if(temp[tempr][tempc] !=1 &&    !checks[tempr][tempc]) {
					checks[tempr][tempc]=true;
					temp[tempr][tempc]=temp[p.r][p.c]-1;
					q.offer(new point(tempr,tempc));
				}
				
			}
		}
		int min=0;
		boolean c=true;
loop:		for(int i=0;i<N;i++) {
			for(int j=0; j<N; j++) {
				if(temp[i][j] < min && !virus[i][j] && temp[i][j] !=1) {
					min=temp[i][j];
				}
				if(!checks[i][j] && temp[i][j]!=1) {
					c=false;
					break loop;
				}
			}
		}
		if(c && Math.abs(min) < answer) {
			answer=Math.abs(min);
			find=true;
			
		}
		
	}

}
