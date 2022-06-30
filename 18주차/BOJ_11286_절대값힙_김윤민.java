import java.io.*;
import java.util.*;

public class BOJ_11286_절대값힙 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if(Math.abs(o1)==Math.abs(o2)) {
					return o1-o2;
				}
				return Math.abs(o1)-Math.abs(o2);
			}
		});
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			if(x != 0) {
				pq.offer(x);
			}else {
				if(pq.isEmpty())System.out.println(0);
				else System.out.println(pq.poll());
			}
		}
	}
}
