# Variables and Domains
variables = ['A', 'B', 'C', 'D']
domains = {
    'A': ['Red', 'Green', 'Blue'],
    'B': ['Red', 'Green', 'Blue'],
    'C': ['Red', 'Green', 'Blue'],
    'D': ['Red', 'Green', 'Blue']
}

# Neighbors (Constraints)
neighbors = {
    'A': ['B', 'C'],
    'B': ['A', 'C', 'D'],
    'C': ['A', 'B', 'D'],
    'D': ['B', 'C']
}

# Check if assignment is valid
def is_valid(assignment, var, value):
    for neighbor in neighbors[var]:
        if neighbor in assignment and assignment[neighbor] == value:
            return False
    return True

# Backtracking CSP solver
def backtrack(assignment):
    if len(assignment) == len(variables):
        return assignment
    
    # Choose an unassigned variable
    unassigned = [v for v in variables if v not in assignment]
    var = unassigned[0]

    for value in domains[var]:
        if is_valid(assignment, var, value):
            assignment[var] = value
            result = backtrack(assignment)
            if result:
                return result
            del assignment[var]
    
    return None

# Run the solver
solution = backtrack({})
print("CSP Solution:")
print(solution)
