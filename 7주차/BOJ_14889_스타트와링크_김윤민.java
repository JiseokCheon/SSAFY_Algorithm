package study_mar;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_14889_스타트와링크 {
	static int[][] S;
	static int min;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		
		S = new int[N][N]; //능력치 저장
		min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//조합 시작
		Combi(0,0, N, new int[N/2]);
		System.out.println(min);
	}
	private static void Combi(int cnt, int start, int N, int[] select) {
		if(cnt == N/2) { //절반 뽑기
			boolean[] visit = new boolean[N];
			int sumS = 0;
			for (int i = 0; i < N/2; i++) {
				visit[select[i]] =true; //스타트팀에 선택되었으면 true로
				for (int j = 0; j < N/2; j++) {
					sumS += S[select[i]][select[j]]; //스타트팀의 능력치 더해주기
				}
			}
			int idx = 0;
			int[] link = new int[N/2];
			for (int i = 0; i < N; i++) {
				if(!visit[i]) link[idx++] = i; //스타트팀이 아닌 인덱스를 골라서 링크팀에
			}
			int sumL = 0; 
			for (int i = 0; i < N/2; i++) {
				for (int j = 0; j < N/2; j++) {
					sumL += S[link[i]][link[j]]; //링크팀 능력치 더해주기
				}
			}
			min = Math.min(min, Math.abs(sumS-sumL));
			return;
		}
		
		for (int i = start; i < N; i++) {
			select[cnt] = i;
			Combi(cnt+1, i+1, N, select);
		}
	}

}
