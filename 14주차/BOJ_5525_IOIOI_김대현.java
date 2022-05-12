import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		char[] arr = br.readLine().toCharArray();
		int[] in = new int[arr.length];
		for (int i = 0; i < arr.length - 2 * n - 1; i++) {
			if (arr[i] == 'O') {
				continue;
			}

			int cnt = 0;
			for (int j = i; j < arr.length; j++) {
				if ((j - i) % 2 == 0) {
					if (arr[j] == 'I') {
						cnt++;
					} else {
						break;
					}
				} else {
					if (arr[j] == 'O') {
						cnt++;
					} else {
						break;
					}
				}
			}
			cnt=(cnt-1)/2;
			
			for(int j=cnt; j>0;j--) {
				in[i]=j;
				i=i+2;
			}
			

		}
		int answer=0;
		for(int i=0; i< in.length;i++) {
			if(in[i]>=n) {
				answer++;
			}
		}
		System.out.print(answer);

	}

}
