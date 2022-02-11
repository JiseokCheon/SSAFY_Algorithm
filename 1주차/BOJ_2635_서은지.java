import java.io.*;

public class Main {

	static int max;
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		int before, after, diff, cnt;
		StringBuilder sb = new StringBuilder();
		String str;
		for (int i = N; i > N/2; i--) {
			before = N;
			after = i;
			str = String.valueOf(before) + " " + String.valueOf(after) + " ";
			cnt = 2;
			
			while (before - after >= 0) {
				diff = before - after;
				str += String.valueOf(diff) + " ";
				before = after;
				after = diff;
				cnt++;
			}
      
			if (cnt > max) {
				max = cnt;
				sb.setLength(0);
				sb.append(String.valueOf(max) + "\n");
				sb.append(str);
			}
		}
		
		System.out.println(sb);

	}
}
