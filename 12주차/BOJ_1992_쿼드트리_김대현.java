import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String str;
		int[][] arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			str = br.readLine();
			for (int j = 0; j < n; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}

		getString(arr, 0, 0, n);
		System.out.print(sb);

	}

	private static void getString(int[][] arr, int r, int c, int n) {

		
		
		int check = 0;
		int temp = arr[r][c];
		loop: for (int i = r; i < r + n; i++) {
			for (int j = c; j < c + n; j++) {
				if (arr[i][j] == temp) {
					check++;
				} else {
					sb.append("(");
					getString(arr, r, c, n / 2);
					getString(arr, r, c+n / 2, n / 2);
					getString(arr, r+ n / 2, c, n / 2);
					getString(arr, r+n / 2, c+ n / 2, n / 2);
					sb.append(")");
					break loop;
				}
			}
		}

		if (check == n * n) {
			sb.append(temp);
		}

	}

}
