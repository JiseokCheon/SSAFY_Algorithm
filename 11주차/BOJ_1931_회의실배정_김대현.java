import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static class meeting implements Comparable<meeting>{
		int start;
		int end;
		
		public meeting(int start, int end) {
			this.start=start;
			this.end=end;
		}

		@Override
		public int compareTo(meeting o) {
			if(this.end==o.end) {
				return this.start-o.start;
			}
			return this.end-o.end;
		}
	}

	public static void main(String[] args) throws  IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int n=Integer.parseInt(br.readLine());
		int[][] arr=new int[n][2];
		PriorityQueue<meeting> pq=new PriorityQueue<meeting>();
		int max=0;
		for(int i=0; i<n; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			pq.offer(new meeting(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		
		int count=0;
		int ends=0;
		while(!pq.isEmpty()) {
			int start=pq.peek().start;
			int end=pq.poll().end;
			
			if(start >= ends) {
				count++;
				ends=end;
			}
		}
		
		
		
		System.out.print(count);
		

	}

}
