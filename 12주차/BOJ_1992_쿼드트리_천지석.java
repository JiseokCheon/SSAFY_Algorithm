import java.io.*;
import java.util.*;

public class Main {

	static int[][] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}
		if (check(0, 0, N, N) == N * N)
			sb.append(1);
		else if (check(0, 0, N, N) == 0)
			sb.append(0);
		else
			QT(0, 0, N);

		System.out.println(sb.toString());
	}

	static void QT(int x, int y, int N) {
		if (N == 1)
			return;

		sb.append("(");

		int[][] arr = { { x, y }, { x, y + N / 2 }, { x + N / 2, y }, { x + N / 2, y + N / 2 } };

		for (int i = 0; i < 4; i++) {
			int count = check(arr[i][0], arr[i][1], arr[i][0] + N / 2, arr[i][1] + N / 2);
			if (count == 0) {
				sb.append("0");
			} else if (count == N * N / 4) {
				sb.append("1");
			} else {
				QT(arr[i][0], arr[i][1], N / 2);
			}
		}

		sb.append(")");
	}

	static int check(int x1, int y1, int x2, int y2) {
		int sum = 0;
		for (int i = x1; i < x2; i++) {
			for (int j = y1; j < y2; j++) {
				sum += arr[i][j];
			}
		}
		return sum;
	}
}
