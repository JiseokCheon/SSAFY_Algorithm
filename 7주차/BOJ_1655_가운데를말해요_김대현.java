package BJ_1655_가운데를말해요;

import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {

	public static class up implements Comparable<up> {
		int n;

		public up(int n) {
			this.n = n;
		}

		@Override
		public int compareTo(up o) {
			return this.n - o.n;
		}
	}

	public static class down implements Comparable<down> {
		int n;

		public down(int n) {
			this.n = n;
		}

		@Override
		public int compareTo(down o) {
			return o.n - this.n;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		PriorityQueue<up> up = new PriorityQueue<up>();
		PriorityQueue<down> down = new PriorityQueue<down>();
		int temp;
		for (int i = 0; i < n; i++) {
			if (i % 2 == 0) {
				down.offer(new down(Integer.parseInt(br.readLine())));
			} else {
				up.offer(new up(Integer.parseInt(br.readLine())));

			}

			if (!up.isEmpty() && !down.isEmpty() && up.peek().n < down.peek().n) {
				temp = up.poll().n;
				up.offer(new up(down.poll().n));
				down.offer(new down(temp));
			}

//			bw.write(down.peek().n+"\n");
			sb.append(down.peek().n).append("\n");

		}
//
//		bw.flush();
//		bw.close();
		System.out.println(sb);

	}

}
