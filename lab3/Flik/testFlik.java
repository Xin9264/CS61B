import static org.junit.Assert.*;
import org.junit.Test;

public class testFlik {
    @Test
    public void testflik(){
//        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
//        int[] b = {1, 2, 3, 4, 5, 6, 7, 8, 9};
//        for(int i = 0; i < 9; i++){
//            assertTrue(Flik.isSameNumber(a[i], b[i]));
//        }
        int a = 128;
        int b = 128;
        assertTrue(Flik.isSameNumber(a, b));
    }

}
