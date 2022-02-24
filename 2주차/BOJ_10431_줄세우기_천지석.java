package com.company;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int P = Integer.parseInt(br.readLine());    // 테스트 케이스 수

        for (int tc = 1; tc <= P; tc++) {
            st = new StringTokenizer(br.readLine());

            int count = 0;  // 아이들이 뒤로 물러난 총합
            int N = Integer.parseInt(st.nextToken());   // 테스트 케이스 번호

            ArrayList<Integer> list = new ArrayList<>();    // 아이들 넣을 리스트

            for (int i = 0; i < 20; i++) {
                int index = i;  // 현재 아이 줄세울 위치
                int child = Integer.parseInt(st.nextToken());   // 현재 줄세울 아이 키
                for (int j = 0; j < i; j++) {
                    if (list.get(j) > child) {      // 앞에서부터 확인후 현재 아이키보다 큰 아이가 있으면 넣을 위치 변경
                        index = j;
                        break;
                    }
                }
                count += (i - index);   // 현재 넣을 위치부터 마지막 학생들이 한칸 씩 물러나기 때문에 더해줌
                list.add(index, child); // 아이 줄세움
            }

            System.out.println(N + " " + count);    // 총합 출력
        }
    }
}
