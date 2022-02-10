package com.ssafy.im;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class BOJ_2477_참외밭 {
	static String src = "7\r\n" + "4 50\r\n" + "2 160\r\n" + "3 30\r\n" + "1 60\r\n" + "3 20\r\n" + "1 100";
	static int maxX, maxY;
	static int minX, minY;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));

		int N = Integer.parseInt(br.readLine()); // 1제곱미터 당 자라나는 참외의 수
		int[] dir = new int[6]; // 방향 배열을 담을 정보(1: 동 2: 서 3 : 남 4 : 북)
		int[] area = new int[6]; // 크기 배열을 담을 정보
		int totalArea;
		
		for (int i = 0; i < 6; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			dir[i] = Integer.parseInt(st.nextToken());
			area[i] = Integer.parseInt(st.nextToken());

		}

		for (int i = 0; i < dir.length; i++) {
			if (dir[i] == 1 || dir[i] == 2) { // 최대 가로값
				maxX = Math.max(maxX, area[i]);
			} else { // 최대 세로값
				maxY = Math.max(maxY, area[i]);
			}
		}
		
		// 이동 방향이 일치해야 최소 가로, 세로 길이를 구할 수 있음
		for(int i=0; i< dir.length; i++) {
			int before = (i+5)%6;
			int next = (i+1)%6;
			
			if(dir[before] == dir[next]) {
				if(dir[i] == 1 || dir[i] == 2) {
					minX = area[i];
				}else {
					minY = area[i];
				}
			}
		}
		
		totalArea = N*(maxX*maxY-minX*minY);
		System.out.println(totalArea);
		
	}

}
