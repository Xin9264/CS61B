import sun.awt.image.ImageWatched;

import javax.sound.midi.Track;

public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> wTd = new LinkedListDeque<>();
        int len = word.length();
        for(int i = 0; i < len; i++){
            wTd.addLast(word.charAt(i));
        }
        return wTd;
    }

//    public LinkedListDeque recursion_helper(LinkedListDeque wordDeque){
//        return null;
//    }
    public boolean isPalindrome(String word){
        if(word.length() <= 1){
            return true;
        }
        Deque<Character> wordDeque = new LinkedListDeque<>();
        wordDeque = wordToDeque(word);
        while(wordDeque.size() > 0){
            Character priv = wordDeque.removeFirst();
            Character last = wordDeque.removeLast();
            if(last == null){
                return true;
            }
            if(priv != last){
                return false;
            }
        }
        return true;

    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        if(word.length() <= 1 || word == null){
            return true;
        }
        Deque<Character> wordDeque = new LinkedListDeque<>();
        wordDeque = wordToDeque(word);
        while(wordDeque.size() > 0){
            Character priv = wordDeque.removeFirst();
            Character last = wordDeque.removeLast();
            if(last == null){
                return true;
            }
            if(cc.equalChars(priv, last) == false){
                return false;
            }
        }
        return true;

    }


}
