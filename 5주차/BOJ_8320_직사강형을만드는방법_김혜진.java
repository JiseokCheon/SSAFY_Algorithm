package com.ssafy.bj;

import java.util.*;
import java.io.*;

public class BOJ_8320_직사강형을만드는방법_김혜진 {
	//static String src = "6";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new StringReader(src));
		
		int N = Integer.parseInt(br.readLine()); // 정사각형의 개수
		
		int cnt = 0;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=i; j++) 
				if(i*j<=N) cnt++;
		}

//		for(int i=1; i<=N; i++) {
//			for(int j=i; j<=N; j++) {
//				if(i*j <= N) cnt++;
//			}
//		}
		System.out.println(cnt);
	}

}
