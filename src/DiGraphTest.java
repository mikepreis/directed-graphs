import java.util.Scanner;

class DiGraphTest {

    public static void main(String [] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int n = in.nextInt();
        DiGraph g = new DiGraph(n);

        System.out.print("\nChoose one of the following operations\n" +
                "- add edge (enter a)\n" +
                "- delete edge (enter d)\n" +
                "- edge count (enter e)\n" +
                "- vertex count (enter v)\n" +
                "- print graph (enter p)\n" +
                "- Quit (enter q)\n");

        boolean quit = false;

        while( !quit ) {

            System.out.print("\nEnter menu choice: ");
            String choice = in.next();
            int from; int to;

            switch (choice) {
                case "a":
                    System.out.print("\nEnter (from) vertex and (to) vertex: ");
                    from = in.nextInt();
                    to = in.nextInt();
                    g.addEdge(from, to);
                    System.out.println("Edge (" + from + ", " + to + ") was added");
                    break;
                case "d":
                    System.out.println("Enter (from) vertex and (to) vertex");
                    from = in.nextInt();
                    to = in.nextInt();
                    g.deleteEdge(from, to);
                    System.out.println("Edge (" + from + ", " + to + ") was deleted");
                    break;
                case "e":
                    System.out.println("Edge count is " + g.edgeCount());
                    break;
                case "v":
                    System.out.println("Vertex count is " + g.vertexCount());
                    break;
                case "p":
                    System.out.println("Graph is the following");
                    g.print();
                    break;
                case "q":
                    System.out.println("Goodbye");
                    quit = true;
                    break;
                case "t":
                    System.out.println("Topological Sort");
                    int [] arr = g.topSort();
                    g.printTopSort(arr);
                    break;
                default:
                    System.out.println("Not a valid entry");
                    break;
            }
        }
    }
}