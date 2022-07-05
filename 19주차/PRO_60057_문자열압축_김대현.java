class Solution {
    public int solution(String s) {
        int answer = s.length();
		int size = s.length();
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = s.charAt(i) - 'a';
		}
		int count, count2; // count= i로 압축 했을 때의 길이, count2= 해당길이에서 반복 횟수
		boolean can2; // 같은지
		for (int i = 1; i <= size/2; i++) {
			int[] temp = new int[i];
			count = size % i;
			count2 = 1;
			for (int j = 0; j < i; j++) {
				temp[j] = arr[j];
			}
			for (int j = 1; j < size / i; j++) {
				can2 = true;
				for (int k = 0; k < i; k++) {
					if (temp[k] != arr[j * i + k]) {
						can2 = false;
						break;
					}
				}
				if (can2) {
					count2++;
				} else {
					count += i;
					if (count2 > 1 && count2 < 10) {
						count++;
					} else if (count2 >= 10 && count2 < 100) {
						count += 2;
					} else if (count2 >= 100) {
						count += 3;
					}
					count2 = 1;
					for (int k = 0; k < i; k++) {
						temp[k] = arr[j * i + k];
					}
				}
			}
			count += i;
			if (count2 > 1 && count2 < 10) {
				count++;
			} else if (count2 >= 10 && count2 < 100) {
				count += 2;
			} else if (count2 >= 100 && count2 <1000) {
				count += 3;
			}else if(count2==1000) {
				count+=4;
			} 

			if (count < answer) {
				answer = count;
			}

		}

		return answer;
    }
}
