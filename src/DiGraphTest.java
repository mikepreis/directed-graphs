import java.util.Scanner;

class Main {

    public static void main(String [] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int n = in.nextInt();
        in.nextLine();
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
            String choice = in.nextLine();
            int from; int to;

            switch (choice) {
                case "a":
                    System.out.print("\nEnter (from) vertex and (to) vertex: ");
                    from = in.nextInt();
                    to = in.nextInt();
                    in.nextLine();
                    g.addEdge(from, to);
                    System.out.println("Edge (" + from + ", " + to + ") was added");
                    break;
                case "d":
                    System.out.println("Enter (from) vertex and (to) vertex");
                    from = in.nextInt();
                    to = in.nextInt();
                    in.nextLine();
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
                    try {
                        int [] arr = g.topSort();
                        g.printTopSort(arr);
                    }catch(IllegalArgumentException e){
                        System.out.print("cyclic graph");
                    }
                    break;
                case "i":
                    System.out.println("Is there a path?");
                    System.out.print("\nEnter (from) vertex and (to) vertex: ");
                    from = in.nextInt();
                    to = in.nextInt();
                    in.nextLine();
                    if (g.isTherePath(from, to)) {
                        System.out.println("There is a path from " + from + " to " + to);
                    } else {
                        System.out.println("There is NOT a path from " + from + " to " + to);
                    }
                    break;
                case "l":
                    System.out.println("What is the length of the path?");
                    System.out.print("\nEnter (from) vertex and (to) vertex: ");
                    from = in.nextInt();
                    to = in.nextInt();
                    in.nextLine();
                    System.out.println("The length from " + from + " to " + to + " is " + g.lengthOfPath(from, to) );
                    break;
                case "s":
                    System.out.println("Print Path");
                    System.out.print("\nEnter (from) vertex and (to) vertex: ");
                    from = in.nextInt();
                    to = in.nextInt();
                    in.nextLine();
                    g.printPath(from, to);
                    break;
                case "b":
                    System.out.println("Build Tree");
                    System.out.print("\nEnter a source vertex number ");
                    int source = in.nextInt();
                    in.nextLine();
                    g.printTree(source);
                default:
                    System.out.println("Not a valid entry");
                    break;
            }
        }
    }
}