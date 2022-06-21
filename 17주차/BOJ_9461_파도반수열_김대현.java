package BJ_9461_파도반수열;

import java.io.*;
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		int[] arr=new int[T];
		int max=5;
		for(int i=0; i<T; i++) {
			arr[i]=Integer.parseInt(br.readLine());
			if(arr[i]>max) {
				max=arr[i];
			}
		}
		long[] answer=new long[max];
		answer[0]=1;
		answer[1]=1;
		answer[2]=1;
		answer[3]=2;
		answer[4]=2;
		for(int i=5; i<max;i++) {
			answer[i]=answer[i-5]+answer[i-1];
		}
		
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<T; i++) {
			sb.append(answer[arr[i]-1]).append("\n");
		}
		System.out.print(sb);
		
	}

}
