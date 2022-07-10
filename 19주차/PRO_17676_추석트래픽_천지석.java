    class Solution {
        public int solution(String[] lines) {
            int[][] array = new int[lines.length][2];
            int start = Integer.MAX_VALUE;
            int end = 0;

            for (int i = 0; i < lines.length; i++) {
                double x = 60 * 60 * Integer.parseInt(lines[i].substring(11, 13))
                        + 60 * Integer.parseInt(lines[i].substring(14, 16))
                        + Integer.parseInt(lines[i].substring(17, 19))
                        + Double.parseDouble("0." + lines[i].substring(20, 23));
                double y = Double.parseDouble(lines[i].substring(24, lines[i].length() - 1));

                array[i][0] = (int) (1000 * x - 1000 * y + 1);
                array[i][1] = (int) (1000 * x);

                if (array[i][0] < 0)
                    array[i][0] = 0;

                if (start > array[i][0])
                    start = array[i][0];
                start = Math.min(start, array[i][0]);
                end = Math.max(end, array[i][1]);
            }

            int max = 0;

            for (int[] value : array) {
                for (int k = 0; k < 2; k++) {
                    int count = 0;

                    for (int[] ints : array) {
                        if (value[k] <= ints[0] && value[k] + 999 >= ints[0]
                                || value[k] + 999 >= ints[1] && ints[1] >= value[k]
                                || value[k] >= ints[0] && value[k] + 999 <= ints[1])
                            count++;
                    }
                    max = Math.max(max, count);
                }
            }

            return max;
        }
    }
