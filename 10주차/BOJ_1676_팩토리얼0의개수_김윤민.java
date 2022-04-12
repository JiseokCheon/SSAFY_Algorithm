import java.io.*;

public class BOJ_1676_팩토리얼0의개수_김윤민 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int cnt=0; 
		cnt+= n/5;
		cnt+= n/25;
		cnt+= n/125;
		System.out.println(cnt);
	}
}
