import edu.princeton.cs.introcs.StdRandom;

/**
 * Created by hug.
 */
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        if (N < 0){
            throw new IllegalArgumentException();
        }
        if (N == 0 || N == 1){
            return 0;
        }
        return optimalIPL(N - 1) + (int) (Math.log(N) / Math.log(2));
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
//        int node = N;
//        int layer = 0;
//        int depth = 0;
//        while (N > 0){
//            int layer_node = N > Math.pow(2, layer) ? (int) Math.pow(2, layer) : N;
//            depth += layer_node * layer;
//            layer++;
//            N = N - layer_node;
//        }
//        double average_depth = (double) depth / node;
//        return average_depth;
        return (double) optimalIPL(N) / N;
    }
    public static void randomDeleteInsert(BST<Integer> bst, boolean symmetricDeletion){
        if (symmetricDeletion){
            bst.deleteTakingRandom(bst.getRandomKey());
        }else {
            bst.deleteTakingSuccessor(bst.getRandomKey());
        }
        int toInsert = StdRandom.uniform(100000);
        while (bst.contains(toInsert)){
            toInsert = StdRandom.uniform(100000); // 判断BST是否已经有此节点
        }
        bst.add(toInsert);
    }

}
