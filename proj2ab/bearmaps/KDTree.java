package bearmaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KDTree implements PointSet{
    private static final boolean HORIZONTAL = true;
    Node root;
    private class Node{
        private Point p;
        private boolean orientation;
        Node left;
        Node right;
        Node(Point p, boolean orient){
            this.p = p;
            this.orientation = orient;
        }
    }


    private Node add(Point p, Node n, boolean splitDim){
        if (n == null){
            return new Node(p, splitDim);
        }
        if (p.equals(n.p)){
            return n;
        }
        int cmp = compare(p, n.p, n.orientation);
        if (cmp < 0){
            n.left = add(p, n.left, !n.orientation);
        } else {
            n.right = add(p, n.right, !n.orientation);
        }

        return n;
    }
    public KDTree(List<Point> points){
        points = new ArrayList<>(points);
        Collections.shuffle(points); // mess up the order
        for (Point p : points){
            root = add(p, root, HORIZONTAL);
        }
    }
    private int compare(Point p1, Point p2, boolean splitDim){
        // if true compare x, else compare y
        if (splitDim == HORIZONTAL){
            return Double.compare(p1.getX(), p2.getX());
        }else {
            return Double.compare(p1.getY(), p2.getY());
        }
    }


    @Override
    public Point nearest(double x, double y) {
        return nearest(root, new Point(x, y), root.p);
    }
    private Point nearest(Node curr, Point goal, Point best){
        if (curr == null){
            return best;
        }

        if (Point.distance(curr.p, goal) < Point.distance(goal, best)){
            best = curr.p;
        }
        Node goodside, badside;
        if (compare(goal, curr.p, curr.orientation) < 0){
            goodside = curr.left;
            badside = curr.right;
        } else {
            goodside = curr.right;
            badside = curr.left;
        }
        best = nearest(goodside, goal, best);
        if (isWorthLooking(curr, goal, best)){
            best = nearest(badside, goal, best);
        }
        return best;
    }

    private boolean isWorthLooking(Node n, Point goal, Point best){
        if (n.orientation == HORIZONTAL){
            return Math.pow(n.p.getX() - goal.getX(), 2) < Point.distance(best, goal);
        } else {
            return Math.pow(n.p.getY() - goal.getY(), 2) < Point.distance(best, goal);
        }
    }


}
