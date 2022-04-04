import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] arr;
	static boolean[] check;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		arr=new int[4][8];
		for(int i=0; i<4; i++) {
			String str=br.readLine();
			
			for(int j=0; j<8;j++) {
				arr[i][j]=(int)(str.charAt(j)-'0');
			}
		}
		
		int k= Integer.parseInt(br.readLine());
		int[][] karr=new int[k][2];
		
		for(int i=0; i<k;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			karr[i][0]=Integer.parseInt(st.nextToken())-1;
			karr[i][1]=Integer.parseInt(st.nextToken());
		}
		int n,dir;
//		for(int j=0; j<4; j++) {
//			for(int l=0; l<8;l++) {
//				System.out.print(arr[j][l]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();

		for(int i=0; i<k; i++) {
			n=karr[i][0];
			dir=karr[i][1];
			check=new boolean[4];
			turn(n,dir);
//			for(int j=0; j<4; j++) {
//				for(int l=0; l<8;l++) {
//					System.out.print(arr[j][l]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			
		}
		int sum=0;
		for(int i=0; i<4;i++) {
			if(arr[i][0]==1) {
				sum=sum+(int)Math.pow(2, i);
			}
		}
		System.out.print(sum);
		
		

	}
	private static void turn(int n, int dir) {
		
		check[n]=true;
		
		if(dir==1) { // 시계방향
			int temp=arr[n][7];
			for(int i=7; i>0;i--) {
				arr[n][i]=arr[n][i-1];
			}
			arr[n][0]=temp;
			if(n<3) { // 오른쪽거 체크
				if(!check[n+1] && arr[n][3]!=arr[n+1][6]) {
					turn(n+1,dir*-1);
				}
			}
			
			if(n>0) { // 왼쪽거 체크
				if(!check[n-1] && arr[n][7]!=arr[n-1][2]) {
					turn(n-1,dir*-1);
				}
			}
			
			
		}else {  // 시계 반대방향
			int temp=arr[n][0];
			for(int i=0; i<7;i++) {
				arr[n][i]=arr[n][i+1];
			}
			arr[n][7]=temp;
			
			if(n<3) { // 오른쪽거 체크
				if(! check[n+1] && arr[n][1]!=arr[n+1][6]) {
					turn(n+1,dir*-1);
				}
			}
			
			if(n>0) { // 왼쪽거 체크
				if(! check[n-1] && arr[n][5]!=arr[n-1][2]) {
					turn(n-1,dir*-1);
				}
			}
			
		}
		
		
		
		
		
		
		
	}

}
