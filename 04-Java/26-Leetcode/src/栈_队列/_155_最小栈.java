package 栈_队列;

import java.util.Stack;

public class _155_最小栈 {
	Stack<Integer> stack;
	Stack<Integer> minStack;
	
	public _155_最小栈() {
		stack = new Stack<>();
		minStack = new Stack<>();
    }
    
    public void push(int x) {
    	stack.push(x);
    	if (minStack.isEmpty()) {
			minStack.push(x);
		} else {
			minStack.push(Math.min(x, minStack.peek()));
		}
    }
    
    public void pop() {
    	stack.pop();
    	minStack.pop();
    }
    
    public int top() {
    	return stack.peek();
    }
    
    public int getMin() {
    	return minStack.peek();
    }
}
