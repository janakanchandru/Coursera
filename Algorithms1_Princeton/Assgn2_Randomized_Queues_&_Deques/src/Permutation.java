import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * takes an integer k as a command-line argument; reads in a sequence of strings from standard input using
 * StdIn.readString(); and prints exactly k of them, uniformly at random
 */
public class Permutation {

    /**
     * see class description
     * @param args takes integer k as command-line argument
     */
    public static void main (String args[]) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomQueue = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            randomQueue.enqueue(StdIn.readString());
        }

        for (int x = 0; x < k; x++) {
            StdOut.println(randomQueue.dequeue());
        }
    }
}
