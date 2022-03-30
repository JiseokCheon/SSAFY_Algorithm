package BJ_14503_로봇청소기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st=new StringTokenizer(br.readLine());

		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int[][] map=new int[n][m];
		boolean[][] check=new boolean[n][m];
		

		st=new StringTokenizer(br.readLine());
		int r=Integer.parseInt(st.nextToken()); // 시작 행
		int c=Integer.parseInt(st.nextToken()); // 시작 열
		int d=Integer.parseInt(st.nextToken()); // 시작 방향
		
		for(int i=0; i<n; i++) { // 공간 정보 받아오기
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		boolean can=true;
		int cnt;
		if(map[r][c]==0) {
			cnt=1;
			check[r][c]=true;
		}else {
			can=false;
			cnt=0;
		}
		int tempr,tempc;
		int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}};//북,동,남,서
		while(can) {
			can=false; 
			for(int i=0;i<4;i++) {
				tempr=r+dir[(d+3-i)%4][0]; // 왼쪽방향을 보고
				tempc=c+dir[(d+3-i)%4][1]; 
				if(tempr>=0 && tempr<n && tempc >=0 && tempc<m ) { // 배열 범위 안에 있고
					if(!check[tempr][tempc] && map[tempr][tempc]==0) { // 아직 청소하지 않고 빈공간이면
						r=tempr; 
						c=tempc;
						d=(d+3-i)%4;
						can=true; // 체크해주기
						break;
					}
				}
			}
			
			if(!can) { // 4방향에 청소할 곳이 없다면
				tempr=r-dir[d][0]; // 방향 반대로 후진
				tempc=c-dir[d][1]; 
				if(tempr>=0 && tempr<n && tempc >=0 && tempc<m ) { // 배열 범위 안에 있는지 체크
					if( map[tempr][tempc]==0) { //  빈공간이면
						r=tempr; 
						c=tempc;
						can=true; // 체크해주기
					}
				}
			}else {
				cnt++;
				check[r][c]=true;
			}
			
		}
		
		System.out.println(cnt);
		

	}

}
