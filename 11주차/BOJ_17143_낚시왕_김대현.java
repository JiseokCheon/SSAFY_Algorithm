import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static class shark{
		int r,c,s,d,z;
		boolean check;
		
		public shark() {}
		
		public shark(int r,int c, int s, int d, int z,boolean check) {
			this.r=r;
			this.c=c;
			this.s=s;
			this.d=d;
			this.z=z;
			this.check=check;
		}
		
	}
	
	static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}}; // 위 오 아 왼
	static int r,c,m;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		r=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		int[][] map=new int[r][c];
		shark[] shark=new shark[m+1];
		shark[0]=new shark();
		for(int i=1; i<=m; i++) {
			st=new StringTokenizer(br.readLine());
			shark[i]=new shark();
			shark[i].r=Integer.parseInt(st.nextToken())-1;
			shark[i].c=Integer.parseInt(st.nextToken())-1;
			int temps=Integer.parseInt(st.nextToken());
			int sd=Integer.parseInt(st.nextToken());
			shark[i].z=Integer.parseInt(st.nextToken());
			switch(sd) {
			case 1:
				shark[i].d=0;
				break;
			case 2:
				shark[i].d=2;
				break;
			case 3:
				shark[i].d=1;
				break;
			case 4:
				shark[i].d=3;
				break;
			}
			if(shark[i].d==0 || shark[i].d==2) {
				shark[i].s=temps%(2*r-2);
			}else {
				shark[i].s=temps%(2*c-2);
				
			}
			map[shark[i].r][shark[i].c]=i;
		}

		int sum=0;
		int tempr,tempc;
		for(int i=0; i<c;i++) {
	
			
			for(int j=0; j<r;j++) {
				if(map[j][i]!=0) {
					shark[map[j][i]].check=true;
					sum+=shark[map[j][i]].z;
					map[j][i]=0;
					break;
				}
			}
			
			for(int j=1 ; j<=m; j++) {
				if(!shark[j].check) {
					map[shark[j].r][shark[j].c]=0;
					move(j,shark);	
				}
			}
			
			for(int j=1; j<= m; j++) {
				if(!shark[j].check) {
					tempr=shark[j].r;
					tempc=shark[j].c;
					if(map[tempr][tempc]!=0) {
						int tempj=map[tempr][tempc];
						if(shark[tempj].z > shark[j].z) {
							shark[j].check=true;
						}else {
							shark[tempj].check=true;
							map[tempr][tempc]=j;
						}
					}else {
						map[tempr][tempc]=j;
					}
					
				}
			}
			
		}
		System.out.print(sum);

	}

	private static void move(int num,shark[] shark) {
		int tempr=shark[num].r;
		int tempc=shark[num].c;
		int tempd=shark[num].d;
		int temps=shark[num].s;
		
		for(int i=0; i<temps; i++) {
			if(check(tempr+dir[tempd][0],tempc+dir[tempd][1])) {

				tempr=tempr+dir[tempd][0];
				tempc=tempc+dir[tempd][1];
			}else {
				tempd=(tempd+2)%4;
				tempr=tempr+dir[tempd][0];
				tempc=tempc+dir[tempd][1];
			}
		}
		shark[num].r=tempr;
		shark[num].c=tempc;
		shark[num].d=tempd;
	}

	private static boolean check(int i, int j) {
		
		if( i < 0 || i >= r || j <0 || j>= c) {
			return false;
		}
		
		return true;
	}

}
