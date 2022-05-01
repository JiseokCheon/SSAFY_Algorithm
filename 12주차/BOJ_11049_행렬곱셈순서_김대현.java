import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws  IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		
		int[][] arr=new int[N][2];
		for(int i=0; i<N; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			arr[i][0]=Integer.parseInt(st.nextToken());
			arr[i][1]=Integer.parseInt(st.nextToken());
		}
		int[][] sum=new int[N][N];
		
		
		for(int i=0; i<N-1; i++) {
			sum[i][i+1]=arr[i][0]*arr[i][1]*arr[i+1][1];
		}
		
		for(int i=1; i<N; i++) {
			for(int j=0; i+j <N; j++) {
				int k= i+j;
				sum[j][k]=Integer.MAX_VALUE;
				for(int l=j; l<k; l++) {
					sum[j][k]=Math.min(sum[j][l]+sum[l+1][k]+arr[j][0]*arr[l+1][0]*arr[k][1], sum[j][k]);
				}
			}
			
		}
		
	
		System.out.print(sum[0][N-1]);

	}

}
