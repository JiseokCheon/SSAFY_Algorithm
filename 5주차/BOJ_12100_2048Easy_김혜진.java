package com.ssafy.bj;

import java.util.*;
import java.io.*;

// 백트래킹
public class BOJ_12100_2048Easy_김혜진 {
	static String src = "3\r\n" + 
			"2 2 2\r\n" + 
			"4 4 4\r\n" + 
			"8 8 8";
	
	static int N;
	static int[][] arr;
	static int answer; // 5번의 이동이 끝났을 때 가장 큰 값
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = Integer.MIN_VALUE;
		startGame(0);
		
		System.out.println(answer);
	}
	
	// dfs 탐색
	public static void startGame(int cnt) {
		// 5번 이동하면 게임 끝-가장 큰 값 찾기
		if(cnt == 5) {
			int max = findMaxValue();
			answer = Math.max(max, answer); // 최대값 answer 저장
			return;
		}
		
		int[][] copyArr = new int[N][N]; // 반복해야하니까 배열 하나 복사

		for(int i=0; i<N ;i++) {
			for(int j=0; j<N; j++) {
				copyArr[i][j] = arr[i][j];
			}
		}
		
		for(int d=0; d<4; d++) {
			move(d);
			startGame(cnt+1);
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					arr[i][j] = copyArr[i][j]; // 바꾼거 옮겨담아주기
				}
			}
		}
	}
	// cnt==5일때 arr 배열에서 최대값 찾기
	public static int findMaxValue() {
		int max = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				max = Math.max(max, arr[i][j]);
			}
		}
		
		return max;
	}
	
	static void move(int dir) {
		Queue<Integer> queue = new LinkedList<>();
		
		switch(dir) {
		// 상
		case 0:
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					// 세로 한 줄씩 - arr이 0이 아닐 경우에 큐에 넣어주고 0으로 변경
					if(arr[j][i]!=0) queue.offer(arr[j][i]);
					arr[j][i]=0;
				}
				
				// 큐에 세로 줄의 정보 담겨있음 - 큐에서 하나씩 꺼내면서 합칠 수 있으면 합치고 다시 arr에 저장
				int idx=0;
				int data;
				while(!queue.isEmpty()) {
					data = queue.poll();
					// 배열이 0이면 아무것도 들어있지 않은 상태
					if(arr[idx][i] ==0) {
						arr[idx][i] = data; // 데이터 넣어줌
					}else if(arr[idx][i] == data) {
						// 큐에서 꺼낸 값과 배열의 값이 같으면 합쳐줌
						arr[idx][i] = data*2;
						idx++; // 인덱스 값 +1
					}else {
						// 배열에 이미 값이 있다면 idx값+1 해준 배열의 위치에 데이터 넣어줌
						idx++;
						arr[idx][i] = data;
					}
				}
			}
			break;
		// 하
		case 1:
			for(int i=0; i<N; i++) {
				for(int j=N-1; j>=0; j--) {
					// 상 과 마찬가지로 세로로 한줄 씩 밑에서부터 
					if(arr[j][i]!=0) queue.offer(arr[j][i]);
					arr[j][i]=0;
				}
				int idx=N-1; // 밑에서부터 합쳐주니까 처음 인덱스 번호 N-1
				int data;
				while(!queue.isEmpty()) {
					data = queue.poll();
					// 배열이 0이면 아무것도 들어있지 않은 상태
					if(arr[idx][i] ==0) {
						arr[idx][i] = data; // 데이터 넣어줌
					}else if(arr[idx][i] == data) {
						// 큐에서 꺼낸 값과 배열의 값이 같으면 합쳐줌
						arr[idx][i] = data*2;
						idx--; // 인덱스 값 -1
					}else {
						// 배열에 이미 값이 있다면 idx값-1 해준 배열의 위치에 데이터 넣어줌
						idx--;
						arr[idx][i] = data;
					}
				}
			}
			break;
		// 좌
		case 2:
			for(int i=0; i<N; i++) {
				for(int j=N-1; j>=0; j--) {
					// 가로로 한줄 씩
					if(arr[i][j]!=0) queue.offer(arr[i][j]);
					arr[i][j] = 0;
				}
				
				// 상,하 와는 다르게 가로줄이 큐에 들어가있음-큐에서 꺼내면서 합치기
				int idx=N-1;
				int data;
				while(!queue.isEmpty()) {
					data = queue.poll();
					// 배열이 0이면 아무것도 들어있지 않은 상태
					if(arr[i][idx] ==0) {
						arr[i][idx] = data; // 데이터 넣어줌
					}else if(arr[i][idx] == data) {
						// 큐에서 꺼낸 값과 배열의 값이 같으면 합쳐줌
						arr[i][idx] = data*2;
						idx--; // 인덱스 값 -1
					}else {
						// 배열에 이미 값이 있다면 idx값+1 해준 배열의 위치에 데이터 넣어줌
						idx--;
						arr[i][idx] = data;
					}
				}
			}
			break;
		// 우
		case 3:
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					// 가로로 한줄 씩
					if(arr[i][j]!=0) queue.offer(arr[i][j]);
					arr[i][j] = 0;
				}
				
				// 상,하 와는 다르게 가로줄이 큐에 들어가있음-큐에서 꺼내면서 합치기
				int idx=0;
				int data;
				while(!queue.isEmpty()) {
					data = queue.poll();
					// 배열이 0이면 아무것도 들어있지 않은 상태
					if(arr[i][idx] ==0) {
						arr[i][idx] = data; // 데이터 넣어줌
					}else if(arr[i][idx] == data) {
						// 큐에서 꺼낸 값과 배열의 값이 같으면 합쳐줌
						arr[i][idx] = data*2;
						idx++; // 인덱스 값 -1
					}else {
						// 배열에 이미 값이 있다면 idx값+1 해준 배열의 위치에 데이터 넣어줌
						idx++;
						arr[i][idx] = data;
					}
				}
			}
			break;
		}
	}

}
