import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

		int cn=Integer.parseInt(br.readLine()); // 컴퓨터수
		int ln=Integer.parseInt(br.readLine()); // 연결된 수
		int[][] larr=new int[ln][2];
		boolean[] check=new boolean[cn+1];
		for(int i=0; i<ln;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			larr[i][0]=Integer.parseInt(st.nextToken());
			larr[i][1]=Integer.parseInt(st.nextToken());
		}
		int count=0;
		virus(larr,check,1,ln);
		for(int i=2; i<cn+1;i++) {
			if(check[i]) {
				count++;
			}
		}
		System.out.println(count);

	}
	private static void virus(int[][] larr,boolean[] check,int n ,int ln){
				
		for(int i=0;i<ln;i++) {
			for(int j=0;j<2;j++) {
				if(larr[i][j]==n) {
//					System.out.printf("i : %d j : %d n : %d larr[i][Math.abs(j-1)] : %d\n",i,j,n,larr[i][Math.abs(j-1)]);
					check[n]=true;
					if(!check[larr[i][Math.abs(j-1)]]) {
						virus(larr,check,larr[i][Math.abs(j-1)],ln);
						
					}
				}
			}
		}
		
	}

}
