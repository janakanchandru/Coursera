import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF UF;
    private boolean[] openSites;
    private int size;
    private int numOpenSites;

    //create grid w/ all sites blocked
    public Percolation(int n) {

        if (n<1)
            throw new java.lang.IllegalArgumentException("n must be positive");

        size = n; //grid length
        UF = new WeightedQuickUnionUF(n*n+2);
        openSites = new boolean[n*n+2]; //0 is closed, 1 is open

    }

    private boolean checkInvalid(int x, int y) {

        return (x<1||y<1||x>size||y>size);

    }

    //convert row and col input to index
    private int xyToIndex(int row, int col) {

        if (checkInvalid(row, col))
            throw new java.lang.IllegalArgumentException();

        int index = (row - 1) * size + (col);

        if (index == 0) //when row = 1 and col = 1
            return 1;
        else
            return index;

    }

    //open site if not already opened
    public void open(int row, int col) {

        if (checkInvalid(row, col))
            throw new java.lang.IllegalArgumentException("row and/or col must be > 0 and < size");

        int index = xyToIndex(row, col);

        //open site if closed
        if (!openSites[index]) {
            openSites[index] = true;
            numOpenSites++;
        }

        //if in top row connect to virtual top site
        if (row == 1)
            UF.union(0, index);

        //if in bottom row connect to virtual bottom site
        if (row == size)
            UF.union((size*size)+1, index);

        //connect to left site if open
        if (col > 1 && isOpen(row, col-1))
            UF.union(xyToIndex(row, col-1), index);

        //connect to right site if open
        if (col < size && isOpen(row, col+1))
            UF.union(xyToIndex(row, col + 1), index);

        //connect to top site if open
        if (row > 1 && isOpen(row-1, col)) {
            UF.union(xyToIndex(row - 1, col), index);
        }

        //connect to bottom site if open
        if (row < size && isOpen(row+1, col))
            UF.union(xyToIndex(row+1, col), index);

    }

    //check if site is open
    public boolean isOpen(int row, int col) {

        if (checkInvalid(row, col))
            throw new java.lang.IllegalArgumentException("row and/or col must be > 0 and < size");

        int index = xyToIndex(row, col);
        return openSites[index];

    }

    //check if site is full
    public boolean isFull(int row, int col){

        if (checkInvalid(row, col)) throw new java.lang.IllegalArgumentException("row and/or col must be > 0 and < size");

        int index = xyToIndex(row, col);
        return UF.connected(index, 0);

    }

    public int numberOfOpenSites(){

        return numOpenSites;
    }

    public boolean percolates(){

        return UF.connected(0, (size*size)+1);
    }

    public static void main (String args[]) {
        //test client
    }
}
