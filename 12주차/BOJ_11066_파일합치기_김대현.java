import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws  IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=Integer.parseInt(br.readLine());
		
		for(int t=0; t<tc;t++) {
			int n=Integer.parseInt(br.readLine());
			
			int[] arr=new int[n+1];
			int[] sum=new int[n+1];
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int i=1; i<=n; i++) {
				arr[i]=Integer.parseInt(st.nextToken());
				sum[i]=sum[i-1]+arr[i];
			}
			int[][] sums=new int[n+2][n+2];
			
			
			for(int i=2; i<=n; i++) {
				for(int j=i-1; j >0; j--) {
					sums[j][i]=Integer.MAX_VALUE;
					for(int k=j; k<=i; k++) {
						sums[j][i]=Math.min(sums[j][k]+sums[k+1][i], sums[j][i]);
					}
					sums[j][i]+=sum[i]-sum[j-1];
				}
				
			}
			
			System.out.println(sums[1][n]);
			
		}

	}

}
