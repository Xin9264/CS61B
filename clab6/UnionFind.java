public class UnionFind {

    // TODO - Add instance variables?
    private int[] instance;
    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        instance = new int[n];
        for (int i = 0; i < n; i++){
            instance[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        // TODO
        if (vertex > instance.length || vertex < 0){
            throw new IllegalArgumentException(vertex + " is not a valid index");
        }
//        if (!(vertex >= 0 && vertex < instance.length)) {
//            throw new RuntimeException(vertex + " is not a valid index.");
//        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        validate(v1);
        while (instance[v1] > -1){
            v1 = instance[v1];
        }
        return -instance[v1];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        validate(v1);
        return instance[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        if (instance[find(v1)] == instance[find(v2)]){
            return true;
        }
        return false;
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a
       vertex with itself or vertices that are already connected should not
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        int rootv1 = find(v1);
        int rootv2 = find(v2);
        if (rootv1 != rootv2){
            if (instance[rootv1] <= instance[rootv2]) {
                instance[rootv1] += instance[rootv2];
                instance[rootv2] = rootv1;
            }else {
                instance[rootv2] += instance[rootv1];
                instance[rootv1] = rootv2;
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        validate(vertex);
        int root = vertex;
        while (instance[root] > -1){
            root = instance[root];
        }
        while (instance[vertex] > -1){
            int temp = instance[vertex];
            instance[vertex] = root;
            vertex = temp;
        }

        return root;
    }

}
