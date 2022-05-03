import java.util.*;

public class BOJ_2606_바이러스 {
	public static List<List<Integer>> list;
	public static boolean[] isChecked;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int nw = sc.nextInt();

		list = new ArrayList<>();
		isChecked = new boolean[N+1];

		for (int i = 0; i < N+1; i++) {
			List<Integer> li = new ArrayList<Integer>();
			list.add(li);
		}
		
		for (int i = 0; i < nw; i++) {
			int com1 = sc.nextInt();
			int com2 = sc.nextInt();
			list.get(com1).add(com2);
			list.get(com2).add(com1);
		}

		dfs(1);
		int cnt = 0;
		for (int i = 0; i < N+1; i++) {
			//System.out.print(isChecked[i]+" ");
			if (isChecked[i] == true)
				cnt++;
		}
		
		System.out.println(cnt-1);
	}

	private static void dfs(int num) {
		List<Integer> li = list.get(num);
		for (int i = 0; i < li.size(); i++) {
			//System.out.println(num+" : "+li.get(i) );
			if (isChecked[li.get(i)] == true)
				continue;
			isChecked[li.get(i)] = true;
			dfs(li.get(i));
		}

	}
}
