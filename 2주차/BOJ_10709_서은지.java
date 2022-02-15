import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int[][] joi = new int[H][W];
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < H; i++) {
			String str = in.readLine();
			// 첫 인덱스의 값이 c이면 0, 아니면 -1 배열에 삽입
			joi[i][0] = str.charAt(0) == 'c' ? 0 : -1;
			sb.append(joi[i][0] + " ");
			for (int j = 1; j < W; j++) {
				// c이면 0, 아니면 -1 일단 삽입
				if (str.charAt(j) == 'c')
					joi[i][j] = 0;
				else
					joi[i][j] = -1;
				
				// 현 인덱스 값이 0이 아니고, 이전 인덱스 값이 -1이 아니라면 이전 인덱스 값 +1
				if (joi[i][j] != 0 && joi[i][j-1] != -1)
					joi[i][j] = joi[i][j-1] + 1;
				
				sb.append(joi[i][j] + " ");
			}
			sb.setLength(sb.length() - 1);
			sb.append("\n");
		}
		
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}

}
