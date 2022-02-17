package com.ssafy.im;

import java.io.*;
import java.util.*;

// 카드섞기
public class BOJ_1091_김혜진 {
	static String src = "3\r\n" + 
			"1 0 2\r\n" + 
			"0 2 1";
	static int N, P[], S[], card[], tmp[];
	static int total = 0;
	static boolean isEqual, error;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		
		N = Integer.parseInt(br.readLine()); // 몇 번 섞어야 하는지 (48이하의 3의 배수)
		
		P = new int[N]; // 길이가 N인 수열P (0,1,2 중 하나)
		S = new int[N]; // 길이가 N인 수열S (0~N-1 중 하나, 중복 x)
		card = new int[N]; // 카드길이만큼 배열
		tmp = new int[N]; // 카드를 N번 섞을 때 저장할 임시 배열
		
		int shuffle=0; // 반복문 돌려서 한 번 돌 때마다 shuffle +1 ( 반복문을 벗어나지 못하고 계속 돌면 -1 출력)
		
		StringTokenizer stP = new StringTokenizer(br.readLine());
		StringTokenizer stS = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			P[i] = Integer.parseInt(stP.nextToken()); // 어떤 플레이어에게 가야 하는지
			S[i] = Integer.parseInt(stS.nextToken()); // 카드가 섞이는 방법
		}
		
		// 카드 초기 상태 (3명에게 나눠서 순서대로 줘야함)
		for(int i=0; i<N; i++) {
			card[i] = i%3;
		}
		
		// 카드가 섞일 때까지 반복 (P[]와 같아질때 까지, while 벗어나지 못하면 -1 출력)
		while(true) {
			isEqual = true;
			error = true;
			
			for(int i=0 ; i<N; i++) {
				// 카드가 P[] 와 일치하는지
				if(card[i]==P[i]) continue;
				else {
					isEqual = false;
					break;
				}
			}
			
			// p[] 와 일치하지 않으면 S[]방법으로 섞인걸 임시 배열에 저장
			if(!isEqual) {
				for(int i=0; i<N; i++) {
					tmp[i] = card[S[i]];
				}
				
				for(int i=0; i<N; i++) {
					card[i] = tmp[i];
				}
				total++; // 셔플 횟수 +1
			}else { // card[]와 P[] 가 일치한다면 셔플횟수 출력
				System.out.println(total);
				break;
			}
			
			for(int i=0; i<N; i++) {
				error = (card[i]!=i%3)? false : true;
			}
			if(error) {
				System.out.println(-1);
				break;
			}
		}
	}

}
