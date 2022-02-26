package com.ssafy.im;

import java.io.*;
import java.util.*;

public class BJ_2941_크로아티아알파벳_김혜진 {
	static String src ="ljes=njak";
	//ljes=njak 6
	//ddz=z= 3
	//nljj 3
	//c=c= 2
	//dz=ak 3
	static String str;
	static char[] c;
	static int total;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		
		str = br.readLine();
		c = new char[str.length()];
		c = str.toCharArray();
		
		int ans = 0;
		
		for(int i=0; i<c.length; i++) {
			if(c[i]== 'c' || c[i] == 's' || c[i]== 'z') {
				if(c[i+1]=='=' || c[i+1]=='-') ans++;
			}else if((c[i] == 'l' || c[i] == 'n') && c[i+1]=='j') {
				ans++;
			}else if(c[i]=='d' && c[i+1]=='z' && c[i+1]=='=') ans++;
//			switch(c[i]) {
//			case 'c':
//				if(c[i+1] == '=' || c[i+1] == '-') ans++;
//				break;
//			case 'd':
//				if((c[i+1] == 'z' && c[i+2] == '=')|| c[i+1] == '-') ans++;
//				break;
//			case 'l':
//				if(c[i+1] == 'j') ans++;
//				break;
//			case 'n':
//				if(c[i+1] == 'j') ans++;
//				break;
//			case 's':
//				if(c[i+1] == '=') ans++;
//				break;
//			case 'z':
//				if(c[i+1] == '=') ans++;
//				break;
				
//			}
		}
		
		total = str.length()-ans;
		System.out.println(total);
	}
	
	// 런타임 에러
	public static void main2(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		
		str = br.readLine();
		
		int ans = 0;
		
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i) == 'c' || str.charAt(i) == 's' || str.charAt(i) == 'z') {
				if(str.charAt(i+1)=='=' || str.charAt(i+1)=='-') ans++;
			}else if(str.charAt(i) == 'l' || str.charAt(i) == 'n') {
				if(str.charAt(i+1)=='j') ans++;
			}else if(str.charAt(i)=='d' && str.charAt(i+1)=='z' && str.charAt(i+2)=='=') ans++;
		}
		
		total = str.length()-ans;
		System.out.println(total);
	}

}
