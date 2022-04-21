package april;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_1927_최소힙 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) { //우선순위 큐사용
				return o1-o2;
			}
		});
		
		for (int i = 0; i < N; i++) {
			int n =  Integer.parseInt(br.readLine());
			if(n==0) { //0인 경우
				if(pq.isEmpty()) bw.write(0+"\n"); //큐가 비어있으면 0 출력
				else bw.write(pq.poll()+"\n"); //큐의 원소중 최소값 출력
			}else { //0이 아닌 경우 que에 넣기
				pq.add(n);
			}
		}
		bw.flush();
		bw.close();
	}
}
