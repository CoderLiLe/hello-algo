package 动态规划.剑指offer;

public class 面63_股票的最大利润 {
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length < 2) return 0;
		
		int min = prices[0];
		int maxDiff = 0;
		if (prices[1] > min) {
			maxDiff = prices[1] - min;
		}
		
		for (int i = 2; i < prices.length; i++) {
			if (prices[i - 1] < min) {
				min = prices[i - 1];
			}
			
			int currDiff = prices[i] - min;
			if (currDiff > maxDiff) {
				maxDiff = currDiff;
			}
		}
		return maxDiff;
    }
	
	public int maxProfit2(int[] prices) {
		if (prices == null || prices.length < 2) return 0;
		
		// 前面扫描过的最小价格
		int minPrice = prices[0];
		// 前面扫描过的最大利润
		int maxProfit = 0;
		// 扫描所有的价格
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] < minPrice) {
				minPrice = prices[i];
			} else { // 把第 i 天的股票卖出
				maxProfit = Math.max(maxProfit, prices[i] - minPrice);
			}
		}
		return maxProfit;
    }
}
