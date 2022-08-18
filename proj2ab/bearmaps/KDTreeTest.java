package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.Random;

import static org.junit.Assert.*;
public class KDTreeTest {
    private static Random r = new Random(10000);
    @Test
    public void test_kd_basic(){
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 5);
        Point p4 = new Point(3, 3);
        Point p5 = new Point(4, 4);
        Point p6 = new Point(1, 5);
        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3, p4, p5, p6));
        Point near1 = nn.nearest(0, 7);
        Point near2 = nn.nearest(3, 3);
        assertEquals(near2, p4);
        assertEquals(near1, p6);

    }
    @Test
    public void testWith1000Points(){
        List<Point> points1000 = randomPoints(1000);
        NaivePointSet nps = new NaivePointSet(points1000);
        KDTree kd = new KDTree(points1000);

        List<Point> query200 = randomPoints(200);
        for (Point p : query200){
            Point expected = nps.nearest(p.getX(), p.getY());
            Point actual = kd.nearest(p.getX(), p.getY());
            assertEquals(expected, actual);
        }
    }
    private List<Point> randomPoints(int N){
        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < N; i++){
            points.add(OnePoint());
        }
        return points;
    }
    private Point OnePoint(){
        double x = r.nextDouble();
        double y = r.nextDouble();
        return new Point(x, y);
    }
    @Test
    public void test_time(){
        Stopwatch sw = new Stopwatch();
        List<Point> points1000 = randomPoints(100000);
        NaivePointSet nps = new NaivePointSet(points1000);
        KDTree kd = new KDTree(points1000);

        List<Point> query200 = randomPoints(20000);
        for (Point p : query200){
            Point expected = nps.nearest(p.getX(), p.getY());
        }
        double naive_time = sw.elapsedTime();
        Stopwatch w = new Stopwatch();
        for (Point p : query200){
            Point ac = kd.nearest(p.getX(), p.getY());
        }
        double kd_time = w.elapsedTime();
//        System.out.println(naive_time);
//        System.out.println(kd_time);
        assertTrue(kd_time / naive_time < 0.1);
    }
}
