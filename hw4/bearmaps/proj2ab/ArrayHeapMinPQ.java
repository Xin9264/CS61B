package bearmaps.proj2ab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private ArrayList<PriorityNode> heap;
    private HashMap<T, Integer> arrayIndex;

    public ArrayHeapMinPQ(){
        heap = new ArrayList<>();
        arrayIndex = new HashMap<>();
    }

    private class PriorityNode implements Comparable<PriorityNode>{
        private T item;
        private double priority;
        PriorityNode(T i, double p){
            this.item = i;
            this.priority = p;
        }
        T getItem(){
            return item;
        }
        double getPriority(){
            return priority;
        }
        
        double setPriority(double priority){
            double ole_priority = priority;
            this.priority = priority;
            return ole_priority;
        }

        @Override
        public int compareTo(PriorityNode o) {
            if (o == null){
                return -1;
            }
            return Double.compare(this.getPriority(), o.getPriority());
        }

        @Override
        public boolean equals(Object o){
            if (o == null || o.getClass() != this.getClass()){
                return false;
            }
            return ((PriorityNode) o).getItem().equals(this.getItem());
        }
    }
    private int parent(int i){
        return (i - 1) / 2;
    }
    private int leftChild(int i){
        return i * 2 + 1;
    }
    private int rightChild(int i){
        return i * 2 + 2;
    }

    private void swap(int i, int j){
        if (i > size() || j > size()){
            throw new IllegalArgumentException();
        }
        PriorityNode temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
        arrayIndex.put(heap.get(i).item, i);
        arrayIndex.put(heap.get(j).item, j);
    }
    private void float_up(int i){
        if (i == 0){
            return;
        }
        int parent = parent(i);
        if (heap.get(i).compareTo(heap.get(parent)) < 0){
            swap(i, parent);
            float_up(parent);
        }
    }
    private void sink(int i){
        while (leftChild(i) < size()){
            int smallest = leftChild(i);
            if (rightChild(i) < size() && less(rightChild(i), leftChild(i))){
                smallest = rightChild(i);
            }
            if (less(i, smallest)){
                break;
            }
            swap(smallest, i);
            i = smallest;
        }
    }
    private boolean less(int i, int j){
        return heap.get(i).compareTo(heap.get(j)) < 0;
    }
    @Override
    public void add(T item, double priority) {
        if (contains(item)){
            throw new IllegalArgumentException();
        }
        heap.add(new PriorityNode(item, priority));
        arrayIndex.put(heap.get(size()-1).item, size()-1);
        float_up(size()-1);
    }

    @Override
    public boolean contains(T item) {
        return arrayIndex.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (size() == 0){
            throw new NoSuchElementException();
        }
        return heap.get(0).item;
    }

    @Override
    public T removeSmallest() {
        if (size() == 0){
            throw new NoSuchElementException();
        }
        swap(0, size()-1);
        T smallest = heap.remove(size()-1).item;
        arrayIndex.remove(smallest);
        sink(0);
        return smallest;
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)){
            throw new NoSuchElementException();
        }
        int i = arrayIndex.get(item);
        if (priority > heap.get(i).setPriority(priority)){
            sink(i);
        } else {
            float_up(i);
        }

    }
}
