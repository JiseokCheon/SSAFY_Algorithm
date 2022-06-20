package BJ_21609_상어중학교;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	public static class point {
		int r, c;

		public point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int total = 0;
		int tempr, tempc;
		while (true) {
			
			// 1번부분
			boolean[][] check = new boolean[n][n];
			boolean find = false;
			int b_max = 0, rainbow_max = 0, b_r = 0, b_c = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!check[i][j] && map[i][j] > 0) {
						int count = 1;
						int r_count = 0;
						int num = map[i][j];
						check[i][j] = true;
						boolean[][] r_check = new boolean[n][n];
						Queue<point> q = new LinkedList<point>();
						q.offer(new point(i, j));
						while (!q.isEmpty()) {
							point p = q.poll();
							for (int d = 0; d < 4; d++) {
								tempr = p.r + dir[d][0];
								tempc = p.c + dir[d][1];
								if (tempr < 0 || tempr >= n || tempc < 0 || tempc >= n || check[tempr][tempc]) {
									continue;
								}
								if (map[tempr][tempc] == num) {
									count++;
									check[tempr][tempc] = true;
									q.offer(new point(tempr, tempc));
								}
								if (map[tempr][tempc] == 0 && !r_check[tempr][tempc]) {
									count++;
									r_count++;
									r_check[tempr][tempc] = true;
									q.offer(new point(tempr, tempc));
								}
							}
						}
						if (count > b_max && count > 1) {
							b_max = count;
							rainbow_max = r_count;
							b_r = i;
							b_c = j;
							find = true;
						} else if (count == b_max && count > 1) {
							if (r_count > rainbow_max) {
								rainbow_max = r_count;
								b_r = i;
								b_c = j;
								find = true;
							} else if (r_count == rainbow_max) {
								b_r = i;
								b_c = j;
							}
						}

					}
				}
			}

			
			// 못찾으면 끝
			if (!find) {
				break;
			}
			
			// 2번 부분
			total += b_max * b_max;
			Queue<point> q = new LinkedList<point>();
			q.offer(new point(b_r, b_c));
			int num = map[b_r][b_c];
			map[b_r][b_c] = -2;
			while (!q.isEmpty()) {
				point p = q.poll();
				for (int d = 0; d < 4; d++) {
					tempr = p.r + dir[d][0];
					tempc = p.c + dir[d][1];
					if (tempr < 0 || tempr >= n || tempc < 0 || tempc >= n) {
						continue;
					}
					if (map[tempr][tempc] == num || map[tempr][tempc] == 0) {
						map[tempr][tempc] = -2;
						q.offer(new point(tempr, tempc));
					}

				}
			}
			// 3번 부분 ( 중력 )
			G(map,n);
			
			// 4번 부분 ( 회전 )
			turn(map,n);
			// 5번 부분 ( 중력 )
			G(map,n);
			
			
			

		}
		System.out.println(total);

	}
	
	private static void turn(int[][] map, int n) {
		int[][] temp=new int[n][n];
		
		for(int i=0; i< n; i++) {
			for(int j=0; j<n; j++) {
				temp[i][n-1-j]=map[n-1-j][n-1-i];
			}
		}
		for(int i=0; i< n; i++) {
			for(int j=0; j<n; j++) {
				map[i][j]=temp[i][j];
			}
		}
		
	}

	public static void G(int[][] map,int n) {
		for(int i=n-2;i>=0; i--) {
			for(int j=n-1;j>=0; j--) {
				if(map[i][j] >=0) {
					boolean check = false;
					for(int k=i+1;k<n;k++) {
						if(map[k][j] !=-2) {
							check=true;
							map[k-1][j]=map[i][j];
							if(k-1 != i) {
								map[i][j]=-2;
							}
							break;
						}
					}
					if(!check) {
						map[n-1][j]=map[i][j];
						map[i][j]=-2;
					}
					
				}
			}
		}
		
	}
	
	public static void print(int[][] arr,int n,String str) {
		System.out.println(str);
		for(int i=0;i<n;i++) {
			for(int j=0; j<n;j++) {
				if(arr[i][j]==-2) {
					System.out.print("   ");
				}else {
					System.out.printf("%3d",arr[i][j]);
				}
			}
			System.out.println();
		}
	}

}
