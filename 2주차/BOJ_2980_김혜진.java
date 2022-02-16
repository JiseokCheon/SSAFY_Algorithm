package com.ssafy.im;

import java.util.*;
import java.io.*;

// 도로와 신호등
public class BOJ_2980_김혜진 {
	static String src = "2 10\r\n" + "3 5 5\r\n" + "5 2 2";

	static int N, L;
	static int d, r, g;
	//static int current, time;
	
	static class Light {
		int r; // 빨간불대기시간
		int g; // 초록불대기시간

		public Light(int r, int g) {
			this.r = r;
			this.g = g;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 신호등의 개수
		L = Integer.parseInt(st.nextToken()); // 도로의 길이

		Light[] len = new Light[L + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			d = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());

			len[d] = new Light(r, g);
		}

		int current = 0; // 현재시간
		int time = 0; // 총 시간
		
		while(current < L) {
			// 다음 위치에 신호등 존재 여부
			if(len[current] != null) {
				int tmp = time %= (len[current].r + len[current].g);
				
				if(tmp <= len[current].r) { // 빨간불이면 해당 시간만큼 대기한 뒤 이동
					time += len[current].r-tmp; 
				}
			}
			
			time++;
			current++;
			
			//if(current==L) return;
		}
		System.out.println(time);
	}
}
