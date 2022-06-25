
public class LinkedListDeque<Type> implements Deque<Type>{
    public class ListNode{
        public Type item;
        public ListNode priv;
        public ListNode next;

        public ListNode(Type x, ListNode n, ListNode p){
            item = x;
            next = n;
            priv = p;
        }

        public ListNode(ListNode n, ListNode p){
            priv = p;
            next = n;
        }
    }
//    private Type first;
    private int size;
    public ListNode sentinel = new ListNode(null, null);

    public LinkedListDeque(){
        sentinel.next = sentinel;
        sentinel.priv = sentinel;
        size = 0;
    }

    public void addFirst(Type item){
        ListNode OldFirst = sentinel.next;
        ListNode NewFirst = new ListNode(item, OldFirst, sentinel);
        sentinel.next = NewFirst;
        OldFirst.priv = NewFirst;
        size += 1;
    }

    public void addLast(Type item){
        ListNode OldLast = sentinel.priv;
        ListNode NewLast = new ListNode(item, sentinel, OldLast);
        sentinel.priv = NewLast;
        OldLast.next = NewLast;
        size += 1;
    }

    public boolean isEmpty(){
        return sentinel.next == sentinel && sentinel.priv == sentinel;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        ListNode temp = sentinel.next;
        while(temp.next != sentinel){
            System.out.print(temp.next.item + " ");
            temp = temp.next;
        }
        System.out.print("\n");
    }

    public Type removeFirst(){
        ListNode p;
        Type re = null;
        if (size != 0) {
            size -= 1;
            p = sentinel.next;
            p.next.priv = sentinel;
            sentinel.next = p.next;
            re = p.item;
        }
        return re;

    }

    public Type removeLast(){
        ListNode p;
       Type re = null;
       if(size != 0){
           p = sentinel.priv;
           sentinel.priv = p.priv;
           p.priv.next = sentinel;
           re = p.item;
       }
        return re;
    }

    public Type get(int index){
        if (index >= size){
            return null;
        }
        ListNode rst = sentinel;
        for(int i = 0; i < index ; i++){
            rst = rst.next;
        }
        return rst.item;
    }

    private Type recurisive_helper(ListNode start, int index){
        if(index == 0){
            return start.item;
        }
        return recurisive_helper(start.next, index-1);
    }
    public Type getRecurisive(int index){
        if(index >= size){
            return null;
        }
        return recurisive_helper(sentinel.next, index-1);
    }
    public static void main(String[] args){
        LinkedListDeque L = new LinkedListDeque();
        L.addFirst('e');
        L.addFirst('b');
//        L.addLast('c');
//        L.addLast('d');
//        L.addFirst('e');
        System.out.println(L.isEmpty());
        L.printDeque();
//        L.removeFirst();
//        System.out.println(L.removeFirst());
//        L.printDeque();
//        System.out.println(L.size());
//        L.removeLast();
//        L.printDeque();
//        System.out.println(L.get(2));
//        System.out.println(L.getRecurisive(2));


    }
}














