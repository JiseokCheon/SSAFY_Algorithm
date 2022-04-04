package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int answer = 0;
        int[] check = new int[4];   // 각 톱니바퀴 회전 방향
        LinkedList<Character>[] lists = new LinkedList[4];    // 톱니바퀴

        for (int i = 0; i < 4; i++) {
            String s = br.readLine();
            lists[i] = new LinkedList<>();
            for (int j = 0; j < 8; j++) {
                lists[i].add(s.charAt(j));
            }
        }

        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(check, 0);

            int num = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            check[num] = d;
            int left = num - 1;
            while (left >= 0) { // 왼쪽 확인
                if (!lists[left].get(2).equals(lists[left + 1].get(6))) {
                    check[left] = check[left + 1] > 0 ? -1 : 1;
                } else {
                    break;
                }
                left--;
            }

            int right = num + 1;
            while (right < 4) { // 오르쪽 확인
                if (!lists[right].get(6).equals(lists[right - 1].get(2))) {
                    check[right] = check[right - 1] > 0 ? -1 : 1;
                } else {
                    break;
                }
                right++;
            }

            for (int j = 0; j < 4; j++) {
                rotate(lists[j], check[j]);
            }
        }

        for (int i = 0; i < 4; i++) {
            answer += (lists[i].get(0) - '0') * Math.pow(2, i);
        }

        System.out.println(answer);
    }


    static void rotate(LinkedList<Character> list, int d) {
        if (d == 1) {
            list.addFirst(list.getLast());
            list.removeLast();
        } else if (d == -1) {
            list.addLast(list.getFirst());
            list.removeFirst();
        }
    }
}


