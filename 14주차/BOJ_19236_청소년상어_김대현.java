package BJ_19236_청소년상어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	static int max=0;
	static int[][] dir= {{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}};
	
	public static class fish{
		int r,c,d,size;
		boolean alive;
		
		public fish(int r, int c, int d, int size, boolean alive) {
			this.r=r;
			this.c=c;
			this.d=d;
			this.size=size;
			this.alive=alive;
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		fish[] fishes=new fish[17];
		int[][] map=new int[4][4];
		
		for(int i=0; i<4; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++) {
				int size=Integer.parseInt(st.nextToken());
				int d=Integer.parseInt(st.nextToken())-1;
				fishes[size]=new fish(i,j,d,size,true);
				map[i][j]=size;
			}
		}
		
		fishes[map[0][0]].alive=false;
		int temp=map[0][0];
		map[0][0]=-1;
		fish shark=new fish(0,0,fishes[temp].d,-1,true);
		dfs(map,fishes,temp,shark);//현재 상어의 r,c,d,먹은번호의 합
		
		System.out.print(max);

	}
	
	
	private static void dfs(int[][] map, fish[] fishes, int sum, fish shark) {
		
		int[][] tmap=new int[4][4];
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				tmap[i][j]=map[i][j];
			}
		}
		fish[] tfishes=new fish[17];
		for(int i=1; i<17; i++) {
			tfishes[i]=new fish(fishes[i].r,fishes[i].c,fishes[i].d,fishes[i].size,fishes[i].alive);
		}
		
		for(int i=1; i<17; i++) {
			fish now=tfishes[i];
			if(!now.alive) {
				continue;
			}
			
			int d=now.d;
			for(int j=0; j<8; j++) {
				int nr=now.r+dir[(d+j)%8][0];
				int nc=now.c+dir[(d+j)%8][1];
				if(!check(nr,nc) ||tmap[nr][nc]==-1) {
					continue;
				}
				tfishes[i].d=(d+j)%8; 
                if(tmap[nr][nc]==0) {
                    tmap[nr][nc] = i; 
                    tmap[now.r][now.c] = 0; 
                    tfishes[i] = new fish(nr,nc,now.d,i,true); 
                    break;
                } 
                if(tmap[nr][nc]>0) { 
                    int nextfish = tmap[nr][nc]; 
                    tmap[now.r][now.c] = nextfish; 
                    tmap[nr][nc] = i; 
                    int a = tfishes[i].d; 
                    int b= tfishes[nextfish].d; 
                    tfishes[i] = new fish(nr,nc,a,i,true); 
                    tfishes[nextfish] = new fish(now.r, now.c, b,tmap[nr][nc],true); 
                    break; 
                } 
				
			}
			
		}
		for (int i = 1; i <= 3; i++) { 
            int nr = shark.r + (dir[shark.d][0])*i; 
            int nc = shark.c + (dir[shark.d][1])*i; 
            if(!check(nr,nc) || tmap[nr][nc]==0) continue; 
            int t = tmap[nr][nc]; 
            fish tshark = new fish(nr,nc, tfishes[t].d,-1,true); 
            tfishes[t].alive = false; 
            tmap[nr][nc] = -1; 
            tmap[shark.r][shark.c] = 0; 
            dfs(tmap, tfishes, sum+t, tshark); 
            tfishes[t] = new fish(nr ,nc , tshark.d,t,true); 
            tmap[shark.r][shark.c] = -1; 
            tmap[nr][nc] = t;
        } 
		
		if(max < sum) {
			max=sum;
		}
		
		
	}


	public static void move(fish[] fishes, int[][] map) {
		
		for(int i=1; i<=16; i++) {
			if(fishes[i].alive) {
				continue;
			}
			int fr=fishes[i].r;
			int fc=fishes[i].c;
			int fd=fishes[i].d;
			int fs=fishes[i].size;
			for(int d=0;d<8;d++) {
				int tempr=fr+dir[d][0];
				int tempc=fc+dir[d][1];
				if(!check(tempr,tempc)) {
					continue;
				}
				
				
			}
		}
		
		
	}
	
	public static boolean check(int r, int c) {
		if(r <0 || r>3 || c <0 || c>3) {
			return false;
		}
		return true;
	}

}
