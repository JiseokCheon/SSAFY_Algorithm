package BJ_14889_스타트와링크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int min=Integer.MAX_VALUE;
	static int[][] arr;
	static int k;

	public static void main(String[] args) throws  IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		k= Integer.parseInt(br.readLine());
		arr=new int[k][k];
		StringTokenizer st;
		for(int i=0;i<k;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<k;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		boolean[] check=new boolean[k];
		getcomb(0, 0,check);
		System.out.println(min);

	}

	private static void getcomb(int cnt,int r, boolean[] check) {
		
		if(r==k/2) {
			int gab=getgab(check);
			if(gab<min) {
				min=gab;
			}
			
		}else {
			for(int i=cnt;i<k;i++) {
				check[i]=true;
				getcomb(i+1,r+1,check);
				check[i]=false;
			}
		}
		
	}

	private static int getgab(boolean[] check) {
		int team1=0;
		int team2=0;
		
		for(int i=0;i<k;i++) {
			if(check[i]) {
				for(int j=0; j<k; j++) {
					if(check[j]==true) {
						team1+=arr[i][j];
					}
				}
			}
		}
		for(int i=0;i<k;i++) {
			if(!check[i]) {
				for(int j=0; j<k; j++) {
					if(!check[j]==true) {
						team2+=arr[i][j];
					}
				}
			}
		}
		
		
		return Math.abs(team1-team2);
	}

}
