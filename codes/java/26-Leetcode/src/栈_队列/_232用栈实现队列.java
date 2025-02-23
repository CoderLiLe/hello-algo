package 栈_队列;

import java.util.Stack;

public class _232用栈实现队列 {
    private Stack<Integer> stackIn;
    private Stack<Integer> stackOut;
    public _232用栈实现队列() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }

    public void push(int x) {
        stackIn.push(x);
    }

    public int pop() {
        checkStackOut();
        return stackOut.pop();
    }

    public int peek() {
        checkStackOut();
        return stackOut.peek();
    }

    private void checkStackOut() {
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
    }

    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }
}
