package BJ_2630_색종이만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] cnt=new int[2];
	static int n;
	static int[][] arr;

	public static void main(String[] args) throws  IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		arr=new int[n][n];
		
		for(int i=0; i<n; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		count(n,0,0);
		
		System.out.print(cnt[0]+"\n"+cnt[1]);

	}

	private static void count(int size, int start_r, int start_c) {
		boolean check=true;
		int temp=arr[start_r][start_c];
loop:	for(int i=start_r;i<start_r+size;i++) {
			for(int j=start_c;j<start_c+size;j++) {
				if(arr[i][j]!=temp) {
					check=false;
					break loop;
				}
			}
		}
		
		if(check) {
			cnt[temp]++;
		}else {
			count(size/2,start_r,start_c);
			count(size/2,start_r,start_c+size/2);
			count(size/2,start_r+size/2,start_c);
			count(size/2,start_r+size/2,start_c+size/2);
		}
		
	}

}
