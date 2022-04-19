import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] arr;
	static int[] count=new int[3];

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		arr=new int[n][n];
		for(int i=0;i<n;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		check(0,0,n);
		for(int i=0;i<3;i++) {
			System.out.println(count[i]);
		}
		

	}
	
	private static void check(int startr,int startc, int num ) {
		
		int temp=arr[startr][startc];
		boolean checks=true;
loop:		for(int i=startr;i<startr+num;i++) {
			for(int j=startc;j<startc+num;j++) {
				if(arr[i][j]!=temp) {
					checks=false;
					break loop;
				}
			}
		}
		
		if(checks) {
			count[temp+1]++;
		}else {
			check(startr,startc,num/3);
			check(startr,startc+num/3,num/3);
			check(startr,startc+num/3*2,num/3);
			check(startr+num/3,startc,num/3);
			check(startr+num/3,startc+num/3,num/3);
			check(startr+num/3,startc+num/3*2,num/3);
			check(startr+num/3*2,startc,num/3);
			check(startr+num/3*2,startc+num/3,num/3);
			check(startr+num/3*2,startc+num/3*2,num/3);
		}
		
		
	}

}
