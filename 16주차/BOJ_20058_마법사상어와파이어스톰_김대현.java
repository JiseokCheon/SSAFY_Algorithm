package BJ_20058_마법사상어파이어스톰;

import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] map;
	static int np;
	static int[][] dir= {{-1,0},{0,1},{0,-1},{1,0}};
	
	public static class point{
		int r,c;
		public point(int r,int c) {
			this.r=r;
			this.c=c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int q=Integer.parseInt(st.nextToken());
		np=(int)Math.pow(2, n);
		map=new int[np][np];
		for(int i=0; i<np; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<np; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		int[] l=new int[q];
		st=new StringTokenizer(br.readLine());
		for(int i=0; i<q; i++) {
			l[i]=Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<q; i++) {
			fire((int)Math.pow(2, l[i]),l[i]);
		}
		int sum=0;
		for(int i=0; i<np; i++) {
			for(int j=0; j<np; j++) {
				if(map[i][j]>0) {
					sum+=map[i][j];
					
				}
			}
		}
		
		boolean[][] check=new boolean[np][np];
		int max=0;
		for(int i=0; i<np; i++) {
			for(int j=0; j<np; j++) {
				if(!check[i][j] && map[i][j]>0) {
					int tc=1;
					Queue<point> que=new LinkedList<point>();
					check[i][j]=true;
					que.offer(new point(i,j));
					while(!que.isEmpty()) {
						int r=que.peek().r;
						int c=que.poll().c;
						for(int d=0;d<4;d++) {
							int tempr=r+dir[d][0];
							int tempc=c+dir[d][1];
							if(tempr <0 || tempc<0 || tempr >=np || tempc >=np || 
									check[tempr][tempc] || map[tempr][tempc] <=0) {
								continue;
							}
							tc++;
							check[tempr][tempc]=true;
							que.offer(new point(tempr,tempc));
						}
					}
					if(max < tc) {
						max =tc;
					}
					
				}
			}
		}

		System.out.println(sum);
		System.out.println(max);
		
		

	}

	private static void fire(int n, int q) {
		int[][] temp=new int[np][np];
		if(q !=0) {
			for(int i=0; i<np/n;i++) {
				for(int j=0; j<np/n;j++) {
					int r=i*n;
					int c=j*n;
					for(int k=0; k<(int)(Math.pow(2, q-1));k++) {// l[i]번 반복
						for(int l=0; l<n-2*k-1;l++) { // q,k
							temp[r+k+l][c+k]=map[r+n-1-k][c+k+l]; 
							temp[r+n-1-k][c+k+l]=map[r+n-1-l-k][c+n-1-k]; 
							temp[r+n-1-l-k][c+n-1-k]=map[r+k][c+n-1-k-l]; 
							temp[r+k][c+n-1-k-l]=map[r+k+l][c+k];
						}
					}
					
				}
			}
		}else {
			for(int i=0; i<np; i++) {
				for(int j=0; j<np; j++) {
					temp[i][j]=map[i][j];
				}
			}
		}
		
		int tempr,tempc;
		for(int i=0; i<np;i++) {
			for(int j=0; j<np; j++) {
				int cnt=0;
				for(int d=0;d<4; d++) {
					tempr=i+dir[d][0];
					tempc=j+dir[d][1];
					if(tempr <0 || tempr >=np || tempc <0 || tempc >=np) {
						continue;
					}
					if(temp[tempr][tempc] >0) {
						cnt++;
					}
				}
				
				if(cnt <3) {
					map[i][j]=temp[i][j]-1;
				}else {
					map[i][j]=temp[i][j];
				}
				
			}
		}
		
		
	}

}
