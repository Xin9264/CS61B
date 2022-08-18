package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet{
    public List<Point> p;
    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        List<Point> list = p;
        Point best = list.get(0);
        double best_dis = Double.MAX_VALUE;
        for (int i = 0; i < list.size(); i++){
            double distance = Point.distance(list.get(i), goal);
            if (distance < best_dis){
                best_dis = distance;
                best = list.get(i);
            }
        }
        return best;
    }
    public NaivePointSet(List<Point> points){
        this.p = points;
    }

    public static void main(String[] args){
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        ret.getX(); // evaluates to 3.3
        ret.getY(); // evaluates to 4.4
        System.out.println(ret.getX());
        System.out.println(ret.getY());
    }
}
