package BJ_14890_경사로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static class ramp{
		int h;
		int count;
		
		public ramp(int h, int count) {
			this.h=h;
			this.count=count;
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());

		int n=Integer.parseInt(st.nextToken());
		int l=Integer.parseInt(st.nextToken());
		
		
		int[][] arr=new int[n][n];
		
		for(int i=0; i<n; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
	
		
		int h;
		int count=0;
		int temp;
		Queue<ramp> q=new LinkedList<>();
		boolean check;

		boolean[][] checks=new boolean[n][n];
		for(int i=0; i<n; i++) { // 행기준
			check=true;
			h=arr[i][0];
	loop:	for(int j=1; j<n; j++) {
				if(arr[i][j]==h) { // 높이 같으면 우선 패스
					continue;
				}
				
				if(Math.abs(arr[i][j]-h)>1) { // 높이 차이가 1보다 크면 실패
					check=false;
					break;
				}
				
				if(arr[i][j]==h+1) {// 다음꺼가 높으면
					if(j-l<0) { // 이전꺼~ -L개만큼 비교하기 위한 조건 
						
						check=false;
						break;
					}
					temp=arr[i][j-1];
					for(int k=j-1;k>=j-l;k--) {
						if(temp!=arr[i][k] || checks[i][k]) {
							check=false;
							break loop;
						}
						checks[i][k]=true;
					}
					h=arr[i][j];
				}
				
				if(arr[i][j]==h-1) { // 다음꺼가 낮으면
					if(j+l>n) { // L개만큼 뒤에 공간이 없으면 실패
						check=false;
						break;
					}
					temp=arr[i][j];
					for(int k=j;k<j+l;k++) {
						if(temp!=arr[i][k] || checks[i][k]) {
							check=false;
							break loop;
						}
						checks[i][k]=true;
					}
					h=arr[i][j];
					j=j+l-1;
					
				}
				
				
				
			}
			
			if(check) {
				count++;
			}
		}
		
		
		checks=new boolean[n][n];
		for(int i=0; i<n; i++) { // 열기준
			check=true;
			h=arr[0][i];
	loop:	for(int j=1; j<n; j++) {
				if(arr[j][i]==h) { // 높이 같으면 우선 패스
					continue;
				}
				
				if(Math.abs(arr[j][i]-h)>1) { // 높이 차이가 1보다 크면 실패
					check=false;
					break;
				}
				
				if(arr[j][i]==h+1) {// 다음꺼가 높으면
					if(j-l<0) { // 이전꺼~ -L개만큼 비교하기 위한 조건 
						
						check=false;
						break;
					}
					temp=arr[j-1][i];
					for(int k=j-1;k>=j-l;k--) {
						if(temp!=arr[k][i] || checks[k][i]) {
							check=false;
							break loop;
						}
						checks[k][i]=true;
					}
					h=arr[j][i];
				}
				
				if(arr[j][i]==h-1) { // 다음꺼가 낮으면
					if(j+l>n) { // L개만큼 뒤에 공간이 없으면 실패
						check=false;
						break;
					}
					temp=arr[j][i];
					for(int k=j;k<j+l;k++) {
						if(temp!=arr[k][i] || checks[k][i]) {
							check=false;
							break loop;
						}
						checks[k][i]=true;
					}
					h=arr[j][i];
					j=j+l-1;
					
				}
				
				
				
			}
			
			if(check) {
				count++;
			}
		}
	
		
		System.out.print(count);

	}

}
