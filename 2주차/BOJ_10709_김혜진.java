package com.ssafy.im;

import java.io.*;
import java.util.*;

public class BOJ_10709_김혜진 {
	static String src = "3 4\r\n" + 
			"c..c\r\n" + 
			"..c.\r\n" + 
			"....";
	static int H,W;
	static char[][] map;
	static int[][] result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		map = new char[H][W]; // 구름의 현 상태를 담을 배열
		result = new int[H][W]; // 1분마다 바뀌는 구름의 상태를 담을 배열
		
		for(int i=0; i<H; i++) {
			String str = br.readLine();
			for(int j=0; j<W; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		for(int i=0; i<H; i++) {
			int timeCheck = 0; // 구름이 이동하는 시간 체크
			for(int j=0; j<W; j++) {
				if(map[i][j]=='c') { // 현재 자리에 구름이 있다면
					timeCheck = 1;
					result[i][j]=0;
				}else if(map[i][j]=='.') { // 현재 자리에 구름이 없고, 전 자리에 구름이 없을 경우
					if(timeCheck==0) {
						result[i][j]=-1;
					}else {
						result[i][j]=timeCheck;
						timeCheck++;
					}
				}
			}
		}
		
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				System.out.print(result[i][j]+" ");
			}
			System.out.println();
		}
	}

}
