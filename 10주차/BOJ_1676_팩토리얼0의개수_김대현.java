package BJ_1676_팩토리얼0의개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws  IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		
		
		if(n==0) {
			System.out.print("0");
		}else {
			int count=0;
			int temp;
			for(int i=2; i<=n;i++ ) {
				temp=i;
				while(temp%5==0) {
					temp/=5;
					count++;
				}
			}
			System.out.print(count);
		}
		
		

	}

}
