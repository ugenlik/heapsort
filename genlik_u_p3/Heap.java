/**
 * Heap data structure
 */
public class Heap {
    /**
     * The item class
     */
    public static class HeapItem {
        public String name;
        public int key;
        public HeapItem(String name, int key) {
            this.name = name;
            this.key = key;
        }
        public HeapItem() {
        }
    }

    /**
     * Number of items
     */
    private int count;
    
    /**
     * The heap array
     */
    private HeapItem[] ar;
    
    /**
     * The indices array
     */
    private int[] index;

    /**
     * Create a new heap
     * @param n The maximum capacity of the heap
     */
    public Heap(int n) {
        this.count = 0;
        ar = new HeapItem[n];
        index = new int[n];
        for (int i = 0; i < n; i++) {
            ar[i] = null;
        }
        for (int i = 0; i < n; i++) {
            index[i] = -1;
        }
    }
    
    /**
     * Swap two items from the heap
     * @param in The first item to swap
     * @param p The second item to swap
     */
    private void swap(int in, int p) {
        HeapItem h1 = ar[in];
        ar[in] = ar[p];
        ar[p] = h1;
        index[Integer.valueOf(ar[in].name)] = in;
        index[Integer.valueOf(ar[p].name)] = p;
    }

    /**
     * Move item up if smaller than its father
     * @param index Item to check
     */
    private void percolateUp(int index) {
        //Slid 14
        if (index > 0) {
            int p = (index - 1) / 2;
            if (ar[p].key > ar[index].key) {
                swap(index, p);
                percolateUp(p);
            }
        }
    }
    
    /**
     * Insert an item in the heap
     * @param hi The item to insert
     */
    public void insert(HeapItem hi) {
        count++;
        ar[count-1] = hi;
        index[Integer.valueOf(hi.name)] = count-1;
        percolateUp(count-1);
    }
    
    /**
     * return the i-th item
     * @param i The index of the item to return
     * @return The i-th item
     */
    public HeapItem at(int i) {
        return ar[i];
    }
    
    /**
     * Returns the size of the heap
     * @return The size of the heap
     */
    public int size() {
        return count;
    }
    
    /**
     * Pushes an item down if it's larger than one of its children
     * @param i The item to check
     */
    private void siftDown(int i) {
        int l = 2*i+1;
        int mv = i;
        if ((l < count) && (ar[l].key < ar[mv].key)) {
            mv = l;
        }
        if (((l+1) < count) && (ar[l+1].key < ar[mv].key)) {
            mv = l+1;
        }
        if (mv!=i) {
            swap(i, mv);
            siftDown(mv);
        }
    }
    
    /**
     * Removes the top of the heap (highest priority item)
     * @return The top of the heap
     */
    public HeapItem deleteMin() {
        HeapItem hi = ar[0];
        index[Integer.valueOf(hi.name)] = -1;
        swap(0, count-1);
        count--;
        if (count>1)
            siftDown(0);
        return hi;
    }
    
    /**
     * Returns the item with the given name or null if it's not found
     * @param name The name of the item to search
     * @return The item with the given name
     */
    public HeapItem find(String name) {
        int nm = Integer.valueOf(name);
        if (nm>=index.length) return null;
        if (index[nm] == -1) return null;
        return ar[index[nm]];
    }
    
    /**
     * Changes the key for a given item
     * @param name The name of the item to change
     * @param key The new value for the key
     */
    public void changeKey(String name, int key) {
        HeapItem hi = find(name);
        if (hi!=null) {
            hi.key = key;
            //Shift this item up or down as necessary to bring it to the right place
            siftDown(index[Integer.valueOf(name)]);
            percolateUp(index[Integer.valueOf(name)]);
        }
    }
}
