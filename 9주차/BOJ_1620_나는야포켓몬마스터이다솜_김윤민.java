import java.io.*;
import java.util.*;

public class BJ_1620_나는야포켓몬마스터이다솜 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);

		//Map 사용
		Map<String, Integer> map = new HashMap<String, Integer>();
		String[] dogam = new String[N+1];
		for (int i = 1; i <= N; i++) {
			String s  = br.readLine();
			map.put(s, i);
			dogam[i] = s;
		}
		for (int i = 0; i < M; i++) {
			String s = br.readLine();
			if(s.charAt(0)>='A' &&s.charAt(0)<='Z') {
				sb.append(map.get(s)+"\n");
			}else {
				int idx = Integer.parseInt(s);
				sb.append(dogam[idx]+"\n");
			}
		}
		System.out.println(sb);
	}
}
