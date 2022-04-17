package BJ_1764_듣보잡;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		Map<String,Integer> map=new HashMap<String,Integer>();
		int count=0;
		for(int i=0; i<n; i++) {
			map.put(br.readLine(), count++);
		}
		int answer=0;
		Queue<String> q=new LinkedList<String>();
		for(int i=0; i<m; i++) {
			String str=br.readLine();
			if(map.get(str)!=null) {
				answer++;
				q.offer(str);
			}
		}
		
		String[] arr=new String[q.size()];
		int cnt=0;
		while(!q.isEmpty()) {
			arr[cnt++]=q.poll();
		}
		
		Arrays.sort(arr);
		System.out.println(answer);
		for(int i=0; i<arr.length;i++) {
			System.out.println(arr[i]);
		}
	}

}
