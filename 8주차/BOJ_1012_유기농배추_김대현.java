package BJ_1012_유기농배추;

import java.util.Scanner;

public class Main {
	static int[][] dir= {{1,0},{0,1},{0,-1},{-1,0}};
	
	private static void delete_baechu(int[][] arr, int i, int j,int m, int n,int[][] visit) {
		
		visit[i][j]=1;
		for(int k=0; k<4; k++) {
			int nr= i+ dir[k][0];
			int nc= j+ dir[k][1];
			
			if(nr>=0 && nc>=0 && nr<m && nc <n) {
				if(visit[nr][nc] ==0 && arr[nr][nc]==1) {
					delete_baechu(arr,nr,nc,m,n,visit);
					
				}
			}
			
			
		}
	}

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		int t=sc.nextInt();
		for(int tc=1;tc<=t;tc++) {
			int m=sc.nextInt();
			int n=sc.nextInt();
			int k=sc.nextInt();
			int[][] arr=new int[m][n];
			int[][] visit=new int[m][n];
			for(int i=0;i<k;i++) {
				int col=sc.nextInt();
				int row=sc.nextInt();
				arr[col][row]=1;
			}
			int count=0;
			for(int i=0; i<m;i++) {
				for(int j=0;j<n;j++) {
					if(arr[i][j]==1&&visit[i][j]==0) {
						count++;
						delete_baechu(arr,i,j,m,n,visit);
					}
				}
			}
			
			System.out.printf("%d\n",count);
			
			
		}
		

	}

}

