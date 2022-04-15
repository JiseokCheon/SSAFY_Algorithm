package BJ_5373_큐빙;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= t; tc++) {
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			char[][][] cube = new char[6][3][3];
			paint(cube);

			for (int i = 0; i < n; i++) {
				String str = st.nextToken();
				if (str.charAt(1) == '+') {
					turnright(str.charAt(0), cube);
				} else {
					for (int j = 0; j < 3; j++) {
						
						turnright(str.charAt(0), cube);
					}
				}
				
//				printCube(cube);

			}
			
			
			

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					sb.append(cube[0][i][j]);
				}
				sb.append("\n");
			}

		}
		System.out.print(sb);

	}
	
	private static void printCube(char[][][] cube) {
		System.out.print("위 \t아래\t왼쪽\t오른\t앞면\t뒷면\t \n");
		for(int i=0; i<3; i++) {
			for(int j=0; j<6; j++) {
				for(int k=0; k<3; k++) {
					System.out.print(cube[j][i][k]+" ");
				}
				System.out.print("\t");
			}
			System.out.println();
		}
		
	}

	private static void turnright(char c, char[][][] cube) {
		char temp[] = new char[3];
		switch (c) { // 큐브 0~5 순서대로 위(흰),아래(노),왼(초),오(파),앞(빨),뒤(주) 
		case 'U':
			for (int i = 0; i < 3; i++) {
				temp[i] = cube[2][0][i]; // 왼쪽 0행 저장
				cube[2][0][i] = cube[4][0][i];
				cube[4][0][i] = cube[3][0][i];
				cube[3][0][i] = cube[5][0][i];
				cube[5][0][i] = temp[i];
			}
			turn(cube, 0);

			break;
		case 'D':
			for (int i = 0; i < 3; i++) {
				temp[i] = cube[2][2][i]; // 왼쪽 2행 저장
				cube[2][2][i] = cube[5][2][i];
				cube[5][2][i] = cube[3][2][i];
				cube[3][2][i] = cube[4][2][i];
				cube[4][2][i] = temp[i];
			}

			turn(cube, 1);

			break;
		case 'F': // 앞면 기준 (4) // 큐브 0~5 순서대로 위,아래,왼,오,앞,뒤
			for (int i = 0; i < 3; i++) {
				temp[i] = cube[0][2][i]; // 윗면 2행 저장
				cube[0][2][i] = cube[2][2 - i][2];
				cube[2][2 - i][2] = cube[1][0][2 - i];
				cube[1][0][2 - i] = cube[3][i][0];
				cube[3][i][0] = temp[i];
			}

			turn(cube, 4);
			break;
		case 'B': // 뒷면 기준 (5)
			for (int i = 0; i < 3; i++) {
				temp[i] = cube[0][0][i]; // 윗면 0행 저장
				cube[0][0][i] = cube[3][i][2];
				cube[3][i][2] = cube[1][2][2 - i];
				cube[1][2][2 - i] = cube[2][2 - i][0];
				cube[2][2 - i][0] = temp[i];
			}

			turn(cube, 5);
			break;
		case 'L': // 왼쪽면 기준 (2) // 위 0 아래 1 앞 4 뒤 5
			for (int i = 0; i < 3; i++) {
				temp[i]=cube[0][i][0];// ......... 윗면 0열 저장
				cube[0][i][0]=cube[5][2-i][2];
				cube[5][2-i][2]=cube[1][i][0];
				cube[1][i][0]= cube[4][i][0];
				cube[4][i][0]=temp[i];
				
				
			}

			turn(cube, 2);
			break;
		case 'R': // 오른쪽면 기준 (3) // 위 0 아래 1 앞 4 뒤 5
			for (int i = 0; i < 3; i++) {
				
				temp[i] = cube[0][i][2]; // 윗면 2열 저장
				cube[0][i][2] = cube[4][i][2];
				cube[4][i][2] = cube[1][i][2];
				cube[1][i][2] = cube[5][2-i][0];
				cube[5][2-i][0] = temp[i];
			}

			turn(cube, 3);
			break;
		}

	}

	private static void turn(char[][][] cube, int n) {
		char[] temp = new char[2];
		for (int i = 0; i < 2; i++) {
			temp[i] = cube[n][0][i];
			cube[n][0][i] = cube[n][2 - i][0];
			cube[n][2 - i][0] = cube[n][2][2 - i];
			cube[n][2][2 - i] = cube[n][i][2];
			cube[n][i][2] = temp[i];
		}

	}

	public static void paint(char[][][] cube) {

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 6; k++) {
					switch (k) {
					case 0: // 윗면
						cube[k][i][j] = 'w';
						break;
					case 1: // 아랫면
						cube[k][i][j] = 'y';
						break;
					case 2: // 왼쪽면
						cube[k][i][j] = 'g';
						break;
					case 3: // 오른쪽면
						cube[k][i][j] = 'b';
						break;
					case 4:// 앞면
						cube[k][i][j] = 'r';
						break;
					case 5: // 뒷면
						cube[k][i][j] = 'o';
						break;
					}
				}
			}
		}
	}
}
