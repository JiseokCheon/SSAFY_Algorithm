import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] f_dir= {{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};
	static int[][] s_dir= {{-1,0},{0,-1},{1,0},{0,1}}; // 상,좌,하,우
	static int eat,r,c;
	static int[] move;
	static ArrayList<Integer>[][] fishes;
	public static class fish{
		int r,c,d;
		
		public fish(int r, int c,int d) {
			this.r=r;
			this.c=c;
			this.d=d;
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());

		int m=Integer.parseInt(st.nextToken());
		int s=Integer.parseInt(st.nextToken());
		fishes = new ArrayList[4][4];
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				fishes[i][j]=new ArrayList<Integer>();
			}
		}
		
		for(int i=0; i<m;i++) {
			st=new StringTokenizer(br.readLine());
			fishes[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1].add(Integer.parseInt(st.nextToken())-1);
		}

		st=new StringTokenizer(br.readLine());
		r=Integer.parseInt(st.nextToken())-1;
		c=Integer.parseInt(st.nextToken())-1;
		int tempr,tempc;
		move=new int[3];
		int[][] smell=new int[4][4];
		for(int t=0; t<s; t++) {
			// 물고기 복제하기
			ArrayList<Integer>[][] t_fishes = new ArrayList[4][4];
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					t_fishes[i][j]=new ArrayList<Integer>();
					for(int k=0; k<fishes[i][j].size();k++) {
						t_fishes[i][j].add(fishes[i][j].get(k));
					}
					fishes[i][j]=new ArrayList<Integer>();
				}
			}
			// 물고기 이동
			f_move(t_fishes,smell);
			
			// 상어 이동-
			boolean[][] visit=new boolean[4][4];
			int[] s_move=new int[3];
			eat=-1;
			move_s(s_move,r,c,0,0,visit); // 이동방향, 상어 위치, 이동횟수, 물고기 마릿수
			for(int i=0; i<3; i++) {
				r=r+s_dir[move[i]][0];
				c=c+s_dir[move[i]][1];
				if(fishes[r][c].size()>0) {
					smell[r][c]=3;
				}
				fishes[r][c]=new ArrayList<Integer>();
			}
			//냄새 제거-
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					if(smell[i][j]>0) {
						smell[i][j]--;
					}
				}
			}
			
			// 물고기 복제 완성
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					for(int k=0; k<t_fishes[i][j].size();k++) {
						fishes[i][j].add(t_fishes[i][j].get(k));
					}
				}
			}
			
		}
		
		int answer=0;
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				answer+=fishes[i][j].size();
			}
		}
		System.out.print(answer);
		

	}

	private static void f_move(ArrayList<Integer>[][] t_fishes, int[][] smell) {
		int tempr,tempc,d;
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				for(int k=0; k<t_fishes[i][j].size();k++) {
					d=t_fishes[i][j].get(k);
					boolean check=false;
					for(int l=0; l<8;l++) {
						tempr=i+f_dir[(d+8-l)%8][0];
						tempc=j+f_dir[(d+8-l)%8][1];
						if(tempr <0 || tempr >3 || tempc <0 || tempc >3) {
							continue;
						}
						if(smell[tempr][tempc] >0) {
							continue;
						}
						if(tempr ==r && tempc == c) {
							continue;
						}
						check=true;
						fishes[tempr][tempc].add((d+8-l)%8);
						break;
					}
					if(!check) {
						fishes[i][j].add(d);
					}
				}
			}
		}
	}

	

	private static void move_s(int[] s_move, int r,int c,int cnt, int num,boolean[][] visit) {
		if(cnt==3) {

			if(num > eat) {
				eat=num;
				for(int i=0; i<3; i++) {
					move[i]=s_move[i];
				}
				
			}
		}else {
			int tempr,tempc;
			for(int i=0; i<4; i++) {
				tempr=r+s_dir[i][0];
				tempc=c+s_dir[i][1];
				if(tempr <0 || tempr >3 || tempc <0 || tempc >3) {
					continue;
				}
				s_move[cnt]=i;
				if(!visit[tempr][tempc]) {
					visit[tempr][tempc]=true;
					move_s(s_move,tempr,tempc,cnt+1,num+fishes[tempr][tempc].size(),visit);
					visit[tempr][tempc]=false;
				}else {
					move_s(s_move,tempr,tempc,cnt+1,num,visit);
					
				}
				
			}
		}
		
	}

	

}
