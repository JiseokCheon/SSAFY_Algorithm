import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(in.readLine());
		StringTokenizer st;
		
		int[] sides = new int[6];
		int max1 = 0, max2 = 0;
		for (int i = 0; i < 6; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			st.nextToken();
			sides[i] = Integer.parseInt(st.nextToken());
			if (i % 2 == 0)
				max1 = sides[i] > max1 ? sides[i] : max1;
			else
				max2 = sides[i] > max2 ? sides[i] : max2;
		}

		int area = 0;
		for (int i = 0; i < 6; i++) {
			area += sides[i] * sides[(i + 1) % 6];
		}

		area = area - max1 * max2 * 2;
		System.out.println(K * area);

		in.close();

	}

}
