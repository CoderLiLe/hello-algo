package 栈_队列;

import java.util.ArrayDeque;
import java.util.Deque;

public class _225用队列实现栈2 {
    // Deque 接口继承了 Queue 接口
    // 所以 Queue 中的 add、poll、peek等效于 Deque 中的 addLast、pollFirst、peekFirst
    Deque<Integer> queue;

    public _225用队列实现栈2() {
        queue = new ArrayDeque<>();
    }

    public void push(int x) {
        queue.addLast(x);
    }

    public int pop() {
        int size = queue.size();
        size--;
        // 将 que1 导入 que2 ，但留下最后一个值
        while (size-- > 0) {
            queue.addLast(queue.peekFirst());
            queue.pollFirst();
        }

        int res = queue.pollFirst();
        return res;
    }

    public int top() {
        return queue.peekLast();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
