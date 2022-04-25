import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static class num implements Comparable<num>{
		int num;
		int count;
		
		public num(int num, int count) {
			this.num=num;
			this.count=count;
		}

		@Override
		public int compareTo(num o) {
			if(this.count==o.count) {
				return this.num-o.num;
			}
			return this.count-o.count;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int r=Integer.parseInt(st.nextToken())-1;
		int c=Integer.parseInt(st.nextToken())-1;
		int k=Integer.parseInt(st.nextToken());
		
		int[][] arr=new int[100][100];
		int[][] arr2=new int[100][100];
		int nowr=3,nowc=3;
		for(int i=0; i<3; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		int answer=-1;
		PriorityQueue<num> pq=new PriorityQueue<num>();
		for(int time=0; time<=100; time++) {
			if(time%2==0) { // 홀수번째 일 때
				
				if(arr[r][c]==k) {
					answer=time;
					break;
				}
				arr2=new int[100][100];
				if(nowr >= nowc) {// 행이 열보다 크거나 같을 때
					for(int i=0; i< nowr; i++) {
						int[] count =new int[101];
						for(int j=0; j<nowc;j++) {
							
							count[arr[i][j]]++;
						}

						pq.clear();
						for(int j=1; j<101; j++) {
							if(count[j]!=0) {
								pq.offer(new num(j,count[j]));
							}
						}
						
						int n=0;
						while(n<100 && !pq.isEmpty()) {
							arr2[i][n++]=pq.peek().num;
							arr2[i][n++]=pq.poll().count;
						}
						if(nowc <n) {
							nowc=n;
						}
						
					}
					
				}else { // 열이 행보다 클 때
					for(int i=0; i< nowc; i++) {
						int[] count =new int[101];
						for(int j=0; j<nowr;j++) {
							
							count[arr[j][i]]++;
						}

						pq.clear();
						for(int j=1; j<101; j++) {
							if(count[j]!=0) {
								pq.offer(new num(j,count[j]));
							}
						}
						
						int n=0;
						while(n<100 && !pq.isEmpty()) {
							arr2[n++][i]=pq.peek().num;
							arr2[n++][i]=pq.poll().count;
						}
						if(nowr <n) {
							nowr=n;
						}
						
					}
				}
			
			
			}else { // 짝수 번째 일 때
				if(arr2[r][c]==k) {
					answer=time;
					break;
				}
				arr=new int[100][100];
				if(nowr >= nowc) {// 행이 열보다 크거나 같을 때
					for(int i=0; i< nowr; i++) {
						int[] count =new int[101];
						for(int j=0; j<nowc;j++) {
							
							count[arr2[i][j]]++;
						}

						pq.clear();
						for(int j=1; j<101; j++) {
							if(count[j]!=0) {
								pq.offer(new num(j,count[j]));
							}
						}
						
						int n=0;
						while(n<100 && !pq.isEmpty()) {
							arr[i][n++]=pq.peek().num;
							arr[i][n++]=pq.poll().count;
						}
						if(nowc <n) {
							nowc=n;
						}
						
					}
					
				}else { // 열이 행보다 클 때
					for(int i=0; i< nowc; i++) {
						int[] count =new int[101];
						for(int j=0; j<nowr;j++) {
							
							count[arr2[j][i]]++;
						}

						pq.clear();
						for(int j=1; j<101; j++) {
							if(count[j]!=0) {
								pq.offer(new num(j,count[j]));
							}
						}
						
						int n=0;
						while(n<100 && !pq.isEmpty()) {
							arr[n++][i]=pq.peek().num;
							arr[n++][i]=pq.poll().count;
						}
						if(nowr <n) {
							nowr=n;
						}
						
					}
				}

			}
			
		}
		System.out.print(answer);

	}

}
