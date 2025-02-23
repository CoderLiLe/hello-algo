package 栈_队列;

import java.util.LinkedList;
import java.util.Queue;

public class _225用队列实现栈 {
    Queue<Integer> queue;

    public _225用队列实现栈() {
        queue = new LinkedList<>();
    }

    // 每 offer 一个数（A）进来，都重新排列，把这个数（A）放到队列的队首
    public void push(int x) {
        queue.offer(x);
        int size = queue.size();
        // 移动除了 A 的其它数
        while (size-- > 1)
            queue.offer(queue.poll());
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
