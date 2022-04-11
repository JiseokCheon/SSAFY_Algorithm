package BJ_10830_행렬제곱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		long b=Long.parseLong(st.nextToken());
		
		int[][] arr=new int[n][n];
		int[][] temp=new int[n][n];
		int[][] now=new int[n][n];
		int num;
		for(int i=0; i<n; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				
				num=Integer.parseInt(st.nextToken())%1000;
				arr[i][j]=now[i][j]=num;
			}
		}
		
		
		
		
		
		
		Stack<Integer> stack=new Stack<Integer>();
		while(1l!=b) {
			if(b%2==0) {
				b=b/2;
				stack.add(2);
			}else {
				b=b-1;
				stack.add(1);
			}
		}
		
		while(!stack.isEmpty()) {
			if(stack.pop()==2) {
				for(int i=0; i<n; i++) {
					for(int j=0; j<n;j++) {
						int sum=0;
						for(int k=0; k<n; k++) {
							sum+=now[i][k]*now[k][j];
						}
						
						temp[i][j]=sum%1000;
					}
				}
				
				for(int i=0; i<n; i++) {
					for(int j=0; j<n; j++) {
						now[i][j]=temp[i][j];
					}
				}
			}else {
				for(int i=0; i<n; i++) {
					for(int j=0; j<n;j++) {
						int sum=0;
						for(int k=0; k<n; k++) {
							sum+=now[i][k]*arr[k][j];
						}
						
						temp[i][j]=sum%1000;
					}
				}
				
				for(int i=0; i<n; i++) {
					for(int j=0; j<n; j++) {
						now[i][j]=temp[i][j];
					}
				}
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(now[i][j]+" ");
			}
			System.out.println();
		}

	}

}
