package com.ssafy.im;

import java.io.*;
import java.util.*;

public class BOJ_2804_크로스워드만들기_김혜진 {
	//static String src = "MAMA TATA";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new StringReader(src));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		String A = st.nextToken(); // 가로로 출력
		String B = st.nextToken(); // 세로로 출력

		int a = A.length();
		int b = B.length(); 
		int x = 0; // 겹치는 글자의 인덱스 저장할 변수(가로)
		int y = 0; // 세로
		char arr[][] = new char[b][a];
		
		// B는 문자 하나 빼고 .을 출력해야 하므로 기본 .으로 세팅
		for(int i=0; i<b; i++) {
			Arrays.fill(arr[i], '.');
		}
		
		boolean check = false;
		for(int i=0; i<a; i++) {
			for(int j=0; j<b; j++) {
				// A와 B에서 공통으로 겹치는 글자가 있으면
				if(A.charAt(i) == B.charAt(j) && !check) {
					check = true;
					x = j; 
					y = i; 
				}
			}
		}
		
		// A문자 인덱스 번호에 A문자 가로로 추가하기
		for(int i=0; i<a; i++) {
			arr[x][i] = A.charAt(i);
		}
		
		// B문자 세로로 하나씩 저장
		for(int i=0; i<b; i++) {
			arr[i][y] = B.charAt(i);
		}
		
		// 결과 출력
		for(int i=0; i<b; i++) {
			for(int j=0; j<a; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}

}
