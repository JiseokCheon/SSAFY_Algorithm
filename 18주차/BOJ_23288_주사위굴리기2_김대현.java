package BJ_23288_주사위굴리기2;

import java.io.*;
import java.util.*;

public class Main {
	
	public static class point{
		int r,c;
		public point(int r, int c){
			this.r=r;
			this.c=c;
		}
	}
	static int[][] map;
	static int[][] dir= {{0,1},{1,0},{0,-1},{-1,0}}; //동, 남, 서, 북
	static int[] dice = {1,6,3,4,2,5};// 위, 아래, 동, 서, 북, 남
	static int d; // 0=동, 1 = 남, 2 = 서, 3 = 북
	static int sum;
	static int n,m;
	static int r,c;
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		
		map=new int[n][m];
		
		for(int i=0; i<n; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		d = 0; // 첫 방향은 동쪽
		sum=0; // 점수 합계
		r = 0;
		c = 0;
		for(int i=0 ; i< k ; i++) {
			go();
		}
		System.out.println(sum);
		
	}
	private static void go() {
		int tempr= r+ dir[d][0];
		int tempc= c+ dir[d][1];
		
		if( tempr <0 || tempr >=n || tempc <0 || tempc >=m) {
			d=(d+2)%4;
			tempr = r+dir[d][0];
			tempc = c+dir[d][1];
		}
		
		dice(d);
		r=tempr;
		c=tempc;
		Queue<point> q=new LinkedList<point>();
		q.offer(new point(tempr,tempc));
		int b=map[tempr][tempc];
		int c=1;
		boolean[][] check=new boolean[n][m];
		check[tempr][tempc]=true;
		while(!q.isEmpty()) {
			point p=q.poll();
			for(int d=0; d<4; d++) {
				tempr=p.r+dir[d][0];
				tempc=p.c+dir[d][1];
				if(tempr <0 || tempr >=n || tempc <0 || tempc >=m || check[tempr][tempc]) {
					continue;
				}
				
				if(map[tempr][tempc]==b) {
					q.offer(new point(tempr,tempc));
					check[tempr][tempc]=true;
					c++;
				}
			}
		}
		sum+=b*c;
		int a = dice[1];
		if(a >b) {
			d=(d+1)%4;
		}else if(a <b) {
			d=(d+3)%4;
		}
		
		
	}
	private static void dice(int d) {
		int[] temp=new int[6];

		switch(d) {
		case 0: // 동
			temp[0]=dice[3];
			temp[1]=dice[2];
			temp[2]=dice[0];
			temp[3]=dice[1];
			temp[4]=dice[4];
			temp[5]=dice[5];
			break;
		case 1: // 남
			temp[0]=dice[4];
			temp[1]=dice[5];
			temp[2]=dice[2];
			temp[3]=dice[3];
			temp[4]=dice[1];
			temp[5]=dice[0];
			break;
		case 2: // 서
			temp[0]=dice[2];
			temp[1]=dice[3];
			temp[2]=dice[1];
			temp[3]=dice[0];
			temp[4]=dice[4];
			temp[5]=dice[5];
			break;
		case 3: // 북
			temp[0]=dice[5];
			temp[1]=dice[4];
			temp[2]=dice[2];
			temp[3]=dice[3];
			temp[4]=dice[0];
			temp[5]=dice[1];
			break;
		}
		for(int i=0; i<6; i++) {
			dice[i]=temp[i];
		}
		
		
	}

}
