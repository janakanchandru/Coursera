import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>{
    private Node head;
    private Node tail;
    private int size;

    //node class to implement doubly linked list
    private class Node {
        Node next;
        Node prev;
        Item item;

        Node() {
            next = null;
            prev = null;
            item = null;
        }

        Node(Node prev0, Node next0, Item item0) {
            next = next0;
            prev = prev0;
            item = item0;
        }
    }

    /**
     * constructs deque
     *
     * a deque is a generalization of a stack and a queue that supports adding and removing items from either the front
     * or the back of the data structure
     */
    public Deque() {
        head = new Node();
        tail = new Node();
        size = 0;
    }

    /**
     * checks if deque is empty
     * @return true if deque is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * returns size of deque
     * @return size of deque
     */
    public int size() {
        return size;
    }

    /**
     * Add item to beginning of deque
     * @param item item to add
     * @throws java.lang.IllegalArgumentException if null item is passed
     */
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("null argument passed");

        if (size == 0) {
            head.next = new Node(head, tail, item);
            tail.prev = head.next;
            size++;
        }
        else {
            Node add = new Node(head, head.next, item);
            head.next.prev = add;
            head.next = add;
            size++;
        }
    }

    /**
     * add item to end of deque
     * @param item item to add
     * @throws java.lang.IllegalArgumentException if null item is passed
     */
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("null argument passed");

        if (size == 0) {
            addFirst(item);
        }
        else {
            Node add = new Node(tail.prev, tail, item);
            tail.prev.next = add;
            tail.prev = add;
            size++;
        }
    }

    /**
     * removes and returns first item in deque
     * @return first item in deque
     * @throws java.util.NoSuchElementException if deque is empty
     */
    public Item removeFirst() {
        if (size == 0) throw new java.util.NoSuchElementException("Deque is empty");

        Node newFirst = head.next.next;
        Item returnItem = head.next.item;
        head.next = newFirst;
        newFirst.prev = head;
        size--;

        return returnItem;
    }

    /**
     * returns and removes last item in deque
     * @return last item in deque
     * @throws java.util.NoSuchElementException if deque is empty
     */
    public Item removeLast() {
        if (size == 0) throw new java.util.NoSuchElementException("Deque is empty");

        Node newLast = tail.prev.prev;
        Item returnItem = tail.prev.item;
        tail.prev = newLast;
        newLast.next = tail;
        size--;

        return returnItem;
    }

    /**
     * return an iterator over items in order from front to end
     * @return an iterator over items in order from front to end
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    //construct iterator, no remove() implemented as not required
    private class ListIterator implements Iterator<Item>{
        private Node current = head.next;
        private int count = 0;

        public boolean hasNext() {
            return count != size;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException("not supported");
        }

        public Item next() {
            if (count==size)
                throw new java.util.NoSuchElementException("No more elements to return");

            Item item = current.item;
            current = current.next;
            count++;
            return item;
        }

    }

    /**
     * unit test the {@code Deque} data type
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Deque<Integer> q = new Deque<>();

        System.out.println("Deque is empty: " + q.isEmpty());
        System.out.println("Deque size: " + q.size());

        q.addLast(0);
        q.addFirst(1);
        q.addFirst(2);
        q.addLast(3);

        System.out.println("Processing...");
        System.out.println("....");

        System.out.println();
        System.out.println("Deque is empty: " + q.isEmpty());
        System.out.println("Deque size: " + q.size());

        System.out.println();
        System.out.println("Processing...");
        System.out.println("....");

        System.out.println();
        System.out.print("Result: ");

        int s = q.size();

        for (int x=0; x<s; x++) {
            int returnItem = q.removeFirst();
            System.out.print(returnItem + " ");
        }
        System.out.println();
        System.out.println("Deque is empty: " + q.isEmpty());
        System.out.println("Deque size: " + q.size());

    }   // unit testing (optional)
}
