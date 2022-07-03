import java.io.*;
import java.util.*;

public class BOJ_11399_ATM {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] time = new int[N];
		for (int i = 0; i < N; i++) {
			time[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(time); //시간 순으로 정렬
		int ans =0; //전체 사람이 필요한 시간
		int wait= 0; //기다린 시간
		for (int i = 0; i < N; i++) {
			ans+= wait+ time[i]; //기다린 시간 + 본인 업무 시간
			wait = wait+ time[i]; //전 사람이 업무를 볼때까지의 시간
		}
		System.out.println(ans);
	}
}
