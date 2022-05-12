import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        StringBuilder S = new StringBuilder(br.readLine());
        StringBuilder P = new StringBuilder("I");

        P.append("OI".repeat(N));

        int index = P.length() - 1;
        int count = 0;
        int size = P.length();

        outer:
        while (index < M) {
            for (int i = 0; i < size; i++) {
                if (S.charAt(index - i) != P.charAt(P.length() - i - 1)) {
                    if (i == 0) {
                        index++;
                        size = P.length();
                    } else if (S.charAt(index - i) == 'I') {
                        size = P.length() - i;
                        index += size;
                    } else {
                        size = P.length() - i+1;
                        index += size;
                    }
                    continue outer;
                }
            }
            count++;
            index += 2;
            size = 2;
        }

        System.out.println(count);
    }
}
