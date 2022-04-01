package BJ_1107_리모컨;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static class mola{
		int num;
		int t;
		int time;
		boolean check;
		
		public mola(int num, int t,boolean check, int time) {
			this.num=num;
			this.t=t;
			this.check=check;
			this.time=time;
		}
		
	}

	public static void main(String[] args) throws  IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int answer=Integer.parseInt(br.readLine());
		int k=Integer.parseInt(br.readLine());
		boolean[] use=new boolean[10];
		Arrays.fill(use, true);
		if(k!=0) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			for(int i=0; i<k; i++) {
				use[Integer.parseInt(st.nextToken())]=false;
			}
			
		}
		
		int min=500000;
		Queue<mola> q=new LinkedList<mola>();
		q.offer(new mola(100,0,false,0));
		int num,t,time;
		boolean check;
		while(!q.isEmpty()) {
			num=q.peek().num;
			t=q.peek().t;
			time=q.peek().time;
			check=q.peek().check;
			
			if(min > Math.abs(num-answer) +time) {
				min=Math.abs(num-answer)+time;
				
			}
			
			if(!check) {
				for(int i=0;i<10;i++) {
					if(use[i]) {
						if(t==0) {
							q.offer(new mola(i,1,false,time+1));
						}else if(t==5) {
							q.offer(new mola(num*10+i,t+1,true,time+1));
						}else {
							q.offer(new mola(num*10+i,t+1,false,time+1));
						}
					
					}
				}
			}
			q.poll();
			
			
			
			
		}
		System.out.print(min);
		
		
	}
	

}
