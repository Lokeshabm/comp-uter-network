import java.util.Scanner;
import java.util.Arrays;

public class BellmanFord {

    private int[] dist; 
    private int numVertices;
    public static final int INF = Integer.MAX_VALUE / 2; // Safe infinity

    public BellmanFord(int numVertices) {
        this.numVertices = numVertices;
        dist = new int[numVertices + 1]; // 1-based indexing
    }

    public void runBellmanFord(int source, int[][] graph) {

        // Step 1: Initialize distances
        Arrays.fill(dist, INF);
        dist[source] = 0;

        // Step 2: Relax edges (V - 1) times
        for (int i = 1; i <= numVertices - 1; i++) {
            for (int u = 1; u <= numVertices; u++) {
                for (int v = 1; v <= numVertices; v++) {
                    if (graph[u][v] < INF) { // There is an edge
                        if (dist[u] + graph[u][v] < dist[v]) {
                            dist[v] = dist[u] + graph[u][v];
                        }
                    }
                }
            }
        }

        // Step 3: Check for negative weight cycles
        for (int u = 1; u <= numVertices; u++) {
            for (int v = 1; v <= numVertices; v++) {
                if (graph[u][v] < INF) {
                    if (dist[u] + graph[u][v] < dist[v]) {
                        System.out.println("Graph contains a negative weight cycle!");
                        return;
                    }
                }
            }
        }

        // Step 4: Print results
        System.out.println("\nShortest distances from source " + source + ":");
        for (int i = 1; i <= numVertices; i++) {
            if (dist[i] >= INF)
                System.out.println("To " + i + " = INF");
            else
                System.out.println("To " + i + " = " + dist[i]);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of vertices:");
        int n = sc.nextInt();

        int[][] graph = new int[n + 1][n + 1];

        System.out.println("Enter adjacency matrix:");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                graph[i][j] = sc.nextInt();

                if (i == j) {
                    graph[i][j] = 0; // distance to itself
                } else if (graph[i][j] == 0) {
                    graph[i][j] = INF; // no edge
                }
            }
        }

        System.out.println("Enter source vertex:");
        int source = sc.nextInt();

        BellmanFord bf = new BellmanFord(n);
        bf.runBellmanFord(source, graph);

        sc.close();
    }
}
