from collections import deque
import copy

# Directions: up, down, left, right
directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]
goal_state = [[1, 2, 3], [4, 5, 6], [7, 8, 0]]

def is_goal(state):
    return state == goal_state

def find_zero(state):
    for i in range(3):
        for j in range(3):
            if state[i][j] == 0:
                return i, j

def generate_successors(state):
    x, y = find_zero(state)
    successors = []
    for dx, dy in directions:
        nx, ny = x + dx, y + dy
        if 0 <= nx < 3 and 0 <= ny < 3:
            new_state = copy.deepcopy(state)
            new_state[x][y], new_state[nx][ny] = new_state[nx][ny], new_state[x][y]
            successors.append(new_state)
    return successors

def state_to_tuple(state):
    return tuple(tuple(row) for row in state)

# BFS implementation
def bfs(start_state):
    visited = set()
    queue = deque([(start_state, [])])
    while queue:
        current, path = queue.popleft()
        if is_goal(current):
            return path + [current]
        visited.add(state_to_tuple(current))
        for successor in generate_successors(current):
            if state_to_tuple(successor) not in visited:
                queue.append((successor, path + [current]))
    return None

# DFS implementation
def dfs(start_state, depth_limit=20):
    visited = set()
    stack = [(start_state, [])]
    while stack:
        current, path = stack.pop()
        if is_goal(current):
            return path + [current]
        if len(path) >= depth_limit:
            continue
        visited.add(state_to_tuple(current))
        for successor in generate_successors(current):
            if state_to_tuple(successor) not in visited:
                stack.append((successor, path + [current]))
    return None

# Helper to print the path
def print_solution(path):
    if path is None:
        print("No solution found.")
    else:
        for step, state in enumerate(path):
            print(f"\nStep {step}:")
            for row in state:
                print(row)

# Example Usage:
start_state = [[1, 2, 3], [4, 0, 6], [7, 5, 8]]
print("BFS Solution:")
print_solution(bfs(start_state))

print("\nDFS Solution:")
print_solution(dfs(start_state))
