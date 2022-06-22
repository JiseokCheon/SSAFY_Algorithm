import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static ArrayList<Long> list = new ArrayList<>(List.of(0L, 1L, 1L, 1L, 2L, 2L));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            sb.append(P(Integer.parseInt(br.readLine()))).append("\n");
        }
        System.out.println(sb);
    }

    static long P(int n) {
        if (list.size() > n) {
            return list.get(n);
        } else {
            long x = P(n - 1) + P(n - 5);
            if (list.size() == n) {
                list.add(x);
            }
            return x;
        }
    }
}

