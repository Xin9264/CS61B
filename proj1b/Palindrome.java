public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> wTd = new LinkedListDeque<>();
        int len = word.length();
        for(int i = 0; i < len; i++){
            wTd.addLast(word.charAt(i));
        }
        return wTd;
    }
}
