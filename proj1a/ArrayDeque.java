public class ArrayDeque<T> {
    private T[] items;
    private int size;
//    private int size;
    public int next_first, next_last;
    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        next_first = 0;
        next_last = 1;
    }

    public int addOne(int a){
        return (a + 1) % items.length;
    }

    public int addOne(int a, T[] x){
        return (a + 1) % x.length;
    }

    public int subOne(int a){
        return (a - 1 + items.length) % items.length;
    }

    private void resize(){
        if(size == items.length){
            expand();
        }
        if(size <= items.length * 0.25 && items.length > 8){
            reduce();
        }

//        T[] a = (T[]) new Object[capacity];
//        System.arraycopy(items, 0, a, 0, size);
//        items = a;
    }
    private void expand(){
        resize_helper(size * 2);
    }

    private void reduce(){
        resize_helper(size / 2);
    }

    private void resize_helper(int capacity){
        T[] a = items;
        items = (T[]) new Object[capacity];
        int begin = addOne(next_first);
        int end = subOne(next_last);
        next_first = 0;
        next_last = 1;
        for(int i = begin; i != end; i = addOne(i, a)){
            items[next_last] = a[i];
            next_last = addOne(next_last);
        }
        items[next_last] = a[end];
        next_last = addOne(next_last);
    }


    public void addFirst(T x){
        resize();
        items[next_first] = x;
        size += 1;
        next_first = subOne(next_first);
    }

    public void addLast(T x){
        resize();
        items[next_last] = x;
        size += 1;
        next_last = addOne(next_last);
    }

    public boolean isEmpty(){
//        if(size == 0)
//        {
//            return true;
//        }
//        return false;
        return  size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for(int i = next_first + 1; i != next_last; i = addOne(i)){
            System.out.print(items[i] + " ");
        }
    }

    public void removeFirst(){
        resize();
        size -= 1;
        next_first = addOne(next_first);
    }

    public void removeLast(){
        resize();
        size -= 1;
        next_last = subOne(next_last);
    }

    public T get(int x){
        if(x >= size || x < 0 || isEmpty()){
            return null;
        }
//        int i = next_first;
//        while(x > 0){
//            i = addOne(i);
//            x -= 1;
//        }
        x = (addOne(next_first) + x) % items.length;
        return items[x];
    }
    public static void main(String[] args){
        ArrayDeque<Integer> A = new ArrayDeque<Integer>();
        A.addFirst(2);
        A.addFirst(3);
        A.addFirst(4);
        A.addFirst(5);
        A.addLast(1);
        A.printDeque();
        System.out.println();
        System.out.println(A.get(2));
    }

}
