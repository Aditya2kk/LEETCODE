class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int left = 0;
        int ones = 0;

        String answer = "";

        for (int right = 0; right < s.length(); right++) {

            if (s.charAt(right) == '1') {
                ones++;
            }

            // Too many 1s
            while (ones > k) {
                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }

            // Exactly k 1s
            if (ones == k) {

                // Remove leading zeros
                while (s.charAt(left) == '0') {
                    left++;
                }

                String current = s.substring(left, right + 1);

                if (answer.equals("")
                        || current.length() < answer.length()
                        || (current.length() == answer.length()
                            && current.compareTo(answer) < 0)) {

                    answer = current;
                }
            }
        }

        return answer;
    }
}