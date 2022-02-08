import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int T = in.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = in.nextInt();
			System.out.println("#" + tc);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= i; j++)
					System.out.print(comb(i, j) +  " ");
				System.out.println();
			}
		}
		in.close();
	}
	
	public static int comb(int n, int r) {
		if (n == r || r == 0)
			return 1;
		else {
			return comb(n-1, r-1) + comb(n-1, r);
		}
	}

}
