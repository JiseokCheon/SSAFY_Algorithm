package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 움직임 map에 저장
        HashMap<String, int[]> map = new HashMap<>() {
            {
                put("R", new int[]{0, 1});
                put("L", new int[]{0, -1});
                put("B", new int[]{1, 0});
                put("T", new int[]{-1, 0});
                put("RT", new int[]{-1, 1});
                put("LT", new int[]{-1, -1});
                put("RB", new int[]{1, 1});
                put("LB", new int[]{1, -1});
            }
        };

        String s1 = st.nextToken();
        String s2 = st.nextToken();
        int N = Integer.parseInt(st.nextToken());

        // (8, a)를 (0, 0)으로 생각하고 계산, A1 -> (0, 7) / A2 -> (0, 6)
        int[] king = {8 - (s1.charAt(1) - '0'), s1.charAt(0) - 65};
        int[] stone = {8 - (s2.charAt(1) - '0'), s2.charAt(0) - 65};

        for (int i = 0; i < N; i++) {
            int[] d = map.get(br.readLine());

            // 입력대로 이동했을 다음 위치
            int[] nextKing = new int[]{king[0] + d[0], king[1] + d[1]};
            int[] nextStone = new int[]{stone[0] + d[0], stone[1] + d[1]};

            // 킹이 체스판 밖으로 나가지 않으면
            if (nextKing[0] >= 0 && nextKing[0] < 8 && nextKing[1] >= 0 && nextKing[1] < 8) {
                // 이동할 킹의 위치와 돌의 위치가 같으면
                if (nextKing[0] == stone[0] && nextKing[1] == stone[1]) {
                    // 이동할 돌의 위치가 체스판 밖으로 나가지 않으면 돌 이동, 밖으로 나가면 킹, 돌 모두 이동 X
                    if (nextStone[0] < 8 && nextStone[0] >= 0 && nextStone[1] >= 0 && nextStone[1] < 8) {
                        stone[0] = nextStone[0];
                        stone[1] = nextStone[1];
                    } else {
                        continue;
                    }
                }
                // 킹과 돌이 모두 조건을 만족하면 킹을 다음 위치로 이동
                king[0] = nextKing[0];
                king[1] = nextKing[1];
            }
        }
        System.out.println((char) (king[1] + 65) + "" + (8 - king[0]));
        System.out.println((char) (stone[1] + 65) + "" + (8 - stone[0]));

    }
}