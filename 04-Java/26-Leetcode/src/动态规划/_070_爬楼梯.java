package 动态规划;

import tools.Asserts;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数
 * 提示：
 * 1 <= n <= 45
 */
public class _070_爬楼梯 {

	public static void main(String[] args) {
		_070_爬楼梯 obj = new _070_爬楼梯();
		Asserts.test(obj.climbStairs1(3) == 3);
		Asserts.test(obj.climbStairs2(3) == 3);
		Asserts.test(obj.climbStairs3(3) == 3);
		Asserts.test(obj.climbStairs4(3) == 3);
	}
	
	/**
	 * 暴力法
	 * T = O(2^n), S = O(n)
	 */
	private int climbStairs1(int n) {
		return climb_stairs1(0, n);
    }

	/**
	 * 递归公式：climbStairs(i, n) = climbStairs(i + 1, n) + climbStairs(i + 2, n)
	 * T = O(2^n), S = O(n)
	 * 超出时间限制
	 *
	 * @param i 当前所在的台阶编号
	 * @param n 总共的台阶数量
	 * @return 从当前台阶到达顶部的路径总数
	 */
	private int climb_stairs1(int i, int n) {
	    // 打印当前遍历的台阶编号，用于调试
	    System.out.println(i);
	    // 如果当前台阶编号超过总台阶数，说明此路径不成立，返回0
	    if (i > n) {
	        return 0;
	    }
	    // 如果当前台阶编号等于总台阶数，说明到达顶部，返回1表示找到一条路径
	    if (i == n) {
	        return 1;
	    }
	    // 递归计算从当前台阶开始，到达顶部的路径总数
	    // 该总数等于从下一级台阶开始的路径数加上从下两级台阶开始的路径数
	    return climb_stairs1(i + 1,  n) + climb_stairs1(i + 2,  n);
	}
	
	// (0, 3) = (1, 3) + (2, 3)
	//        = (2, 3) + (3, 3) + (3, 3) + (4, 3)
	//        = (3, 3) + (4, 3) + (3, 3) + (3, 3) + (4, 3)
	//        = 1 + 0 + 1 + 1 + 0
	//        = 3
	
	/**
	 * 记忆化递归
	 * T = O(n), S = O(n)
	 */
	private int climbStairs2(int n) {
	    // 初始化记忆化数组，长度为n+1，以容纳所有可能的台阶数
	    int[] mem = new int[n + 1];
	    // 从第0级台阶开始尝试攀登，目标是第n级台阶
	    return climb_stairs2(0, n, mem);
	}

	/**
	 * 使用记忆化搜索优化的爬楼梯函数
	 * 该方法计算从第i级楼梯爬到第n级楼梯的所有可能路径数
	 * 为了避免重复计算已解决的子问题，使用一个记忆数组mem来存储结果
	 *
	 * @param i 当前所在的楼梯级别
	 * @param n 目标楼梯级别
	 * @param mem 记忆数组，用于存储已计算过的楼梯级别的路径数
	 * @return 从当前楼梯级别i到目标楼梯级别n的所有可能路径数
	 */
	private int climb_stairs2(int i, int n, int[] mem) {
	    // 超过目标楼梯级别，当前路径无效
	    if (i > n) {
	        return 0;
	    }
	    // 达到目标楼梯级别，找到一条有效路径
	    if (i == n) {
	        return 1;
	    }
	    // 检查记忆数组中是否已有结果，避免重复计算
	    if (mem[i] > 0) {
	        return mem[i];
	    }
	    // 计算当前楼梯级别的路径数，并存储到记忆数组中
	    mem[i] = climb_stairs2(i + 1, n, mem) + climb_stairs2(i + 2, n, mem);
	    return mem[i];
	}
	
	/**
	 * 使用动态规划的方法计算爬楼梯的方案数
	 * 递推公式：dp[i] = dp[i - 1] + dp[i - 2]
	 * 时间复杂度T = O(n)，空间复杂度S = O(n)
	 *
	 * @param n 楼梯的总数
	 * @return 爬楼梯的方案数
	 */
	private int climbStairs3(int n) {
	    // 当楼梯总数为1时，直接返回1种方案
	    if (n == 1) return 1;

	    // 初始化动态规划数组，长度为楼梯总数加1，以便直接使用索引n
	    int[] dp = new int[n + 1];

	    // 定义初始状态：1个楼梯有1种方案，2个楼梯有2种方案
	    dp[1] = 1;
	    dp[2] = 2;

	    // 从第3个楼梯开始，每个楼梯的方案数是前两个楼梯方案数之和
	    for (int i = 3; i <= n; i++) {
	        dp[i] = dp[i - 1] + dp[i - 2];
	    }

	    // 返回到达n个楼梯时的方案数
	    return dp[n];
	}
	
	/**
	 * 使用动态规划的方法计算爬楼梯的方案数，使用迭代的方式优化了空间复杂度
	 * 计算斐波那契数列中的第n个数
	 * T = O(n), S = O(1)
	 *
	 * @param n 斐波那契数列中的位置，从1开始计数
	 * @return 斐波那契数列中第n个数的值
	 */
	private int climbStairs4(int n) {
	    // 当n为1时，直接返回1，因为斐波那契数列的第一个数是1
	    if (n == 1) return 1;
	    // 初始化前两个斐波那契数
	    int first = 1;
	    int second = 2;
	    // 从第三个数开始计算，直到第n个数
	    for (int i = 3; i <= n; i++) {
	        // 计算当前斐波那契数
	        int third = first + second;
	        // 更新first和second，为下一次迭代做准备
	        first = second;
	        second = third;
	    }
	    // 返回第n个斐波那契数
	    return second;
	}

}
