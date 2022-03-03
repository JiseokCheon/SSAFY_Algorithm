package com.ssafy.bj;

import java.io.*;
import java.util.*;

// N장의 카드 중에서 숫자 3개를 뽑았을 때, M을 넘지 않으면서 M에 최대한 가까운 카드 // 완전탐색? 
// 반복문 돌며 세 개의 숫자 합이 M이 되지 않는 수 계속 찾기 -> M을 넘지 않는 가장 큰 값 출력
public class BOJ_2798_블랙잭_김혜진 {
	static String src = "5 21\r\n" + 
			"5 6 7 8 9";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 카드의 개수
		int M = Integer.parseInt(st.nextToken()); // 3개의 숫자 합 중 가능한 최대 숫자의 수
		
		int[] card = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			card[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = getMax(card, N, M);
		
		System.out.println(max);
	}
	
	// 카드 3개를 뽑아 합이 M과 가장 가까운 수 찾기
	public static int getMax(int[] card, int N, int M) {
		int max = 0;
		// 첫번째 카드 (겹치면 안되므로 N-2까지)
		for(int i=0; i<N-2; i++) {
			// 첫 번째로 뽑은 카드가 M보다 크면 탐색할 필요 x 
			if(card[i] > M) continue;
			// 두번째 카드 (i+1부터 N-1까지)
			for(int j=i+1; j<N-1; j++) {
				// 첫 번째 , 두 번째 카드의 합이 M보다 크면 탐색할 필요 x
				if(card[i]+card[j] > M) continue;
				// 세번째 카드
				for(int k=j+1; k<N; k++) {
					int sum = card[i] + card[j] + card[k];
					// 세 카드의 합이 M과 같으면 
					if(M==sum) return sum;
					else if(sum<M) max = Math.max(max, sum);
				}
			}
		}
		
		return max;
	}
}
