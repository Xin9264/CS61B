package bearmaps;


import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest{
    @Test
    public void teat_add(){
        ArrayHeapMinPQ<Integer> a = new ArrayHeapMinPQ<>();
        a.add(1, 0);
        a.add(453, 42);
        a.add(2, 3);
        a.add(34, 2523);
        a.add(243, 52);
        assertTrue(a.size()==5);

    }
    @Test
    public void test_contains(){
        ArrayHeapMinPQ<Integer> a = new ArrayHeapMinPQ<>();
        a.add(1, 0);
        a.add(453, 42);
        a.add(2, 3);
        a.add(34, 2523);
        a.add(243, 52);
        assertTrue(a.contains(453));
        assertTrue(a.contains(34));
        assertFalse(a.contains(1217));
    }

    @Test
    public void test_Smallest(){
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();
        minHeap.add(1, 1);
        minHeap.add(2, 2);
        minHeap.add(3, 3);
        minHeap.add(4, 4);
        minHeap.add(5, 0);
        minHeap.add(6, 3);
        assertTrue(minHeap.getSmallest() == 5);
        assertTrue(minHeap.removeSmallest() == 5);
        assertTrue(minHeap.removeSmallest() == 1);

    }
    @Test
    public void test_changePriority(){
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();
        minHeap.add(1, 1);
        minHeap.add(2, 2);
        minHeap.add(3, 3);
        minHeap.add(4, 4);
        minHeap.add(5, 0);
        minHeap.add(6, 3);
        assertTrue(minHeap.getSmallest() == 5);
//        minHeap.changePriority(6, 0);
        minHeap.changePriority(5, 5);
        minHeap.changePriority(6, 0);
        assertTrue(minHeap.getSmallest() == 6);
//        System.out.println(minHeap.getSmallest());
    }

    public static void main(String[] args) {
        ArrayHeapMinPQ<Integer> minPQ = new ArrayHeapMinPQ<>();
        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < 1000000; i += 1) {
            minPQ.add(i, i + 1);
        }
        for (int i = 0; i < 1000000; i += 1) {
            minPQ.changePriority(i, i + 2);
        }
        System.out.println("Total time elapsed: " + sw.elapsedTime() +  " seconds.");
    }
}
