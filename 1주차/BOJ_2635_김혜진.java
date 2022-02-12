package com.ssafy.im;

import java.io.*;
import java.util.*;

// 수 이어가기
public class BOJ_2635_김혜진 {
	static int MAX;
	static ArrayList<Integer> list, result;
	
	static String src = "100";
	
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		sc = new Scanner(new StringReader(src));
		
		int N = sc.nextInt();
		
		for(int i=N/2; i<N; i++) {
			list = new ArrayList<Integer>();
			list.add(N);
			list.add(i);
			while(true) {
				int num1 = list.get(list.size()-2);
				int num2 = list.get(list.size()-1);
				if(num1>num2) {
					list.add(num1-num2);
				}else
					break;
			}
			if(MAX<list.size()) {
				result = new ArrayList<Integer>();
				for(int j=0; j<list.size(); j++) {
					result.add(list.get(j));
				}
				MAX = list.size();
			}
		}
		
		System.out.println(MAX);
		for(int i=0; i<result.size()-1; i++) {
			System.out.print(result.get(i)+" ");
		}
		int lastIndex = result.size()-1;
		System.out.print(result.get(lastIndex));
		
	}
	
}
