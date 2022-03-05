package com.ssafy.bj;

import java.util.*;
import java.io.*;

public class BOJ_17413_단어뒤집기2_김혜진 {
	static String src = "<problem>17413<is hardest>problem ever<end>";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringBuilder sb = new StringBuilder();
		
		String str = br.readLine();
		// stack 활용해 문자열 거꾸로 출력
		Stack<Character> st = new Stack<>();
		// <태그> 구분 위해 boolean형 변수 - 태그 안에 있으면 true 밖에 있으면 false
		boolean check = false; // <> 만나면 true 되게
		
		for(int i=0; i<str.length(); i++) {
			// 문자 하나씩 탐색하면서 < 찾기
			if(str.charAt(i) == '<') {
				check = true; // check 바꿔주고 태그 시작
				// 스택에 저장되어 있는 문자열 하나씩 꺼내면서 출력
				while(!st.isEmpty()) {
					sb.append(st.pop());
				}
				// < 기호 출력
				sb.append(str.charAt(i));
			// >오면 태그 끝
			}else if(str.charAt(i) == '>') {
				check = false; // 태그 끝 - false
				sb.append(str.charAt(i));
			// check-true 이면 태그 안임(단어 그대로 출력)
			}else if(check) {
				sb.append(str.charAt(i));
			// check-false이면 태그 밖임 ( 공백을 구분해서 단어 뒤집기 )
			}else{
				// 공백 - 단어구분
				if(str.charAt(i)==' ') {
					// 스택에 저장되어 있는 문자열 하나씩 꺼내면서 출력
					while(!st.isEmpty()) {
						sb.append(st.pop());
					}
					sb.append(str.charAt(i));
				}else {
					// 공백이 아니면 단어 뒤집어야하므로 스택에 넣기
					st.push(str.charAt(i));
				}
			}
		}
		
		// 단어들만 스택에 저장되어있으니 그대로 출력하면 뒤집어짐
		while(!st.isEmpty()) {
			sb.append(st.pop());
		}
		
		System.out.println(sb);
	}

}
