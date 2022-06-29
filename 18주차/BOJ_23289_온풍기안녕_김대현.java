package BJ_23289_온풍기안녕;

import java.io.*;
import java.util.*;

public class Main {

	static int[][] dir = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } }; // 오, 왼, 위, 아래
	static int[][][] dir2 = { { { 0, 1 }, { -1, 1 }, { 1, 1 } }, { { 0, -1 }, { 1, -1 }, { -1, -1 } },
			{ { -1, 0 }, { -1, -1 }, { -1, 1 } }, { { 1, 0 }, { 1, 1 }, { 1, -1 } } };
	static int[][][] dir3 = { { { -1, 0 }, { 1, 0 } }, { { 1, 0 }, { -1, 0 } }, { { 0, -1 }, { 0, 1 } },
			{ { 0, 1 }, { 0, -1 } } };
	static int[][] map, add,tmpt;
	static int R, C;

	public static class point {
		int r, c, num;

		public point(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		map = new int[R * 2 - 1][C * 2 - 1];
		int count = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i * 2][j * 2] = Integer.parseInt(st.nextToken());
				if (map[i * 2][j * 2] == 5) {
					count++;
				}
			}
		}

		int W = Integer.parseInt(br.readLine());
		int tempr, tempc;
		for (int i = 0; i < W; i++) {
			st = new StringTokenizer(br.readLine());
			tempr = Integer.parseInt(st.nextToken()) - 1;
			tempc = Integer.parseInt(st.nextToken()) - 1;
			if (Integer.parseInt(st.nextToken()) == 0) {
				map[tempr * 2 - 1][tempc * 2] = -5;
			} else {
				map[(tempr) * 2][tempc * 2 + 1] = -5;
			}
		}
		add = new int[R * 2][C * 2];
	
		point[] check = new point[count];
		count = 0;
		for (int i = 0; i < R * 2 - 1; i = i + 2) {
			for (int j = 0; j < C * 2 - 1; j = j + 2) {
				if(map[i][j] >0 && map[i][j] <5) {
					hot(i,j,map[i][j]-1);
				}
				if(map[i][j]==5) {
				check[count++]=new point(i,j,0);
					
				}


			}
		}
		int cho=0;
		tmpt=new int[2*R-1][2*C-1];
		while(cho<=100) {
			// 온풍기 
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C;j++) {
					tmpt[i*2][j*2]+=add[i * 2][j * 2];
				}
			}
			
			//온도 조절
			control();
			//1도 감소
			down();
			//초콜릿 먹기
			cho++;
			//온도 체크
			int cnt=0;
			for(int i=0; i<count;i++) {
				if(tmpt[check[i].r][check[i].c] >= K) {
					cnt++;
				}else{
					break;
				}
			}
			if(cnt==count) {
				break;
			}
			
		}
		
		
		System.out.println(cho);


	}

	private static void down() {
		if(tmpt[0][0] >0) {
			tmpt[0][0]++;
		}
		if(tmpt[0][2*(C-1)]>0) {
			tmpt[0][2*(C-1)]++;
		}
		if(tmpt[2*(R-1)][0]>0) {
			tmpt[2*(R-1)][0]++;
		}
		if(tmpt[2*(R-1)][2*(C-1)]>0) {
			tmpt[2*(R-1)][2*(C-1)]++;
		}
		for(int i=0; i<R;i++) {
			if(tmpt[i*2][0]>0) {
				tmpt[i*2][0]--;
			}
		}
		for(int i=0; i<R;i++) {
			if(tmpt[i*2][2*(C-1)]>0) {
				tmpt[i*2][2*(C-1)]--;
			}
		}
		for(int i=0; i<C;i++) {
			if(tmpt[0][i*2]>0) {
				tmpt[0][i*2]--;
			}
		}
		for(int i=0; i<C;i++) {
			if(tmpt[2*(R-1)][i*2]>0) {
				tmpt[2*(R-1)][i*2]--;
			}
		}
	}

	private static void control() {
		int[][] temp_arr=new int[2*R-1][2*C-1];
		int temp,sum,tempr,tempc;
		
		for(int i=0; i<R*2-1; i=i+2) {
			for(int j=0; j<C*2-1;j=j+2) {
				if(tmpt[i][j] >3) {
					sum=0;
					temp=tmpt[i][j];
					for(int d=0; d<4; d++) {
						tempr=i+dir[d][0];
						tempc=j+dir[d][1];
						if(tempr <0 || tempr >=2*R-1 || tempc <0 || tempc >=2*C-1) {
							continue;
						}
						if(map[tempr][tempc]!=-5) {
							tempr+=dir[d][0];
							tempc+=dir[d][1];
							if(tmpt[i][j]>tmpt[tempr][tempc]) {
								temp_arr[tempr][tempc]+=(temp-tmpt[tempr][tempc])/4;
								sum+=(temp-tmpt[tempr][tempc])/4;
							}
						}
					}
					temp_arr[i][j]+=temp-sum;
				}else {
					temp_arr[i][j]+=tmpt[i][j];
				}
				
			}
		}
		
		for(int i=0; i<2*R-1;i++) {
			for(int j=0; j<2*C-1;j++) {
				tmpt[i][j]=temp_arr[i][j];
			}
		}
		
	}

	private static void hot(int r, int c, int d) {// d=오왼위아
		int tempr = r + dir[d][0] * 2;
		int tempc = c + dir[d][1] * 2;
		int num;
		boolean[][] check = new boolean[R * 2 - 1][C * 2 - 1];
		add[tempr][tempc] += 5;
		check[tempr][tempc] = true;
		Queue<point> q = new LinkedList<point>();
		q.offer(new point(tempr, tempc, 5));
		while (!q.isEmpty()) {
			point p = q.poll();
			for (int i = 0; i < 3; i++) {
				tempr = p.r + dir2[d][i][0] * 2;
				tempc = p.c + dir2[d][i][1] * 2;
				if (tempr < 0 || tempc < 0 || tempr > 2 * R - 1 || tempc > 2 * C - 1) {
					continue;
				}
				if (!check[tempr][tempc]) {
					if (map[tempr - dir[d][0]][tempc - dir[d][1]] == -5) {
						continue;
					}
					if (i == 0) {
						add[tempr][tempc] += p.num-1;
						check[tempr][tempc] = true;
						if (p.num > 1) {
							q.offer(new point(tempr, tempc, p.num - 1));
						}
					} else {
						if (map[p.r + dir3[d][i - 1][0]][p.c + dir3[d][i - 1][1]] != -5) {
							add[tempr][tempc] += p.num-1;
							check[tempr][tempc] = true;
							if (p.num > 1) {
								q.offer(new point(tempr, tempc, p.num - 1));
							}
						}
					}

				}
			}

		}

	}

}
