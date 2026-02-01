package VendingMachinePackage;

import java.util.*;

public class SimpleExchange implements ExchangeStrategy {

    @Override
    public boolean isPossible(CoinInventory cv, int amount) {
        return exchange(cv, amount) != null;
    }

    @Override
    public Map<Currency, Integer> exchange(CoinInventory cv, int amount) {
        Map<Currency, Integer> inventory = cv.getInv();

        List<Currency> coins = new ArrayList<>(inventory.keySet());
        coins.sort(Comparator.comparingInt(Currency::getVal)); // ascending

        int n = coins.size();
        boolean[][] dp = new boolean[n + 1][amount + 1];
        dp[0][0] = true;

        for (int i = 1; i <= n; i++) {
            Currency c = coins.get(i - 1);
            int val = c.getVal();
            int maxCount = inventory.get(c);

            for (int j = 0; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j]; 
                for (int k = 1; k <= maxCount && k * val <= j; k++) {
                    if (dp[i - 1][j - k * val]) {
                        dp[i][j] = true;
                        break;
                    }
                }
            }
        }

        if (!dp[n][amount]) return null; 

        Map<Currency, Integer> result = new HashMap<>();
        int j = amount;

        for (int i = n; i > 0; i--) {
            Currency c = coins.get(i - 1);
            int val = c.getVal();
            int maxCount = inventory.get(c);

            for (int k = 0; k <= maxCount && k * val <= j; k++) {
                if (dp[i - 1][j - k * val]) {
                    if (k > 0) result.put(c, k);
                    j -= k * val;
                    break;
                }
            }
        }
        return result;
    }
}
