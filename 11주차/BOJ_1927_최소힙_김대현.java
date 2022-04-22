package BJ_1927_최소힙;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws  IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		int n=Integer.parseInt(br.readLine());
		int temp;
		PriorityQueue<Integer> pq=new PriorityQueue<Integer>();
		for(int i=0; i<n; i++) {
			temp=Integer.parseInt(br.readLine());
			if(temp==0) {
				if(pq.isEmpty()) {
					sb.append(0).append("\n");
				}else {
					sb.append(pq.poll()).append("\n");
				}
			}else {
				pq.offer(temp);
			}
		}
		System.out.println(sb);
		
		

	}

}
