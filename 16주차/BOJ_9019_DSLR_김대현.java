package BJ_9019_DSLR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static class dslr{
		int n;
		String answer;
		
		public dslr(int n, String answer) {
			this.n=n;
			this.answer=answer;
		}
	}

	public static void main(String[] args) throws  IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		StringBuilder sbs=new StringBuilder("dd");
		for(int tc=0; tc<t; tc++) {
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			int n1=Integer.parseInt(st.nextToken());
			int n2=Integer.parseInt(st.nextToken());
			boolean[] check=new boolean[10000];
			Queue<dslr> q= new LinkedList<dslr>();
			check[n1]=true;
			q.offer(new dslr(n1,""));
			int temp,ntemp;
			String temps;
			while(q.peek().n !=n2) {
				temp=q.peek().n;
				temps=q.poll().answer;
				ntemp=D(temp);
				if(!check[ntemp]) {
					check[ntemp]=true;
					q.offer(new dslr(ntemp,new StringBuilder(temps).append("D").toString()));
				}
				ntemp=S(temp);
				if(!check[ntemp]) {
					check[ntemp]=true;
					q.offer(new dslr(ntemp,new StringBuilder(temps).append("S").toString()));
				}
				ntemp=L(temp);
				if(!check[ntemp]) {
					check[ntemp]=true;
					q.offer(new dslr(ntemp,new StringBuilder(temps).append("L").toString()));
				}
				ntemp=R(temp);
				if(!check[ntemp]) {
					check[ntemp]=true;
					q.offer(new dslr(ntemp,new StringBuilder(temps).append("R").toString()));
				}
				
			}
			sb.append(q.peek().answer).append("\n");
			
		}
		
		System.out.print(sb);
	}

	public static int D(int n) {

		n *= 2;
		if (n > 9999) {
			n = n - 10000;
		}
		return n;
	}

	public static int S(int n) {
		n=n-1;
		if(n==-1) {
			n=9999;
		}
		return n;
	}
	
	public static int L(int n) {
		int temp=n/1000;
		n=n-temp*1000;
		n=n*10+temp;
		return n;
	}
	
	public static int R(int n) {
		int temp=n%10;
		n=n/10;
		n=n+temp*1000;
		return n;
	}

}
