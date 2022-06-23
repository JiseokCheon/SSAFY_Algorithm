package July;

import java.io.*;
import java.util.*;

public class BOJ_21611_마법사상어와블리자드 {
	public static void main(String[] args) throws IOException {
		int[][] move = {{-1,0},{1,0},{0,-1},{0,1}}; //문제에서 주어진 방향
		int[][] round = {{-1,0},{0,-1},{1,0},{0,1}}; //map에서 회전하기 위한 방향
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] ans = new int[3]; //폭발한 구슬 수 저장
		
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] move_info = new int[M][2];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			move_info[i][0] = Integer.parseInt(st.nextToken())-1; //방향
			move_info[i][1] = Integer.parseInt(st.nextToken()); //거리
		}
		
		for (int m = 0; m < M; m++) {
			int si = N/2, sj = N/2; //시작 위치
			//구슬 파괴하기
			for (int d = 1; d <= move_info[m][1]; d++) { //파괴 정보에 맞게 구슬 파괴하기
				int ni =si+move[move_info[m][0]][0] * d;
				int nj =si+move[move_info[m][0]][1] * d;
				if(ni>=0 && ni<N && nj>=0 && nj<N) { 
					map[ni][nj] = 0;
				}
			}
			Queue<Integer> que = new LinkedList<Integer>();
			
			//파괴 후 구슬 이동하기
			int i = si, j = sj-1, dir =1;
			boolean[][] visit = new boolean[N][N];
			visit[si][sj] = true;
			for (int t = 0; t < N*N - 1; t++) {
				visit[i][j] = true;
				if(map[i][j]!=0) que.offer(map[i][j]); //0이 아닌경우에만 que에 넣기
				
				//방향 변경
				if(!visit[i+round[(dir+1)%4][0]][j+round[(dir+1)%4][1]]) {
					i = i+round[(dir+1)%4][0]; j =j+round[(dir+1)%4][1];
					dir = (dir+1)%4 ;
				}else {
					i= i+round[dir][0];
					j= j+round[dir][1];
				}
			}
			//구슬 폭발
			while(true) {
				boolean flag = false;
				Queue<Integer> temp = new LinkedList<Integer>(); //임시 큐
				int cnt=0, num=0;
				while(!que.isEmpty()) {
					if(que.peek() != num) {//다른 숫자가 나오면 이전까지 나왔던 숫자의 개수를 판단해서 que에 넣어주기
						if(cnt<4) { //4개보다 적으면 temp 큐에 넣기
							for (int k = 0; k < cnt; k++) {
								temp.offer(num);
							}
						}else { //많으면 ans배열에 추가해주고, 플래그 true
							flag=true;
							ans[num-1]+= cnt;
						}
						cnt=1; //개수 추기화
						num = que.poll(); 
					}else { //숫자가 동일하면 cnt증가
						cnt++; que.poll();
					}
				}
				//마지막 남은 것까지 넣어주기 위해서 한번더
				if(cnt<4) {
					for (int k = 0; k < cnt; k++) {
						temp.offer(num);
					}
				}else {
					flag=true;
					ans[num-1]+= cnt;

				}
				
				//que배열에 옮겨주기
				while(!temp.isEmpty()) {
					que.offer(temp.poll());
				}
				if(!flag) break;
			}
			//연속하는 구슬 그룹으로 나눠주기
			Queue<Integer> temp = new LinkedList<Integer>();
			int cnt=0, num=0;
			while(!que.isEmpty()) {
				if(que.peek() != num) {//다른 숫자가 나오면 이전까지 나왔던 숫자의 개수를 판단해서 que에 넣어주기
					if(num!=0) { //개수가  0이 아닌경우에만 temp에 넣기
						temp.offer(cnt); //A구역에는 개수
						temp.offer(num); //B구역에는 구슬 번호
					}
					
					cnt=0;
					cnt++;
					num = que.poll();
				}else { //숫자가 동일하면 cnt증가
					cnt++; que.poll();
				}
			}temp.offer(cnt);temp.offer(num); //마지막 남은것 까지 모두 추가
			
			//map에다가 다시 넣어주기
			i = si; j = sj-1; dir = 1;
			visit = new boolean[N][N];
			visit[si][sj] = true;
			for (int t = 0; t < N*N - 1; t++) {
				visit[i][j] = true;
				if(!temp.isEmpty()) map[i][j] = temp.poll(); //temp에 구슬이 있으면 map에 구슬 번호를 넣어주고
				else map[i][j] = 0; //없다면 0으로
				
				//방향 변경
				if(!visit[i+round[(dir+1)%4][0]][j+round[(dir+1)%4][1]]) {
					i = i+round[(dir+1)%4][0]; j =j+round[(dir+1)%4][1];
					dir = (dir+1)%4 ;
				}else {
					i= i+round[dir][0];
					j= j+round[dir][1];
				}
			}
		}
		System.out.println(ans[0]+ 2* ans[1]+ 3*ans[2]);
	}
}
