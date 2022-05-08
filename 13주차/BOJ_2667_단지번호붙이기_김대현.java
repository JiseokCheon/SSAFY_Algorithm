import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static boolean[][] visit;
	static int[][] dir= {{1,0},{0,1},{0,-1},{-1,0}};
	static int n,cnt;
	static int[][] arr;
	public static void main(String[] args) throws  IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		n= Integer.parseInt(br.readLine());
		
		arr=new int[n][n];
		
		for(int i=0;i<n;i++) {
			String str=br.readLine();
			for(int j=0;j<n;j++) {
				arr[i][j]=str.charAt(j)-'0';
			}
		}
		visit=new boolean[n][n];
		cnt=2;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(arr[i][j]==1 && !visit[i][j]) {
					bfs(i,j);
					cnt++;
				}
			}
		}
		int[] answer=new int[cnt-2];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(arr[i][j]>1) {
					answer[arr[i][j]-2]++;
				}
			}
		}
		Arrays.sort(answer);
		System.out.println(answer.length);
		for(int i=0;i<answer.length;i++) {
			System.out.println(answer[i]);
		}
	
	}
	private static void bfs(int r, int c) {
		visit[r][c]=true;
		arr[r][c]=cnt;
		for(int i=0;i<4;i++) {
			int tempr=r+dir[i][0];
			int tempc=c+dir[i][1];
			if(check(tempr,tempc) && !visit[tempr][tempc]&&arr[tempr][tempc]==1) {
				bfs(tempr,tempc);
			}
		}

		
		
	}
	
	
	private static boolean check(int r, int c) {
		
		if(r>=n || r<0 || c>=n || c<0) {
			return false;
		}
		
		return true;
	}
		
		
		
		
			
}


