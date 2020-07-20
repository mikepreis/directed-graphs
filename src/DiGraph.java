import java.util.LinkedList;

class DiGraph {

    private LinkedList [] adjacencyList;

    public DiGraph(int numberOfVerticies) {
        adjacencyList = new LinkedList[numberOfVerticies];
        for( int i = 0; i < numberOfVerticies; i++) {
            adjacencyList[i] = new LinkedList();
        }
    }

    private boolean isValid(int vertex) {
        return (vertex >= 0 && vertex <= adjacencyList.length);
    }

    public void addEdge(int from, int to) {
        if (!adjacencyList[from-1].contains(to) && isValid(to) && isValid(from))
            adjacencyList[from-1].add(to);

    }

    public void deleteEdge(int from, int to) {
        if( adjacencyList[from].contains(to) )
            adjacencyList[from].remove(to);
    }

    public int edgeCount() {
        int numEdges = 0;
        for ( LinkedList l : adjacencyList ) {
            numEdges += l.size();
        }
        return numEdges;
    }

    public int vertexCount() {
        return adjacencyList.length;
    }

    public void print() {
        for( int i = 0; i < adjacencyList.length; i++ ) {
            System.out.print((i+1) + " is connected to: ");
            for( int j = 0; j < adjacencyList[i].size(); j++) {
                if( !(j+1 == adjacencyList[i].size() ) ) {
                    System.out.print(adjacencyList[i].get(j) + ", ");
                } else {
                    System.out.print(adjacencyList[i].get(j) + " ");
                }
            }
            System.out.println();
        }
    }

    private int[] indegrees(LinkedList [] adj ) {

        int [] result = new int[adj.length];



        for(int i = 0; i < adj.length; i++) {
            for(int j = 0; j < adj[i].size(); j++) {
                result[(int) adj[i].get((j))-1] += 1;
            }
        }

        return result;
    }

    public void printTopSort(int[] arr) {

        for ( int i = 0; i < arr.length; i++ ) {
            if( !(i+1 == arr.length ) ) {
                System.out.print(arr[i] + ", ");
            } else {
                System.out.print(arr[i] + " ");
            }
        }
    }

    public int[] topSort() {

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

        for( int i = 0; i < result.length; i++ ) {
            System.out.println(result[i]);
        }

        return result;
    }
}