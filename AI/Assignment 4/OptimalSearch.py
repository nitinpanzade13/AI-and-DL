import heapq

# Graph definition (adjacency list with costs)
graph = {
    "Pune": [("Expressway", 50), ("OldHighway", 30), ("CoastalRoad", 20)],
    "Expressway": [("Mumbai", 100)],
    "OldHighway": [("Mumbai", 120)],
    "CoastalRoad": [("Mumbai", 180)],
    "Mumbai": []
}

# Heuristic values (estimated cost to reach goal)
heuristic = {
    "Pune": 150,
    "Expressway": 100,
    "OldHighway": 120,
    "CoastalRoad": 180,
    "Mumbai": 0
}

# -------------------- A* Implementation --------------------
def a_star(start, goal):
    # Priority queue (min-heap)
    open_list = []
    heapq.heappush(open_list, (heuristic[start], 0, start))  # (f, g, node)

    g_score = {start: 0}
    parent = {start: None}

    while open_list:
        f, g, current = heapq.heappop(open_list)

        # Goal check
        if current == goal:
            path = []
            while current:
                path.append(current)
                current = parent[current]
            path.reverse()
            print("Path found:", " -> ".join(path))
            print("Total cost:", g)
            return

        # Explore neighbours
        for neighbor, cost in graph[current]:
            tentative_g = g + cost
            if neighbor not in g_score or tentative_g < g_score[neighbor]:
                g_score[neighbor] = tentative_g
                parent[neighbor] = current
                f_new = tentative_g + heuristic[neighbor]
                heapq.heappush(open_list, (f_new, tentative_g, neighbor))

    print("No path found!")


# -------------------- MAIN --------------------
if __name__ == "__main__":
    print("Running A* Search from Pune to Mumbai...\n")
    a_star("Pune", "Mumbai")
