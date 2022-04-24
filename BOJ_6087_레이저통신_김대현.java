import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] dirarr= {{-1,0},{0,1},{1,0},{0,-1}};
	
	public static class point{
		int r,c,d;
		
		public point(int r, int c, int d) {
			this.r=r;
			this.c=c;
			this.d=d;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int w=Integer.parseInt(st.nextToken());
		int h=Integer.parseInt(st.nextToken());
		
		char[][] map=new char[h][w];
		boolean[][] mapcheck=new boolean[h][w];
		int[][] mapcount=new int[h][w];
		for(int i=0; i<h; i++) {
			for(int j=0; j <w; j++) {
				mapcount[i][j]=Integer.MAX_VALUE;
			}
		}
		int[][] ra=new int[2][2];
		int count=0;
		for(int i=0; i<h; i++) {
			String str=br.readLine();
			for(int j=0; j<w; j++) {
				map[i][j]=str.charAt(j);
				if(map[i][j]=='C') {
					ra[count][0]=i;
					ra[count++][1]=j;
				}
				
				if(map[i][j]=='*') {
					mapcheck[i][j]=true;
				}
			}
		}
		Queue<point> q = new LinkedList<point>();
		q.offer(new point(ra[0][0],ra[0][1],-1));
		mapcheck[ra[0][0]][ra[0][1]]=true;
		mapcount[ra[0][0]][ra[0][1]]=0;
		int r,c,d=0;
		int tempr,tempc;
		while(!q.isEmpty()) {
			point p=q.poll();
			r=p.r;
			c=p.c;
			d=p.d;
			if(r==ra[1][0] && c==ra[1][1]) {
				break;
			}
			for(int i=0; i<4; i++) {
				for(int j=1; ;j++) {
					tempr=r+dirarr[i][0]*j;
					tempc=c+dirarr[i][1]*j;
					if(tempr <0 || tempr >=h || tempc <0 || tempc >=w || mapcheck[tempr][tempc]) {
						break;
					}
					if(!mapcheck[tempr][tempc] && mapcount[tempr][tempc] > d+1) {
						mapcount[tempr][tempc]=d+1;
						q.offer(new point(tempr,tempc,d+1));
					}
					
					
				}
			}
			
		}
		System.out.print(d);

	}

}
