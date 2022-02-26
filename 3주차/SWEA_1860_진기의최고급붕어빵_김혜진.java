package algorithm_SWEA;

import java.io.*;
import java.util.*;

public class SWEA_1860_진기의최고급붕어빵_김혜진 {
	static int N,M,K;
	static StringBuilder sb = new StringBuilder();
	static int[] customer;
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input_D3_1860_진기의최고급붕어빵.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());

		for(int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 손님 수
			M = Integer.parseInt(st.nextToken()); // M초의 시간동안
			K = Integer.parseInt(st.nextToken()); // 만드는 붕어빵의 개수
			
			customer = new int[N]; // 손님 도착 시간
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				customer[i] = Integer.parseInt(st.nextToken());
			}
			
			// 시간 순서 오름차순 정렬 - 먼저 온 사람한테 나눠줘야함
			Arrays.sort(customer);
			
			boolean checked = true;
			int total = 0;
			for(int i=0; i<N; i++) {
				// 손님 도착시간 후 진기가 만든 붕어빵의 개수
				total = (customer[i]/M) * K;
				// 총 만든 붕어빵의 개수가 도착 손님 수보다 적으면 붕어빵 만들 수 없음
				if(total < i+1) checked = false;
			}
			
			if(checked) {
				sb.append("Possible").append("\n");
			}else {
				sb.append("Impossible").append("\n");
			}
		}
		System.out.println(sb);
	}

}
