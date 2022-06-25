/** Array based list.
 *  @author Josh Hug
 */

public class AList {
    private int[] items;
    private int size;
    /** Creates an empty list. */
    public AList() {
        items = new int[100];
        size = 0;
    }

    private void resize(int capacity){
//        if(size >= items.length){
            int[] a = new int[capacity];
            System.arraycopy(items, 0, a, 0, size);
//            a[size] = x;
            items = a;
//            size += 1;
//        }
    }

    /** Inserts X into the back of the list. */
    public void addLast(int x) {
        if(size >= items.length){
            resize(size*3);
        }
        items[size] = x;
        size += 1;
    }

    /** Returns the item from the back of the list. */
    public int getLast() {
        return items[size - 1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {
        if (i >= size)
            return 0;
        return items[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public int removeLast() {
        int x = getLast();
        size -= 1;
//        items[size] = 0;
        return x;
    }
} 