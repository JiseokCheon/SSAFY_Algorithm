import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	public static class p {
		int n, m, h,d;

		public p(int h, int m, int n,int d) {
			this.n = n;
			this.m = m;
			this.h = h;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		boolean[][][] check= new boolean[h][m][n];
		int cnt=0;
		Queue<p> q=new LinkedList<p>();
		int temp;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < m; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < n; k++) {
					temp = Integer.parseInt(st.nextToken());
					if(temp==1) {
						check[i][j][k]=true;
						q.offer(new p(i,j,k,1));
					}else if(temp==-1) {
						check[i][j][k]=true;
					}else {
						cnt++;
					}
				}
			}

		}
		int time = 0;
		int[][] dir = { { -1, 0, 0 }, { 1, 0, 0 }, { 0, 1, 0 }, { 0, -1, 0 }, { 0, 0, 1 }, { 0, 0, -1 } };
		boolean can=false;
		while(!q.isEmpty()) {
			p p=q.poll();
			int i=p.h;
			int j=p.m;
			int k=p.n;
			
			for (int d = 0; d < 6; d++) {
				int nh = i + dir[d][0];
				int nm = j + dir[d][1];
				int nn = k + dir[d][2];
				if (nh < 0 || nn < 0 || nm < 0 || nh >= h || nn >= n || nm >= m || check[nh][nm][nn]) {
					continue;
				}
				
				check[nh][nm][nn]=true;
				q.offer(new p(nh, nm, nn,p.d+1)); 
				if(p.d > time) {
					time=p.d;
				}
				cnt--;
			}
			if(cnt==0) {
				can=true;
				break;
			}	
			
		}
		if(can) {
			System.out.print(time);
			
		}else {
			System.out.print(-1);
		}
		
	}

}
