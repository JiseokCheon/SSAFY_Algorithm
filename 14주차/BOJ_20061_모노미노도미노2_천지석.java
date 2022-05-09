import java.io.*;
import java.util.*;

public class Main {
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        count = 0;
        ArrayList<Integer>[] blue = new ArrayList[4];
        ArrayList<Integer>[] green = new ArrayList[4];

        for (int i = 0; i < 4; i++) {
            blue[i] = new ArrayList<>();
            green[i] = new ArrayList<>();

            for (int j = 0; j < 6; j++) {
                blue[i].add(0);
                green[i].add(0);
            }
        }

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int indexBlue = findIndex(blue, x);
            int indexGreen = findIndex(green, y);
            if (t == 1) {
                blue[x].set(indexBlue, 1);
                green[y].set(indexGreen, 1);
            } else if (t == 2) {
                indexGreen = Math.min(findIndex(green, y + 1), indexGreen);
                blue[x].set(indexBlue, 1);
                blue[x].set(indexBlue - 1, 1);
                green[y].set(indexGreen, 1);
                green[y + 1].set(indexGreen, 1);
            } else if (t == 3) {
                indexBlue = Math.min(findIndex(blue, x + 1), indexBlue);
                blue[x].set(indexBlue, 1);
                blue[x + 1].set(indexBlue, 1);
                green[y].set(indexGreen, 1);
                green[y].set(indexGreen - 1, 1);
            }

            checkLine1(blue);
            checkLine1(green);
            checkLine2(blue);
            checkLine2(green);
        }
        System.out.println(count);
        count = 0;

        for (ArrayList<Integer> integers : blue) {
            for (Integer integer : integers) {
                if (integer == 1) {
                    count++;
                }
            }
        }

        for (ArrayList<Integer> integers : green) {
            for (Integer integer : integers) {
                if (integer == 1) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    static int findIndex(ArrayList<Integer>[] lists, int a) {
        for (int i = 0; i < 6; i++) {
            if (lists[a].get(i) != 0) {
                return i - 1;
            }
        }
        return 5;
    }

    static void checkLine1(ArrayList<Integer>[] lists) {
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (lists[j].get(i) != 1) {
                    break;
                }
                if (j == 3) {
                    count++;
                    removeLine(lists, i);
                }
            }
        }
    }

    static void checkLine2(ArrayList<Integer>[] lists) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (lists[j].get(i) == 1) {
                    removeLine(lists, 5);
                    break;
                }
            }
        }
    }

    static void removeLine(ArrayList<Integer>[] lists, int a) {
        for (int k = 0; k < 4; k++) {
            lists[k].remove(a);
            lists[k].add(0, 0);
        }
    }
}
