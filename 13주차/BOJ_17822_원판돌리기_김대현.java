package BJ_17822_원판돌리기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n,m;
	public static class point{
		int r,c;
		
		public point(int r, int c) {
			this.r=r;
			this.c=c;
			
		}
	}
	

	
	public static void main(String[] args ) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		int t=Integer.parseInt(st.nextToken());
		
		int[][] narr=new int[n][m];
		for(int i=0; i<n; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				narr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] tarr=new int[t][3];
		for(int i=0; i<t; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				tarr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		int[] dir= {1,m-1};
		int[][] dirs= {{-1,0},{1,0},{0,1},{0,m-1}};
		for(int i=0; i<t; i++) {
			//회전
			int x=tarr[i][0]; //x의 배수
			int d=tarr[i][1]; // d방향 (0=시계, 1= 반시계)
			int k=tarr[i][2]; // k칸 회전
			
			for(int j=0; j<n;j++) {
				if((j+1)%x==0) {
					int[] temp=new int[m];
					for(int a=0; a<m; a++) {
						temp[(a+k*dir[d])%m]=narr[j][a];
					}
					
					for(int a=0; a<m; a++) {
						narr[j][a]=temp[a];
					}
					
				}
			}
			
			//원판 숫자 인접한거 지우기
			boolean check= true;
			boolean[][] checks=new boolean[n][m];
			
			for(int a=0; a<n;a++ ) {
				for (int b=0; b<m; b++) {
					if(!checks[a][b] && narr[a][b]!=0) {
						checks[a][b]=true;
						int temp=narr[a][b];
						
						Queue<point> q=new LinkedList<point>();
						q.offer(new point(a,b));
						while(!q.isEmpty()) {
							point p=q.poll();
							int r=p.r;
							int c=p.c;
							for(int di=0; di<4;di++) {
								int tempr=r+dirs[di][0];
								int tempc=c+dirs[di][1];
								if(tempr <0 || tempr >=n) {
									continue;
								}
								if(narr[tempr][tempc%m]==temp) {
									narr[r][c%m]=0;
									narr[tempr][tempc%m]=0;
									check=false;
									checks[tempr][tempc%m]=true;
									
									q.offer(new point(tempr,tempc));
								}
								
							}
						}
					}
				}
			}
			
			//인접한거 없는 원판들 평균보다 크면 -1, 평균보다 작으면 +1
			if(check) {
				int sum=0;
				int count=0;
				for(int j=0;j<n;j++ ) {
					for(int a=0; a<m; a++) {
						if(narr[j][a]!=0) {
							sum+=narr[j][a];
							count++;
						}
					}
				}
				double ever=(double)sum/count;
				for(int j=0;j<n;j++ ) {
					for(int a=0; a<m; a++) {
						if(narr[j][a]!=0) {
							if(narr[j][a]>ever) {
								narr[j][a]--;
							}else if(narr[j][a]<ever) {
								narr[j][a]++;
							}
						}
					}
				}
			}
			
		}
		int answer=0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				answer+=narr[i][j];
			}
		}
		
		System.out.println(answer);
		
		
		
	}

}
