package BJ_1389_케빈베이컨의6단계법칙;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	

	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int[][] arr=new int[n+1][n+1];
		for(int i=0; i<=n;i++) {
			for(int j=0 ; j<=n; j++) {
				if(i==j) {
					arr[i][j]=0;
				}else {
					arr[i][j]=n;
					
				}
				
			}
		}
		
		int p1,p2;
		for(int i=0; i<m;i++) {
			st=new StringTokenizer(br.readLine());
			p1=Integer.parseInt(st.nextToken());
			p2=Integer.parseInt(st.nextToken());
			arr[p1][p2]=1;
			arr[p2][p1]=1;
		}
		
		
		for(int i=1; i<=n;i++) {
			for(int j=1; j<=n; j++) {
				for(int k=1; k<=n;k++) {
					arr[j][k]=Math.min(arr[j][i]+arr[i][k], arr[j][k]);
				}
			}
		}
		int temp;
		int min=Integer.MAX_VALUE;
		int minI=0;
	
		for(int i=1; i<=n;i++) {
			temp=0;
			for(int j=1; j<=n; j++) {
				temp+=arr[i][j];
			}
			if(temp<min) {
				min=temp;
				minI=i;
			}
		}
		
		
		
		System.out.print(minI);
		
	}

}
