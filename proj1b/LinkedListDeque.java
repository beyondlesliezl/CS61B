public class LinkedListDeque<T>implements Deque<T> {
    public class TNode {
        public TNode prev;
        public T item;
        public TNode next;

        public TNode(TNode p, T i, TNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private int size;
    private TNode sentinel;

    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        TNode temp = new TNode(sentinel, item, sentinel.next);
        sentinel.next.prev = temp;//更改原第一个节点的prev
        sentinel.next = temp;//更改哨兵节点的next
        size += 1;
    } //添加一个类型的项目T到双端队列的前面。

    @Override
    public void addLast(T item) {
        TNode temp = new TNode(sentinel.prev, item, sentinel);
        sentinel.prev.next = temp; //更改最后一个节点的next
        sentinel.prev = temp; //更改第一个节点的prev
        size += 1;
    }// 添加一个类型的项目T到双端队列的后面。

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }//如果双端队列为空，则返回 true，否则返回 false。

    @Override
    public int size() {
        return size;
    }//返回双端队列中的项目数。

    @Override
    public void printDeque() {
        if (size == 0) {
            return;
        }
        TNode ptr = sentinel;
        for (int i = 0; i < size; i++) {
            System.out.print(ptr.next.item + " ");
            ptr = ptr.next;
        }
    } //从头到尾打印双端队列中的项目，以空格分隔。

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T target = sentinel.next.item;
        size -= 1;
        sentinel.next.prev = sentinel.next;
        sentinel.next.next.prev = sentinel;//第二个节点的prev指向第一个节点
        sentinel.next = sentinel.next.next;//哨兵节点next指向第二个节点
        return target;
    } //删除并返回双端队列前面的项目。如果不存在这样的项目，则返回 null。

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T target = sentinel.prev.item;
        size -= 1;
        sentinel.prev.prev.next = sentinel;//更改倒数第二个节点的next
        sentinel.prev = sentinel.prev.prev;//更改哨兵节点的prev
        return target;
    }
    //删除并返回双端队列后面的项目。如果不存在这样的项目，则返回 null。

    @Override
    public T get(int index) {
        if (size == 0 || index > size - 1) {
            return null;
        }
        TNode ptr = sentinel;
        for (int i = 0; i < index; i++) {
            ptr = ptr.next;
        }
        return ptr.next.item;
    } //获取给定索引处的项目，其中 0 是前面的项目，1 是下一个项目，依此类推。如果不存在这样的项目，则返回 null。不能改变双端队列！
    //use iteration

    private T gethelper(int index, TNode node) {
        if (size == 0 || index > size - 1) {
            return null;
        }
        if (index == 0) {
            return node.next.item;
        }
        return gethelper(index - 1, node.next);
    }


    public T getRecursive(int index) {
        T target = gethelper(index, sentinel);
        return target;
    } //Same as get, but uses recursion.
}
