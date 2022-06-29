import java.io.*;
import java.util.*;

public class BOJ_23289_온풍기안녕 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken()); //온도 기준
		
		List<int[]> list = new ArrayList<>(); //온풍기들 저장
		List<int[]> research = new ArrayList<>(); //조사할 칸 저장
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				int n =Integer.parseInt(st.nextToken());
				if(n==5) { //조사할 칸 저장
					research.add(new int[] {i,j});
				}else if(n>0) { //온풍기 저장
					list.add(new int[] {i,j,n-1});
				}
				
			}
		}
		int W = Integer.parseInt(br.readLine());
		boolean[][][][] wall = new boolean[R][C][R][C];
		for (int k = 0; k < W; k++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1; //x
			int y = Integer.parseInt(st.nextToken())-1; //y
			if(Integer.parseInt(st.nextToken()) == 0 ) {//t
				wall[x][y][x-1][y] = true; //벽 방향에 맞춰서 있으면 true 
				wall[x-1][y][x][y] = true;
			} else {
				wall[x][y][x][y+1] = true;
				wall[x][y+1][x][y] = true;
			}
		}
		int[][] map = new int[R][C];
		//온풍기 틀기
		int [][] move= {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}}; //8방
		int ans = 0;
		while(ans <= 100 ) {
			for (int i = 0; i < list.size(); i++) { //온풍기 수만큼 실행
				int[] t = list.get(i);
				int dir = t[2]; //온풍기 방향
				int idx = 0;
				//온풍기 방향에 맞춰서 시작 인덱스 설정
				if(dir==0) idx = 0;
				else if(dir==1) idx = 4;
				else if(dir==2) idx = 6;
				else if(dir==3) idx = 2;
				
				Queue<int[]> que = new LinkedList<>();
				boolean[][] visit =new boolean[R][C];
				int ni = t[0]+move[(idx+2)%8][0], nj = t[1]+move[(idx+2)%8][1]; //해당 방향쪽으로 한칸 
				que.offer(new int[]{ni,nj, 5}); //온도5 부터 넣어주기
				visit[ni][nj]= true;//방문체크
				
				while(!que.isEmpty()) {
					int[] temp = que.poll();
					int x = temp[0], y = temp[1]; //현재 좌쇼
					map[x][y] += temp[2]; //온도 추가
					if(temp[2]==0) break; //현재 온도가 0이면 break;
					
					//왼쪽 대각선 방향 확인
					int nx = x+move[idx][0], ny = y+move[idx][1] ,mx = x+move[(idx+1)%8][0], my = y+move[(idx+1)%8][1];
					if(nx>=0 &&ny>=0 &&mx>=0 &&my>=0 && nx<R &&ny<C &&mx<R &&my<C //범위 체크
							&& !wall[x][y][nx][ny]  //벽이 뚤려 있는지
							&& !wall[nx][ny][mx][my] //벽이 뚤려 있는지
									&& !visit[mx][my]) {
						que.offer(new int[] {mx,my, temp[2]-1});
						visit[mx][my] =true;
					}
					//직선 방향 확인
					nx = x+move[(idx+2)%8][0]; ny = y+move[(idx+2)%8][1];
					if(nx>=0 &&ny>=0  && nx<R &&ny<C 
							&& !wall[x][y][nx][ny] 
							&&!visit[nx][ny])  {
						que.offer(new int[] {nx, ny, temp[2]-1});
						visit[nx][ny] =true;
					}
					//오른쪽 대각선 방향 확인
					nx = x+move[(idx+4)%8][0]; ny = y+move[(idx+4)%8][1];
					mx = x+move[(idx+3)%8][0]; my = y+move[(idx+3)%8][1];
					if(nx>=0 &&ny>=0 &&mx>=0 &&my>=0 && nx<R &&ny<C &&mx<R &&my<C &&
							! wall[x][y][nx][ny] 
							&& !wall[nx][ny][mx][my]
									&& !visit[mx][my]) {
						que.offer(new int[] {mx,my, temp[2]-1});
						visit[mx][my] =true;
					}
				}
			}
			//온도 조절하기
			int [][] four= {{1,0},{-1,0},{0,1},{0,-1}};
			int[][] copy = new int[R][C];
			for (int i = 0; i < R ; i++) {
				copy[i] = Arrays.copyOf(map[i], C);
			}
			boolean[][] visit = new boolean[R][C];
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(copy[i][j] != 0) {
						visit[i][j] = true; //방문 체크
						for (int k = 0; k < 4; k++) {
							int ni = i+four[k][0];
							int nj = j+four[k][1];
							if(ni>=0 && ni <R && nj >=0 && nj<C
									&& !wall[i][j][ni][nj] //벽이 있는지 확인
											&& !visit[ni][nj]) {
								int gap = ((int)Math.abs(copy[ni][nj]-copy[i][j]))/4; //차이계산
								if(copy[i][j] < copy[ni][nj]) {
									map[i][j] += gap;
									map[ni][nj] -= gap;
								}else {
									map[i][j] -= gap;
									map[ni][nj] += gap;
								}
								if(map[i][j]<0) map[i][j] = 0;
								if(map[ni][nj]<0) map[ni][nj] = 0;
							}
						}
					}
				}
			}
			//바깥쪽 온도 감소
			for (int i = 0; i < 2; i++) {
				int p =(R-1+i)%(R);
				for (int j = 0; j < C; j++) {
					if(map[p][j] >0) map[p][j]--;
				}
			}
			for (int j = 0; j < 2; j++) {
				int q =(C-1+j)%(C);
				for (int i = 1; i < R-1; i++) {
					if(map[i][q] >0) map[i][q]--;
				}
			}
			ans++;
			//조사칸 파악하기
			int cnt = 0;
			for (int i = 0; i < research.size(); i++) {
				if(map[research.get(i)[0]][research.get(i)[1]] >= K) {
					cnt++;
				}
			}
			if(cnt==research.size()) break;
		}
		if(ans >100) System.out.println(101);
		else System.out.println(ans);
	}
}
