import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static class Point{
		int r, c, m;
		
		public Point(int r, int c, int m) {
			this.r=r;
			this.c=c;
			this.m=m;
		}
	}
	
	static int[][] dir= {{-1,0},{0,1},{0,-1},{1,0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int r=Integer.parseInt(st.nextToken());
		int c= Integer.parseInt(st.nextToken());
		int t= Integer.parseInt(st.nextToken());
		
		int[][] map=new int[r][c];
		int ar=0,ac=0;
		for(int i=0; i<r; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<c; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==-1) {
					ar=i;
					ac=j;
				}
			}
		}
		int count;
		int tempr,tempc;
		Queue<Point> q=new LinkedList<Point>();
		for(int time=0; time<t;time++) {
			for(int i=0; i<r; i++) {
				for(int j=0; j<c; j++) {
					if(map[i][j] >0) {
						count=0;
						for(int k=0; k<4; k++) {
							tempr=i+dir[k][0];
							tempc=j+dir[k][1];
							if(tempr <0 || tempr >=r || tempc <0 || tempc >=c || map[tempr][tempc]==-1) {
								continue;
							}
							count++;
							q.add(new Point(tempr,tempc,map[i][j]/5));
						}
						map[i][j]=map[i][j]-count*(map[i][j]/5);
					}
				}
			}
			while(!q.isEmpty()) {
				Point p =q.poll();
				map[p.r][p.c]+=p.m;
			}
			
		
			
			for(int i= ar-2;i>0;i--) {
				map[i][0]=map[i-1][0];
			}
			for(int i= 0; i<c-1;i++ ) {
				map[0][i]=map[0][i+1];
			}
			for(int i=0 ; i<ar-1;i++) {
				map[i][c-1]=map[i+1][c-1];
			}
			for(int i=c-1; i>0; i--) {
				map[ar-1][i]=map[ar-1][i-1];
			}
			map[ar-1][1]=0;
			
			for(int i=ar+1;i<r-1;i++) {
				map[i][0]=map[i+1][0];
			}
			for(int i=0; i<c-1;i++) {
				map[r-1][i]=map[r-1][i+1];
			}
			for(int i=r-1;i>ar;i--) {
				map[i][c-1]=map[i-1][c-1];
			}
			for(int i=c-1;i>0;i--) {
				map[ar][i]=map[ar][i-1];
			}
			map[ar][1]=0;
			
		
			
		}
		int sum=2;
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				sum+=map[i][j];
			}
		}
		System.out.print(sum);
		
	}

}
