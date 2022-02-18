package com.ssafy.im;

import java.io.*;
import java.util.*;

public class BOJ_10431_줄세우기_김혜진 {
	static String src = "4\r\n" + 
			"1 900 901 902 903 904 905 906 907 908 909 910 911 912 913 914 915 916 917 918 919\r\n" + 
			"2 919 918 917 916 915 914 913 912 911 910 909 908 907 906 905 904 903 902 901 900\r\n" + 
			"3 901 902 903 904 905 906 907 908 909 910 911 912 913 914 915 916 917 918 919 900\r\n" + 
			"4 918 917 916 915 914 913 912 911 910 909 908 907 906 905 904 903 902 901 900 919";
	static StringBuilder sb;
	static int ans;
	static int T;
	static ArrayList<Integer> std, stdNew;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		sb = new StringBuilder();
		
		int P = Integer.parseInt(br.readLine()); // 테케 번호
		
		for(int tc=1; tc<=P; tc++) {
			
			std = new ArrayList<Integer>(); // 학생들 담을 arrayList
			stdNew = new ArrayList<Integer>(); // 오름차순 정렬 후 arrayList
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			T = Integer.parseInt(st.nextToken());
			sb.append(T).append(" ");
			for(int i=0; i<20; i++) {
				std.add(Integer.parseInt(st.nextToken()));
			}
			
			ans = 0;
			
			for(int i=0; i<20; i++) {
				int num = i;
				int height = std.get(i); // 학생 한명 기준 키
				for(int j=0; j<i; j++) { // 비교학생의 순서 앞까지 반복
					if(std.get(j) > height) { // 현재 학생의 앞에 큰 학생이 있을 때 
						num = j;
						break;
					}
				}
				ans += (i-num); // 20명의 학생 중 한칸 씩 뒤로 가야하니까 학생 번호를 빼줌
				stdNew.add(num,height); // 오름차순 정렬 후 학생 키 순서대로
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
}
