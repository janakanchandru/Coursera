//https://github.com/SawyerSun/Algorithms-and-Data-Structures/blob/master/coursera-algorithms-princeton/src/main/java/two/RandomizedQueue.java

import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;


public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int size = 0;

    /**
     * Initializes an empty queue and sets size to zero
     */
    public RandomizedQueue() {
        size = 0;
        items = (Item[]) new Object[1];
    }

    /**
     * Checks if queue is empty
     * @return true if queue is empty, false if not
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * returns number of items in queue
     * @return number of items in queue
     */
    public int size() {
        return size;
    }

    // Changes size of queue
    private void resizeArray(int length) {
        Item[] newItems = (Item[]) new Object[length];

        for (int x=0; x<size; x++) {
            newItems[x] = items[x];
        }

        items = newItems;
    }

    /**
     * Adds the item to the queue
     * @param item the item to add
     * @throws java.lang.IllegalArgumentException if null item is passed
     */
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("cannot enqueue null argument");

        if (size == items.length) {
            resizeArray(items.length * 2);
        }
        items[size++] = item;
    }

    /**
     * remove and return a random item
     * @return item that is removed
     * @throws java.util.NoSuchElementException if queue is empty
     */
    public Item dequeue() {
        if (size==0) throw new java.util.NoSuchElementException("Queue is empty!");

        if (items.length == size*4) {
            resizeArray(items.length / 2);
        }

        int index = StdRandom.uniform(size);
        Item returnItem = items[index];
        items[index] = items[size-1];
        items[--size] = null;

        return returnItem;
    }

    /**
     * returns a random item (but do not remove it)
     * @return a random item but does not remove it from queue
     * @throws java.util.NoSuchElementException if queue is empty
     */
    public Item sample() {
        if (size == 0) throw new java.util.NoSuchElementException("Queue is empty!");

        return items[StdRandom.uniform(size)];
    }

    /**
     * return an independent iterator over items in random order
     * @return an independent iterator over items in random order
     */
    public Iterator<Item> iterator() {
        return new RandomizedArrayIterator();
    }

    //construct iterator, does not implement remove because it is optional
    private class RandomizedArrayIterator implements Iterator<Item> {
        private int[] randomIndex = new int[size];
        private int current = 0;

        public RandomizedArrayIterator() {
            for (int x = 0; x < size; x++) {
                randomIndex[x] = x;
            }
            StdRandom.shuffle(randomIndex);
        }

        public boolean hasNext() {
            return current != size - 1;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException("remove() note supported");
        }

        public Item next() {
            if (items[randomIndex[current]]==null)
                throw new java.util.NoSuchElementException("No more elements to return");

            return items[randomIndex[++current]];
        }
    }

    /**
     * unit test the {@code RandomizedQueue} data type
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RandomizedQueue rq = new RandomizedQueue();
        rq.enqueue(46);
        rq.enqueue(19);
        System.out.println(rq.isEmpty());
        System.out.println(rq.dequeue());
    }
}
