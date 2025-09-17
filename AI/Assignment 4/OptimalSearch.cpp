// A* Search Algorithm Example
#include <bits/stdc++.h>
using namespace std;

// Structure for edges (directed, with weight)
struct Edge {
    string to;
    int cost;
};

// Heuristic values (h(n)) – estimated cost to reach goal
unordered_map<string, int> heuristic = {
    {"Pune", 150},
    {"Expressway", 100},
    {"OldHighway", 120},
    {"CoastalRoad", 180},
    {"Mumbai", 0}
};

// Graph definition (Adjacency list)
unordered_map<string, vector<Edge>> graph = {
    {"Pune", {{"Expressway", 50}, {"OldHighway", 30}, {"CoastalRoad", 20}}},
    {"Expressway", {{"Mumbai", 100}}},
    {"OldHighway", {{"Mumbai", 120}}},
    {"CoastalRoad", {{"Mumbai", 180}}},
    {"Mumbai", {}}
};

// Node structure for priority queue
struct Node {
    string name;
    int g; // cost so far
    int f; // g + h
    bool operator>(const Node& other) const {
        return f > other.f;
    }
};

// -------------------- A* Implementation --------------------
void aStarSearch(string start, string goal) {
    priority_queue<Node, vector<Node>, greater<Node>> open;
    unordered_map<string, int> gScore;
    unordered_map<string, string> parent;

    gScore[start] = 0;
    open.push({start, 0, heuristic[start]});

    while (!open.empty()) {
        Node current = open.top();
        open.pop();

        // Goal reached
        if (current.name == goal) {
            cout << "Path found: ";
            vector<string> path;
            for (string at = goal; !at.empty(); at = parent[at]) {
                path.push_back(at);
            }
            reverse(path.begin(), path.end());
            for (auto& city : path) cout << city << " ";
            cout << "\nTotal cost: " << current.g << endl;
            return;
        }

        // Explore neighbours
        for (auto& edge : graph[current.name]) {
            int tentative_g = current.g + edge.cost;
            if (!gScore.count(edge.to) || tentative_g < gScore[edge.to]) {
                gScore[edge.to] = tentative_g;
                parent[edge.to] = current.name;
                int f = tentative_g + heuristic[edge.to];
                open.push({edge.to, tentative_g, f});
            }
        }
    }

    cout << "No path found!" << endl;
}

// -------------------- MAIN --------------------
int main() { 
    cout << "Running A* Search from Pune to Mumbai...\n";
    aStarSearch("Pune", "Mumbai");
    return 0;
}
