import java.util.*;

class Node implements Comparable<Node> {
    String name;
    int g; // cost so far
    int f; // estimated total cost (g + h)
    Node parent;

    Node(String name, int g, int f, Node parent) {
        this.name = name;
        this.g = g;
        this.f = f;
        this.parent = parent;
    } 

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.f, other.f);
    }
}

public class AStarSearch {

    // Graph definition (adjacency list with costs)
    static Map<String, List<AbstractMap.SimpleEntry<String, Integer>>> graph = new HashMap<>();
    static Map<String, Integer> heuristic = new HashMap<>();

    static {
        // Define graph
        graph.put("Pune", Arrays.asList(
                new AbstractMap.SimpleEntry<>("Expressway", 50),
                new AbstractMap.SimpleEntry<>("OldHighway", 30),
                new AbstractMap.SimpleEntry<>("CoastalRoad", 20)
        ));
        graph.put("Expressway", Arrays.asList(new AbstractMap.SimpleEntry<>("Mumbai", 100)));
        graph.put("OldHighway", Arrays.asList(new AbstractMap.SimpleEntry<>("Mumbai", 120)));
        graph.put("CoastalRoad", Arrays.asList(new AbstractMap.SimpleEntry<>("Mumbai", 180)));
        graph.put("Mumbai", new ArrayList<>());

        // Heuristic values
        heuristic.put("Pune", 150);
        heuristic.put("Expressway", 100);
        heuristic.put("OldHighway", 120);
        heuristic.put("CoastalRoad", 180);
        heuristic.put("Mumbai", 0);
    }

    public static void aStar(String start, String goal) {
        PriorityQueue<Node> openList = new PriorityQueue<>();
        Map<String, Integer> gScore = new HashMap<>();

        Node startNode = new Node(start, 0, heuristic.get(start), null);
        openList.add(startNode);
        gScore.put(start, 0);

        while (!openList.isEmpty()) {
            Node current = openList.poll();

            // Goal check
            if (current.name.equals(goal)) {
                printPath(current);
                return;
            }

            // Explore neighbors
            for (AbstractMap.SimpleEntry<String, Integer> edge : graph.get(current.name)) {
                String neighbor = edge.getKey();
                int cost = edge.getValue();
                int tentativeG = current.g + cost;

                if (!gScore.containsKey(neighbor) || tentativeG < gScore.get(neighbor)) {
                    gScore.put(neighbor, tentativeG);
                    int fNew = tentativeG + heuristic.get(neighbor);
                    openList.add(new Node(neighbor, tentativeG, fNew, current));
                }
            }
        }

        System.out.println("No path found!");
    }

    private static void printPath(Node goalNode) {
        List<String> path = new ArrayList<>();
        Node current = goalNode;
        while (current != null) {
            path.add(current.name);
            current = current.parent;
        }
        Collections.reverse(path);
        System.out.println("Path found: " + String.join(" -> ", path));
        System.out.println("Total cost: " + goalNode.g);
    }

    public static void main(String[] args) {
        System.out.println("Running A* Search from Pune to Mumbai...\n");
        aStar("Pune", "Mumbai");
    }
}
