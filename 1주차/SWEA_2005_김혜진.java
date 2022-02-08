package com.ssafy.im;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

public class SWEA_2005_김혜진 {
	static String src ="10\r\n" + 
			"1\r\n" + 
			"2\r\n" + 
			"3\r\n" + 
			"4\r\n" + 
			"5\r\n" + 
			"6\r\n" + 
			"7\r\n" + 
			"8\r\n" + 
			"9\r\n" + 
			"10";
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		in = new BufferedReader(new StringReader(src));
		
		int T = Integer.parseInt(in.readLine());
	
		for(int tc=1; tc<=T; tc++) {
			
			int N = Integer.parseInt(in.readLine());
			int[][] arr = new int[N][N];
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<=i; j++) {
					if(i==j || j==0) {
						arr[i][j] = 1;
					}else {
						arr[i][j] = arr[i-1][j-1]+arr[i-1][j];
					}
				}
			}
			
			System.out.println("#"+tc);
			for(int i=0; i<N; i++) {
				for(int j=0; j<=i; j++) {
					System.out.print(arr[i][j]+ " ");
				}
				System.out.println();
			}
						
		}	
		
	}
}
