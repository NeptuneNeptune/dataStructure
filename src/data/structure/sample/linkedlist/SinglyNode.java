package data.structure.sample.linkedlist;

/**
 * 单向链表的节点
 *
 * @author Neptune
 * @date 2020/6/17 23:16
 */
public class SinglyNode<T> {
    /**
     * 存放数据元素
     */
    private T data;
    /**
     * 存放下一个节点地址的指针
     */
    private SinglyNode<T> next;

    public SinglyNode() {
    }

    public SinglyNode(T data, SinglyNode<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public SinglyNode<T> getNext() {
        return next;
    }

    public void setNext(SinglyNode<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "SinglyNode{" +
                "data=" + data + '}';
    }
}
