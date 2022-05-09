package BJ_20061_모노미노도미노2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int count=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		boolean[][] g=new boolean[6][4];
		boolean[][] b=new boolean[6][4];
		
		int n=Integer.parseInt(br.readLine());
		int[][] block=new int[n][3];
		StringTokenizer st;
		
		for(int i=0; i<n; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<3;j++) {
				block[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<n; i++) {
			int t=block[i][0];
			int x=block[i][1];
			int y=block[i][2];
			down(t,x,y,g);
			if(t==1) {
				down(t,y,x,b);
			}else if(t==2) {
				down(3,y,x,b);
			}else if(t==3){
				down(2,y,x,b);
			}
			delete(g);
			delete(b);
			int gcheck=0;
			int bcheck=0;
			for(int j=4;j<6;j++) {
				int gcnt=0;
				int bcnt=0;
				for(int k=0;k<4;k++) {
					if(g[j][k]) {
						gcnt++;
					}
					if(b[j][k]) {
						bcnt++;
					}
				}
				if(gcnt>0) {
					gcheck++;
				}
				if(bcnt>0) {
					bcheck++;
				}
			}
			delete56(g,gcheck);
			delete56(b,bcheck);
			
			
			
		}
		int answer=0;
		for(int j=0; j<4; j++) {
			for(int k=0; k<4; k++) {
				if(b[j][k]) {
					answer++;
				}
				if(g[j][k]){
					answer++;
				}
			}
		}
		System.out.println(count);
		System.out.println(answer);

	}

	private static void delete56(boolean[][] map, int cnt) {
		
		for(int c=0; c<cnt;c++) {
			for(int i=0; i<5;i++) {
				for(int j=0; j<4; j++) {
					map[i][j]=false;
					map[i][j]=map[i+1][j];
				}
				
			}
			for(int j=0; j<4; j++) {
				map[5][j]=false;
			}
			
			
		}
		
		
	}

	private static void delete(boolean[][] map) {
		int cnt;
		for(int i=0; i<4;i++) {
			cnt=0;
			for(int j=0; j<4;j++) {
				if(map[i][j]) {
					cnt++;
				}
			}
			if(cnt==4) {
				count++;
				for(int ni=i;ni<5;ni++) {
					for(int j=0; j<4; j++) {
						map[ni][j]=false;
						map[ni][j]=map[ni+1][j];
					}
				}
				for(int j=0; j<4; j++) {
					map[5][j]=false;
				}
				i--;
			}
			
		}
		
	}

	private static void down(int t, int x, int y, boolean[][] map) {
		
		if(t==1) {
			down1(x,y,map);
		}else if(t==2) {
			down2(x,y,map);
		}else if(t==3) {
			down3(x,y,map);
		}
	}

	private static void down3(int x, int y, boolean[][] map) {

		boolean check=false;
		int nx=0;
		for(int i=3; i>=0; i--) {
			if(map[i][y]) {
				check=true;
				nx=i+1;
				break;
			}
		}
		if(check) {
			map[nx][y]=true;
			map[nx+1][y]=true;
		}else {
			map[0][y]=true;
			map[1][y]=true;
		}
		
	}

	private static void down2(int x, int y, boolean[][] map) {
		boolean check=false;
		int nx=0;
		for(int i=3; i>=0; i--) {
			if(map[i][y]||map[i][y+1]) {
				check=true;
				nx=i+1;
				break;
			}
		}
		if(check) {
			map[nx][y]=true;
			map[nx][y+1]=true;
		}else {
			map[0][y]=true;
			map[0][y+1]=true;
		}
		
	}

	private static void down1(int x, int y, boolean[][] map) {
		boolean check=false;
		int nx=0;
		for(int i=3; i>=0;i--) {
			if(map[i][y]) {
				check=true;
				nx=i+1;
				break;
			}
		}
		if(check) {
			map[nx][y]=true;
		}else {
			map[0][y]=true;
		}
		
	}

}
