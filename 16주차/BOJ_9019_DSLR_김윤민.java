package July;

import java.io.*;
import java.util.*;

public class BOJ_9019_DSLR_김윤민 {
	static class Node{ //명령어 저장
		String command;
		int ln, N;
		public Node(String command, int ln, int n) {
			this.command = command;
			this.ln = ln;
			N = n;
		}

	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String min_command = ""; //최소 길이의 명령어 저장
			String[] nums = br.readLine().split(" ");
			int A = Integer.parseInt(nums[0]); 
			int B = Integer.parseInt(nums[1]); 
			
            boolean[] visit = new boolean[10000];//방문배열
            Queue<Node> que = new LinkedList<>();
            que.add(new Node("", 0, A));
            visit[A] = true;
			while(!que.isEmpty()) {
				Node node = que.poll();
				if(node.N == B) { //B와 N이 동일하면 bfs 종료
					min_command = node.command;
					break;
				}
				//모든 경우를 다 구해보기
                if(!visit[D(node.N)]){ //만약 계산된 값이 false이면 que에 추가
                    que.add(new Node(node.command.concat("D"), node.ln+1,D(node.N)));
                    visit[D(node.N)] = true;
                }
                if(!visit[S(node.N)]){
                	que.add(new Node(node.command.concat("S"), node.ln+1,S(node.N)));
                    visit[S(node.N)] = true;
                }
                if(!visit[R(node.N)]){
                	que.add(new Node(node.command.concat("R"), node.ln+1,R(node.N)));
                    visit[R(node.N)] = true;
                }
                if(!visit[L(node.N)]){
                	que.add(new Node(node.command.concat("L"), node.ln+1,L(node.N)));
                    visit[L(node.N)] = true;
                }
			}
			sb.append(min_command+"\n");
		}
		System.out.println(sb);
	}
	static int D(int n) { //
		return (n*2)%10000; 
	}
	static int S(int n) {
		if(n==0) return 9999;
		return n-1;
	}
	static int L(int n) {
		return (n%1000)*10 + n/1000;
	}
	static int R(int n) {
		return (n%10)*1000 + n/10;
	}
}
