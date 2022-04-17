public class CoinChangeMinSolution {

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        int amount = 11;
        System.out.println(coinChange(coins, amount));
    }

    /**
     * 步骤1：定义dp[j]，dp[j]的含义是凑出j金额的最小方案数
     * 步骤2：找到状态转移方程，dp[j] = min(dp[j], dp[j - coins[i]] + 1)，dp[j]的结果，一种是dp[j-conins[i]]再加上一枚硬币的情况，一种就是dp[j]本身，这俩这需要取最小值
     * 步骤3：初始化，dp[0]=1,dp[1……j]=INT_MAX，这里最大值的初始化很关键，如果设置为0就会把正确的值覆盖
     * 步骤4：确定遍历顺序，由于这里取的是最小组合个数，不在乎是组合还是排列，因此先遍历conins或先遍历金额都可以，这里为了方便先遍历金额
     * 步骤5：公式推导
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 1; i < amount + 1; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (int coin : coins) {
            //外部遍历所有的金币情况
            for (int valueIndex = coin; valueIndex < amount + 1; valueIndex++) {
                //内部遍历可能凑出的金额数的最小方案，最小金额数第一个是最小的金币值
                if (dp[valueIndex - coin] != Integer.MAX_VALUE) {
                    //如果最新的金额是初始化值，不参与比较，原因是这个情况肯定是当前的金额是最终值
                    dp[valueIndex] = Math.min(dp[valueIndex - coin] + 1, dp[valueIndex]);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
