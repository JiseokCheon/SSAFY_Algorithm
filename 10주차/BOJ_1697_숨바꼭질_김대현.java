import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited;
	
	public static class su{
		int n;
		int time;
		public su(int n, int time) {
			this.n=n;
			this.time=time;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		visited=new boolean[200001];
		int n1=Integer.parseInt(st.nextToken());
		int n2=Integer.parseInt(st.nextToken());
		
		System.out.print(bfs(n1,n2,0));
	}

	private static int bfs(int n1, int n2, int cnt) {
		
		Queue<su> q= new LinkedList<su>();
		q.offer(new su(n1,0));
		visited[n1]=true;
		su now = null;
		while(!q.isEmpty()) {
			now=q.poll();
			
			if(now.n==n2) {
				break;
			}
			
			if(now.n<66667 && !visited[now.n*2]) {
				visited[now.n*2]=true;
				q.offer(new su(now.n*2,now.time+1));
			}
			
			if(now.n < 200000 &&!visited[now.n+1]) {
				visited[now.n+1]=true;
				q.offer(new su(now.n+1,now.time+1));
			}
			
			if(now.n>=1 && !visited[now.n-1]) {
				visited[now.n-1]=true;
				q.offer(new su(now.n-1,now.time+1));
			}
			
			
		}
		
		return now.time;
	}

}
