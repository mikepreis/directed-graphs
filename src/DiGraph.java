import java.util.LinkedList;

class DiGraph {

    //Part 1 of 4

    //a
    private LinkedList[] adjacencyList;

    //b
    public DiGraph(int numberOfVerticies) {

        adjacencyList = new LinkedList[numberOfVerticies];

        for (int i = 0; i < numberOfVerticies; i++) {

            adjacencyList[i] = new LinkedList();

        }
    }

    //c
    public void addEdge(int from, int to) {

        if (!adjacencyList[from - 1].contains(to) && isValid(to) && isValid(from))
            adjacencyList[from - 1].add(to);

    }

    //d
    public void deleteEdge(int from, int to) {

        if (adjacencyList[from-1].contains(to))
            adjacencyList[from-1].remove( (Object) (to));
    }

    //e
    public int edgeCount() {
        int numEdges = 0;
        for (LinkedList l : adjacencyList) {
            numEdges += l.size();
        }
        return numEdges;
    }

    //f
    public int vertexCount() {
        return adjacencyList.length;
    }

    //g
    public void print() {
        for (int i = 0; i < adjacencyList.length; i++) {
            System.out.print((i + 1) + " is connected to: ");
            for (int j = 0; j < adjacencyList[i].size(); j++) {
                if (!(j + 1 == adjacencyList[i].size())) {
                    System.out.print(adjacencyList[i].get(j) + ", ");
                } else {
                    System.out.print(adjacencyList[i].get(j) + " ");
                }
            }
            System.out.println();
        }
    }

//Part 2 of 4

    //a
    private int[] indegrees(LinkedList[] adj) {

        int[] result = new int[adj.length];

        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                result[(int) adj[i].get((j)) - 1] += 1;
            }
        }

        return result;
    }

    //b
    public int[] topSort() throws IllegalArgumentException {

        int[] result = new int [adjacencyList.length];
        int[] inDegrees = indegrees(adjacencyList);
        LinkedList<Integer> q = new LinkedList<Integer>();

        for( int i = 0; i < inDegrees.length; i++ ) {
            if( inDegrees[i] == 0 ) {
                q.addLast(i+1);
            }
        }

        int k = 0;

        while( !q.isEmpty() ) {

            int u = q.removeFirst();
            result[k] = u;
            k++;

            for (Object v : adjacencyList[u-1]) {

                Integer vertex = Integer.parseInt(v.toString());
                inDegrees[vertex-1] -= 1;

                if( inDegrees[vertex-1] == 0 ) {
                    q.addLast(vertex);
                }
            }
        }

        if( k != adjacencyList.length ) {
            throw new IllegalArgumentException();
        }

        return result;
    }

    //Part 3 of 4

    //a
    private class VertexInfo {

        int lengthOfPath;
        int predecessor;

        public VertexInfo(int l, int p) {
            lengthOfPath = l;
            predecessor = p;
        }
    }

    //b
    private VertexInfo[] BFS(int s) {

        int n = adjacencyList.length;

        VertexInfo[] vertexInfoObjects = new VertexInfo[n];

        for (int u = 0; u < n; u++) {

            vertexInfoObjects[u] = new VertexInfo(-1, -1);

        }

        vertexInfoObjects[s-1].lengthOfPath = 0;

        LinkedList<Integer> queue = new LinkedList<Integer>();

        queue.addLast(s);

        while (!queue.isEmpty()) {

            int k = queue.removeFirst();

            for (int v = 0; v < adjacencyList[k-1].size(); v++) {

                Integer value = Integer.parseInt(adjacencyList[k-1].get(v).toString());

                if (vertexInfoObjects[value-1].lengthOfPath == -1) {

                    vertexInfoObjects[value - 1].lengthOfPath = vertexInfoObjects[k -1].lengthOfPath + 1;

                    vertexInfoObjects[value-1].predecessor = k;

                    queue.addLast(value);
                }
            }
        }

        return vertexInfoObjects;

    }

    //c
    public boolean isTherePath(int from, int to) {

        VertexInfo [] resultFromBFS = BFS(from);

        return resultFromBFS[to-1].predecessor == -1 ? false : true;

    }

    //d
    public int lengthOfPath(int from, int to) {

        VertexInfo [] resultFromBFS = BFS(from);

        return resultFromBFS[to-1].lengthOfPath;

    }

    private boolean isValid(int vertex) {
        return (vertex >= 0 && vertex <= adjacencyList.length);
    }

    //e
    public void printPath(int from, int to){
        if(!(isTherePath(from, to))){
            System.out.print("There is no path");
        }
        else{
            String output = "";
            VertexInfo [] bfsRes = BFS(from);
            while(from != to){
                output = "->" +  to + output;
                to = bfsRes[to-1].predecessor;
            }
            output = from + output;
            System.out.println(output);
        }
    }

    public void printTopSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            if (!(i + 1 == arr.length)) {
                System.out.print(arr[i] + ", ");
            } else {
                System.out.print(arr[i] + " ");
            }
        }
    }

    private TreeNode buildTree(int s) {

        VertexInfo [] rbfs = BFS(s);

        TreeNode[] arrayTreeNodes = new TreeNode [rbfs.length];

        //create an array of tree nodes with the number of the vertex and empty list of children

        // tree nodes have attributes value and vertexChildren
        for(int i = 0; i < rbfs.length; i ++){

            LinkedList children = new LinkedList();

            arrayTreeNodes[i] = new TreeNode(i+1, children);
        }
        // for each element add the current element to its predecessors list of children.
        for(int i = 0; i < rbfs.length; i ++){
            if (rbfs[i].predecessor != -1){
                TreeNode pred = arrayTreeNodes[rbfs[i].predecessor - 1];pred.vertexChildren.add(arrayTreeNodes[i]);
            }
        }
        return arrayTreeNodes[s-1];

    }

    private static String getSpaces(int i) {
        String s = "";
        while(i-- > 0) {
            s += "   ";
        }

        return s;
    }


    private class TreeNode {

        int vertexNumber;
        LinkedList<TreeNode> vertexChildren = new LinkedList<TreeNode>();


        public TreeNode(int vNum, LinkedList<TreeNode> vChildren) {

            vertexNumber = vNum;
            vertexChildren = vChildren;

        }
    }


    public void printTree(int s) {
        TreeNode root = buildTree(s);
        int level = 0;
        System.out.println(getSpaces(level) + root.vertexNumber);
        for(TreeNode child : root.vertexChildren) {
            printTree2_worker(child, level);
        }
    }

    private void printTree2_worker(TreeNode child, int level) {
        level +=1;
        System.out.println(getSpaces(level) + child.vertexNumber);
        for(TreeNode grandkid: child.vertexChildren) {
            printTree2_worker(grandkid, level);
        }
    }

}