package BJ_10026_적록색약;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[][] dir= {{-1,0},{1,0},{0,1},{0,-1}};
	static int n,rgbcount,rbcount;
	static boolean[][] visitedrgb,visitedrb;
	static char[][] rgb,rb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		n=Integer.parseInt(br.readLine());
		
		rgb=new char[n][n];
		rb=new char[n][n];
		
		for(int i=0;i<n;i++) {
			String st=br.readLine();
			for(int j=0;j<n;j++) {
				rgb[i][j]=st.charAt(j);
				if(rgb[i][j]=='R'||rgb[i][j]=='G') {
					rb[i][j]='R';
				}else {
					rb[i][j]='B';
				}
			}
		}
		
		visitedrgb=new boolean[n][n];
		rgbcount=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(!visitedrgb[i][j]) {
					rgbcount++;
					char temp=rgb[i][j];
					checkrgb(temp,i,j);
				}
			}
		}
		
		rbcount=0;
		visitedrb=new boolean[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(!visitedrb[i][j]) {
					rbcount++;
					char temp=rb[i][j];
					checkrb(temp,i,j);
				}
			}
		}
		System.out.print(rgbcount+" "+rbcount);

	}

	private static void checkrb(char temp, int row, int col) {
		int nrow,ncol;
		visitedrb[row][col]=true;
		for(int i=0; i<4; i++) {
			nrow=row+dir[i][0];
			ncol=col+dir[i][1];
			if(check(nrow,ncol)&&!visitedrb[nrow][ncol]) {
				if(rb[nrow][ncol]==temp) {
					checkrb(temp,nrow,ncol);
				}
			}
		}
		
		
	}

	private static void checkrgb(char temp,int row,int col) {
		int nrow,ncol;
		visitedrgb[row][col]=true;
		for(int i=0; i<4; i++) {
			nrow=row+dir[i][0];
			ncol=col+dir[i][1];
			if(check(nrow,ncol)&&!visitedrgb[nrow][ncol]) {
				if(rgb[nrow][ncol]==temp) {
					checkrgb(temp,nrow,ncol);
				}
			}
		}

		
	}
	
	private static boolean check(int row, int col) {
		if(row>=n || row<0 || col<0 || col>=n) {
			return false;
		}
		return true;
	}

}

