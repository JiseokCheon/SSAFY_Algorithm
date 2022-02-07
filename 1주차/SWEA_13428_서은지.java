import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			char[] num = in.readLine().toCharArray();
			
			// 1. 전체를 하나의 수로 보면서 최대최소 구하기
			int min = Integer.parseInt(String.valueOf(num));
			int max = Integer.parseInt(String.valueOf(num));
			
			for (int i = 0; i < num.length; i++) {
				for (int j = i; j < num.length; j++) {
					swap(num, i, j);
					if (num[0] != '0') {
						min = Math.min(min, Integer.parseInt(String.valueOf(num)));
						max = Math.max(max, Integer.parseInt(String.valueOf(num)));
					}
					swap(num, i, j);
				}
			}
			
			System.out.println("#" + tc + " " + min + " " + max);
//			System.out.println("#" + tc + " " + getResult(num, 0, "min") + " " + getResult(num, 0, "max"));
		}
		
	}
	
	// 2. 배열 요소에서 최대최소값 인덱스 찾아 스왑
	public static int getResult(char[] arr, int idx, String str) {
		int max = 0, min = 9;
		int maxIdx = idx, minIdx = idx;
		int result = 0;
		
		switch(str) {
		case "min":
			for (int i = idx; i < arr.length; i++) {
				if (idx == 0)
					if (arr[i] == '0')
						continue;
				if ((arr[i] - '0') <= min) {
					min = arr[i] - '0';
					minIdx = i;
				}
			}
			
			if (min == (arr[idx]-'0') && idx+1 < arr.length) {
				return getResult(arr, idx+1, str);
			}
			swap(arr, idx, minIdx);
			result = Integer.parseInt(String.valueOf(arr));
			swap(arr, idx, minIdx);
			break;
			
		case "max":
			for (int i = idx; i < arr.length; i++) {
				if ((arr[i] - '0') >= max) {
					max = arr[i] - '0';
					maxIdx = i;
				}
			}
			if (max == (arr[idx] - '0') && idx+1 < arr.length) {
				return getResult(arr, idx+1, str);
			}
			swap(arr, idx, maxIdx);
			result = Integer.parseInt(String.valueOf(arr));
			swap(arr, idx, maxIdx);
			break;
		}
		return result;
	}
	
	public static void swap(char[] arr, int a, int b) {
		char tmp;
		tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

}
