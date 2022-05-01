import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws  IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int[] arr=new int[n];
		for(int i=0; i<n; i++) {
			arr[i]=Integer.parseInt(br.readLine());
		}
		if(n==1) {
			System.out.print(arr[0]);
		}else if(n==2) {
			System.out.print(arr[0]+arr[1]);
		}else {
			int[][] max=new int[n][3];
			max[0][0]=0;
			max[0][1]=arr[0];
			max[0][2]=arr[0];
			max[1][0]=arr[0];
			max[1][1]=arr[1];
			max[1][2]=arr[0]+arr[1];
			for(int i=2; i<n; i++) {
				max[i][0]=Math.max(max[i-1][2], max[i-1][1]);
				max[i][1]=max[i-1][0]+arr[i];
				max[i][2]=max[i-1][1]+arr[i];
			}
			System.out.print(Math.max(max[n-1][1],max[n-1][2]));
			
		}

	}

}
