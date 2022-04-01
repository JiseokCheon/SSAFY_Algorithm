package study_mar;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_14890_경사로 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 0; //가능한 길의 개수
		//지나갈 수 있는지 구하기
		//행 먼저
		for (int i = 0; i < N; i++) {
			boolean[] visit  = new boolean[N]; //경사로 배치 여부
		line :for (int j = 0; j < N; j++) {
				if(j==N-1) { //마지막에 온 경우 가능한 길이므로 cnt추가 
					cnt++; break;
				}   
				if(map[i][j] == map[i][j+1]+1) { //다음칸이  1 더 작은 경우 => 현재 위치에서 앞쪽에 경사로 배치
					for (int k = 1; k <= L; k++) { //경사로 길이만큼
						if(j+k < 0 || j+k >= N ||  //범위 벗어나거나
								visit[j+k] || map[i][j+k] != map[i][j+1]) { //이미 경사로가 있거나, 높이가 다른 경우
							break line; //경사로 배치가 불가능한 길이므로 다음 행으로
						}
					}
					for (int k = 1; k <= L; k++) { //경사로를 놓을 수 있는 경우이므로 경사로 배치해주기 
						visit[j+k] = true;
					}
				}else if(map[i][j] == map[i][j+1]-1){ //다음칸이 1 큰 경우 => 현재칸부터 뒤쪽으로 경사로 배치
					for (int k = 0; k < L; k++) {
						if(j-k < 0 || j-k >= N || visit[j-k] || map[i][j-k] != map[i][j]) {
							break line;
						}
					}
					for (int k = 0; k < L; k++) { //경사로 배치
						visit[j-k] = true;
					}
				}else if(Math.abs(map[i][j] - map[i][j+1])>1) { //차이가 1보다 큰 경우 다음 행으로
					break;
				}
			}
		}
		//열 검사 
		for (int j = 0; j < N; j++) { 
			boolean[] visit  = new boolean[N];
			line :for (int i = 0; i < N; i++) {
				if(i==N-1) { //마지막에 온 경우 가능한 길이므로 cnt추가 
					cnt++; break;
				}   
				if(map[i][j] == map[i+1][j]+1) { // 1 더 작은 경우
					for (int k = 1; k <= L; k++) {
						if(i+k < 0 || i+k >= N || visit[i+k] || map[i+k][j] != map[i+1][j]) {
							break line;
						}
					}
					for (int k = 1; k <= L; k++) {
						visit[i+k] = true;
					}
				}else if(map[i][j] == map[i+1][j]-1){ //다음칸이 1 큰 경우
					for (int k = 0; k < L; k++) {
						if(i-k < 0 || i-k >= N || visit[i-k] || map[i-k][j] != map[i][j]) {
							break line;
						}
					}
					for (int k = 0; k < L; k++) {
						visit[i-k] = true;
					}
				}else if(Math.abs(map[i][j] - map[i+1][j])>1) { //차이가 1보다 큰 경우 종료
					break;
				}
			}
		}
		System.out.println(cnt);
	}

}
