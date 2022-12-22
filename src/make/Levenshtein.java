package make;

public class Levenshtein {
    public static int getDistance(ValueLog v1, ValueLog v2) {
        int len1 = v1.getValueLog().size();
        int len2 = v2.getValueLog().size();
        int[][] dp = new int[len2 + 1][len1 + 1];
        // dp[0][0] = 0;
        for (int i = 1; i <= len1; ++i) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= len2; ++i) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= len2; ++i) {
            for (int j = 1; j <= len1; ++j) {
                if(v1.getValueLog().get(j-1).equals(v2.getValueLog().get(i-1))){
                //if (s1.charAt(j - 1) == s2.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1,
                            Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }
        return dp[len2][len1];
    }
}
