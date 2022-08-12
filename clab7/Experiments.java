import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by hug.
 */
public class Experiments extends ExperimentHelper{
    public static void experiment1() {
        BST tree = new BST<Integer>();
        Random r = new Random();
        List<Double> yValues = new ArrayList<>();  // optimal average depth
        List<Double> y2Values = new ArrayList<>();  // Your BST
        List<Integer> xValues = new ArrayList<>();  // number of items
        for (int x = 1; x < 5000; x += 1) {
            double thisY =  optimalAverageDepth(x);
            xValues.add(x);
            yValues.add(thisY);
            tree.add(r.nextInt(10000));
            double TreeDepth = tree.Average_Depth();
            y2Values.add(TreeDepth);
        }
        XYChart chart = new XYChartBuilder().width(1600).height(600).xAxisTitle("number of items").yAxisTitle("Average depth").build();
        chart.addSeries("optimal average depth vs the number of items", xValues, yValues);
        chart.addSeries("Your average depth", xValues, y2Values);
        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
        BST tree1 = new BST<Integer>();
        Random r = new Random();
        List<Integer> x = new ArrayList<>();
        List<Double> y1 = new ArrayList<>();
        for (int i = 0; i < 5000; i++){
            tree1.add(r.nextInt(100000));
        }
        for (int i = 0; i < 10000; i++){
            double treeDepth = tree1.Average_Depth();
            x.add(i);
            y1.add(treeDepth);
            randomDeleteInsert(tree1, false);
        }
        XYChart chart = new XYChartBuilder().width(1600).height(600).xAxisTitle("number of experiments").yAxisTitle("Average depth").build();
        chart.addSeries("deleteTakingRandom", x, y1);
        new SwingWrapper(chart).displayChart();

    }

    public static void experiment3() {
        BST tree1 = new BST<Integer>();
        Random r = new Random();
        List<Integer> x = new ArrayList<>();
        List<Double> y1 = new ArrayList<>();
        for (int i = 0; i < 5000; i++){
            tree1.add(r.nextInt(100000));
        }
        for (int i = 0; i < 10000; i++){
            double treeDepth = tree1.Average_Depth();
            x.add(i);
            y1.add(treeDepth);
            randomDeleteInsert(tree1, true);
        }
        XYChart chart = new XYChartBuilder().width(1600).height(600).xAxisTitle("number of experiments").yAxisTitle("Average depth").build();
        chart.addSeries("deleteTakingRandom", x, y1);
        new SwingWrapper(chart).displayChart();

    }


    public static void main(String[] args) {
        experiment1();
//        experiment2();
//        experiment3();
    }
}
