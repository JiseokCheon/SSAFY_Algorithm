import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] map;
	static int answer=Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		map=new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for(int x=0; x<N;x++) {
			for(int y=0; y<N;y++) {
				for(int d1=1; d1<Math.min(N-x, y+1); d1++) {
					for(int d2=1; d2<Math.min(N-x-d1, N-y); d2++) {
						gab(x,y,d1,d2);
					}
				}
			}
		}
		System.out.println(answer);
		

	}
	private static void gab(int x, int y, int d1, int d2) {
		int sum1=0;
		int sum2=0;
		int sum3=0;
		int sum4=0;
		int sum5=0;
		//1번 구역
//		int[][] temp=new int[N][N];
		for(int i=0; i<N;i++) {
			for(int j=0; j<N; j++) {
				if( i< x+d1 && j<=y && j <y+x-i) { // x=1 y=4, d1=3, d2=2
					sum1+=map[i][j];
//					temp[i][j]=1;
				}else if( i <= x+d2 && y<j && y-x+i<j) {
					sum2+=map[i][j];
//					temp[i][j]=2;
				}else if( i >= x+d1 && j<y-d1+d2 && j <y-d1+i-x-d1 ) {
					sum3+=map[i][j];
//					temp[i][j]=3;
				}else if( i > x+d2 && j >y+d2-i+x+d2 ) {
					sum4+=map[i][j];
//					temp[i][j]=4;
				}else {
					sum5+=map[i][j];
//					temp[i][j]=5;
				}
			}
		}
		
//		System.out.println("x : "+x+" y: "+y+" d1: "+d1+" d2: "+d2);
//		for(int i=0; i<N;i++) {
//			for(int j=0; j<N;j++) {
//				System.out.print(temp[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		int min=Math.min(sum1, Math.min(sum2, Math.min(sum3, Math.min(sum4, sum5))));
		int max=Math.max(sum1, Math.max(sum2, Math.max(sum3, Math.max(sum4, sum5))));
		if(max-min <answer) {
			answer=max-min;
		}
		
	}

}
