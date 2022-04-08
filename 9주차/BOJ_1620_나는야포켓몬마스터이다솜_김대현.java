package BJ_1620_나는야포켓몬마스터이다솜;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		Map<String,Integer> map1=new HashMap<String,Integer>();
		Map<Integer,String> map2=new HashMap<Integer,String>();
		String str;
		for(int i=1; i<=n;i++) {
			str=br.readLine();
			map1.put(str, i);
			map2.put(i, str);
		}
		
		StringBuilder sb=new StringBuilder();
		int temp;
		for(int i=0; i<m;i++) {
			str=br.readLine();
			try {
				temp= Integer.parseInt(str);
				sb.append(map2.get(temp)).append("\n");
			}catch(Exception e) {
				sb.append(map1.get(str)).append("\n");
			}
		}
		
		
		System.out.print(sb);
	}

}
