package BJ_9095_123더하기;

import java.io.*;

public class Main {

	static int n, answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < t; tc++) {
			n = Integer.parseInt(br.readLine());
			answer = 0;
			get(0);
			sb.append(answer).append("\n");

		}

		System.out.print(sb);

	}

	private static void get(int now) {

		if (now == n) {
			answer++;
		} else {
			get(now + 1);
			if (now < n - 1) {
				get(now + 2);
			}
			if (now < n - 2) {
				get(now + 3);
			}
		}

	}

}
