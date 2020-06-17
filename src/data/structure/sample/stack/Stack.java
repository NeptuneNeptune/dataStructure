package data.structure.sample.stack;

import data.structure.sample.queue.Queue;

import java.util.Arrays;

import static java.lang.reflect.Array.newInstance;

/**
 * 使用数组模拟栈结构
 *
 * @author Neptune
 * @date 2020/6/17 22:37
 */
public class Stack<T> {
    /**
     * 数组用来存储元素
     */
    private T[] body;
    /**
     * 栈顶指针，指向栈顶元素，初始值为-1
     */
    private int top;

    public Stack(int initialSize, Class<T> clazz) {
        this.body = (T[]) newInstance(clazz, initialSize);
        this.top = -1;
    }

    /**
     * 判断栈是否已满
     */
    public boolean isFull() {
        return top == body.length - 1;
    }

    /**
     * 判断栈是否为空
     */
    public boolean isEmpty() {
        return top < 0;
    }

    /**
     * 获取栈中元素个数
     */
    public int size() {
        return top + 1;
    }

    /**
     * 压栈
     */
    public boolean push(T t) {
        if (!isFull()) {
            body[++top] = t;
            return true;
        }
        return false;
    }

    /**
     * 弹栈
     */
    public T pop() {
        if (!isEmpty()) {
            return body[top--];
        }
        return null;
    }

    /**
     * 获取栈顶元素，但不弹出
     */
    public T peek() {
        if (!isEmpty()) {
            return body[top];
        }
        return null;
    }

    /**
     * 清空栈
     */
    public boolean clear() {
        top = -1;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i <= top; i++) {
            if (i == top) {
                sb.append(body[i]);
            } else {
                sb.append(body[i]).append(", ");
            }
        }
        sb.append("]<<");
        return sb.toString();
    }
}


class Test {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>(4, Integer.class);
        System.out.println(stack.push(1));
        System.out.println(stack.push(2));
        System.out.println(stack.push(3));
        System.out.println(stack.push(4));
        System.out.println(stack);
        System.out.println(stack.push(5));
        System.out.println(stack.peek());
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
        System.out.println("**************");
        System.out.println(stack.push(5));
        System.out.println(stack);
        System.out.println(stack.push(6));
        System.out.println(stack);
        System.out.println(stack.push(7));
        System.out.println(stack);
        System.out.println(stack.push(8));
        System.out.println(stack);
        System.out.println(stack.clear());
        System.out.println(stack);
    }
}
