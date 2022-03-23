package BJ_14501_퇴사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] days = new int[n + 2];
		int[] pay = new int[n + 2];

		for (int i = 1; i < n + 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			days[i] = Integer.parseInt(st.nextToken());
			pay[i] = Integer.parseInt(st.nextToken());
		}
		int maxs[] = new int[n + 2];
		int max = 0;
		int nextI;
		for (int i = 1; i <= n + 1; i++) {
			max = Math.max(max, maxs[i]);

			nextI = days[i] + i;
			if (nextI <= n + 1) {
				maxs[nextI] = Math.max(maxs[nextI], max + pay[i]);

			}
		}

		System.out.print(maxs[n + 1]);

	}

}

