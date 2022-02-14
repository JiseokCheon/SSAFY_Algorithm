import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(in.readLine());
			int pigs[] = new int[6];
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int sum = 0;
			for (int i = 0; i < 6; i++) {
				pigs[i] = Integer.parseInt(st.nextToken());
				sum += pigs[i];
			}
			
			int cnt = 1;
			while (sum <= N) {
				sum *= 4;
				cnt++;
				if (sum > N)
					break;
			}
			System.out.println(cnt);
		}

	}

}
