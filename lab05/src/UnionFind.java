public class UnionFind {

    private int[] parents;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be positive");
        }

        parents = new int[N];

        for (int i = 0; i < N; i++) {
            parents[i] = -1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        validate(v);

        return -parents[find(v)];
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        validate(v);

        return parents[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);

        return find(v1) == find(v2);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        validate(v);

        if (parents[v] < 0) {
            return v;
        }
        // Path-compression
        parents[v] = find(parents[v]);
        return parents[v];
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);

        int root1 = find(v1);
        int root2 = find(v2);

        if (root1 == root2) {
            return;
        }

        if (sizeOf(v1) <= sizeOf(v2)) {
            parents[root2] += parents[root1];
            parents[root1] = root2;
        } else {
            parents[root1] += parents[root2];
            parents[root2] = root1;
        }
    }

    private void validate(int v) {
        if (v < 0 || v >= parents.length) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " +
                    (parents.length - 1));
        }
    }
}
