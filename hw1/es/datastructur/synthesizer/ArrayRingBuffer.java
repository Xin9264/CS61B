package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T>{
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];

    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if(isFull()){
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last += 1;
        fillCount += 1;
        if (last > capacity() - 1) {
            last = last - capacity();
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (rb[first] == null) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T dequeue = rb[first];
        rb[first] = null;
        first += 1;
        fillCount -= 1;
        if (first > rb.length - 1) {
            first = first - rb.length;
        }
        return dequeue;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if (rb[first] == null) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    @Override
    public int capacity(){
        return rb.length;
    }

    @Override
    public int fillCount(){
        return fillCount;
    }
    @Override
    public boolean isEmpty(){
        return fillCount() == 0;
    }

    @Override
    public Iterator<T> iterator(){
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T>{
        private int currPos;
        private int count;
        public ArrayRingBufferIterator(){
            currPos = 0;
            count = 0;
        }
        @Override
        public boolean hasNext(){
            return count < fillCount();
        }
        @Override
        public T next(){
            T currItem = rb[currPos];
            currPos = (currPos + 1) % capacity();
            count += 1;
            return currItem;
        }
    }

    @Override
    public boolean equals(Object others){
        if (others == this){
            return true;
        }
        if (others == null){
            return false;
        }
        if (others.getClass() != this.getClass()){
            return false;
        }
        ArrayRingBuffer<T> o = (ArrayRingBuffer<T>) others;
        if(o.fillCount() != this.fillCount()){
            return false;
        }

        Iterator<T> this_iterator = this.iterator();
        Iterator<T> others_iterator = o.iterator();
        while (this_iterator.hasNext() && others_iterator.hasNext()){
            if (this_iterator.next() != others_iterator.next()){
                return false;
            }
        }
        return true;

    }
    public static void main(String[] args){
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(3);
        System.out.println(arb.capacity());
        System.out.println(arb.isEmpty());

    }
}
