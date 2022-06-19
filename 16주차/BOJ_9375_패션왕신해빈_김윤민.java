package July;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class BOJ_9375_패션왕신해빈 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			Map<String, Integer> map = new HashMap<>();
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < N; i++) {
				String[] str= br.readLine().split(" ");
				if(map.containsKey(str[1])) { //map에 해당 종류가 이미 있으면 개수만 수정
					int cnt = map.get(str[1]);
					map.replace(str[1], cnt+1);
				}else { //없으면 map새롭게 추가해주기
					map.put(str[1], 1);
					list.add(str[1]);
				}
			}
			
			//가능한 조합 수 구하기 
			int ans=1;
			Iterator<Entry<String, Integer>> es = map.entrySet().iterator();
			while(es.hasNext()) {
				Map.Entry<String, Integer> e = es.next();
				ans*= e.getValue()+1; //안입은 경우도 포함하기 위해서 1 더하기
			}
			
			System.out.println(ans-1); //모두 안입은 경우 1빼서 출력
		}
	}
}
