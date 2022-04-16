import java.io.*;
import java.util.*;

public class BOJ_1764_듣보잡 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		Map<String, String> map = new HashMap<>(); //듣도 못한 사람 저장
		for (int i = 0; i < N; i++) {
			String s= br.readLine();
			map.put(s,s);
		}
		ArrayList<String> arr = new ArrayList<>(); //듣도 보도 못한 사람 저장할 리스트
		for (int i = 0; i < M; i++) {
			String s= br.readLine();
			if(map.containsKey(s)) arr.add(s); //듣도 못한 사람에 이미 존재하면 list에 추가
		}
		Collections.sort(arr); //정렬
		sb.append(arr.size()+"\n");
		for (String s : arr) {
			sb.append(s+"\n");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb);
	}
}
