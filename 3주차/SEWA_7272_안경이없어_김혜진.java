package algorithm_SWEA;

import java.io.*;
import java.util.*;

public class _7272_안경이없어_김혜진 {
	static String src = "5\r\n" + "ABCD EFGH\r\n" + "ABCD PBZO\r\n" + "PRQO OQAD\r\n" + "ZXCV HJKL\r\n" + "BBBB BBB";
//	#1 DIFF
//	#2 SAME
//	#3 SAME
//	#4 SAME
//	#5 DIFF
	static String str1, str2;
	static String ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");

			StringTokenizer st = new StringTokenizer(br.readLine());
			str1 = st.nextToken();
			str2 = st.nextToken();
			// int형 배열 생성(0: 구멍x 1: 구멍1개 2: 구멍2개)
			int[] arr1 = new int[str1.length()];
			int[] arr2 = new int[str2.length()];

//			// str1과 str2의 길이가 같지 않다면 다른 문자열
//			if (str1.length() != str2.length()) {
//				sb.append("DIFF").append("\n");
//			}else {
//				
//			}
			
			// str1의 길이만큼 비교
			for(int i=0; i<arr1.length; i++) {
				// 구멍이 한개일 때
				if(str1.charAt(i)=='A'||str1.charAt(i)=='D'||str1.charAt(i)=='O'||str1.charAt(i)=='P'||str1.charAt(i)=='Q'||str1.charAt(i)=='R') { arr1[i] = 1; }
				// 구멍 두개
				else if(str1.charAt(i)=='B') { arr1[i] = 2; }
				// 나머지
				else { arr1[i] = 0; } 
				
			}
			
			for(int i=0; i<arr2.length; i++) {

				// 구멍이 한개일 때
				if(str2.charAt(i)=='A'||str2.charAt(i)=='D'||str2.charAt(i)=='O'||str2.charAt(i)=='P'||str2.charAt(i)=='Q'||str2.charAt(i)=='R') { arr2[i] = 1; }
				// 구멍 두개
				else if(str2.charAt(i)=='B') { arr2[i] = 2; }
				// 나머지
				else { arr2[i] = 0; } 
				
			}
			
			// str1과 str2의 길이가 다르면 DIFF
			if(arr1.length!=arr2.length) {
				sb.append("DIFF").append("\n");
			}else {
				// 문자개수를 담은 배열이 일치하면 SAME 아니면 DIFF
				if(Arrays.equals(arr1, arr2)) sb.append("SAME").append("\n");
				else sb.append("DIFF").append("\n");
			}
		}
		
		System.out.println(sb);
	}

}
