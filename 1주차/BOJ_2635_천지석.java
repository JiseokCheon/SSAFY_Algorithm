package com.company;

import java.util.*;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        StringBuilder temp = new StringBuilder(); // 규칙에 의해 만들어지는 수들 담음
        String answer = null;   // 출력할 정답

        int N = sc.nextInt();   // 첫번째 수
        int a, b, c, count; // a = 앞의 앞의 수, b = 앞의 수, c = a - b
        int max = 0;    // 규칙으로 만들어지는 최대 갯수

        // 모든 수 비교하기 위해 두번재 수를 모두 비교
        for (int i = N; i > 0; i--) {
            temp.setLength(0);  // 출력할 문장 초기화
            temp.append(N).append(" ").append(i).append(" ");   // N, i는 무조건 추가
            a = N;          // 앞의 앞의 수
            b = i;          // 앞의 수
            count = 2;      // N, i 두 개는 무조건 counting
            while ((c = a - b) >= 0) {  // 앞의 앞의 수에서 앞의 수 빼서 음수가 아닐 때까지
                count++;    // 갯수
                a = b;      // 앞의 앞의 수를 앞의 수로 바꿈
                b = c;      // 앞의 수를 현재 수로 바꿈
                temp.append(c + " ");   // 현재 수 추가
            }

            if (max < count) {  // 새로운 최대 개수 등장
                max = count;    // max 값 변경
                answer = temp.toString();   // 출력할 정답 변경
            }
        }
        System.out.println(max);
        System.out.println(answer);
    }
}
