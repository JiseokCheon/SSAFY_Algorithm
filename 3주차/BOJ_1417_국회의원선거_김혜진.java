package com.ssafy.im;

import java.util.*;
import java.io.*;

// 완전탐색
public class BJ_1417_국회의원선거_김혜진 {
//	static String src = "4\r\n" + 
//			"10\r\n" + 
//			"10\r\n" + 
//			"10\r\n" + 
//			"10";
	
	static int N;
	static int dasom;
	//static int[] vote;
	static List<Integer> arr;
	static int person = 0; // 다솜이 매수해야할 사람들의 수
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new StringReader(src));
		
		N = Integer.parseInt(br.readLine()); // 선거에 출마한 사람들의 수
		//vote = new int[N-1]; // 다솜이를 제외한 사람들의 득표 수
		
		dasom = Integer.parseInt(br.readLine()); // 다솜의 득표 수	
		arr = new ArrayList<Integer>();
		
		for(int i=0; i<N-1; i++) {
			//vote[i] = Integer.parseInt(br.readLine());
			int e = Integer.parseInt(br.readLine());
			if(e>= dasom) {
				arr.add(e);
			}
		}
		
		// 반복해서 배열을 정렬하며 최소 매수 사람 수를 구해야함
		while(!arr.isEmpty()) {
			//Arrays.sort(vote); // 오름차순 정렬 : 가장 많은 득표수를 가진 사람과 교환하면 최소가 됨
			Collections.sort(arr);
			int idx = arr.size()-1; // 정렬 후 최다 득표수를 가진 사람의 인덱스 번호
			int vote = arr.get(idx);
			// 후보들 중 최다 득표를 받은 사람이 다솜의 득표수보다 높은 경우
			if(dasom <= vote) {
				dasom++; // 다솜 득표 +1
				arr.set(idx, --vote); // 최다득표자  득표수-1
				person++; // 매수사람 +1
			}
			
			// while문을 빠져나가기 위해 arr 리스트 하나씩 삭제
			for(int i=idx; i>=0; --i) {
				if(dasom>arr.get(i)) {
					arr.remove(i);
				}
			}
		}

		// 후보가 다솜 혼자인 경우
		if(N==1) {
			System.out.println(0);
			return;
		}
		System.out.println(person);
	}

}
