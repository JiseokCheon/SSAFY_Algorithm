package BJ_6064_카잉달력;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int max = m * n;

			int ans = -1;
			int a = 0;

			// am+x = bn+y
			if (y == n) {
				y = 0;
			}

			while ((m * a + x) <= max) {
				if ((m * a + x) % n == y) {
					ans = (m * a + x);
					break;
				}
				a++;
			}
			sb.append(ans).append("\n");

		}
		System.out.print(sb);

	}

}
