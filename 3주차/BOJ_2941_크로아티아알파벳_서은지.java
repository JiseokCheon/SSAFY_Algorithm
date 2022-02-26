package com.ssafy.study;

import java.io.*;

public class BOJ_2941_크로아티아알파벳_서은지 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input = in.readLine();
		int cnt = 0;
		int len = input.length();
		for (int i = 0; i < len; i++) {
			if (input.charAt(i) == 'c' && len - i > 1 && (input.charAt(i + 1) == '=' || input.charAt(i + 1) == '-')) {
				i++;
			} else if (input.charAt(i) == 'd') {
				if (len - i > 1 && input.charAt(i + 1) == '-')
					i++;
				else if (len - i > 2 && input.charAt(i + 1) == 'z' && input.charAt(i + 2) == '=')
					i += 2;
			} else if ((input.charAt(i) == 'l' || input.charAt(i) == 'n') && len - i > 1 && input.charAt(i + 1) == 'j')
				i++;
			else if ((input.charAt(i) == 's' || input.charAt(i) == 'z') && len - i > 1 && input.charAt(i + 1) == '=')
				i++;
			cnt++;
		}
		System.out.println(cnt);
	}
}
