import org.junit.Test;
import static org.junit.Assert.*;
public class TestUnionFind {
    UnionFind uf = new UnionFind(10);

    @Test
    public void testBasic(){
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(1, 2);
        assertEquals(0, uf.parent(1));
        assertTrue(uf.connected(0, 2));
        uf.union(0, 2);
        assertEquals(2, uf.parent(3));
        assertEquals(4, uf.sizeOf(2));
        assertEquals(4, uf.sizeOf(1));
        assertEquals(4, uf.sizeOf(0));
        assertTrue(uf.connected(1, 3));
        assertEquals(0, uf.find(3));
    }


}

