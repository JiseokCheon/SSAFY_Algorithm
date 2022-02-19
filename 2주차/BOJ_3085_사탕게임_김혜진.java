package com.ssafy.im;

import java.util.*;
import java.io.*;

/**
 * 
 * @author aa981
 * 1. 인접 사탕(가로-가로) (세로-세로)
 * 2. swap 후 다시 원 상태로 돌려줘야함 - 다음 swap
 */
public class BOJ_3085_사탕게임_김혜진 {
	static String src ="3\r\n" + 
			"CCP\r\n" + 
			"CCP\r\n" + 
			"PPC";
	static int N;
	static char[][] candy;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		
		N = Integer.parseInt(br.readLine());
		candy = new char[N][N];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) {
				candy[i][j] = str.charAt(j);
			}
		}
		
		swap(); // 인접 사탕 위치 교환
		
		System.out.println(max);
	}
	
	public static void swap() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N-1; j++) { // j의 다음까지 확인하므로 N-1 전까지
				// 가로  swap
				char tmp = candy[i][j];
				candy[i][j] = candy[i][j+1];
				candy[i][j+1] = tmp;
				
				isEqual();
				// 다음 swap을 위해 다시 원상태로
				tmp = candy[i][j+1];
				candy[i][j+1] = candy[i][j];
				candy[i][j] = tmp;
				
				// 세로 swap
				tmp = candy[j][i];
				candy[j][i] = candy[j+1][i];
				candy[j+1][i] = tmp;
				
				isEqual();
				// 다음 swap을 위해 다시 원상태로
				tmp = candy[j+1][i];
				candy[j+1][i] = candy[j][i];
				candy[j][i] = tmp;
			}
		}
	}
	
	public static void isEqual() {
		// 가로 사탕의 색깔이 같은지
		for(int i=0; i<N; i++) {
			int tmp = 1;
			for(int j=0; j<N-1; j++) {
				if(candy[i][j] == candy[i][j+1]) tmp++;
				else {
					max = Math.max(max,tmp);
					tmp = 1; // 최대값 계속 구해야하므로 다시 1로 초기화
				}
			}
			max = Math.max(max, tmp);
		}
		// 세로 사탕의 색깔이 같은지
		for(int i=0; i<N; i++) {
			int tmp = 1;
			for(int j=1; j<N-1; j++) {
				if(candy[j][i] == candy[j+1][i]) tmp++;
				else {
					max = Math.max(max, tmp);
					tmp = 1;
				}
			}
			max = Math.max(max, tmp);
		}
	}
	

}
