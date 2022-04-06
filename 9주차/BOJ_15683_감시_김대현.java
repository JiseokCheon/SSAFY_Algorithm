package BJ_15683_감시;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] arr,temp;
	static int n, m;
	static int count = 0;
	static int min;
	static int[][] cctvs;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m]; // 전체 맵 저장
		min = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] > 0 && arr[i][j] < 6) {
					count++;
				}
				if(arr[i][j]==0) {
					min++;
				}
			}
		}
		boolean[][] checks=new boolean[count][4];
		cctvs = new int[count][3];
		
		int temp=0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] > 0 && arr[i][j] < 6) {
					cctvs[temp][0] = arr[i][j];
					cctvs[temp][1] = i;
					cctvs[temp][2] = j;
					temp++;
				}
			}
		}
		cctv(checks,0);
		System.out.print(min);

	}

	static void cctv(boolean[][] checks, int cnt) {
		if (cnt == count) {
			int sum=0;
			temp=new int[n][m];
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					temp[i][j]=arr[i][j];
				}
			}
			for(int i=0; i<count;i++) {
				for(int j=0;j<4;j++) {
					if(checks[i][j]) {
						cover(temp,cctvs[i][1],cctvs[i][2],j);
					}
				}
			}
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if(temp[i][j]==0) {
						sum++;
					}
				}
			}
			if(sum<min) {
				min=sum;
			}

		} else {
			switch (cctvs[cnt][0]) {
			case 1:
				for(int i=0;i<4;i++) { // checks[index][0] :위, 1: 오른쪽, 2: 아래, 3:왼쪽
					checks[cnt][i]=true;
					cctv(checks,cnt+1);
					checks[cnt][i]=false;
				}
				break;
			case 2: // 좌우, 위아래
				for(int i=0;i<2;i++) {
					checks[cnt][i]=true;
					checks[cnt][i+2]=true;
					cctv(checks,cnt+1);
					checks[cnt][i]=false;
					checks[cnt][i+2]=false;
				}
				break;
					
			case 3: // 90도씩
				for(int i=0;i<4;i++) {
					checks[cnt][i]=true;
					checks[cnt][(i+1)%4]=true;
					cctv(checks,cnt+1);
					checks[cnt][i]=false;
					checks[cnt][(i+1)%4]=false;
				}
				break;
			case 4:// 한곳 빼고
				for(int i=0;i<4;i++) {
					checks[cnt][i]=true;
					checks[cnt][(i+1)%4]=true;
					checks[cnt][(i+2)%4]=true;
					checks[cnt][(i+3)%4]=false;
					cctv(checks,cnt+1);
				}
				
				break;
			case 5: // 4방향
				for(int i=0;i<4;i++) {
					checks[cnt][i]=true;
				}
				cctv(checks,cnt+1);
				break;

			}

		}
		return;

	}
	
	private static void cover(int[][] temp,int row, int col, int direction) {
		switch(direction) {
		case 0://위쪽
			for(int i=row-1;i>=0;i--) {
				if(temp[i][col]==0) {
					temp[i][col]=-1;
				}
				if(temp[i][col]==6) {
					break;
				}
				
			}
			break;
		case 1:// 오른쪽
			for(int i=col+1;i<m;i++) {
				if(temp[row][i]==0) {
					temp[row][i]=-1;
				}
				if(temp[row][i]==6) {
					break;
				}
				
			}
			break;
		case 2://아래쪽
			for(int i=row+1;i<n;i++) {
				if(temp[i][col]==0) {
					temp[i][col]=-1;
				}
				if(temp[i][col]==6) {
					break;
				}
				
			}
			break;
		case 3://왼쪽
			for(int i=col-1;i>=0;i--) {
				if(temp[row][i]==0) {
					temp[row][i]=-1;
				}
				if(temp[row][i]==6) {
					break;
				}
				
			}
			break;
		
		}
		
	}


}
