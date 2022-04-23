import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ_1931_회의실배정_김윤민 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] == o2[1]?o1[0]- o2[0]:o1[1] - o2[1]);
        
        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split(" ");
            pq.add(new int[] { Integer.parseInt(str[0]), Integer.parseInt(str[1]) });
        }
        int cnt = 0;
        int end = 0;
        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            if (end <= temp[0]) {
                // System.out.println(Arrays.toString(temp));
                cnt++;
                end = temp[1];
            }
        }
        System.out.println(cnt);
    }
}
