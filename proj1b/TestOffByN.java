import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static OffByN n = new OffByN(5);

    @Test
    public void testOffByN(){
        assertTrue(n.equalChars('a', 'f'));
        assertTrue(n.equalChars('f', 'a'));
        assertFalse(n.equalChars('g', 'f'));

    }
}
