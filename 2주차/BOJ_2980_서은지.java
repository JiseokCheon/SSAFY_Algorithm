import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[][] tl = new int[N][3];
		int total = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			tl[i][0] = Integer.parseInt(st.nextToken());
			tl[i][1] = Integer.parseInt(st.nextToken());
			tl[i][2] = Integer.parseInt(st.nextToken());
			
			tl[i][0] += total;
			if (tl[i][0] % (tl[i][1] + tl[i][2]) < tl[i][1])
				total += tl[i][1] - (tl[i][0] % (tl[i][1] + tl[i][2]));
		}
		System.out.println(L+total);
	}

}
