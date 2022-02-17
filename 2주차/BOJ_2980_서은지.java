import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int total = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int D = Integer.parseInt(st.nextToken()) + total;
			int R = Integer.parseInt(st.nextToken());
			int G = Integer.parseInt(st.nextToken());
			
			if (D % (R + G) < R)
				total += (R - D % (R + G));
		}
		System.out.println(L + total);
	}

}
