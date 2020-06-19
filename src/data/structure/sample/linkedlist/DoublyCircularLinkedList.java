package data.structure.sample.linkedlist;

/**
 * 双向循环链表
 *
 * @author Neptune
 * @date 2020/6/18 15:02
 */
public class DoublyCircularLinkedList<T> {
    /**
     * 有效节点个数
     */
    private int size;
    /**
     * 第一个节点
     */
    private DoublyNode<T> first;

    /**
     * 创建一个空链表
     */
    public DoublyCircularLinkedList() {
        this.size = 0;
        this.first = null;
    }

    /**
     * 创建一个带一个节点的链表
     *
     * @param data 第一个节点的数据
     */
    public DoublyCircularLinkedList(T data) {
        this.size = 0;
        init(data);
    }

    /**
     * 初始化第一个节点，并形成闭环
     *
     * @param data 初始化节点元素
     */
    private void init(T data) {
        this.first = new DoublyNode<>(data, null, null);
        this.first.setNext(this.first);
        this.first.setPre(this.first);
        this.size += 1;
    }

    /**
     * 获取有效元素个数
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
     * 获取要操作的目标节点
     *
     * @param index 目标节点下标
     */
    private DoublyNode<T> getTargetNode(int index) {
        DoublyNode<T> current = this.first;
        if (index <= size / 2) {
            // 目标在前半部分，从前往后遍历快些
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        } else {
            // 目标在后半部分，从后往前遍历快些
            for (int i = 0; i < size - index; i++) {
                current = current.getPre();
            }
        }
        return current;
    }

    /**
     * 在指定位置插入元素
     */
    public boolean insert(int index, T data) {
        if (index < 0 || index > size) {
            return false;
        }
        // 没有初始化过，index只可能是0
        if (null == this.first) {
            init(data);
            return true;
        }
        DoublyNode<T> newNode = new DoublyNode<>(data, null, null);
        DoublyNode<T> current = getTargetNode(index);
        newNode.setNext(current);
        newNode.setPre(current.getPre());
        current.getPre().setNext(newNode);
        current.setPre(newNode);
        if (index == 0) {
            this.first = newNode;
        }
        this.size += 1;

        return true;
    }

    /**
     * 在链表最前端插入元素
     */
    public boolean prepend(T data) {
        return insert(0, data);
    }

    /**
     * 在链表最尾端插入元素
     */
    public boolean append(T data) {
        return insert(size, data);
    }

    /**
     * 返回并从链表中删除指定位置的元素
     */
    public T poll(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        DoublyNode<T> current = getTargetNode(index);
        current.getNext().setPre(current.getPre());
        current.getPre().setNext(current.getNext());

        if (index == 0) {
            this.first = current.getNext();
        }
        this.size -= 1;

        return current.getData();
    }

    /**
     * 返回并从链表中删除第一个元素
     */
    public T pollFirst() {
        return poll(0);
    }

    /**
     * 返回并从链表中删除末尾元素
     */
    public T pollLast() {
        return poll(size - 1);
    }

    /**
     * 获取但不删除指定位置的元素
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        return getTargetNode(index).getData();
    }

    /**
     * 获取但不删除第一个元素
     */
    public T getFirst() {
        return this.first.getData();
    }

    /**
     * 获取但不删除末尾元素
     */
    public T getLast() {
        return this.first.getPre().getData();
    }

    /**
     * 修改指定位置的元素
     */
    public boolean update(int index, T data) {
        if (index < 0 || index >= size) {
            return false;
        }

        getTargetNode(index).setData(data);
        return true;
    }

    /**
     * 清空链表
     */
    public boolean clear() {
        this.first = null;
        this.size = 0;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<--->");
        if (size > 0) {
            DoublyNode<T> current = this.first;
            do {
                sb.append(current.getData()).append("<--->");
            }
            while ((current = current.getNext()) != this.first);
        }
        return sb.toString();
    }
}

class TestDoublyCircularLinkedList {
    public static void main(String[] args) {
        DoublyCircularLinkedList<Integer> list = new DoublyCircularLinkedList<>();
        System.out.println(list.append(1));
        System.out.println(list.append(2));
        System.out.println(list.prepend(3));
        System.out.println(list.insert(1, 4));
        System.out.println(list.append(5));
        System.out.println(list);
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        System.out.println(list.get(2));
        System.out.println(list);
        System.out.println(list.update(list.size() - 1, 999));
        System.out.println(list);
        System.out.println(list.update(0, 111));
        System.out.println(list);
        System.out.println(list.update(2, 666));
        System.out.println(list);
        System.out.println(list.pollFirst());
        System.out.println(list);
        System.out.println(list.pollLast());
        System.out.println(list);
        System.out.println(list.poll(1));
        System.out.println(list);
        System.out.println(list.clear());
        System.out.println(list);
    }
}

