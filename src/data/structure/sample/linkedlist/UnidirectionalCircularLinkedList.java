package data.structure.sample.linkedlist;

/**
 * 不带头结点的单向循环链表
 *
 * @author Neptune
 * @date 2020/6/18 15:01
 */
public class UnidirectionalCircularLinkedList<T> {
    /**
     * 有效节点个数
     */
    private int size;
    /**
     * 第一个节点
     */
    private SinglyNode<T> first;
    /**
     * 最后一个节点
     */
    private SinglyNode<T> last;

    /**
     * 创建一个空链表
     */
    public UnidirectionalCircularLinkedList() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    /**
     * 创建一个带一个节点的链表
     *
     * @param data 第一个节点的数据
     */
    public UnidirectionalCircularLinkedList(T data) {
        this.size = 0;
        init(data);
    }

    /**
     * 初始化第一个节点，并形成闭环
     *
     * @param data 初始化节点元素
     */
    private void init(T data) {
        this.first = new SinglyNode<>(data, null);
        this.first.setNext(this.first);
        this.last = this.first;
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
        SinglyNode<T> current = this.last;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        SinglyNode<T> newNode = new SinglyNode<>(data, current.getNext());
        current.setNext(newNode);
        if (index == 0) {
            this.first = newNode;
        }
        if (index == size) {
            this.last = newNode;
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
        SinglyNode<T> current = this.last;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        SinglyNode<T> resultNode = current.getNext();
        current.setNext(resultNode.getNext());
        if (index == 0) {
            this.first = current.getNext();
        }
        if (index == size - 1) {
            this.last = current;
        }
        this.size -= 1;

        return resultNode.getData();
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
        SinglyNode<T> current = this.first;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        return current.getData();
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
        return this.last.getData();
    }

    /**
     * 修改指定位置的元素
     */
    public boolean update(int index, T data) {
        if (index < 0 || index >= size) {
            return false;
        }
        SinglyNode<T> current = this.first;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        current.setData(data);
        return true;
    }

    /**
     * 清空链表
     */
    public boolean clear() {
        this.first = null;
        this.last = null;
        this.size = 0;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-->");
        if (size > 0) {
            SinglyNode<T> current = this.first;
            do {
                sb.append(current.getData()).append("-->");
            }
            while ((current = current.getNext()) != this.first);
        }
        return sb.toString();
    }
}

class TestUnidirectionalCircularLinkedList {
    public static void main(String[] args) {
        UnidirectionalCircularLinkedList<Integer> list = new UnidirectionalCircularLinkedList<>();
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
