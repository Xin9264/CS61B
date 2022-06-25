
public class LinkedListDeque<Type> {
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
    public ListNode sent_front = new ListNode(null, null);
    public ListNode sent_back = new ListNode(null, null);

    public LinkedListDeque(){
//        first = new ListNode(x, null, null);
//        first.priv = sentinel;
//        sentinel.next = first;
//        ListNode L = new ListNode(x, sent_front, sent_front);
        sent_front.next = sent_back;
        sent_back.priv = sent_front;
        size = 0;
    }

    public void addFirst(Type item){
        ListNode L_ = new ListNode(item, null, sent_front);
        L_.next = sent_front.next;
        sent_front.next.priv = L_;
        sent_front.next = L_;
        size += 1;
    }

    public void addLast(Type item){
        ListNode L_ = new ListNode(item, sent_back, null);
        L_.priv = sent_back.priv;
        sent_back.priv.next = L_;
        sent_back.priv = L_;
        size += 1;
    }

    public boolean isEmpty(){
        if(sent_front.next == sent_front && sent_front.priv == sent_back){
            return true;
        }
        else {
            return false;
        }
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        ListNode temp = sent_front;
        while(temp.next != sent_back){
            System.out.print(temp.next.item + " ");
            temp = temp.next;
        }
        System.out.print("\n");
    }

    public void removeFirst(){
        sent_front.next = sent_front.next.next;
        sent_front.next.next.priv = sent_front;
    }

    public void removeLast(){
        sent_back.priv = sent_back.priv.priv;
        sent_back.priv.priv.next = sent_back;
    }

    public Type get(int index){
        if (index >= size){
            return null;
        }
        ListNode rst = sent_front;
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
        return recurisive_helper(sent_front.next, index-1);
    }
    public static void main(String[] args){
        LinkedListDeque L = new LinkedListDeque();
        L.addFirst(12);
        L.addFirst(11);
        L.addLast(17);
        L.addLast(33);
        L.addFirst(521);
        System.out.println(L.isEmpty());
        L.printDeque();
        L.removeFirst();
        L.printDeque();
        System.out.println(L.size());
        L.removeLast();
        L.printDeque();
        System.out.println(L.get(2));
        System.out.println(L.getRecurisive(2));


    }
}














