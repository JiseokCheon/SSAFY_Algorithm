package BJ_9375_패션왕신해빈;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Main {

	static BufferedReader br;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		int T = Integer.parseInt(br.readLine());// 테스트 케이스 수

		for (int tc = 0; tc < T; tc++) {
			int n=Integer.parseInt(br.readLine());
			Map<String, Integer> p = new HashMap<String, Integer>();
			for (int i = 0; i < n; i++) {
				String key = br.readLine().split(" ")[1];
				if (p.containsKey(key)) {
					p.put(key, p.get(key) + 1);
				} else {
					p.put(key, 1);
				}
			}
			Collection<Integer> c = p.values();
			if(c.size() ==0) {
				sb.append(0).append("\n");
			}else if(c.size()==1) {
				sb.append(n).append("\n");
			}else {
				int answer = 1;
				for (int i : c) {
					answer*=(i+1);
				}
				sb.append(answer-1).append("\n");
			}

			
			
		}
		System.out.println(sb);
	}


}
