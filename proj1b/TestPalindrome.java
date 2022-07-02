import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome(){
//        boolean actual = true;
//        boolean fact = palindrome.isPalindrome("noon");
        assertTrue(palindrome.isPalindrome("ava"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertFalse(palindrome.isPalindrome("Diana"));
        assertTrue(palindrome.isPalindrome("aaaaa"));
        assertFalse(palindrome.isPalindrome("wangxin"));
        OffByOne cc = new OffByOne();
        assertFalse(palindrome.isPalindrome("minami", cc));
        assertTrue(palindrome.isPalindrome("ava", cc));
    }
}
