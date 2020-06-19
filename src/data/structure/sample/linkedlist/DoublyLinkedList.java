package data.structure.sample.linkedlist;

/**
 * 带头节点的双向链表
 *
 * @author Neptune
 * @date 2020/6/18 11:54
 */
public class DoublyLinkedList<T> {
    /**
     * 头节点，不存储数据
     */
    private DoublyNode<T> head;
    /**
     * 当前链表有效元素个数
     */
    private int size;

    public DoublyLinkedList() {
        this.head = new DoublyNode<>(null, null, null);
        this.size = 0;
    }

    /**
     * 得到链表元素个数
     */
    public int size() {
        return this.size;
    }

    /**
     * 判断链表是否为空
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * 在指定位置插入元素
     */
    public boolean insert(int index, T data) {
        if (index < 0 || index > this.size) {
            return false;
        }
        DoublyNode<T> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        DoublyNode<T> newNode = new DoublyNode<>(data, current, current.getNext());
        if (index != size) {
            current.getNext().setPre(newNode);
        }
        current.setNext(newNode);
        this.size += 1;

        return true;
    }

    /**
     * 向链表末尾插入元素
     */
    public boolean append(T data) {
        return insert(size, data);
    }

    /**
     * 向链表头部插入元素
     */
    public boolean prepend(T data) {
        return insert(0, data);
    }

    /**
     * 删除并返回指定位置的元素
     */
    public T poll(int index) {
        if (isEmpty() || index < 0 || index >= size) {
            return null;
        }
        DoublyNode<T> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        DoublyNode<T> resultNode = current.getNext();
        current.setNext(resultNode.getNext());
        if (index != size - 1) {
            current.getNext().setPre(current);
        }
        this.size -= 1;

        return resultNode.getData();
    }

    /**
     * 删除并返回链表末尾元素
     */
    public T pollLast() {
        return poll(size - 1);
    }

    /**
     * 删除并返回链表头部元素
     */
    public T pollFirst() {
        return poll(0);
    }

    /**
     * 查询指定位置元素
     */
    public T get(int index) {
        if (isEmpty() || index < 0 || index >= size) {
            return null;
        }
        DoublyNode<T> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        DoublyNode<T> resultNode = current.getNext();

        return resultNode.getData();
    }

    /**
     * 查询链表末尾元素
     */
    public T getLast() {
        return get(size - 1);
    }

    /**
     * 查询链表头部元素
     */
    public T getFirst() {
        return get(0);
    }

    /**
     * 更新指定位置元素
     */
    public boolean update(int index, T data) {
        if (index < 0 || index >= this.size) {
            return false;
        }
        DoublyNode<T> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        DoublyNode<T> targetNode = current.getNext();
        targetNode.setData(data);

        return true;
    }

    /**
     * 查找指定元素的位置，-1表示未找到
     */
    public int findIndexOf(T data) {
        DoublyNode<T> current = this.head;
        int index = 0;
        while ((current = current.getNext()) != null) {
            if (current.getData().equals(data)) {
                break;
            }
            index++;
        }

        return size == index ? -1 : index;
    }

    /**
     * 清空所有元素
     */
    public boolean clear() {
        this.head.getNext().setPre(null);
        this.head.setNext(null);
        this.size = 0;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("head: ");
        DoublyNode<T> current = this.head;
        while ((current = current.getNext()) != null) {
            if (current.getNext() == null) {
                sb.append(current.getData());
            } else {
                sb.append(current.getData()).append("<--->");
            }
        }
        sb.append(" :end");
        return sb.toString();
    }
}

class TestDoublyLinkedList {
    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        System.out.println(list.append(1));
        System.out.println(list.append(2));
        System.out.println(list.append(3));
        System.out.println(list.append(4));
        System.out.println(list.append(5));
        System.out.println(list);
        System.out.println(list.prepend(0));
        System.out.println(list.prepend(-1));
        System.out.println(list.prepend(-2));
        System.out.println(list);
        System.out.println(list.insert(5, 1000));
        System.out.println(list);
        System.out.println(list.get(5));
        System.out.println(list.getLast());
        System.out.println(list.getFirst());
        System.out.println(list);
        System.out.println(list.poll(5));
        System.out.println(list);
        System.out.println(list.pollFirst());
        System.out.println(list);
        System.out.println(list.pollLast());
        System.out.println(list);
        System.out.println(list.findIndexOf(7));
        System.out.println(list.findIndexOf(3));
        System.out.println(list.update(0, 999));
        System.out.println(list.update(list.size() - 1, 888));
        System.out.println(list.update(7, 777));
        System.out.println(list);
        System.out.println(list.clear());
        System.out.println(list);
    }
}
