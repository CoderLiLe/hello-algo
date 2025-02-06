package dataStructure.design;

import java.util.HashMap;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/maximum-frequency-stack/
 *
 * 最大频率栈
 */

public class FreqStack {
    // 记录 FreqStack 中元素的最大频率
    private int maxFreq = 0;
    // 记录 FreqStack 中每个 val 对应的出现频率，后文成为 VF 表
    private HashMap<Integer, Integer> valToFreq = new HashMap<>();
    // 记录 FreqStack 中每个频率 freq 对应的 val 列表，后文称为 FV 表
    private HashMap<Integer, Stack<Integer>> freqToVals = new HashMap<>();

    public FreqStack() {

    }

    public void push(int val) {
        // 修改 VF 表：val 对应的 freq 加1
        Integer freq = valToFreq.getOrDefault(val, 0) + 1;
        valToFreq.put(val, freq);
        // 修改 FV 表：在 freq 对应的列表加上 val
        freqToVals.putIfAbsent(freq, new Stack<>());
        freqToVals.get(freq).push(val);
        // 更新 maxFreq
        maxFreq = Math.max(maxFreq, freq);
    }

    public int pop() {
        // 修改 FV 表：pop 出一个 maxFreq 对应的元素 v
        Stack<Integer> vals = freqToVals.get(maxFreq);
        Integer v = vals.pop();
        // 修改 VF表：v 对应的 freq 减1
        Integer freq = valToFreq.get(v) - 1;
        // 更新 maxFreq
        if (vals.isEmpty()) {
            // 如果 maxFreq 对应的元素空了
            maxFreq--;
        }
        return v;
    }
}
