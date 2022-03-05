package com.ssafy.study;

import java.io.*;

public class BOJ_17413_단어뒤집기2_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String str = in.readLine();
		StringBuilder sb = new StringBuilder(); 	// 출력 문자열
		StringBuilder tmp = new StringBuilder();	// 뒤집을 문자열

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != ' ' && str.charAt(i) != '<')
				tmp.append(str.charAt(i));	// 공백이나 '<'가 아니면 뒤집을 문자열에 저장
			else {	// 공백이나 <면
				sb.append(tmp.reverse());	// tmp 문자열 뒤집어서 출력 문자열에 추가
				tmp.setLength(0);	// tmp 초기화
				if (str.charAt(i) == ' ')
					sb.append(' ');
				else if (str.charAt(i) == '<') {	// '<'면 '>'만날 때까지 출력 문자열에 추가
					while (str.charAt(i) != '>') {
						sb.append(str.charAt(i));
						i++;
					}
					sb.append('>');	// 다음 문자 '>'도 추가
				}
			}
		}
		sb.append(tmp.reverse());	// 남은 tmp 문자열 정리(마지막이 '>'로 끝나지 않은 경우)
		System.out.println(sb);
	}
}
