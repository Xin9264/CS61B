public class OffByN implements CharacterComparator{
    public int z;
    public OffByN(int x){
        this.z = x;
    }
    @Override
    public boolean equalChars(char x, char y) {
        int number = x - y;
        number = Math.abs(number);
        return number == this.z;
    }

//    public static void main(String args[]){
//        OffByN offBy5 = new OffByN(5);
//        offBy5.equalChars('a', 'f');  // true
//        offBy5.equalChars('f', 'a');  // true
//        offBy5.equalChars('f', 'h');  // false
//    }
}
