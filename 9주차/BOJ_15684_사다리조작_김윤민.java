package April;
import java.io.*;
import java.util.*;
public class BJ_15684_사다리조작 {
	static int[][] map;
	static int N, M, H, min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H+1][N+1];
		for (int i = 0; i < M; i++) {
			st= new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1; //사다리는 1로 
		}
		min = -1;
		if(check()) min = 0; //추가사다리 없는 경우 체크
		
		//사다리 가로선 놓기 1개부터 3개까지
		for (int i = 1; i <= 3; i++) {
			if(min != -1) break;
			sadari(0, i);
		}
		System.out.println(min);
	}
	private static void sadari(int cnt, int k) { //현재 놓은 사다리 수, 총 놓을 사다리 수
		if(cnt == k) { //사다리 다 놓았음
			if(!check()) return; //i번세로선의 결과가 i번인지 확인
			min = k;
			return;
		}
		for (int i = 1; i < H+1; i++) {
			for (int j = 1; j < N; j++) {
				if(map[i][j]==1) continue; //이미 사다리 있으면 
				if(j-1>=1 && map[i][j-1]==1 || j+1 < N && map[i][j+1]==1 ) continue; //양옆에 사다리가 존재하면
					map[i][j] = 1;
					sadari(cnt+1, k); 
					map[i][j] = 0;
				
			}
		}
	}
	private static boolean check() { //사다리타기 하기
		for (int j = 1; j <= N; j++) {
			int now = j;
			for (int i = 1; i <= H; i++) {
				if(now-1>=1 && map[i][now-1]==1) { //왼쪽에 사다리가 있으면 열을 -1 하기
					now = now-1;
				}else if(map[i][now]==1) { //오른쪽에 사다리가 있으면 열+1
					now = now+1;
				}
			}
			if(now != j) return false; //처음 시작 열과 마지막 끝 열이 다르면 false반환
		}
		return true;
	}
}
