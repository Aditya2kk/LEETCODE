class Solution {
    public int distinctSubseqII(String s) {

        final long MOD = 1_000_000_007L;

        // dp[i] = number of distinct subsequences
        // including the empty subsequence
        long[] dp = new long[s.length() + 1];

        dp[0] = 1;

        // Store dp value before the previous occurrence
        // of each character
        long[] last = new long[26];

        for (int i = 1; i <= s.length(); i++) {

            int c = s.charAt(i - 1) - 'a';

            dp[i] = (2 * dp[i - 1] - last[c] + MOD) % MOD;

            last[c] = dp[i - 1];
        }

        // Remove empty subsequence
        return (int)((dp[s.length()] - 1 + MOD) % MOD);
    }
}