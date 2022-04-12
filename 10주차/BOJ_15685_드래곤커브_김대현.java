package BJ_15685_드래곤커브;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] dir= {{1,0},{0,-1},{-1,0},{0,1}};//오, 위, 왼, 아래
	static boolean[][] map;
	

	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int n=Integer.parseInt(br.readLine());
		int[][] curv=new int[n][4];
		
		for(int i=0; i<n; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++) {
				curv[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		map=new boolean[101][101];
		for(int i=0; i<n; i++) {
			dragon(curv[i][0],curv[i][1],curv[i][2],curv[i][3]);
		}
		
		
		int count=0;
		for(int i=0; i<100; i++) {
			for(int j=0 ; j<100 ; j++) {
			
				if(map[i][j]) {
					if(map[i+1][j] && map[i+1][j+1] && map[i][j+1]) {
						count++;
					}
				}
			}
		}
		System.out.print(count);
		
	}



	private static void dragon(int x, int y, int d, int g) {
		map[x][y]=true;
		
		ArrayList<Integer> list=new ArrayList<Integer>();
		list.add(d);
		
		for(int i=1; i<=g; i++) {
			int n= list.size();
			
			for(int j=0; j<n; j++) {
				d=(list.get(n-1-j)+1)%4;
				list.add(d);
			}
			
		}
		
		for(int i=0; i< list.size(); i++) {
			x= x+dir[list.get(i)][0];
			y= y+dir[list.get(i)][1];
			map[x][y]= true;
		}
		
	}



	

	

}
