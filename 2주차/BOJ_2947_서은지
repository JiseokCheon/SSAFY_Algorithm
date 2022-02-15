import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int[] arr = new int[5];

		for (int i = 0; i < 5; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		StringBuilder sb = new StringBuilder();

		boolean flag = false;
		while (!flag) {
			for (int i = 0; i < 4; i++) {
				if (arr[i] > arr[i + 1]) {
					int tmp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = tmp;
					flag = true;
					for (int j = 0; j < 4; j++) {
						if (arr[j] > arr[j+1])
							flag = false;
						sb.append(arr[j] + " ");
					}
					sb.append(arr[4]+"\n");
				}
			}
		}
		System.out.println(sb);
	}

}
