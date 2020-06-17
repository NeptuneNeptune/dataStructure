package data.structure.sample.queue;

import static java.lang.reflect.Array.newInstance;

/**
 * 循环使用数组空间模拟队列
 *
 * @author Neptune
 * @date 2020/6/17 19:07
 */
public class Queue<T> {
    /**
     * 数组最大空间
     */
    private int maxSize;
    /**
     * 数组用来存放元素
     */
    private T[] body;
    /**
     * 头指针，指向队首元素
     */
    private int front;
    /**
     * 尾指针，指向队尾元素之后的下一个存放位置
     */
    private int rear;

    /**
     * 创建一个队列
     *
     * @param initialSize 初始化队列长度
     * @param clazz       队列元素类型
     */
    public Queue(int initialSize, Class<T> clazz) {
        this.maxSize = initialSize + 1;
        this.front = 0;
        this.rear = 0;
        this.body = (T[]) newInstance(clazz, this.maxSize);
    }

    /**
     * 判断队列是否已满
     */
    public synchronized boolean isFull() {
        return (this.rear + 1) % this.maxSize == this.front;
    }

    /**
     * 判断队列是否为空
     */
    public synchronized boolean isEmpty() {
        return this.rear != this.front;
    }

    /**
     * 判断队列有效元素个数
     */
    public synchronized int size() {
        return (this.rear + this.maxSize - this.front) % this.maxSize;
    }

    /**
     * 向队尾添加元素
     */
    public synchronized boolean offer(T t) {
        if (!isFull()) {
            this.body[this.rear] = t;
            this.rear = (this.rear + 1) % this.maxSize;
            return true;
        }
        return false;
    }

    /**
     * 移除并返回队首元素
     */
    public synchronized T poll() {
        if (isEmpty()) {
            T t = this.body[this.front];
            this.front = (this.front + 1) % this.maxSize;
            return t;
        }
        return null;
    }

    /**
     * 返回队首元素，不删除
     */
    public synchronized T peek() {
        if (isEmpty()) {
            return this.body[this.front];
        }
        return null;
    }

    /**
     * 清空队列
     */
    public synchronized boolean clear() {
        this.rear = 0;
        this.front = 0;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<<[");
        for (int i = 0; i < this.size(); i++) {
            if (i == this.size() - 1) {
                sb.append(this.body[(this.front + i) % this.maxSize]);
            } else {
                sb.append(this.body[(this.front + i) % this.maxSize]).append(", ");
            }
        }
        sb.append("]<<");
        return sb.toString();
    }
}


class Test {
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>(4, Integer.class);
        System.out.println(queue.offer(1));
        System.out.println(queue.offer(2));
        System.out.println(queue.offer(3));
        System.out.println(queue.offer(4));
        System.out.println(queue);
        System.out.println(queue.offer(5));
        System.out.println(queue.peek());
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue);
        System.out.println("**************");
        System.out.println(queue.offer(5));
        System.out.println(queue);
        System.out.println(queue.offer(6));
        System.out.println(queue);
        System.out.println(queue.offer(7));
        System.out.println(queue);
        System.out.println(queue.offer(8));
        System.out.println(queue);
        System.out.println(queue.clear());
        System.out.println(queue);
    }
}