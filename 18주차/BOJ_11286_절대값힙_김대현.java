import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	
	public static class heap implements Comparable<heap>{
		
		int num;
		
		public heap(int num){
			this.num=num;
		}
		@Override
		public int compareTo(heap o) {
			if(Math.abs(this.num)==Math.abs(o.num)) {
				return this.num-o.num;
			}
			return Math.abs(this.num)-Math.abs(o.num);
		}

		
		
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		
		PriorityQueue<heap> pq=new PriorityQueue<heap>();
		StringBuilder sb= new StringBuilder();
		for(int i=0; i<N; i++) {
			int next=Integer.parseInt(br.readLine());
			if(next==0) {
				if(pq.size()==0) {
					sb.append("0").append("\n");
				}else {
					sb.append(pq.poll().num).append("\n");
				}
			}else {
				pq.offer(new heap(next));
			}
		}
		System.out.print(sb);

	}

}
