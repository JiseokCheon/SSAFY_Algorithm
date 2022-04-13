package BJ_15686_치킨배달;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n,m;
	static int distance;
	static int min;
	static boolean[] visited;
	static int[][] arr;
	static int[][] c;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		int cc=0;
		arr=new int[n][n];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
				if(arr[i][j]==2) {
					cc++;
				}
			}
		}
		c=new int[cc][2];
		int temp=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(arr[i][j]==2) {
					c[temp][0]=i;
					c[temp][1]=j;
					temp++;
				}
			}
		}
		visited=new boolean[cc];
		distance=n*2*(n*n-cc);
		
		Com(cc,cc-m,0);
		System.out.print(distance);

	}

	private static void Com(int r, int count,int cnt) {
		
		if(count==0) {
			int temp=getTotal(visited);
			if(temp<distance) {
				distance=temp;
			}
		
		}else {
			for(int i=cnt;i<r;i++) {
				visited[i]=true;
				Com(r,count-1,i+1);
				visited[i]=false;
				
			}
			
		}
		
	}

	private static int getTotal(boolean[] visited) {
		
		int sum=0;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(arr[i][j]==1) {
					sum+=getDistance(visited,i,j);
				}
			}
		}
		return sum;
		
		
	}

	private static int getDistance(boolean[] visited, int i, int j) {
		
		int min=2*n;
		int temp=0;
		for(int k=0;k<visited.length;k++) {
			if(!visited[k]) {
				temp=Math.abs(i-c[k][0])+Math.abs(j-c[k][1]);
				if(temp<min) {
					min=temp;
				}
			}
		}
		
		return min;
	}

}
