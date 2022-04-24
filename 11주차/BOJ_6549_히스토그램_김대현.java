import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while (true) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			if (n == 0) {
				break;
			}
			int[] arr = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			long max = 0l;

			Stack<Integer> stack = new Stack<Integer>();

			for (int i = 0; i < n; i++) {
				while ((!stack.isEmpty()) && arr[stack.peek()] > arr[i]) {
					int h = arr[stack.pop()];
					long w = stack.isEmpty() ? i : i - 1 - stack.peek();
					max = Math.max(max, h * w);
				}

				stack.push(i);

			}

			while (!stack.isEmpty()) {
				int h = arr[stack.pop()];

				long w = stack.isEmpty() ? n : n - 1 - stack.peek();
				max = Math.max(max, w * h);

			}

			sb.append(max).append("\n");

		}
		System.out.print(sb);

	}

}
