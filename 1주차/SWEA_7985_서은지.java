import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, idx;
	static int[] nodes;
	static int[] original;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			int K = Integer.parseInt(in.readLine());
			st = new StringTokenizer(in.readLine(), " ");
			N = (int)Math.pow(2, K);
			nodes = new int[N];
			original = new int[N];
			// 중위순회 결과(입력) 배열
			for (int i = 1; i < N; i++) {
				nodes[i] = Integer.parseInt(st.nextToken());
			}
			
			// 1부터 중위순회 시작(배열 인덱스가 1부터)
			idx = 1;
			dfs(1);

			System.out.print("#" + tc + " ");
			idx = 1;
			// 레벨 별 정점 번호 출력(레벨 별 정점 수 : 2^i)
			for (int i = 0; i < K; i++) {
				for (int j = 0; j < (int)Math.pow(2, i); j++) {
					System.out.print(original[idx++] + " ");
				}
				System.out.println();
			}

		}
		
	}
	
	// 루트에서 중위순회하면서 중위순회 결과값 삽입
	public static void dfs(int current) {
		if (current * 2 < N)
			dfs(current * 2);
		
		original[current] = nodes[idx++];
		
		if (current * 2 + 1 < N)
			dfs(current * 2 + 1);
	}

}
