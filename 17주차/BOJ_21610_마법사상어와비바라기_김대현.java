package BJ_21610_마법사상어와비바라기;
import java.io.*;
import java.util.*;
public class Main {
	
	static int[][] dir= {{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};
	static int[][] bug_dir= {{-1,-1},{-1,1},{1,-1},{1,1}};
	static int n;
	static int[][] map;
	static boolean[][] rain;
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		map=new int[n][n];
		for(int i=0; i<n; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		
		int[][] clouds = new int[m][2];
		for(int i=0; i<m; i++) {
			st=new StringTokenizer(br.readLine());
			clouds[i][0]=Integer.parseInt(st.nextToken())-1;
			clouds[i][1]=Integer.parseInt(st.nextToken());
		}

		rain=new boolean[n][n];
		rain[n-1][0]=true;
		rain[n-1][1]=true;
		rain[n-2][0]=true;
		rain[n-2][1]=true;
		
		for(int i=0; i<m; i++) {
			moveAndRain(clouds[i][0],clouds[i][1]);
			bug();
			newRain();
		}
		getSum();
	}
	private static void getSum() {
		int sum=0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				sum+=map[i][j];
			}
		}
		System.out.println(sum);
	}
	private static void newRain() {
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(rain[i][j]) {
					rain[i][j]=false;
				}else {
					if(map[i][j]>=2) {
						map[i][j]-=2;
						rain[i][j]=true;
					}
				}
			}
		}
		
	}
	private static void bug() {
		int count,tempr,tempc;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(rain[i][j]) {
					count=0;
					for(int d=0; d<4; d++) {
						tempr=i+bug_dir[d][0];
						tempc=j+bug_dir[d][1];
						if(tempr <0 || tempr >=n || tempc <0 || tempc >=n) {
							continue;
						}
						if(map[tempr][tempc]>0) {
							count++;
						}
					}
					map[i][j]+=count;
				}
			}
		}
		
		
	}
	private static void moveAndRain(int d, int s) {
		boolean[][] temp=new boolean[n][n];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(rain[i][j]) {
					temp[(i+dir[d][0]*s+s*n)%n][(j+dir[d][1]*s+s*n)%n]=true;
				}
			}
		}
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				rain[i][j]=temp[i][j];
				if(rain[i][j]) {
					map[i][j]++;
				}
			}
		}
		
	}

}
