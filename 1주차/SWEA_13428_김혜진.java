package com.ssafy.im;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

public class SWEA_13428_김혜진 {
	static String src = "4\r\n" + 
			"12345\r\n" + 
			"54321\r\n" + 
			"142857\r\n" + 
			"10000\r\n" +
			"";
	static int min;
	static int max;
	static char tmp;
	static char[] input;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		in = new BufferedReader(new StringReader(src));
		
		int T = Integer.parseInt(in.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			String str = in.readLine();
			input = str.toCharArray();
			
			min = Integer.parseInt(String.valueOf(input));
			max = Integer.parseInt(String.valueOf(input));
			
			for(int i=0; i<input.length-1; i++) {
				
				for(int j=i+1; j<input.length; j++) {
					tmp = input[i];
					input[i] = input[j];
					input[j] = tmp;

					if(input[0]!='0') {
						min = Math.min(min, Integer.parseInt(String.valueOf(input)));
						max = Math.max(max,  Integer.parseInt(String.valueOf(input)));
					}
				
				}
				
			}
			System.out.println("#"+tc+" "+min+" "+max);
		}
		
		
	}
	

}
