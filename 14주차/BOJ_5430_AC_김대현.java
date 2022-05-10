package BJ_5430_AC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb=new StringBuilder();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(br.readLine());
		for(int tc=0; tc<t; tc++) {
			String str=br.readLine();
			int n=Integer.parseInt(br.readLine());
			Deque<Integer> deque=new ArrayDeque<>();
			StringTokenizer st=new StringTokenizer(br.readLine(),"[,]");
			for(int i=0;i<n;i++) {
				deque.add(Integer.parseInt(st.nextToken()));
			}
			boolean check=true;
			boolean front=true;
			for(int i=0; i<str.length();i++) {
				if(str.charAt(i)=='D') {
					if(deque.size()<=0) {
						check=false;
						sb.append("error").append("\n");
						break;
					}
					if(front) {
						deque.pollFirst();
					}else {
						deque.pollLast();
					}
					
				}else {
					if(front) {
						front=false;
					}else {
						front=true;
					}
				}
			}
			
			if(check) {
				sb.append("[");
				if(front) {
					while(deque.size()>0) {
						sb.append(deque.pollFirst());
						if(deque.size()>0) {
							sb.append(",");
						}
					}
				}else {
					while(deque.size()>0) {
						sb.append(deque.pollLast());
						if(deque.size()>0) {
							sb.append(",");
						}
					}
				}
				
				sb.append("]").append("\n");
			}
			
			
			
		}
		System.out.print(sb);
		

	}

}
