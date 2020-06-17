package data.structure.sample.linkedlist;

/**
 * 双向链表的节点
 *
 * @author Neptune
 * @date 2020/6/17 23:17
 */
public class DoublyNode<T> {
    /**
     * 存放数据元素
     */
    private T data;
    /**
     * 存放上一个节点地址的指针
     */
    private DoublyNode<T> pre;
    /**
     * 存放下一个节点地址的指针
     */
    private DoublyNode<T> next;

    public DoublyNode() {}

    public DoublyNode(T data, DoublyNode<T> pre, DoublyNode<T> next) {
        this.data = data;
        this.pre = pre;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public DoublyNode<T> getPre() {
        return pre;
    }

    public void setPre(DoublyNode<T> pre) {
        this.pre = pre;
    }

    public DoublyNode<T> getNext() {
        return next;
    }

    public void setNext(DoublyNode<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "DoublyNode{" +
                "data=" + data + '}';
    }
}
