package BJ_14499_주사위굴리기;

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

		int x=Integer.parseInt(st.nextToken());
		int y=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		int[] move=new int[k];
		
		for(int i=0; i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		

		st=new StringTokenizer(br.readLine());
		for(int i=0; i<k ; i++) {
			move[i]=Integer.parseInt(st.nextToken());
		}
	
		StringBuilder sb=new StringBuilder();
		int[] dice= {0,0,0,0,0,0};// 위,북,동,서,남,아래
		int temp;
		for(int i=0; i<k; i++) {
			switch(move[i]) {
			case 1:
				if(y+1<m) {
					y=y+1;
					temp=dice[0];
					dice[0]=dice[3];
					dice[3]=dice[5];
					dice[5]=dice[2];
					dice[2]=temp;
					if(map[x][y]==0) {
						map[x][y]=dice[5];
					}else {
						dice[5]=map[x][y];
						map[x][y]=0;
					}
					sb.append(dice[0]).append("\n");
				}
				break;
			case 2:
				if(y-1>=0) {
					y=y-1;
					temp=dice[0];
					dice[0]=dice[2];
					dice[2]=dice[5];
					dice[5]=dice[3];
					dice[3]=temp;
					if(map[x][y]==0) {
						map[x][y]=dice[5];
					}else {
						dice[5]=map[x][y];
						map[x][y]=0;
					}
					sb.append(dice[0]).append("\n");
				}
				break;
			case 3:
				if(x-1>=0) {
					x=x-1;
					temp=dice[0];
					dice[0]=dice[4];
					dice[4]=dice[5];
					dice[5]=dice[1];
					dice[1]=temp;
					if(map[x][y]==0) {
						map[x][y]=dice[5];
					}else {
						dice[5]=map[x][y];
						map[x][y]=0;
					}
					sb.append(dice[0]).append("\n");
				}
				break;
			case 4:
				if(x+1<n) {
					x=x+1;
					temp=dice[0];
					dice[0]=dice[1];
					dice[1]=dice[5];
					dice[5]=dice[4];
					dice[4]=temp;
					if(map[x][y]==0) {
						map[x][y]=dice[5];
					}else {
						dice[5]=map[x][y];
						map[x][y]=0;
					}
					sb.append(dice[0]).append("\n");
				}
				break;
			}
		}
		System.out.print(sb);
		

	}

}
