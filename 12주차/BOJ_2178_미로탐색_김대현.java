import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static int min;
	static int[][] dir= {{1,0},{0,1},{0,-1},{-1,0}};
	static int[][] arr;
	static boolean[][] visited;
	
	private static void go(int r,int c) {
		
		
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {r,c});
		
		while(!q.isEmpty()) {
			int qr=q.peek()[0];
			int qc=q.poll()[1];
			for(int i=0; i<4;i++) {
				int nr=qr+dir[i][0];
				int nc=qc+dir[i][1];
				if(check(nr,nc) && !visited[nr][nc] && !(arr[nr][nc]==0)) {
					q.offer(new int[] {nr,nc});
					visited[nr][nc]=true;
					arr[nr][nc]=arr[qr][qc]+1;
				}
			}
			
		}
		
		
	}
	
	private static boolean check(int r, int c) {
		if(r<0 || r>=n || c<0 || c>=m) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		arr=new int[n][m];
		min=n*m;
		for(int i=0;i<n;i++) {
			String str= br.readLine();
			for(int j=0;j<m;j++) {
				arr[i][j]=(int)str.charAt(j)-'0';
			}
		}
		visited=new boolean[n][m];

		visited[0][0]=true;
		go(0,0);
		System.out.print(arr[n-1][m-1]);
		

	}

}
