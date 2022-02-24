package com.ssafy.im;

import java.util.*;
import java.io.*;

public class BOJ_1063_킹_김혜진 {
	static String src = "A8 B7 18\r\n" + 
			"RB\r\n" + 
			"RB\r\n" + 
			"RB\r\n" + 
			"RB\r\n" + 
			"RB\r\n" + 
			"RB\r\n" + 
			"RB\r\n" + 
			"RB\r\n" + 
			"RB\r\n" + 
			"RB\r\n" + 
			"RB\r\n" + 
			"RB\r\n" + 
			"RB\r\n" + 
			"RB\r\n" + 
			"RB\r\n" + 
			"RB\r\n" + 
			"RB\r\n" + 
			"RB";
 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		char[] king = st.nextToken().toCharArray(); // 킹의 위치(0:열,1:행)
		char[] stone = st.nextToken().toCharArray(); // 돌의 위치(0:열,1:행)
		int N = Integer.parseInt(st.nextToken()); // 이동횟수
		
		// 이동 횟수만큼 반복
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			
			// 다음 위치 저장을 위해 복사
			char[] nKing = king.clone();
			char[] nStone = stone.clone();
			
			moveChess(str, nKing);
			// 다음위치로 이동할 킹이 범위 내에 존재하지 않으면 skip- 
			if(nKing[0]<'A' || nKing[0]>'H' || nKing[1]<'1' || nKing[1]>'8') continue;
			// 다음위치로 이동할 킹과 돌의 위치가 같다면 돌 위치 킹 이동방향으로 변경
			if(nKing[0]==nStone[0] && nKing[1]==nStone[1]) {
				moveChess(str, nStone);
				// 돌의 이동범위도 확인
				if(nStone[0]<'A' || nStone[0]>'H' || nStone[1]<'1' || nStone[1]>'8') continue;
			}
			
			// 이동횟수(N)만큼 이동해줘야 하므로 다음 위치 현 위치에 저장
			king = nKing; 
			stone = nStone;
		}
		
		System.out.println(king);
		System.out.println(stone);
	}
	
	// 킹, 돌 이동 (0:열, 1:행)
	public static void moveChess(String str, char[] n) {
		switch(str) {
		case "R": // 오른쪽 한 칸
			n[0]++;
			break;
		case "L": // 왼쪽 한 칸
			n[0]--;
			break;
		case "B": // 아래로 한 칸
			n[1]--;
			break;
		case "T": // 위로 한 칸
			n[1]++;
			break;
		case "RT": // 오른쪽 위 대각선
			n[0]++;
			n[1]++;
			break;
		case "LT":
			n[0]--;
			n[1]++;
			break;
		case "RB":
			n[0]++;
			n[1]--;
			break;
		case "LB":
			n[0]--;
			n[1]--;
			break;
		}
	}

}
